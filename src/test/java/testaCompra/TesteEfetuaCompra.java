package testaCompra;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

public class TesteEfetuaCompra {


    public WebDriver navegador;
    @Test
    public void TestaAcesso(){

        System.setProperty("webdriver.chrome.driver", "/home/bruna/SeleniumJars/chromedriver");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);

        navegador.get("http://www.automationpractice.com");

        //add to cart
        navegador.findElement(By.linkText("Blouse")).click();
        navegador.findElement(By.id("add_to_cart")).click();
        navegador.findElement(By.linkText("Proceed to checkout")).click();

        //test products quantity
        String cart = navegador.findElement(By.id("summary_products_quantity")).getText();
        assertEquals("1 Product", cart);

        navegador.findElement(By.linkText("Proceed to checkout")).click();

        //if test
        String textoAlerta;
        textoAlerta = navegador.findElement(By.cssSelector(".alert.alert-danger")).getText();


        if(textoAlerta != null){

            //login
            navegador.findElement(By.id("login_form")).findElement(By.id("email")).sendKeys(
                    "tulipa0007@hotmail.com");
            navegador.findElement(By.id("passwd")).sendKeys("tulipinha0007");
            navegador.findElement(By.id("SubmitLogin")).click();

        }else{

            navegador.findElement(By.id("create-account_form")).findElement(By.id("email_create")).sendKeys(
                    "tulipa0007@hotmail.com");
            navegador.findElement(By.name("SubmitCreate")).click();

            navegador.findElement(By.id("create-account_form")).findElement(By.id("email_create")).sendKeys(
                    "tulipa0007@hotmail.com");
            navegador.findElement(By.name("SubmitCreate")).click();

            //Personal information form
            navegador.findElement(By.id("id_gender2")).click();
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("customer_firstname")).sendKeys(
                    "Tulipa");
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("customer_lastname")).sendKeys(
                    "Santos");
            //navegador.findElement(By.id("account-creation-form")).findElement(By.id("email"));
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("passwd")).sendKeys(
                    "tulipinha0007");

            Select dropdownDays = new Select(navegador.findElement(By.id("days")));
            dropdownDays.selectByValue("12");
            Select dropdownMonths = new Select(navegador.findElement(By.id("months")));
            dropdownMonths.selectByIndex(11);
            Select dropdownYears = new Select(navegador.findElement(By.id("years")));
            dropdownYears.selectByValue("1991");


            navegador.findElement(By.id("account-creation_form")).findElement(By.id("newsletter")).click();
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("optin")).click();

            //Address form
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("firstname")).click();
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("lastname")).click();

            navegador.findElement(By.id("account-creation_form")).findElement(By.id("company")).sendKeys(
                    "Empresa");
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("address1")).sendKeys(
                    "Rua das Rosas");
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("address2")).sendKeys(
                    "Rua das Hortências");
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("city")).sendKeys(
                    "Porto Alegre");
            Select dropdownState = new Select(navegador.findElement(By.id("id_state")));
            dropdownState.selectByIndex(5);

            navegador.findElement(By.id("account-creation_form")).findElement(By.id("postcode")).sendKeys(
                    "12345");
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("other")).sendKeys(
                    "8585-4545");
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("phone")).sendKeys(
                    "3535-6767");
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("phone_mobile")).sendKeys(
                    "99999999");
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("alias")).sendKeys(
                    "Rua das Gaivotas");
            navegador.findElement(By.id("account-creation_form")).findElement(By.id("submitAccount")).click();

            //logar
            navegador.findElement(By.id("login_form")).findElement(By.id("email")).sendKeys(
                    "tulipa0007@hotmail.com");
            navegador.findElement(By.id("passwd")).sendKeys("tulipinha0007");
            navegador.findElement(By.id("SubmitLogin")).click();

        }

        if (navegador.findElements(By.id("address_delivery")).size() != 0){

            navegador.findElement(By.name("processAddress")).click();

        }else{
            //else não esta sendo utilizado

        }

        navegador.findElement(By.id("cgv")).click();
        navegador.findElement(By.name("processCarrier")).click();

        //conferindo valor da compra
        String totalProducts = navegador.findElement(By.id("total_product")).getText().replace("$","");
        double dTotalProducts = Double.parseDouble(totalProducts);

        String totalShiping = navegador.findElement(By.id("total_shipping")).getText().replace("$","");
        double dTotalShiping = Double.parseDouble(totalShiping);

        double total = dTotalProducts + dTotalShiping;

        String valueTotal = navegador.findElement(By.id("total_price")).getText().replace("$","");
        double dValueTotal = Double.parseDouble(valueTotal);


        assertEquals(total,dValueTotal,total);

        navegador.findElement(By.cssSelector("p.payment_module a.bankwire")).click();
        navegador.findElement(By.cssSelector(".cart_navigation .button-medium")).click();


        String finalMessage = navegador.findElement(By.className("dark")).getText();
        finalMessage.contains("complete");

        navegador.quit();

    }





}
