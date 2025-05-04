package setup;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeClass
    public void setUp() {

        String baseUrl = System.getProperty("base.url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalArgumentException("A propriedade 'base.url' não está definida. Certifique-se de passar a URL base correta.");
        }
        System.out.println("Base URL: " + baseUrl);
        Configuration.baseUrl = baseUrl;
        Configuration.remote = System.getProperty("selenide.remote");
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadTimeout = 39000;
        Configuration.remoteConnectionTimeout = 9000;
        Configuration.timeout = 9000;
      //  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        open();
        Selenide.webdriver().object().manage().window().maximize();

    }


    @AfterMethod
    public void fecha() {
        Selenide.webdriver().driver().getWebDriver().quit();

    }
}


