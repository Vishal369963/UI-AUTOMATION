package com.magentoautomation.tests9;



import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    @Test
    public void testSignUp() {
        driver.findElement(By.linkText("Create an Account")).click();
        driver.findElement(By.id("firstname")).sendKeys("John");
        driver.findElement(By.id("lastname")).sendKeys("Doe");
        driver.findElement(By.id("email_address")).sendKeys("your1_email@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("Test@12345");
        driver.findElement(By.id("password-confirmation")).sendKeys("Test@1234");
        driver.findElement(By.cssSelector("button[title='Create an Account']")).click();

        // Validate successful sign up
        String successMessage = driver.findElement(By.cssSelector(".message-success")).getText();
        Assert.assertTrue(successMessage.contains("Thank you for registering with Main Website Store."));
    }
}
