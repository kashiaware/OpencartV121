package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {


	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("***** Starting  TC001_AccountRegistrationTest  *****");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Cliked on MyAccount Link ");
		
		hp.clickRegister();
		
		logger.info("Cliked on Register Link ");
		
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer details.... ");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");//randomely generate the email
		regpage.setTelephone( randomeNumber());
		
		
		String password= randomeAlphaNumber();
		
		regpage.setPassword( password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivatepolicy();
		regpage.clickOnContinueButton();
		
		logger.info("validating expected message ");
		String confmsg=regpage.getConfirmationmsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}
	catch(Exception e)	{
		logger.error("Test Failed..");
		logger.debug("Debug log");
		
	   }
		logger.info("***** Finished TC001_AccountRegistrationTest  *****");
	}

}

