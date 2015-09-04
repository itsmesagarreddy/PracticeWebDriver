package testPackage;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

/**
 * Created by sagar on 9/1/15.
 */
public class ManipulationExercise {

    static WebDriver driver;

    @BeforeClass
    public static void loadPage(){
        driver = new FirefoxDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    @Test
    public void submitFormTest(){
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Assert.assertThat(driver.getTitle(), is("Processed Form Details"));
    }

//    @Test
//    public void checkCommentsTest(){
//        WebElement element=  driver.findElement(By.name("comments"));
//        element.clear();
//        element.sendKeys("Entering my own comments");
//        driver.findElement(By.xpath("//input[@value='rd2']")).click();
//        driver.findElement(By.xpath("//input[@value='cb1']")).click();
//        driver.findElement(By.name("submitbutton")).submit();
//        Assert.assertTrue(driver.findElement(By.xpath("//ul/li[@id='_valuecomments']")).getText().equals("Entering my own comments"));
//    }

    @Test
    public void userInteractionTest() {
        Actions action = new Actions(driver);
        action.dragAndDrop(driver.findElement(By.id("draggable1")), driver.findElement(By.id("droppable1"))).perform();
        assertEquals("Dropped!", driver.findElement(By.id("droppable1")).getText());

        action.clickAndHold(driver.findElement(By.id("draggable2"))).moveToElement(driver.findElement(By.id("droppable1"))).release().perform();
        assertEquals("Get Off Me!", driver.findElement(By.id("droppable1")).getText());

        action.keyDown(Keys.CONTROL);
        action.keyDown(Keys.SHIFT);
        action.sendKeys("b");
        action.keyUp(Keys.SHIFT).keyUp(Keys.CONTROL);
        action.perform();
        assertEquals("Bwa! Ha! Ha!", driver.findElement(By.id("draggable1")).getText());

    }

    @Test
    public void testSynchronization(){

        Select category = new Select(driver.findElement(By.id("combo1")));
        //category.selectByValue("3");
        //new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("HTML Form Elements"));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("option[value='23']")));
        Select language = new Select(driver.findElement(By.id("combo2")));
        language.selectByValue("23");
        driver.findElement(By.cssSelector("input[name='submitbutton']")).submit();
       sampleMethod();
    }

    public void sampleMethod(){
        assertEquals("23", driver.findElement(By.id("_valuelanguage_id")).getText());
    }

    @Test
    public void inlineClassTest(){
        WebElement element1= driver.findElement(By.id("combo1"));
        element1.findElement(By.cssSelector("option[value='3']")).click();
        WebElement ele= new WebDriverWait(driver,10).until(new ExpectedCondition<WebElement>() {
            // @Override
            public WebElement apply(WebDriver webDriver) {
                return  selectJava();

            }

            public WebElement selectJava() {
                WebElement combo2 = driver.findElement(By.id("combo2"));
                return combo2.findElement(By.cssSelector("option[value='23']"));
            }

        });

        assertEquals("Checking Anonymous Class", "Java", ele.getText());
    }

    @Test
    public void AnonymousClassInPrivateMethod(){
        WebDriverWait wait = new WebDriverWait(driver,10);
      //  WebElement element = driver.findElement(By.cssSelector("option[value='3']"));
        assertEquals("Server",titleContainsMethod("Sagar"));
    }

    private ExpectedCondition<String> titleContainsMethod(final String title){
        return new ExpectedCondition<String>(){
            public String apply(WebDriver drive r){
                WebElement element= driver.findElement(By.cssSelector("option[value='3']"));
                if(element.getText().contains(title)){
                    System.out.println("Contains null");
                    return null;
                }
                else {
                    System.out.println(element.getText());
                    return element.getText();
                }
            }
        };
    }

    @AfterClass
    public static void closeBrowser(){
     driver.quit();

    }
}
