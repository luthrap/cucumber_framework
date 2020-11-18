package com.paras.bdd.cucumber_selenium.stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.Cart;
import pageObjects.CommonActions;
import pageObjects.HomePage;
import pageObjects.ProductInfo;

import static com.paras.bdd.cucumber_selenium.utils.Initialization.getDriver;

public class ValidationActions {

    WebDriver thisDriver = getDriver();
    HomePage homePage = new HomePage(thisDriver);
    Cart cart = new Cart(thisDriver);
    ProductInfo info = new ProductInfo(thisDriver);
    Context context = Context.getInstance();
    CommonActions commonActions = new CommonActions(thisDriver);

    @Given("^I am on to (.+) Page$")
    public void validatePageTitle(String pageName){
        Assert.assertEquals(pageName,commonActions.getPageTitle());
    }

    @Then("^I should see (.+) product$")
    public void isProductPresent(String product){

    }

    @Then("^I verify the purchase price against cart price$")
    public void assertCartPrice(){
        String actualPriceInfo = cart.getFinalPurcharasePriceInfo();
        String expectedPriceInfo = "Amount: " + String.valueOf(context.getExpectedPrice());
        if(!actualPriceInfo.contains(expectedPriceInfo)){
            Assert.assertTrue("Expected Price Info:" + expectedPriceInfo + ", Actual Price Info: " + actualPriceInfo, false);
        }

    }
}
