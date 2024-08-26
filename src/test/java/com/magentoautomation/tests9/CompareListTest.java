package com.magentoautomation.tests9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration; // Import Duration

public class CompareListTest extends BaseTest {

    @Test
    public void testAddToCompareList() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Use Duration.ofSeconds

        // Sign In
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("your1_email@yopmail.com");
        driver.findElement(By.id("pass")).sendKeys("Test@12345");
        driver.findElement(By.id("send2")).click();

        // Navigate to Men > Tops and add the first item to compare
        driver.findElement(By.linkText("Men")).click();
        driver.findElement(By.linkText("Tops")).click();

        // Wait for the first item to be visible and get its text
        WebElement itemElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-of-type(1) > .product-item-info .product-item-link")));
        String itemName1 = itemElement1.getText();
        itemElement1.click();

        // Click on the "Add to Compare" button
        WebElement addToCompareButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-addto-links > .action.tocompare > span")));
        addToCompareButton1.click();
        Thread.sleep(15000); // Wait for login to complete

        // Navigate back
        driver.navigate().back();

        // Navigate to the second item and add it to compare
        WebElement itemElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-of-type(2) > .product-item-info .product-item-link")));
        String itemName2 = itemElement2.getText();
        itemElement2.click();

        // Click on the "Add to Compare" button
        WebElement addToCompareButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-addto-links > .action.tocompare > span")));
        addToCompareButton2.click();
        Thread.sleep(15000); // Wait for login to complete

        // Locate the "Compare Products" link and click on it
        WebElement compareProductsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".compare.item.link > a[title='Compare Products']")));
        compareProductsLink.click();

        // Wait for item 3 and 4 to be visible
        WebElement itemElement3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("strong > a[title='Cassius Sparring Tank']")));
        String itemName3 = itemElement3.getText();

        WebElement itemElement4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("strong > a[title='Logan  HeatTec® Tee']")));
        String itemName4 = itemElement4.getText();

        // Print the texts for verification
        System.out.println("LHS1 : " + itemName1);
        System.out.println("LHS2 : " + itemName3);
        System.out.println("RHS1 : " + itemName2);
        System.out.println("RHS2 : " + itemName4);

        // Validate that both titles are equal
        Assert.assertEquals(itemName1, itemName3, "Titles do not match!");
        Assert.assertEquals(itemName2, itemName4, "Titles do not match!");

        // Optionally, print a confirmation message if validation passes
        System.out.println("Adding multiple items to compare and Validation Passed: ");

        // Validate items added to compare list (if necessary)
        // String compareListMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-success"))).getText();
        // Assert.assertTrue(compareListMessage.contains("has been added to your Compare List"));
    }
}

