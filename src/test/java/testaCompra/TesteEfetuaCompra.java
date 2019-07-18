package testaCompra;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TesteEfetuaCompra {
    @Test

    public void TestaAcesso(){

        System.setProperty("webdriver.chrome.driver", "/home/bruna/SeleniumJars/chromedriver");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);

        navegador.get("http://www.automationpractice.com");

        //add to cart
        navegador.findElement(By.linkText("Blouse")).click();
        navegador.findElement(By.id("add_to_cart")).click();
        navegador.findElement(By.linkText("Proceed to checkout")).click();

        String cart = navegador.findElement(By.id("summary_products_quantity")).getText();

        assertEquals("1 Product", cart);

        navegador.findElement(By.linkText("Proceed to checkout")).click();

        navegador.findElement(By.id("create-account_form")).findElement(By.id("email_create")).sendKeys("tulipa0007@hotmail.com");
        navegador.findElement(By.name("SubmitCreate")).click();

        //Preenche form
        navegador.findElement(By.id("id_gender2")).isSelected();
        navegador.findElement(By.id("account-creation_form")).findElement(By.id("customer_firstname")).sendKeys("Tulipa");
        navegador.findElement(By.id("account-creation_form")).findElement(By.id("customer_lastname")).sendKeys("Santos");
        navegador.findElement(By.id("account-creation_form")).findElement(By.id("passwd")).sendKeys("tulipinha0007");
        navegador.findElement(By.id("account-creation_form")).findElement(By.id("days")).sendKeys("12");
        navegador.findElement(By.id("account-creation_form")).findElement(By.id("months")).sendKeys("November");
        navegador.findElement(By.id("account-creation_form")).findElement(By.id("years")).sendKeys("1991");


        //navegador.quit();



    }





}
