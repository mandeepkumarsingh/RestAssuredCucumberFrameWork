package Lombok;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CLAddToCart_Prescription {
	 private String dob;
	 private String gender;
	 private String notes;
	 private String userName;
	 private String powerType;	
	 private CLAddToCart_Prescription_Left left;
	 private CLAddToCart_Prescription_Right right;
	 

}
