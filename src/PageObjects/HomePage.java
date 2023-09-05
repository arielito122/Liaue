package PageObjects;


import org.openqa.selenium.*;
import org.testcontainers.containers.wait.strategy.Wait;

import java.util.List;


public class HomePage extends BasePage {

    String city = "שדרות";
    By searchField = By.cssSelector("[class='multi-search-input-wrapper css-ixz1sj ealzp4l9']");

    By arrowElement = By.cssSelector("data-auto='bulletins-pagination-2'");

    By Title = By.cssSelector("[data-auto='primary_address_text']");




    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSearchField() throws InterruptedException {

          Thread.sleep(5000);
          waitForElement(searchField);
          WebElement searchField1 = driver.findElement(searchField);
          scrollToElement(searchField1);
          click(searchField);
        System.out.println("Hay");
      }

    public void insertValue() throws InterruptedException {
        Thread.sleep(5000);
        clickOnSearchField();
        By searchElementField = By.cssSelector("[data-auto='autocomplete-textfield']");
        WebElement searchElement = driver.findElement(searchElementField);
        searchElement.sendKeys(city);
        System.out.println("Hay2");
        By CityFieldDropDown =By.cssSelector("[data-auto='autocomplete-suggestion']");
        waitForElement(CityFieldDropDown);
        List<WebElement> list = driver.findElements(CityFieldDropDown);
        System.out.println("Option of places:  "  +  list.size());
        for(int i=0; i< list.size();i++){
            String name = list.get(i).getText();
            if (name.contains(city) && name.contains("עיר")){
                (list.get(i)).click();
                break;
            }
        }

    }

    By backArrow = By.cssSelector("[data-auto='back-button-text']");

    public void allPostSize() throws InterruptedException {
        List<WebElement> posts = driver.findElements(By.cssSelector(".universal-card-body-wrapper.css-79elbk.e1sx3tzs15"));//50
        System.out.println("Post size:"  +  posts.size());
        for (int i=0; i<=posts.size(); i++){
            WebElement post =  posts.get(i);
            clickElementWithJavaScript(post);
            blockAds();
            //getTitle();
            click(backArrow);
            Thread.sleep(5000);
            posts = driver.findElements(By.cssSelector(".universal-card-body-wrapper.css-79elbk.e1sx3tzs15"));//50

            if(i == posts.size()){
               clickNextPage();
            }
        }
    }
    public void blockAds(){
     try{  By add = By.cssSelector("[data-auto='modal-popup']");
        WebElement addElement = driver.findElement(add);
        if(addElement.isDisplayed()){
            By xButton = By.cssSelector("div [data-auto='modal-close-button']");
            click(xButton);}} catch (NoSuchElementException e){}

        }

     public void ignoreNewProject(){
        By newProject = By.cssSelector("[class='css-dh9ns9 e1sx3tzs5']");
        Waitviseblity(newProject);

     }


    public void clickNextPage(){
        waitForElement(arrowElement);
        click(arrowElement);

    }
    public void getTitle ()
    {

        waitForElement(Title); // Make sure this method correctly waits for the element
        WebElement element = driver.findElement(Title); // Assuming 'driver' is your WebDriver instance
        String title = element.getText();
        System.out.println(title);}

    }










