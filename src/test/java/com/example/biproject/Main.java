package com.example.biproject;


import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = AppTest.createDriver();
        AppTest.printQKartLoadingtime(driver);
        AppTest.captureFullPageScreenshot(driver);
        AppTest.GetProductImageandURL(driver, "Thinking, Fast and Slow");
        driver.close();

    }

}
