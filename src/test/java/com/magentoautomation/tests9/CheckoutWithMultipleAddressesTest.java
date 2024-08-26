package com.magentoautomation.tests9;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutWithMultipleAddressesTest extends BaseTest {

    @Test
    public void testCheckoutWithMultipleAddresses() throws InterruptedException {
        // Log in first
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("your_email@yopmail.com");
        driver.findElement(By.id("pass")).sendKeys("Test@1234");
        driver.findElement(By.id("send2")).click();

        // Browse to an item and add to cart
        driver.findElement(By.linkText("Men")).click();
        driver.findElement(By.linkText("Jackets")).click();
        driver.findElement(By.cssSelector("img[alt='Proteus Fitness Jackshirt']")).click();
        // Select size
        WebElement sizeOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".size.swatch-attribute > div[role='listbox'] > div:nth-of-type(1)")));
        sizeOption.click();
        Thread.sleep(3000); // Wait for size to be selected

        // Select color
        WebElement colorOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".color.swatch-attribute > div[role='listbox'] > div:nth-of-type(1)")));
        colorOption.click();
        Thread.sleep(3000); // Wait for color to be selected
        driver.findElement(By.id("product-addtocart-button")).click();
        Thread.sleep(5000); // Wait for login to complete


        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-item-link")));
        product.click();
        Thread.sleep(3000); // Wait for the product page to load
        // Select size
        WebElement sizeOption1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".size.swatch-attribute > div[role='listbox'] > div:nth-of-type(1)")));
        sizeOption1.click();
        Thread.sleep(3000); // Wait for size to be selected

        // Select color
        WebElement colorOption1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".color.swatch-attribute > div[role='listbox'] > div:nth-of-type(1)")));
        colorOption1.click();
        Thread.sleep(3000); // Wait for color to be selected
        driver.findElement(By.id("product-addtocart-button")).click();
        Thread.sleep(5000); // Wait for login to complete



        // Click on cart counter
        WebElement cartCounter = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".minicart-wrapper > .action.showcart")));
        cartCounter.click();
        Thread.sleep(20000); // Wait for cart to open


        // Click on view and edit cart
        WebElement viewandeditcart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action.viewcart > span")));
        viewandeditcart.click();
        Thread.sleep(3000); // Wait for checkout page to load

        // Click on checkout with multiple address
        WebElement checkoutwithmultipleaddresss = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action.multicheckout > span")));
        checkoutwithmultipleaddresss.click();
        Thread.sleep(15000); // Wait for checkout page to load


        // Locate the first dropdown using XPath with contains() to handle dynamic numbers
        WebElement firstAddressDropdown = driver.findElement(By.xpath("//select[contains(@name,'ship[0]') and contains(@name,'[address]')]"));
        Select selectFirstAddress = new Select(firstAddressDropdown);
        int firstAddressSize = selectFirstAddress.getOptions().size();
        selectFirstAddress.selectByIndex(firstAddressSize - 1); // Select the last address

        // Click on the last address option
        WebElement lastAddressOption = driver.findElement(By.xpath("//select[contains(@name,'ship[0]') and contains(@name,'[address]')]/option[last()]"));
        lastAddressOption.click();
        Thread.sleep(15000); // Wait for checkout page to load

        // Locate the second dropdown using XPath with contains() to handle dynamic numbers
        WebElement secondAddressDropdown = driver.findElement(By.xpath("//select[contains(@name,'ship[1]') and contains(@name,'[address]')]"));
        Select selectSecondAddress = new Select(secondAddressDropdown);
        int secondAddressSize = selectSecondAddress.getOptions().size();
        selectSecondAddress.selectByIndex(secondAddressSize - 2); // Select the second-last address

        // Click on the second-last address option
        WebElement secondLastAddressOption = driver.findElement(By.xpath("//select[contains(@name,'ship[1]') and contains(@name,'[address]')]/option[last()-1]"));
        secondLastAddressOption.click();
        Thread.sleep(3000); // Wait for checkout page to load



        // Click on shipping information
        WebElement shippinginformation = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='Go to Shipping Information'] > span")));
        shippinginformation.click();
        Thread.sleep(3000); // Wait for checkout page to load

        // Click on  first shipping method
        WebElement shippingingmethod1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-of-type(1) > .block-content > .box.box-shipping-method > .box-content > dl > dd:nth-of-type(1) > .fieldset > .choice.field > label > .price")));
        shippingingmethod1.click();
        Thread.sleep(3000); // Wait for checkout page to load

        // Click on second shipping method
        WebElement shippingingmethod2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-of-type(2) > .block-content > .box.box-shipping-method  dl > dd:nth-of-type(1) > .fieldset > .choice.field > label")));
        shippingingmethod2.click();
        Thread.sleep(3000); // Wait for checkout page to load

        // Click on continue billing information
        WebElement billinginformation = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action.continue.primary > span")));
        billinginformation.click();
        Thread.sleep(3000); // Wait for checkout page to load

        // Click on review order
        WebElement revieworder = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#payment-continue > span")));
        revieworder.click();
        Thread.sleep(3000); // Wait for checkout page to load

        // Click on place order
        WebElement placeorder = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#review-button > span")));
        placeorder.click();
        Thread.sleep(3000); // Wait for checkout page to load

        // Locate the first order number using its CSS selector and store it in a variable o1
        WebElement order1Element = driver.findElement(By.cssSelector("li:nth-of-type(1) > .order-id > a"));
        String o1 = order1Element.getText();

        // Locate the second order number using its CSS selector and store it in a variable o2
        WebElement order2Element = driver.findElement(By.cssSelector("li:nth-of-type(2) > .order-id > a"));
        String o2 = order2Element.getText();

        // Locate the first address using its CSS selector and store it in a variable a1
        WebElement address1Element = driver.findElement(By.cssSelector("li:nth-of-type(1) > .shipping-item > .shipping-address"));
        String a1 = address1Element.getText();

        // Locate the second address using its CSS selector and store it in a variable a2
        WebElement address2Element = driver.findElement(By.cssSelector("li:nth-of-type(2) > .shipping-item > .shipping-address"));
        String a2 = address2Element.getText();

        // Print the stored order numbers and addresses to verify
        System.out.println("Order 1 Number: " + o1);
        System.out.println("Order 2 Number: " + o2);
        System.out.println("Address 1: " + a1);
        System.out.println("Address 2: " + a2);


        // Click the down arrow to open the account dropdown menu
        WebElement accountDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header.panel > .header.links span[role='button'] > .action.switch")));
        accountDropdown.click();
        Thread.sleep(2000); // Pause for 2 seconds to ensure the dropdown menu opens

        // Click on "My account detail" from the dropdown menu
        WebElement myaccount = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header.panel > .header.links  .customer-menu > .header.links > li:nth-of-type(1) > a")));
        myaccount.click();
        Thread.sleep(3000); // Pause for 3 seconds to ensure the wishlist page loads


        // Step 3: Store order number 3 (o3) and click on "View Order"
        WebElement order3Element = driver.findElement(By.cssSelector("tbody tr:nth-of-type(1) .id"));
        String o3 = order3Element.getText();
        WebElement viewOrder3Element = driver.findElement(By.cssSelector("tr:nth-of-type(1) > .actions.col > .action.view > span"));
        viewOrder3Element.click();

        // Step 4: Store address 3 (a3)
        WebElement address3Element = driver.findElement(By.cssSelector(".box.box-order-shipping-address > .box-content > address"));
        String a3 = address3Element.getText();

        // Step 5: Go back to the previous page
        driver.navigate().back();

        // Step 6: Store order number 4 (o4) and click on "View Order"
        WebElement order4Element = driver.findElement(By.cssSelector("tr:nth-of-type(2) > .col.id"));
        String o4 = order4Element.getText();
        WebElement viewOrder4Element = driver.findElement(By.cssSelector("tr:nth-of-type(2) > .actions.col > .action.view > span"));
        viewOrder4Element.click();

        // Step 7: Store address 4 (a4)
        WebElement address4Element = driver.findElement(By.cssSelector(".box.box-order-shipping-address > .box-content > address"));
        String a4 = address4Element.getText();

        // Print the stored order numbers and addresses to verify
        System.out.println("Order 3 Number: " + o3);
        System.out.println("Order 4 Number: " + o4);
        System.out.println("Address 3: " + a3);
        System.out.println("Address 4: " + a4);

        // Step 8: Validate conditions
      //  boolean validation1 = o1.equals(o4) && a4.contains(a1);
      //  boolean validation2 = o2.equals(o3) && a3.contains(a2);

    //    boolean validation3 = o1.equals(o3) && a3.contains(a1);
     //   boolean validation4 = o2.equals(o4) && a4.contains(a2);

        // Normalize addresses
        String normalizedA1 = normalizeAddress(a1);
        String normalizedA2 = normalizeAddress(a2);
        String normalizedA3 = normalizeAddress(a3);
        String normalizedA4 = normalizeAddress(a4);

// Update validation conditions with normalized addresses
        boolean validation1 = o1.equals(o4) && normalizedA4.contains(normalizedA1);
        boolean validation2 = o2.equals(o3) && normalizedA3.contains(normalizedA2);

        boolean validation3 = o1.equals(o3) && normalizedA3.contains(normalizedA1);
        boolean validation4 = o2.equals(o4) && normalizedA4.contains(normalizedA2);


        System.out.println("normalization address a1 " + normalizedA1);
        System.out.println("normalization address a2 " + normalizedA2);
        System.out.println("normalization address a3 " + normalizedA3);
        System.out.println("normalization address a4 " + normalizedA4);



        // Test result based on validation
        Assert.assertTrue(validation1 && validation2 || validation3 && validation4, "Test failed: Orders and/or Addresses do not match as expected.");
        System.out.println("Test passed: Orders and Addresses match as expected.");


    }
    private static String normalizeAddress(String address) {
        return address.trim()                           // Remove leading and trailing spaces
                .replace("\n", " ")             // Replace newlines with spaces
                .replace(",", "")               // Remove commas
                .replaceAll("T:\\s*\\d+", "")  // Remove telephone numbers (pattern: T: <number>)
                .toLowerCase();                 // Convert to lowercase
    }
}
