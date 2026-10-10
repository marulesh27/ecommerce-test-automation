package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import utilities.PropertiesUtility;

public class BaseClass {

    protected WebDriver driver;
    protected PropertiesUtility pro;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Suite execution started");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Test execution started");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Class execution started");
    }
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(String browser) throws Exception {
        pro = new PropertiesUtility();
     //String browser=pro.getProperty("browser");
        String url = pro.getProperty("url");
        if (browser.equalsIgnoreCase("chrome")) {
        	ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
       }
        else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
        System.out.println("Browser launched");
    }

    @AfterMethod
    public void logout() {
        driver.quit();
        System.out.println("Logout executed");
    }
    @AfterClass
    public void afterClass() {
        System.out.println("Class execution completed");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("Test execution completed");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Suite execution completed");
    }
}