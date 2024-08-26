package com.magentoautomation.tests9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class WishlistTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginAndCreateWishlist() throws InterruptedException {
        // Navigate to Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on Sign In link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In")));
        signInLink.click();
        Thread.sleep(2000); // Pause for 2 seconds to ensure the page loads

        // Enter login credentials
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("vs2982001@gmail.com"); // Replace with your test email

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Vishal@2002"); // Replace with your test password

        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
        Thread.sleep(3000); // Pause for 3 seconds to ensure login completes

        // Navigate to a product page
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-item-link")));
        product.click();
        Thread.sleep(2000); // Pause for 2 seconds to ensure the product page loads

        // Wait and click the heart button (Add to Wishlist)
        WebElement heartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-addto-links > .action.towishlist > span")));
        heartButton.click();
        Thread.sleep(2000); // Pause for 2 seconds to ensure the item is added to the wishlist

        // Click the down arrow to open the account dropdown menu
        WebElement accountDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header.panel > .header.links span[role='button'] > .action.switch")));
        accountDropdown.click();
        Thread.sleep(2000); // Pause for 2 seconds to ensure the dropdown menu opens

        // Click on "My Wish List" from the dropdown menu
        WebElement wishlistLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header.panel > .header.links .customer-menu > .header.links > .link.wishlist > a")));
        wishlistLink.click();
        Thread.sleep(3000); // Pause for 3 seconds to ensure the wishlist page loads

        // Check if the message "You have no items in your wish list." is present
        List<WebElement> emptyMessage = driver.findElements(By.cssSelector("form#wishlist-view-form > .empty.info.message"));

        // Assert that the empty message is not present, meaning the wishlist contains items
        Assert.assertTrue(emptyMessage.isEmpty(), "Wishlist is empty, but it should contain items.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}



