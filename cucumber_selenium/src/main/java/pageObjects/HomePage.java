package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends BaseActions{

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.CLASS_NAME, using = "col-lg-9")
    private WebElement productContainer;

    public HomePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(this.driver, this);
    }

    public void chooseCategory(String category){
        driver.findElement(By.partialLinkText(category)).click();
    }

    public void chooseProduct(String productName) throws Exception{
        if(waitForJSandJQueryToLoad(driver))
        {
            List<WebElement> products = driver.findElements(By.className("hrefch"));
            for(WebElement product : products) {
                if (product.getText().equals(productName)) {
                    product.click();
                    break;
                }
            }
        }
        else{
            throw new Exception("Page could not be loaded");
        }
    }
}
