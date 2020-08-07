package Lombok;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author mandeep
 * 
 * */

@Data
@NoArgsConstructor
public class CLAddToCart {
	
private String productId;
private String quantity;
private CLAddToCart_Prescription prescription;

	

}
