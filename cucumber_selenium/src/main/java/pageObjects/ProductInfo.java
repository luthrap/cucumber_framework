package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductInfo extends BaseActions{

    private WebDriver driver;

    @FindBy(how = How.LINK_TEXT, using = "Add to cart")
    public WebElement addToCart;
    private WebDriverWait wait;

    public ProductInfo(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
        wait = new WebDriverWait(this.driver, 20);
    }

    public void addToCart(){
        WebElement element = wait.until(ExpectedConditions.visibilityOf(addToCart));
        element.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

    }
}
