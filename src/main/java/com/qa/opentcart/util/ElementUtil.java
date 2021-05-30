package com.qa.opentcart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver){
		this.driver=driver;
		
	}
	
	public WebElement getElement(By locator){
		return driver.findElement(locator);
	}
	
	public List<WebElement> getElementList(By locator){
		return driver.findElements(locator);
	}
	
	public void doClick(By locator){
		driver.findElement(locator).click();
	}
	
	public String doGetText(By locator){
		return getElement(locator).getText();
	}
	
	public int getElementsCount(By locator){
		int count = getElementList(locator).size();
		return count;
		
	}
	
	public String getPageTitle(){
		return driver.getTitle();
	}
	
	public void doSendKeys(By locator, String value){
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	public void doActionsSendKeys(By locator, String value){
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator),value).build().perform();
	}
	
	public void doActionsClick(By locator){
		Actions action = new Actions(driver);
		action.click(getElement(locator)).perform();;
	}
	
	public void doSendKeysWithMoveToElement(By locator, String value){
		Actions action  = new Actions(driver);
		action.moveToElement(getElement(locator)).sendKeys(value).build().perform();
		
	}
	
	public void doClickWithMoveToElement(By locator){
		Actions  action = new Actions(driver);
		action.moveToElement(getElement(locator)).click().build().perform();
	}
	
	public boolean doIsDisplayed(By locator){
		return getElement(locator).isDisplayed();
	}
	
	public List<String> getAttributeList(String tagName, String attributeName){
		List<String> arrList  = new ArrayList<String>();
		List<WebElement> elementList =driver.findElements(By.tagName(tagName));
		for(WebElement ele : elementList){
			String text  = ele.getAttribute(attributeName);
			arrList.add(text);
		}
		return arrList;
		
	}
	
	
	//**********************DROP DOWN UTILS*******************
	
	public void doSelectDropDownByVisibleText(By locator, String text){
		Select select  = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void doSelectDropDownByIndex(By locator, int index){
		Select select  = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doSelectDropDownByValue(By locator, String value){
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public void selectDropDownValueWithoutSelectClass(By locator, String value){
		
		List<WebElement> optionList =  getElementList(locator);
		for(WebElement ele : optionList){
			String text=ele.getText();
			if(text.equals(value)){
				ele.click();
				break;
			}
		}
	}
	
	//*************************WAIT UTILS******************************
	public List<WebElement> visibilityOfAllElements(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}
	
	public void getPageLinksText(By locator, int timeOut){
		visibilityOfAllElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}
	
	public int getPageLinksCount(By locator, int timeOut){
		return visibilityOfAllElements(locator, timeOut).size();
	}
	
	public String waitForTitlePresent(String titleValue,int timeOut, int intervalTime){
		WebDriverWait wait  = new WebDriverWait(driver, timeOut,intervalTime);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
		
	}
	
	public Alert waitForAlertToPresent(int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public boolean waitForUrl(String urlValue, int timeOut){
		WebDriverWait wait  =  new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(urlValue));
	}
	
	public WebElement waitForElementToBeLocated(By locator, int timeOut){
		WebDriverWait wait  = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementToBeVisible(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	
	public void clickWhenReady(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * This is custom dynamic wait to find the webelement
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement retryingElement(By locator) {

		WebElement element = null;
		int attempts = 0;

		while (attempts < 30) {

			try {
				element = driver.findElement(locator);
				break;
			}

			catch (StaleElementReferenceException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {

				}
			}

			catch (NoSuchElementException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {

				}

				System.out.println("element is not found in attempt : " + (attempts + 1));
			}

			attempts++;
		}

		return element;

	}
	

}
