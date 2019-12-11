import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Selenium4Test {

    @Test
    public void test_Saxsys() {

        /**
         * What is GeckoDriver?
         GeckoDriver is a connecting link to the Firefox browser for your scripts in Selenium.
         GeckoDriver is a proxy which helps to communicate with the Gecko-based browsers (e.g. Firefox), for which it provides HTTP API.

         Firefox's geckodriver *requires* you to specify its location.
         */
        System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");

        /**
         * Here comes the WebDriver, driver ...
         * The WebDriver is a tool for writing automated tests of websites.
         * It aims to mimic the behaviour of a real user, and as such interacts with the HTML of the application.
         * The existing drivers are the ChromeDriver, InternetExplorerDriver, EdgeDriver, FirefoxDriver, OperaDriver and HtmlUnitDriver.
         *
         * Firefox driver is included in the selenium-server-stanalone.jar.
         */
        FirefoxDriver driver=new FirefoxDriver();

        // Aufruf der Seite durch den Webdriver
        driver.get("https://www.sogehtsoftware.de");



    }

}
