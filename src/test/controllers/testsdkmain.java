package test.controllers;

import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import SNET.Core.APIContext;
import SecureNetRestApiSDK.Api.Controllers.PaymentsController;
import SecureNetRestApiSDK.Api.Models.Address;
import SecureNetRestApiSDK.Api.Models.Card;
import SecureNetRestApiSDK.Api.Models.Check;
import SecureNetRestApiSDK.Api.Models.DeveloperApplication;
import SecureNetRestApiSDK.Api.Models.ExtendedInformation;
import SecureNetRestApiSDK.Api.Requests.AuthorizeRequest;
import SecureNetRestApiSDK.Api.Requests.ChargeRequest;
import SecureNetRestApiSDK.Api.Responses.AuthorizeResponse;
import SecureNetRestApiSDK.Api.Responses.ChargeResponse;
import test.HelperTest;

public class testsdkmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testsdkmain gg=new testsdkmain();
		try {
			gg.creditcardnotpresentauthorizationOnlyrequestreturnssuccessfully();
			gg.creditcardnotpresentchargeauthorizationandcapturerequestreturnssuccessfully();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void main() {
		testsdkmain gg=new testsdkmain();
		try {
			gg.creditcardnotpresentauthorizationOnlyrequestreturnssuccessfully();
			gg.creditcardnotpresentchargeauthorizationandcapturerequestreturnssuccessfully();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	Properties config ;
	HelperTest helper;
	
	
	public void before() throws Exception{
		InputStream stream  = this.getClass().getResourceAsStream("/config.properties");
		config = new Properties();
		config.load(stream);
		helper = new HelperTest();
	}
	
	public void creditcardnotpresentauthorizationOnlyrequestreturnssuccessfully()
			throws Exception {
		
		InputStream stream  = this.getClass().getResourceAsStream("/config.properties");
		config = new Properties();
		config.load(stream);
		helper = new HelperTest();
		
		// Arrange
		AuthorizeRequest request = new AuthorizeRequest();
		request.setDeveloperApplication(getDeveloperApplication());
		request.setCard(getCard());
		request.setAddToVault(true);
		request.setAmount(20d);
		request.setExtendedInformation(getExtendedInformation());
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		AuthorizeResponse response = (AuthorizeResponse) controller.processRequest(apiContext, request,AuthorizeResponse.class);
		System.out.println("response====="+response);
		System.out.println("response====="+response.getResponseCode());

		// Assert
/*		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());*/
		System.out.println("response.getTransaction().getTransactionId()="+response.getTransaction().getTransactionId());
		//return response.getTransaction().getTransactionId();
	}

	/**
	 * Successful response returned from a Charge-Authorization and Capture
	 * request.
	 * https://apidocs.securenet.com/docs/creditcardnotpresent.html?lang=JSON#charge
	 */
	@Test
	@Ignore
	public void creditcardnotpresentchargeauthorizationandcapturerequestreturnssuccessfully()
			throws Exception {
		// Arrange
		ChargeRequest request = new ChargeRequest();
		request.setCard(getCard());
		request.setAddToVault(true);
		request.setAmount(100d);
		request.setDeveloperApplication(getDeveloperApplication());
		ExtendedInformation extendedInfo = getExtendedInformation();
		extendedInfo.setTypeOfGoods("PHYSICAL");
		request.setExtendedInformation(extendedInfo);
		APIContext apiContext = new APIContext();
		PaymentsController controller = new PaymentsController();
		// Act
		ChargeResponse response = (ChargeResponse) controller.processRequest(apiContext, request,ChargeResponse.class);
		System.out.println(">>>>>>>>>response="+response);
		System.out.println(">>>>>>>>>response="+response.getSuccess() +"response.toResponseString()="+response.toResponseString());
		// Assert
/*		Assert.assertTrue(response.toResponseString(), response.getSuccess());
		Assert.assertEquals(response.getTransaction().getSoftDescriptor(), helper.getResponseSoftDescriptor());
		Assert.assertEquals(response.getTransaction().getDynamicMCC(), helper.getResponseDynamicMCC());*/
	}



	private Address getAddress() {
		Address address = new Address();
		address.setCity("Austin");
		address.setCountry("US");
		address.setLine1("123 Main St.");
		address.setState("TX");
		address.setZip("78759");
		return address;
	}

	private DeveloperApplication getDeveloperApplication() {
		DeveloperApplication devApp = new DeveloperApplication();
		devApp.setDeveloperId(Integer.parseInt(config.getProperty("developerId")));
		devApp.setVersion(config.getProperty("versionId"));
		return devApp;
	}
	
	private Card getCard(){
		Card card = new Card();
		card.setAddress(getAddress());
		card.setCvv("123");              
		card.setExpirationDate("07/2018");
		card.setNumber("4111111111111111");
		return card;
	}
	
	private Check getCheck() {
		Check check = new Check();
		check.setFirstName("Bruce");
		check.setLastName("Wayne");
		check.setRoutingNumber("222371863");
		check.setAccountNumber("123456");
		return check;
	}

	private ExtendedInformation getExtendedInformation() {
		ExtendedInformation extendedInfo = new ExtendedInformation();
		extendedInfo.setSoftDescriptor(helper.getRequestSoftDescriptor());
		extendedInfo.setDynamicMCC(helper.getRequestDynamicMCC());
		return extendedInfo;
	}
       
	
}
