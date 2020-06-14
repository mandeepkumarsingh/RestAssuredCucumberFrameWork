package resources;

import POJO.AddAddress;
import POJO.AddAddressData;
import POJO.AuthenticateUser;
import POJO.OrderPayment;
import POJO.OrderPaymentInfo;
import POJO.PowerProductCart;
import POJO.RegisterUser;

public class TestDataBinder {
	/** request Payload  for register User*/
	public static RegisterUser registerUserPayloads(String firstname,String lastName,String mobile){
		RegisterUser register=new RegisterUser();
		try{
			register.setFirstName(firstname);
			register.setLastName(lastName);
			register.setPassword("valyoo123");
			register.setMobile(mobile);
			register.setEmail(mobile+"@gmail.com");
			register.setReferalCode(null);
		}catch(Exception e){

		}
		return register;
	}
	public static AuthenticateUser authenticateUserPayload(String Username,String Password){
		AuthenticateUser auth=new AuthenticateUser();
		try{
			auth.setUsername(Username);
			auth.setPassword(Password);
		}catch(Exception e){

		}
		return auth;
	}
	public static PowerProductCart powerProductaddtoCartPayloads(String PowerType, String ProductId,String packageid){
		PowerProductCart powercart=new PowerProductCart();
		try{
			powercart.setPowerType(PowerType);
			powercart.setProductId(ProductId);
			powercart.setPackageId(packageid);
		}catch(Exception e){}
		return powercart;
	}
	public static AddAddress addAddressPayloads(String Email,String postcode,String phoneNo,String state){
		AddAddress addAddress= new AddAddress();
		try{
			AddAddressData data= new AddAddressData();
			data.setFirstName("Test");
			data.setLastName("User");
			data.setGender("Male");
			data.setPostcode(postcode);
			data.setAddressline1("Test wala address hai");
			data.setLandmark("testlandmark");
			data.setEmail(Email);
			data.setPhoneCode("+91");
			data.setPhone(phoneNo);
			data.setCountry("IN");
			data.setCity("faridabad");
			data.setState(state);
			addAddress.setAddress(data);
		}
		catch(Exception e){}
		return addAddress;
	}
	public static OrderPayment orderpaymentPayloads(String clientType){
		OrderPayment orderpayment=new OrderPayment();
		try{
			OrderPaymentInfo paymentinfo=new OrderPaymentInfo();
			paymentinfo.setPaymentMethod("cod");
			orderpayment.setDevice(clientType);
			orderpayment.setPaymentInfo(paymentinfo);
		}catch(Exception e){}
		return orderpayment;
	}
}
