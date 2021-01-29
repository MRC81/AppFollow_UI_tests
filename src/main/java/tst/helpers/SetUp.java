package tst.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import tst.logging.CustomReport;

import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static java.lang.System.setProperty;


@Listeners(CustomReport.class)
public class SetUp {

    // Get 'browser' value from the POM file
    static String browser = System.getProperty("browser");


    @BeforeClass
    public void launchBrowserAndOpenSite() {
        //DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("enableVNC", true);

        switch (browser) {
            case "Firefox":
                /* --------------------Defined local Web Driver-----------------------*/
                //setProperty("webdriver.firefox.driver", "./drivers/geckodriver.exe");
                //WebDriver firefoxDriver  = new FirefoxDriver();
                //WebDriverRunner.setWebDriver(firefoxDriver);
                /* --------------------Defined local Web Driver-----------------------*/
                Configuration.browser = "firefox";
                //Configuration.remote = "http://192.168.3.146:4444/wd/hub";
                //capabilities.setBrowserName("firefox");
                //capabilities.setVersion("78.0");
                //Configuration.browserCapabilities = capabilities;
                break;

            case "Chrome":

            case "Edge":
                /* --------------------Defined local Web Driver-----------------------*/
                //setProperty("webdriver.edge.driver", "./drivers/MicrosoftWebDriver.exe");
                //WebDriver edgeDriver  = new EdgeDriver();
                //WebDriverRunner.setWebDriver(edgeDriver);
                /* --------------------Defined local Web Driver-----------------------*/
                Configuration.browser = "edge";
                /*Currently, there's no Selenoid container with Edge browser*/

                break;
            default:
                /* --------------------Defined local Web Driver-----------------------*/
                //setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
                //WebDriver chromeDriver  = new ChromeDriver();
                //WebDriverRunner.setWebDriver(chromeDriver);
                /* --------------------Defined local Web Driver-----------------------*/
                Configuration.browser = "chrome";
                //Configuration.remote = "http://192.168.3.146:4444/wd/hub";
                //capabilities.setBrowserName("chrome");
                //capabilities.setVersion("83.0");
                //Configuration.browserCapabilities = capabilities;
        }

        String host = "https://appfollow.io/";

        clearBrowserCache();
        WebDriverRunner.driver().clearCookies();

        Configuration.holdBrowserOpen = false;
        Configuration.startMaximized = true;
        Configuration.fastSetValue = true;
        Configuration.baseUrl = host;
        Configuration.headless = false;
        //Configuration.browserSize = "1920x1080";
        Configuration.reportsFolder = "test-output/reports";

        setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));

        Selenide.open(host);
    }


    @AfterClass
    public void tearDown() {
        // WebDriver need to be closed this way if it was run in 'defined' mode
        //getWebDriver().close();

        WebDriverRunner.closeWebDriver();
    }


}
