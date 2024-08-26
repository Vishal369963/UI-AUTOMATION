package com.magentoautomation.tests9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class OrderCreation_ValidationTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginAndCreateOrder() throws InterruptedException {
        // Navigate to Magento website
        driver.get("https://magento.softwaretestingboard.com/");
        Thread.sleep(3000); // Wait for the page to load

        // Click on Sign In link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In")));
        signInLink.click();
        Thread.sleep(3000); // Wait for the Sign In page to load

        // Enter login credentials
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("your1_email@yopmail.com"); // Replace with your test email

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Test@12345"); // Replace with your test password

        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
        Thread.sleep(5000); // Wait for login to complete

        // Navigate to a product page
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-item-link")));
        product.click();
        Thread.sleep(3000); // Wait for the product page to load

        // Select size
        WebElement sizeOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".size.swatch-attribute > div[role='listbox'] > div:nth-of-type(1)")));
        sizeOption.click();
        Thread.sleep(3000); // Wait for size to be selected

        // Select color
        WebElement colorOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".color.swatch-attribute > div[role='listbox'] > div:nth-of-type(1)")));
        colorOption.click();
        Thread.sleep(3000); // Wait for color to be selected

        // Click on Add to Cart button
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#product-addtocart-button > span")));
        addToCartButton.click();
        Thread.sleep(3000); // Wait for item to be added to cart

        // Click on cart counter
        WebElement cartCounter = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".minicart-wrapper > .action.showcart")));
        cartCounter.click();
        Thread.sleep(20000); // Wait for cart to open

        // Click on Proceed to Checkout button
        WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#top-cart-btn-checkout")));
        proceedToCheckoutButton.click();
        Thread.sleep(3000); // Wait for checkout page to load


        WebElement newaddress = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-bind='i18n\\: \\'New Address\\'']")));
        newaddress.click();
        Thread.sleep(3000); // Wait for checkout page to load



        // Fill in the checkout details
        WebElement streetAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='street[0]']")));
        streetAddress.sendKeys("500 Flaugherty Run Rd");
        Thread.sleep(3000);




        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='city']")));
        cityField.sendKeys("New York");
        Thread.sleep(3000);





// Wait until the dropdown is visible and enabled
       // WebElement stateDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='region_id']")));

// Create an instance of the Select class and pass the dropdown WebElement
        // Wait until the dropdown is visible and enabled
        // Wait until the dropdown is visible and enabled



// Wait for 3 seconds to observe the selection (can be removed in production code)
        Thread.sleep(3000);

        WebElement zipCode = driver.findElement(By.cssSelector("input[name='postcode']"));
        zipCode.sendKeys("15108");
        Thread.sleep(3000);

        WebElement countryDropdown = driver.findElement(By.cssSelector("select[name='country_id']"));
        countryDropdown.sendKeys("United States");
        Thread.sleep(3000);

        WebElement phoneNumber = driver.findElement(By.cssSelector("input[name='telephone']"));
        phoneNumber.sendKeys("9182345656");
        Thread.sleep(3000);


        WebElement stateDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[name='region_id']")));

// Create an instance of the Select class and pass the dropdown WebElement
        Select selectState = new Select(stateDropdown);

        try {
            // Get all available options in the dropdown
            List<WebElement> stateOptions = selectState.getOptions();

            // Calculate the index for the 4th option from the last
            int indexFromLast = 4;
            int optionIndex = stateOptions.size() - indexFromLast;

            // Verify if the calculated index is valid
            if (optionIndex >= 0 && optionIndex < stateOptions.size()) {
                // Select the option by index
                selectState.selectByIndex(optionIndex);

                // Output the selected state's text for verification
                System.out.println("Selected State: " + stateOptions.get(optionIndex).getText());
            } else {
                System.out.println("Calculated index is out of range.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        // Wait until the radio button is clickable
  //      WebElement shippingMethodRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr:nth-of-type(1) > .col.col-price > .price > .price")));

// Click on the radio button
//        shippingMethodRadioButton.click();
  //      Thread.sleep(3000);

// Wait until the radio button is selected
//        wait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("tr:nth-of-type(1) > .col.col-price > .price > .price")));

        WebElement shipaddress = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action.action-save-address.primary")));
        shipaddress.click();
        Thread.sleep(3000); // Wait for address to be saved

        WebElement shippingMethodRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr:nth-of-type(1) > .col.col-price > .price > .price")));

// Click on the radio button
        shippingMethodRadioButton.click();
        Thread.sleep(3000);


        // Click on Save Address button
        WebElement nextbutton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action.button.continue.primary")));
        nextbutton.click();
        Thread.sleep(3000); // Wait for address to be saved

        WebElement placeorder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Place Order']")));
        placeorder.click();
        Thread.sleep(15000);

        // Locate the element with the order number using the CSS selector
        WebElement orderNumberElement = driver.findElement(By.cssSelector(".order-number > strong"));

// Extract the text content, which is the order number
        String orderNumber = orderNumberElement.getText();

// Now you can use the order number in your test
        System.out.println("Order Number: " + orderNumber);






        // Validate order creation
        WebElement shippingAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkout-success > p:nth-of-type(1)")));
        Assert.assertTrue(shippingAddress.isDisplayed(), "Order creation failed or address not displayed correctly.");
        System.out.println("Succesful order creation!");
        Thread.sleep(3000); // Wait for final verification


        WebElement accountDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header.panel > .header.links span[role='button'] > .action.switch")));
        accountDropdown.click();
        Thread.sleep(2000); // Pause for 2 seconds to ensure the dropdown menu opens

        // Click on "My Wish List" from the dropdown menu
        WebElement myaccount = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header.panel > .header.links  .customer-menu > .header.links > li:nth-of-type(1) > a")));
        myaccount.click();
        Thread.sleep(3000); // Pause for 3 seconds to ensure the wishlist page loads

        // Locate the element with the order number using the CSS selector
        WebElement orderNumberElement2 = driver.findElement(By.cssSelector("tbody tr:nth-of-type(1) .id"));

// Extract the text content, which is the order number
        String orderNumber2 = orderNumberElement2.getText();

// Now you can use the order number in your test
        System.out.println("Order Number: " + orderNumber2);

        // Assuming orderNumber1 is the order number obtained after placing the order
// And orderNumber2 is the order number obtained from the account section

        if (orderNumber.equals(orderNumber2)) {
            System.out.println("Succesful order Validation");
        } else {
            System.out.println("UnSuccesful order Validation");
        }




    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
