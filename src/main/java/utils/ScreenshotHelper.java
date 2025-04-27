package utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Getter
@Setter
public class ScreenshotHelper {
    public static void takeScreenshot(String name) {
        try {
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            try (InputStream is = new ByteArrayInputStream(screenshot)) {
                Allure.addAttachment(name.replace(" ","_"), is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//    @Attachment(value = "nomeEvidencia", type = "image/png")
//    public static byte[] takeScreenshot() {
//        WebDriver driver = Selenide.webdriver().object();
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    }
    
}
