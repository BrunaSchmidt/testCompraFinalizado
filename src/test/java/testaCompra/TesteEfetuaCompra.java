package testaCompra;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

public class TesteEfetuaCompra {


    WebDriver driver;
    @Test
    public void OpenBrowser(){

        System.setProperty("webdriver.chrome.driver", "/home/bruna/SeleniumJars/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);

        driver.get("http://www.automationpractice.com");

        //add to cart
        driver.findElement(By.linkText("Blouse")).click();
        driver.findElement(By.id("add_to_cart")).click();
        driver.findElement(By.linkText("Proceed to checkout")).click();

        //test products quantity
        String cart = driver.findElement(By.id("summary_products_quantity")).getText();
        assertEquals("1 Product", cart);

        driver.findElement(By.linkText("Proceed to checkout")).click();


        //Create login
        driver.findElement(By.id("create-account_form")).findElement(By.id("email_create")).sendKeys(
                "tulipa00700@hotmail.com");
        driver.findElement(By.name("SubmitCreate")).click();

        //Personal information form
        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.id("account-creation_form")).findElement(By.id("customer_firstname")).sendKeys(
                "Tulipa");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("customer_lastname")).sendKeys(
                "Santos");
        //driver.findElement(By.id("account-creation-form")).findElement(By.id("email")).sendKeys(
             //   "tulipa000700@hotmail.com");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("passwd")).sendKeys(
                "tulipinha0007");
        Select dropdownDays = new Select(driver.findElement(By.id("days")));
        dropdownDays.selectByValue("12");
        Select dropdownMonths = new Select(driver.findElement(By.id("months")));
        dropdownMonths.selectByIndex(11);
        Select dropdownYears = new Select(driver.findElement(By.id("years")));
        dropdownYears.selectByValue("1991");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("newsletter")).click();
        driver.findElement(By.id("account-creation_form")).findElement(By.id("optin")).click();
        driver.findElement(By.id("account-creation_form")).findElement(By.id("firstname")).click();
        driver.findElement(By.id("account-creation_form")).findElement(By.id("lastname")).click();
        driver.findElement(By.id("account-creation_form")).findElement(By.id("company")).sendKeys(
                "Empresa");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("address1")).sendKeys(
                "Rua das Rosas");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("address2")).sendKeys(
                "Rua das Hortências");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("city")).sendKeys(
                "Porto Alegre");
        Select dropdownState = new Select(driver.findElement(By.id("id_state")));
        dropdownState.selectByIndex(5);
        driver.findElement(By.id("account-creation_form")).findElement(By.id("postcode")).sendKeys(
                "12345");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("other")).sendKeys(
                "8585-4545");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("phone")).sendKeys(
                "3535-6767");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("phone_mobile")).sendKeys(
                "99999999");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("alias")).sendKeys(
                "Rua das Gaivotas");
        driver.findElement(By.id("account-creation_form")).findElement(By.id("submitAccount")).click();

        /*login
        driver.findElement(By.id("login_form")).findElement(By.id("email")).sendKeys(
                "tulipa00070@hotmail.com");
        driver.findElement(By.id("passwd")).sendKeys("tulipinha0007");
        driver.findElement(By.id("SubmitLogin")).click();
        */

        if (driver.findElements(By.id("address_delivery")).size() != 0){

            driver.findElement(By.name("processAddress")).click();

        }else{
            //else não esta sendo utilizado

        }

        driver.findElement(By.id("cgv")).click();
        driver.findElement(By.name("processCarrier")).click();

        //conferindo valor da compra
        String totalProducts = driver.findElement(By.id("total_product")).getText().replace("$","");
        double dTotalProducts = Double.parseDouble(totalProducts);

        String totalShiping = driver.findElement(By.id("total_shipping")).getText().replace("$","");
        double dTotalShiping = Double.parseDouble(totalShiping);

        double total = dTotalProducts + dTotalShiping;

        String valueTotal = driver.findElement(By.id("total_price")).getText().replace("$","");
        double dValueTotal = Double.parseDouble(valueTotal);

        assertEquals(total,dValueTotal,total);

        driver.findElement(By.cssSelector("p.payment_module a.bankwire")).click();
        driver.findElement(By.cssSelector(".cart_navigation .button-medium")).click();


        String finalMessage = driver.findElement(By.className("dark")).getText();
        finalMessage.contains("complete");

        driver.quit();


    }

}
