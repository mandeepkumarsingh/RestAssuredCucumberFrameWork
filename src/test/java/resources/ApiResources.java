package resources;
/**
 * @author mandeep
 * */
public  enum  ApiResources {

	regiterapi("/v2/customers/register"),
	removeaccount("/me/index/removeAccount/"),
	authenticate("/v2/customers/authenticate"),
	addtocart("/v2/carts/"),
	addaddress("/v2/carts/shippingAddress"),
	orderpayment("/v2/orderpayment/");
	private  String resource;
	ApiResources(String resource){
		this.resource=resource;
	}

	public  String getResource(){
		return resource;
	}
}
