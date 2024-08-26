package com.magentoautomation.tests9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("your1_email@yopmail.com");
        driver.findElement(By.id("pass")).sendKeys("Test@12345");
        driver.findElement(By.id("send2")).click();

        // Wait for the specific text to be visible
        WebElement welcomeMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Welcome, John Doe!')]"))
        );

        // Validate successful login
        String welcomeMessage = welcomeMessageElement.getText();
        Assert.assertTrue(welcomeMessage.contains("Welcome, John Doe!"), "Login was not successful.");
    }
}



