package com.magentoautomation.tests9;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyAccountOrderValidationTest extends BaseTest {

    @Test
    public void testValidateOrders() {
        // Log in first
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("your_email@yopmail.com");
        driver.findElement(By.id("pass")).sendKeys("Test@1234");
        driver.findElement(By.id("send2")).click();

        // Navigate to My Account > Orders
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("My Orders")).click();
        // Click the down arrow to open the account dropdown menu
        WebElement accountDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header.panel > .header.links span[role='button'] > .action.switch")));
        accountDropdown.click();
        // Pause for 5 seconds to ensure the dropdown menu opens

        WebElement accountvalidate = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header.panel > .header.links  .customer-menu > .header.links > li:nth-of-type(1) > a")));
        accountvalidate .click();

        // Validate that orders are listed
        String orderSectionText = driver.findElement(By.cssSelector(".order-history")).getText();
        Assert.assertTrue(orderSectionText.contains("Order #"), "Order section is empty or not visible.");
    }
}

