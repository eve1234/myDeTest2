package com.deko.testing.robot.backoffice;

import com.deko.testing.robot.BaseRobot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BackofficeRobot extends BaseRobot {

    public BackofficeRobot(WebDriver driver) { super(driver);}

    @FindBy(name = "LoginForm")
    private WebElement loginForm;

    @FindBy(xpath = "//html/body/div[1]/div/div/div/div/form/div[2]/input")
    private WebElement usernameField;

    @FindBy(xpath = "//html/body/div/div/div/div/div/form/div[3]/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class = \"btn btn-link forgotten-link\"]")
    private WebElement forgotPasswordLink;

    @FindBy(xpath ="//*[@class='btn btn-sm btn-primary']")
    		//+ "button[data-ng-class=/'{'btn-loader disabled' : LoadingLogin}/']")
    		//+ "//*[@id='ng-app']/div/div/div/div/div/form/div[6]/button")
    		//+ "//*[@class='\btn btn-sm btn-primary']")
    		//"//*[@id='ng-app/']/div[1]/div/div/div/div/form/div[6]/button")
    private WebElement signInButton;

    @FindBy(xpath ="//p[text()='Sorry, the details you provided were incorrect.']")
    		//"/html/body/div[1]/div/div/div/div/form/div[1]/div/div/p")
    public WebElement signInError;
    
    @FindBy(xpath ="//*[@id=\'top-bar\']/div[1]/div[1]/div/div/a/span[1]")
    private WebElement accountName;
    
    @FindBy(xpath="//*[@class='ng-binding']")
    private WebElement dashboardName;

    //todo: locate this webelement which will need to be used in later methods and tests.
    @FindBy(xpath ="//*[@id=\'ng-app\']/div/div/div/div/form/div[2]/input")
    private WebElement resetPasswordField;

    //todo: locate this webelement which will need to be used in later methods and tests.
    @FindBy(xpath="//*[@id=\'ng-app\']/div/div/div/div/form/div[3]/div/button[2]/span")
    private WebElement resetButton;

    //todo: locate this webelement which will need to be used in later methods and tests.
    @FindBy(xpath="//*[@id=/'ng-app/']/div/div/div/div/form/div[2]/div/button/span")
    private WebElement resetSignInButton;

    //todo: locate this webelement which will need to be used in later methods and tests.
    //It seems this element can only be done when an email is received.
    //For now I will makeup an element which would not work.
    @FindBy(xpath="//*id='theresetSuccessTest'")
    private WebElement resetSuccessText;

    @FindBy(id = "top-bar")
    private WebElement backOfficeDashboardTopBar;
    
   // a[data-ng-click='logout()']
    
   

    private final String baseUrl = "https://release.dekopay.org/backoffice/v2/#/"; //insert provided test url here

    public BackofficeRobot openBackofficeLoginPage(){
        goTo(baseUrl);
        wait.until(ExpectedConditions.visibilityOf(this.loginForm));
        return this;
    }

    public BackofficeRobot fillLoginUsername(String username){
        type(usernameField, username);
        return this;
    }

    public BackofficeRobot fillLoginPassword(String password){
        type(passwordField, password);
        return this;
    }

    public BackofficeRobot submitLoginForm() {
        click(signInButton);
       // waitUntilNotVisible(signInButton);
        return this;
    }

    public BackofficeRobot clickForgottenPasswordLink(){
        click(forgotPasswordLink);
        waitUntilURLContains("reset");
        return this;
    }

    public BackofficeRobot resetPassword(){
        //todo: Complete this method
    	click(resetButton);
    	waitUntilURLContains("reset");
        return this;
    }

    public boolean verifySignInError(String text){
        //todo: Complete this method, so that tests can pass in expected error text
       
    	wait.until(ExpectedConditions.visibilityOf(signInError));
    	
    	String actualSignInErrorReturned=signInError.getText();     //.toString();
    	
    	System.out.println(actualSignInErrorReturned);
    	System.out.println(text);
    	if (actualSignInErrorReturned==text) {
    			//"Sorry, the details you provided were incorrect.") {
    		return true;	
    	}
    	return false;	  		
    }

    public boolean verifyBackofficeUrl(){
        if (verifyURLContains("backoffice")){
            return true;
        }
        return false;
    }

    public boolean verifySuccessfulLogin(){
        //todo: Complete this verify method, to be used by test class
    	wait.until(ExpectedConditions.visibilityOf(dashboardName));
    	//switchToDefaultFrame();
    	String accountLoginName =accountName.getText();
    	String myDashboardName =dashboardName.getText(); // .toString();
    	System.out.println(accountLoginName);
    	System.out.println("The dashboard name is " + myDashboardName);
    	String currentPageTitle =driver.getTitle();
    	
    	if (currentPageTitle.contains("BackOffice")) {
    	//(myDashboardName.contains("Yvonne's")) {
    		return true;
    		}
        return false;
        
    }

    public boolean verifyResetPasswordSuccess(){
        //todo: Complete this verify method, to be used by test class
    	
    	String resetSuccessMessage=resetSuccessText.getText();
    	
    	if(resetSuccessMessage=="you have successfully reset your password") {
    		return true;
    	}
        return false;
    }
    
    public void clearUsernameAndPasswordfields() {
    	clear(usernameField, "//html/body/div[1]/div/div/div/div/form/div[2]/input");
    	clear(passwordField, "//html/body/div/div/div/div/div/form/div[3]/input");
    }
    
    public void waitUntillSiginButtonIsClickable() {
    	waitUntilClickable(signInButton);
    	
    }
}
