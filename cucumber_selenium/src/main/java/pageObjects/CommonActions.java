package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CommonActions extends BaseActions{

    private WebDriver driver;


    public CommonActions(WebDriver driver) {
        this.driver = driver;
    }

    public void clickByLinkText(String text) {
        driver.findElement(By.linkText(text)).click();
    }

    public void navigation(String pageName){
        driver.findElement(By.partialLinkText(pageName)).click();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}