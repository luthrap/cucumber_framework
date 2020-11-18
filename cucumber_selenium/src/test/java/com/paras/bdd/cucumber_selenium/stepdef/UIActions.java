package com.paras.bdd.cucumber_selenium.stepdef;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Cart;
import pageObjects.CommonActions;
import pageObjects.HomePage;
import pageObjects.ProductInfo;
import java.util.Map;

import static com.paras.bdd.cucumber_selenium.utils.Initialization.*;

public class UIActions {

    WebDriver thisDriver = getDriver();
    HomePage homePage = new HomePage(thisDriver);
    Cart cart = new Cart(thisDriver);
    ProductInfo info = new ProductInfo(thisDriver);
    Context context = Context.getInstance();
    CommonActions commonActions = new CommonActions(thisDriver);
    WebDriverWait wait=new WebDriverWait(thisDriver, 20);


    @When("^I select (.+) category$")
    public void chooseCategory(String category){
        homePage.chooseCategory(category);
    }

    @When("^I select (.+) product$")
    public void chooseProduct(String product) throws Exception{
        homePage.chooseProduct(product);
    }

    @When("^I add the product in the cart$")
    public void addToCart(){
        info.addToCart();
    }

    @When("^I navigate to (.+) page$")
    public void navigate(String page){
        commonActions.navigation(page);
    }

    @When("^I delete (.+) product$")
    public void deleteProduct(String productName){
        cart.deleteProduct(productName);
    }

    @Then("^the cart should not have (.+) product$")
    public void verifyNotContainsProduct(String productName){
        cart.cartDoesNotContains(productName);
    }

    @When("^I get the total cart price$")
    public void getCartPrice(){
        context.setExpectedPrice(cart.getInitialPurchasePrice());
    }

    @When("^I click on (.+)$")
    public void initiatePlaceOrder(String linkName){
        cart.placeOrder();
    }
    @When("^I purchase with following info$")
    public void placeOrder(DataTable table) throws InterruptedException {
        Map<String,String> customerDetails = table.asMaps().get(0);
        cart.purchaseProduct(customerDetails);
    }
}
