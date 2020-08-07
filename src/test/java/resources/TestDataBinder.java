package resources;

import Lombok.CLAddToCart;
import Lombok.CLAddToCart_Prescription;
import Lombok.CLAddToCart_Prescription_Left;
import Lombok.CLAddToCart_Prescription_Right;
import POJO.AddAddress;
import POJO.AddAddressData;
import POJO.AuthenticateUser;
import POJO.OrderPayment;
import POJO.OrderPaymentInfo;
import POJO.PowerProductCart;
import POJO.RegisterUser;


/**
 * @author mandeep
 * 
 * 
 * */
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
	public static CLAddToCart ContactLensAddToCartPayloads
	(String qty,String productId,String userName,String powerType,String left_box,String left_sph,String right_box,String right_sph) {
		CLAddToCart cart=new CLAddToCart();
		cart.setQuantity(qty);
		cart.setProductId(productId);
		CLAddToCart_Prescription prescription=new CLAddToCart_Prescription();
		prescription.setUserName(userName);
		prescription.setPowerType(powerType);
		CLAddToCart_Prescription_Left left=new CLAddToCart_Prescription_Left();
		left.setBoxes(left_box);
		left.setSph(left_sph);
		prescription.setLeft(left);
		CLAddToCart_Prescription_Right right=new CLAddToCart_Prescription_Right();
		right.setBoxes(right_box);
		right.setSph(right_sph);
		prescription.setRight(right);
		cart.setPrescription(prescription);
		return cart;		
	}
}
