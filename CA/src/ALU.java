
public class ALU {
	
	
	public static int add(int R1, int R2, int R3) { //self explanatory 
	 int result = gp_registers.readregister(R2) + gp_registers.readregister(R3);
	 //gp_registers.loadregister(R1, result);
	 //System.out.println("The contents of R" + R1 + " are now " + gp_registers.readregister(R1));
	 System.out.println("The contents of R"+R2+"(" + gp_registers.readregister(R2) +")" + " and R"+R3+ "(" + gp_registers.readregister(R3) +")" + " have been added"
	 +";The result is: " + result);
	return result;
	}
	
	public static int addi(int R1, int R2, int immediate) { //self explanatory 
		int result = gp_registers.readregister(R2) + immediate;
		// gp_registers.loadregister(R1, result);
		// System.out.println("The contents of R" + R1 + " are now " + gp_registers.readregister(R1));
		System.out.println("The contents of R"+R2+ "(" + gp_registers.readregister(R2) +")" +" and the value "+immediate +" have been added" +";The result is: " + result);
		return result;
		
	}
	
	public static int sub(int R1, int R2, int R3) { //self explanatory 
		int result = gp_registers.readregister(R2) - gp_registers.readregister(R3);
		 //gp_registers.loadregister(R1, result);
		// System.out.println("The contents of R" + R1 + " are now " + gp_registers.readregister(R1));
		System.out.println("The contents of R"+R3 + "(" + gp_registers.readregister(R3) +")" +" has been subtracted from R"+R2+"(" + gp_registers.readregister(R2) +")" +";The result is: " + result);
		return result;
	}
	
	public static int muli(int R1, int R2, int immediate) { //self explanatory 
		int result = gp_registers.readregister(R2) * immediate;
		 //gp_registers.loadregister(R1, result);
		 //System.out.println("The contents of R" + R1 + " are now " + gp_registers.readregister(R1));
		System.out.println("The contents of R"+R2+"(" + gp_registers.readregister(R2) +")"+" and the value "+immediate +" have been multiplied"+";The result is: " + result);
		return result;
	}
	
	public static int andi(int R1, int R2, int immediate) { //self explanatory 
		int result = gp_registers.readregister(R2) & immediate;
		// gp_registers.loadregister(R1, result);
		// System.out.println("The contents of R" + R1 + " are now " + gp_registers.readregister(R1));
		System.out.println("The contents of R"+R2 +"(" + gp_registers.readregister(R2) +")"+" and the value "+immediate +" have been Bit-wise ANDed" +";The result is: " + result);
		return result;
	}
	
	public static int ori(int R1, int R2, int immediate) { //self explanatory 
		int result = gp_registers.readregister(R2) | immediate;
		 //gp_registers.loadregister(R1, result);
		// System.out.println("The contents of R" + R1 + " are now " + gp_registers.readregister(R1));
		System.out.println("The contents of R"+R2 +"(" + gp_registers.readregister(R2) +")"+" and the value "+immediate +" have been Bit-wise ORed" +";The result is: " + result);
		return result;
	}
	
	public static int shift_left(int R1, int R2, int shift_amount) { //self explanatory 
		int artoo = gp_registers.readregister(R2);
		int result = artoo << shift_amount;
		//gp_registers.loadregister(R1, result);
		//System.out.println("The contents of R" + R1 + " are now " + gp_registers.readregister(R1));
		System.out.println("The value in R"+R2 +"(" + gp_registers.readregister(R2) +")"+" has been shifted left by "+shift_amount +";The result is: " + result);
		return result;
	}
	
	public static int shift_right(int R1, int R2, int shift_amount) { //self explanatory 
		int artoo = gp_registers.readregister(R2);
		int result = artoo >>> shift_amount;
		//gp_registers.loadregister(R1, result);
		//System.out.println("The contents of R" + R1 + " are now " + gp_registers.readregister(R1));
		System.out.println("The value in R"+R2 +"(" + gp_registers.readregister(R2) +")"+" has been shifted right by "+shift_amount +";The result is: " + result);
		return result;
	}
	
	public static void main(String[] args) {
		int test = -5;
		System.out.println(test >>> 1);
		
		
		Integer.parseInt("10100011011000000000000000000101", 2);
	}
}
