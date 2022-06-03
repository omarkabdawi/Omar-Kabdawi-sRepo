import java.util.Arrays;

public class decoder {
	
	//riddle me this batman, without me things are only positive
	//but with me things change, even though I only add compliments
	public static String binfull(String r) {
		String res = "";
		while(res.length() + r.length()!= 32) {
			res = res + r.charAt(0);
		}
		res = res +r;
		return res;
	}
	
 
	//riddle me this, batman, why am I putting s[1] in result
	//decodes 32 bit string and returns array representing it 
	public static int [] decode(String s[]) {
		System.out.println("Decoding Instruction " + s[1]); 
		if(s[0] == "01") {
			return new int[10];
		}
		
		int opcode = Integer.parseInt(s[0].substring(0, 4),2);
		
		if (opcode == 0 || opcode == 1 || opcode == 8 || opcode == 9) { // check if it is R type 
			int R1 = Integer.parseInt(s[0].substring(4, 9),2);
			int R2 = Integer.parseInt(s[0].substring(9, 14),2);
			int R3 = Integer.parseInt(s[0].substring(14, 19),2);
			int shift_amount = Integer.parseInt(s[0].substring(19, 32),2);
			System.out.println("The instruction has been decoded to: " + opcode +"/"+ R1 +"/"+ R2 +"/"+ R3 +"/"+ shift_amount);
			int[] result = new int[6];
			result[0]=opcode; result[1]=R1; result[2]=R2; result[3]=R3; result[4]=shift_amount; result[5] = Integer. parseInt(s[1]);
			return result;
			//	instructionset.perform_Rtype(opcode,R1,R2,R3,shift_amount);
		}else if (opcode == 2 || opcode == 3 || opcode == 4 || opcode == 5 || opcode == 6 || opcode == 10 || opcode == 11) { // check if it is I type
			int R1 = Integer.parseInt(s[0].substring(4, 9),2);
			int R2 = Integer.parseInt(s[0].substring(9, 14),2);
			int immediate = Integer.parseUnsignedInt(binfull(s[0].substring(14, 32)),2);
			System.out.println("The instruction has been decoded to: " + opcode +"/"+ R1 +"/"+ R2 +"/"+ immediate);
			int[] result = new int[5];
			result[0]=opcode; result[1]=R1; result[2]=R2; result[3]=immediate;result[4] = Integer. parseInt(s[1]);
			return result;
			
			//instructionset.perform_Itype(opcode,R1,R2,immediate);
			
			
		} else if (opcode == 7){ // check if it is J Type 
			int Address =  Integer.parseInt(s[0].substring(4, 32),2);
			int[] result = new int[3];
			result[0]=opcode; result[1]=Address; result[2] = Integer. parseInt(s[1]);
			System.out.println("The instruction has been decoded to: " + opcode +"/"+ Address );
			return result;
			//instructionset.perform_Jump(opcode,Address);
		}else {
			System.out.println("INVALID OPCODE!!");
		}
		return null;
		
		
		
		
	}

	public static void main(String[] args) {
		
		System.out.println(gp_registers.readregister(1));
	}







	
	
}
