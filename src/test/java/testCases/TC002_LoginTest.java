package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("***** Starting  TC001_LoginTest *****");	
      try
      {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();


		//LoginPage

		LoginPage lp=new LoginPage(driver);
		lp.setEmailAdress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();

		//MyAccountPage

		MyAccountPage macc=new MyAccountPage(driver);
		boolean	targetPage=macc.isMyAccountPageExists();

   //Assert.assertEquals(targetPage, true,"Login failed");
		Assert.assertTrue(targetPage);
      }
      catch(Exception e)
      {
    	  Assert.fail();
      }
		logger.info("***** Finished TC001_LoginTest  *****");

	}


}
