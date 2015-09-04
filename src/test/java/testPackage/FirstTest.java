package testPackage;
/**
 * Created by sagar on 8/31/15.
 */

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FirstTest {
    @Test
    public void firstTestMethod(){
        WebDriver driver= new FirefoxDriver();
        driver.get("http://www.google.com");
        Assert.assertTrue("Checking for the title", driver.getTitle().endsWith("le"));

        driver.close();
    }

    @Test
    public void testNavigation(){
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("http://compendiumdev.co.uk/selenium");
        Assert.assertTrue("Checking for title", driver.getTitle().contains("Selenium Simplified"));

        driver.close();

    }

    @Test
    public void interrogationBasicTest(){
        WebDriver driver= new FirefoxDriver();
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_web_page.html");
        Assert.assertEquals("Basic Web Page Title", driver.getTitle());
        Assert.assertEquals("http://www.compendiumdev.co.uk/selenium/basic_web_page.html", driver.getCurrentUrl());
        Assert.assertTrue(driver.getPageSource().contains("A paragraph of text"));
        driver.close();
    }

    @Test
    public void interrogationNextLevelTest(){
        WebDriver driver= new FirefoxDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
        WebElement element = driver.findElement(By.id("p13"));
        System.out.println(element.getText());
        Assert.assertEquals(element, driver.findElement(By.id("p13")));
    }

    @Test
    public void interrogation3Test(){
        WebDriver driver= new FirefoxDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
        List<WebElement> elements;
        elements= driver.findElements(By.tagName("div"));
        Assert.assertEquals(21, elements.size());
    }

    @Test
    public void xPathTest(){
        WebDriver driver= new FirefoxDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
         WebElement element;
        element = driver.findElement(By.xpath("//*[@id='p31']"));
        Assert.assertEquals("pName31", element.getAttribute("name"));

        element= driver.findElement(By.xpath("//*[contains(@name,'ulName1')]"));
        Assert.assertEquals("ul1",element.getAttribute("id"));
        driver.close();
    }

    @Test
    public void googleTest(){
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
        driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("asu");
        driver.findElement(By.xpath("//button[@value='Search']")).click();
//        driver.findElement(By.xpath("//a[@data-href='http://www.asu.edu/']")).click();
//        new WebDriverWait(driver,10).until(ExpectedConditions.titleContains("Arizona State University"));
    }
}
