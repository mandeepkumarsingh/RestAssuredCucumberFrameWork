package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import Lombok.CLAddToCart;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author mandeep
 * */

public class Utils {
	public static Logger logger ;
	public static FileWriter writer;
	public static Logger getlogger(){
		if(logger==null){
			DOMConfigurator.configure("./log4j2.xml");  
			logger = LogManager.getLogger(Utils.class.getName());
		}
		return logger;
	}

	public static RequestSpecification req;

	public static RequestSpecification requestSpecification(){
		//		if(req==null){
		try{

			PrintStream printstream= new PrintStream(new FileOutputStream("loging.txt",true));
			HashMap<String, String>headers=new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			//				headers.put("X-Session-Token", session());
			headers.put("x-api-client",getGlobalValue("ClientType") );
			req=new RequestSpecBuilder().setBaseUri(getGlobalValue("BaseUrl")).addHeaders(headers)
					.addFilter(RequestLoggingFilter.logRequestTo(printstream))
					.addFilter(ResponseLoggingFilter.logResponseTo(printstream)).build();
		}catch(Exception e){
			getlogger().info("Exception occured while creating spec request builder "+e);


		}
		//		}
		return req;
	}
	public static String session(){
		String sessionid = null;
		try{
			RestAssured.baseURI=getGlobalValue("BaseUrl");
			Response res=RestAssured.given().when().post("/v2/sessions").
					then().assertThat().statusCode(200).extract().response();
			String response=res.asString();
			JsonPath jpath=new JsonPath(response);
			sessionid=jpath.get("result.id");
			getlogger().info("Guest session created "+sessionid);
		}catch(Exception e){
			getlogger().error("Exception occured while generating session "+e);	
		}


		return sessionid;

	}

	public static String getGlobalValue(String key){
		String value = null;
		try{
			String Path=System.getProperty("user.dir")+"/src/test/java/resources/global.properties";
			FileInputStream file=new FileInputStream(Path);
			Properties prop=new Properties();
			prop.load(file);
			value=prop.get(key).toString();
			getlogger().info("Properties file loaded sucessfully");
		}catch(Exception e){
			getlogger().error("Exception Occured while loading the Properties file"+e);
		}
		return  value;
	}
	public static String  getResponseValue(Response respone,String key){
		String val=respone.asString();
		JsonPath jpath =new JsonPath(val);
		String value=jpath.get(key).toString();
		return value;
	}
	public static String base64Code(String data){
		String base64=Base64.getEncoder().encodeToString(data.getBytes());
		return base64;
	}

	public static String userSession(String Userid,String Password){
		String token = null;
		try{
			ApiResources apiresource=ApiResources.valueOf("authenticate".toLowerCase());
			RestAssured.baseURI=getGlobalValue("BaseUrl");
			RequestSpecification reqest=RestAssured.given().request().spec(requestSpecification())
					.header("X-Session-Token", session()).body(TestDataBinder.authenticateUserPayload(Userid,Password));
			Response respon=reqest.when().post(apiresource.getResource()).then().assertThat().statusCode(200).extract().response();
			getlogger().info("authenticate Api Executed for user "+Userid+" with Status code "+respon.statusCode());
			token=getResponseValue(respon, "result.token");
			getlogger().info("user seesion created successfully "+token);
		}catch(Exception e){
			getlogger().error("Exception occured while getting  user session "+e);

		}
		return token;
	}
	public static int  addPowerProductToCart(String PowerType,String ProductId,String packageId,String SessionId){
		int statuscode = 0;
		try{
			ApiResources apiresource=ApiResources.valueOf("addtocart");
			RequestSpecification reqest=RestAssured.given().spec(requestSpecification()).header("x-session-token",SessionId).
					body(TestDataBinder.powerProductaddtoCartPayloads(PowerType, ProductId, packageId));
			Response res=reqest.when().post(apiresource.getResource()).then().extract().response();
			String var=res.asString();
			getlogger().info("Add To cart executed for product id "+ProductId+" with status code :" +res.getStatusCode()+"response :-"+var);
			statuscode=res.getStatusCode();
		}catch(Exception e){
			getlogger().error("Exception occured while adding product into cart "+e);
		}
		return statuscode;
	}
	
	public static int  addCLProductToCart
	(String productId , String qty,String userName,String powerType,String left_box,String left_sph,String right_box,String right_sph,String SessionId){
		int statuscode = 0;
		try{
			ApiResources apiresource=ApiResources.valueOf("addtocart");
			RequestSpecification reqest=RestAssured.given().spec(requestSpecification()).header("x-session-token",SessionId).
					body(TestDataBinder.ContactLensAddToCartPayloads(qty, productId, userName, powerType, left_box, left_sph, right_box, right_sph));
			Response res=reqest.when().post(apiresource.getResource()).then().extract().response();
			String var=res.asString();
			getlogger().info("Add To cart executed for product id "+productId+" with status code :" +res.getStatusCode()+"response :-"+var);
			statuscode=res.getStatusCode();
		}catch(Exception e){
			getlogger().error("Exception occured while adding product into cart "+e);
		}
		return statuscode;
	}
	public static int addAddress(String Email,String postcode,String phoneNo,String state,String SessionId ){
		int statuscode = 0;
		try{
			ApiResources apiresource=ApiResources.valueOf("addaddress");
			RequestSpecification request= RestAssured.given().spec(requestSpecification()).header("x-session-token",SessionId)
					.body(TestDataBinder.addAddressPayloads(Email, postcode, phoneNo, state));
			Response response=request.when().post(apiresource.getResource()).then().assertThat()
					.statusCode(200).extract().response();
			String result=response.asString();
			getlogger().info("Address add api for email  "+Email+" executed with status code :" +response.getStatusCode()+"result"+result);
			statuscode=response.statusCode();
		}catch(Exception e){
			getlogger().error("exception occred while adding address"+e);
		}
		return statuscode;
	}
	public static void writeInFile( String data){
		try{
			writer=new FileWriter("Oders.txt",true);
			writer.write("\r\n");
			writer.write(data);
			writer.close();
			getlogger().info("writen sucessfully in logger file");
		}
		catch(Exception e){
			getlogger().error("Exception occur while writing in file,Exception "+e);
		}

	}

	public static void main(String args[]){
		//		System.out.println(base64Code("Mandeep"));\
	    //  	String s=userSession("lenskart.test52@gmail.com","valyoo123");
		//		System.out.println(s);
		//		addPowerProductToCart("single_vision","129078","59c24834e4b09bd3c4c8af50",s);
		//		addAddress("lenskart.test52@gmail.com","121003","9999999999","Haryana",s);
		//		writeInFile("Mandeep");
		//		writeInFile("Kumar");
		//     addCLProductToCart("38726","2","df","CONTACT_LENS","1","-0.50","1","-0.50",s);

	}
}
