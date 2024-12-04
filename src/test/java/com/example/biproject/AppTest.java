package com.example.biproject;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

private static final String url ="https://crio-qkart-frontend-qa.vercel.app/";

   public static ChromeDriver createDriver() {
     WebDriverManager.chromedriver().setup();
     return new ChromeDriver();
   }

   public static void printQKartLoadingtime(ChromeDriver driver){
       //TODO Navigate to home page of QKart and verify the time taken for the page to load
       driver.manage().window().maximize();
       long startTime = System.currentTimeMillis();
       driver.navigate().to(url);
       long endtime= System.currentTimeMillis();
       System.out.printf("Time Taken to load the Homepage is %d%n",(endtime-startTime));


   }

   public static void captureFullPageScreenshot(ChromeDriver driver){
       //TODO: Capture the full page screenshot
       //Save the file with a unique name
       // Print the path of the file
       File file = new File("src\\Screenshorts");
       if(!file.exists()){
        file.mkdirs();
       }
       String filename =String.valueOf(UUID.randomUUID());
       Screenshot s=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
       try {
        String name =String.format("C:\\Users\\prono\\OneDrive\\Desktop\\bi_project\\src\\Screenshorts\\%s.png", filename);
        ImageIO.write(s.getImage(),"PNG",new File(name));
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }


   }

   public static void GetProductImageandURL(ChromeDriver driver, String productName) throws InterruptedException{
       //TODO: Given the product name, print the price of the project and the url of the image
       WebElement searchBtn = driver.findElement(By.xpath("(//input[@name='search'])[1]"));
       searchBtn.sendKeys(productName , Keys.ENTER);
       WebDriverWait wdw = new WebDriverWait(driver, 10);
       wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//img[@alt='Thinking, Fast and Slow']"))));
       WebElement imgElement = driver.findElement(By.xpath("//img[@alt='Thinking, Fast and Slow']"));
       WebElement priceTag = driver.findElement(By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']//p[2]"));
       System.out.printf("Attribute Value is %s and Price is %s%n",imgElement.getAttribute("src"),priceTag.getText());


   }

}
