import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        Path path = Paths.get("apks", "org.wikipedia.apk");
        System.out.println(path.toAbsolutePath());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", path.toAbsolutePath().toString());

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCancelSearchEx3Ex4() {
        String searchText = "Java";
        // Ex3,Ex4 task
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                searchText,
                "Cannot find 'Search Wikipedia' input"
        );

        List<WebElement> articles = waitForElementsPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot see results on the page",
                15
        );

        Assert.assertTrue(
                "Cannot find results on the page",
                articles.size() > 5 );

        checkResultContains(articles, searchText);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X close button to cancel the search"
        );

        Assert.assertTrue(
                "Still see results on the page",
                waitForElementNotPresent(
                        By.id("org.wikipedia:id/page_list_item_title"),
                        "Still see results on the page",
                        5
                )
        );
    }


    @Test
    public void testSearchEx2() {
        // Ex2 task
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input"
        );
        checkSearchField();
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private List<WebElement> waitForElementsPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        long timeoutInSeconds = 5;
        return waitForElementPresent(by, error_message, timeoutInSeconds);
    }

    private List<WebElement> waitForElementsPresent(By by, String error_message) {
        long timeoutInSeconds = 5;
        return waitForElementsPresent(by, error_message, timeoutInSeconds);
    }

    private Boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private Boolean waitForElementNotPresent(By by, String error_message) {
        long timeoutInSeconds = 5;
        return waitForElementNotPresent(by, error_message, timeoutInSeconds);
    }

    private Boolean waitForElementsNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        List<WebElement> elements = waitForElementsPresent(by, error_message, timeoutInSeconds);
        return wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    private WebElement waitForElementAndClick(By by, String error_message) {
        WebElement element =  waitForElementPresent(by, error_message);
        element.click();
        return element;
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element =  waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String text, String error_message) {
        WebElement element =  waitForElementPresent(by, error_message);
        element.sendKeys(text);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message) {
        long timeoutInSeconds = 5;
        return waitForElementAndClear(by, error_message, timeoutInSeconds);
    }

    private WebElement checkSearchField() {
        return waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find 'Search…' text in input"
        );
    }

    private void checkResultContains(List<WebElement> articles, String searchText) {
        List<String> results = new ArrayList<>();
        for (WebElement el : articles) {
            if (el.getAttribute("text").contains(searchText)) {
                results.add(el.getAttribute("text"));
            }
        }

        System.out.println(results);
        Assert.assertEquals(
                "Some article(s) without the search word",
                articles.size(),
                results.size()
        );
    }
}