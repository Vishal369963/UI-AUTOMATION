package com.magentoautomation.tests9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions; // Import Actions class for mouse hover
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class WishlistdeleteTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginAndManageWishlist() throws InterruptedException {
        // Navigate to Magento website
        driver.get("https://magento.softwaretestingboard.com/");
        Thread.sleep(5000); // Pause for 5 seconds to ensure the page loads

        // Click on Sign In link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In")));
        signInLink.click();
        Thread.sleep(5000); // Pause for 5 seconds to ensure the page loads

        // Enter login credentials
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("your_email@yopmail.com"); // Replace with your test email
        Thread.sleep(2000); // Pause for 2 seconds

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Test@1234"); // Replace with your test password
        Thread.sleep(2000); // Pause for 2 seconds

        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
        Thread.sleep(5000); // Pause for 5 seconds to ensure login completes

        // Navigate to a product page
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-item-link")));
        product.click();
        Thread.sleep(5000); // Pause for 5 seconds to ensure the product page loads

        // Wait and click the heart button (Add to Wishlist)
        WebElement heartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-addto-links > .action.towishlist > span")));
        heartButton.click();
        Thread.sleep(5000); // Pause for 5 seconds to ensure the item is added to the wishlist

        // Click the down arrow to open the account dropdown menu
        WebElement accountDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header.panel > .header.links span[role='button'] > .action.switch")));
        accountDropdown.click();
        Thread.sleep(5000); // Pause for 5 seconds to ensure the dropdown menu opens

        // Click on "My Wish List" from the dropdown menu
        WebElement wishlistLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header.panel > .header.links .customer-menu > .header.links > .link.wishlist > a")));
        wishlistLink.click();
        Thread.sleep(5000); // Pause for 5 seconds to ensure the wishlist page loads

        // Hover over the wishlist item to reveal the "Remove Item" link
        WebElement wishlistItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-of-type(1) > div > a[title='Radiant Tee'] img[alt='Radiant Tee']"))); // Replace with actual selector for the wishlist item
        Actions actions = new Actions(driver);
        actions.moveToElement(wishlistItem).perform();
        Thread.sleep(5000); // Pause for 5 seconds to ensure hover action

        // Copy the CSS path of the item
        String itemCssPath = wishlistItem.getAttribute("outerHTML"); // Use a unique identifier or attribute if needed
        System.out.println("Item CSS Path: " + itemCssPath); // Print the CSS path for debugging

        // Click the "Remove Item" link
        WebElement removeItemLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='Remove Item']")));
        removeItemLink.click();
        Thread.sleep(5000); // Pause for 5 seconds to ensure the item is removed

        // Validate that the item is no longer in the wishlist
        List<WebElement> wishlistItems = driver.findElements(By.cssSelector("li:nth-of-type(1) > div > a[title='Radiant Tee'] img[alt='Radiant Tee']")); // Replace with actual selector for wishlist items

        boolean itemRemoved = true;
        for (WebElement item : wishlistItems) {
            if (item.getAttribute("outerHTML").equals(itemCssPath)) {
                itemRemoved = false;
                break;
            }
        }

        // Assert that the item was removed
        Assert.assertTrue(itemRemoved, "Item was not removed from the wishlist.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}


