package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;
import java.util.List;

public class Cart extends BaseActions{
    private WebDriver driver;
    private WebDriverWait wait;
    private static Logger log = Logger.getLogger(Cart.class.getSimpleName());

    @FindBy(how = How.XPATH, using = "//div[@class = 'table-responsive']/table[1]")
    private WebElement cartInfo;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrder;

    @FindBy(how = How.ID, using = "totalp")
    private WebElement price;

    @FindBy(how = How.ID, using = "name")
    private WebElement customerName;

    @FindBy(how = How.ID, using = "country")
    private WebElement customerCountry;

    @FindBy(how = How.ID, using = "city")
    private WebElement customerCity;

    @FindBy(how = How.ID, using = "card")
    private WebElement customerCreditCard;

    @FindBy(how = How.ID, using = "month")
    private WebElement month;

    @FindBy(how = How.ID, using = "year")
    private WebElement year;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Purchase')]")
    private WebElement purchase;

    @FindBy(how = How.XPATH, using = "//p[@class='lead text-muted ']")
    private WebElement actualPurchasePrice;

//    @FindBy(how = How.LINK_TEXT, using = "Delete")
//    private WebElement delete;

    public Cart(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
        wait = new WebDriverWait(this.driver, 20);
    }

    public boolean hasProductInCart(String productName){
        boolean found = false;
        List<WebElement> allRows = cartInfo.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for(WebElement eachRow : allRows){
            List<WebElement> allData = eachRow.findElements(By.tagName("td"));
            for(WebElement eachData : allData){
                if(eachData.getText().equals(productName)){
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public boolean cartDoesNotContains(String productName){
        wait.until(ExpectedConditions.elementToBeClickable(cartInfo));
        boolean notFound = true;
        List<WebElement> allRows = cartInfo.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for(WebElement eachRow : allRows){
            List<WebElement> allData = eachRow.findElements(By.tagName("td"));
            for(WebElement eachData : allData){
                if(eachData.getText().equals(productName)){
                    notFound = false;
                    break;
                }
            }
        }
        return notFound;
    }

    public int getInitialPurchasePrice(){
        waitForJSandJQueryToLoad(driver);
        return Integer.parseInt(price.getText());

    }
    public void purchaseProduct(Map<String,String> info){
        wait.until(ExpectedConditions.elementToBeClickable(purchase));
        customerName.sendKeys(info.get("name"));
        customerCountry.sendKeys(info.get("country"));
        customerCity.sendKeys(info.get("city"));
        customerCreditCard.sendKeys(info.get("credit_card"));
        year.sendKeys(info.get("year"));
        month.sendKeys(info.get("month"));
        purchase.click();
    }

    public String getFinalPurcharasePriceInfo(){
        String finalPurchasePrice = actualPurchasePrice.getText();
        return finalPurchasePrice;
    }

    public boolean deleteProduct(String productName){
        wait.until(ExpectedConditions.visibilityOf(cartInfo));
        boolean deleted = false;
        int counter = 0;
        WebElement bodyElement = cartInfo.findElement(By.tagName("tbody"));
        List<WebElement> allRows = bodyElement.findElements(By.tagName("tr"));
        for(WebElement eachRow : allRows){
            counter++;
            List<WebElement> allData = eachRow.findElements(By.tagName("td"));
            for(WebElement eachData : allData){
                if(eachData.getText().equals(productName)){
                    bodyElement.findElement(By.xpath("tr[" + String.valueOf(counter) + "]")).findElement(By.tagName("a")).click();
                    deleted = true;
                    break;
                }
            }
            if(deleted){
                break;
            }
        }
        return deleted;
    }

    public void placeOrder(){
        waitForJSandJQueryToLoad(driver);
        placeOrder.click();
    }
}

