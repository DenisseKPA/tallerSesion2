package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TodoTest {
    private AppiumDriver driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName","JB9");
        capabilities.setCapability("platformVersion","9.0");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity",".ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @Test
    public void appiumTest() throws InterruptedException {

        // click [+] agregar
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        // escribir Texto
        driver.findElement(By.xpath("//android.widget.EditText[@text='Title']")).sendKeys("Nota");
        // escribir telefono
        driver.findElement(By.xpath("//android.widget.EditText[@text='Notes']")).sendKeys("Hoy es 7 de Agosto");
        // click [un check]
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

        // verificar que la nota fue creada
        String expectedResult="Nota";
        String actualResult=driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text")).getText();

        //System.out.println(actualResult);
        Assertions.assertEquals(expectedResult,actualResult,"ERROR! no fue agregado a la Lista");

    }

    @AfterEach
    public void closeApp(){
        driver.quit();
    }

}
