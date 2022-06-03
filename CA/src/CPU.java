import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.Arrays;

public class CPU {
	
	static Object mainmemory [] = new Object [2048];
	static boolean jump_questionmark = false;
	static memory M = new memory(mainmemory);
	static int registers [] = new int [32];
	static gp_registers R =new gp_registers(registers);
	static int program_counter;
	static program_counter PC = new program_counter(program_counter);
	static boolean decoded = false;
	static boolean executed = false;
	static int [] instdec;
	static int []	register_result;
	static Integer	memory_result;
	static String fetched_instruction[] = new String [2];
	static int[] jumpsave = new int [2];
	static int no_of_inst =0;
	static boolean done = false;
	static boolean stopFetching = false;
	
	static int executing_cycles= 0;
	static int decoding_cycles= 0;

	
	
	
	
	public static void clear() {
		 int x = PC.readPC() - 1;
         PC.setPC(x);		
		 instdec = null;
		 register_result = null;
		 fetched_instruction[0] = "";
		 executing_cycles= 0;
		 decoding_cycles= 0;
		 stopFetching = false;
	 }
	 
	 
	//takes int in string form and returns a 5 bit string of its representation 
	public static String bin(String r) throws invalidInstruction {
		String res;
		if((r.length()!=2 && r.length()!=3)||r.charAt(0)!='R'||(r.charAt(1)=='0'&& r.length()>2)) {
			throw new invalidInstruction("Document Includes invalid Instruction");
		}
			try{
					int val = Integer.parseInt(r.substring(1));
					res = Integer.toBinaryString(val);
					while(res.length()!=5)
						res = "0" + res;
			}catch(NumberFormatException e){
				  
				  throw new invalidInstruction("Document Includes invalid Instruction");
				}
		
		return res;
	}
	public static String binfull(String r) {
		String res = "";
		while(res.length() + r.length()!= 32) {
			res = res + r.charAt(0);
		}
		res = res +r;
		return res;
	}
	
	
	//takes int and returns an 18 bit string of its representation 
	public static String bin(int val) {
		String res = Integer.toBinaryString(val);
		while(res.length()<32)
			res = "0" + res;
		return res.substring(14);
	}
	//takes int and returns an 28 bit string of its representation 
		public static String binthree(int val) {
			String res = Integer.toBinaryString(val);
			while(res.length()!=28)
				res = "0" + res;
			return res;
		}
	
	//takes int and returns a 14 bit string of its representation 
	public static String bintoo(int val) {
		String res = Integer.toBinaryString(val);
		while(res.length()!=14)
			res = "0" + res;
		return res;
	}
	
	
	//fetches String representation of the instruction from memory and increments the PC
	public static String fetch(int j) {
		int x = PC.readPC() + 1;
		
		PC.incPC();
		String s = "";
			if(memory.fetch(j) != "00000000000000000000000000000000") {
			System.out.println("Instruction "+ x +" Fetched:");
			System.out.println(memory.fetch(j));
			s = memory.fetch(j);
			}
		return s;
	}
	
	
	
	//returns array of numbers representing the opcode of decoded instruction
	public static int[] decode(String inst[]) {
		return decoder.decode(inst);
	}
	
	//returns value of result of executed ALU operations
	public static int [] execute(int[] instdec) {
		if ( instdec[0]== 10 || instdec[0]==11 ) {
			System.out.println("Instruction " + instdec [instdec.length-1]+ " has passed through the execution step");
			int [] result = new int [6]; 
			result [0] =  instdec [0];
			result [1] =  instdec [1];
			result [3] = instdec [2];
			result [4] = instdec [3];
			result [5] = instdec [instdec.length-1];
			return result;
		}else { 
		return instructionset.execute(instdec);
		} 
	}
	
	

	//returns value needed to be loaded, or the original value of the register that was supposed to be stored in memory or the inputed register_result
	public static int [] memory(int[] register_result) {
		System.out.println("Memory Access for Instruction " + register_result[5] + ":");
		if (register_result[0]==11||register_result[0]==10) {
			
			return instructionset.memory(register_result);	
		}else {
			System.out.println("No memory accesses required for Instruction " + register_result[5]);
			return register_result;
		}
		
		
	}
	
	
	//loads whatever is in the register_result into the destination Register
	public static void write_back(int[]register_result) {
		if(register_result[0]==11|| register_result[0] == 4 || register_result[0] == 7) {
			System.out.println("No Write Back needed for Instruction " + register_result[5]);
		}else {
			System.out.println("Write Back for Instruction " + register_result[5]);
			instructionset.write_back(register_result);
		}
		
	}
	
	
	// takes line of text and turns it into an instruction of 32 bit ones and zeros
	public static String turn_to_inst(String[] x) throws invalidInstruction{
		if((!(x.length>3)) && x[0]== "j") {
			throw new invalidInstruction("Document Includes invalid Instruction");
		}
		
		String instruction ="01";
		
		if(x[0].toLowerCase().equals("add")) {
			
			instruction = "0000" + bin(x[1]) + bin(x[2]) + bin(x[3]) + "0000000000000";
			
		}else if(x[0].toLowerCase().equals("sub")) {
			
			instruction = "0001" + bin(x[1]) + bin(x[2]) + bin(x[3]) + "0000000000000";
		}else if (x[0].toLowerCase().equals("muli")) {
			instruction = "0010" + bin(x[1]) + bin(x[2])+ bin(Integer.parseInt(x[3]));
		}else if(x[0].toLowerCase().equals("addi")) {
			instruction = "0011" + bin(x[1]) + bin(x[2])+ bin(Integer.parseInt(x[3]));
		}else if(x[0].toLowerCase().equals("bne")) {
			instruction = "0100" + bin(x[1]) + bin(x[2])+ bin(Integer.parseInt(x[3]));
		}else if(x[0].toLowerCase().equals("andi")) {
			instruction = "0101" + bin(x[1]) + bin(x[2])+ bin(Integer.parseInt(x[3]));
		}else if(x[0].toLowerCase().equals("ori")) {
			instruction = "0110" + bin(x[1]) + bin(x[2])+ bin(Integer.parseInt(x[3]));
		}else if(x[0].toLowerCase().equals("j")) {
			instruction = "0111" + binthree(Integer.parseInt(x[1]));
		}else if(x[0].toLowerCase().equals("sll")){
			instruction = "1000" + bin(x[1]) + bin(x[2])+ "0000" + bintoo(Integer.parseInt(x[3]));
		}else if (x[0].toLowerCase().equals("srl")) {
			instruction = "1001" + bin(x[1]) + bin(x[2])+ "0000" + bintoo(Integer.parseInt(x[3]));
		}else if (x[0].toLowerCase().equals("lw")) {
			instruction = "1010" + bin(x[1]) + bin(x[2])+ bin(Integer.parseInt(x[3]));
		}else if (x[0].toLowerCase().equals("sw")) {
			instruction = "1011" + bin(x[1]) + bin(x[2])+ bin(Integer.parseInt(x[3]));
		}else {
			throw new invalidInstruction("Document Includes invalid Instruction");
		}
		return instruction;
		
		
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, invalidInstruction {
		Arrays.fill(mainmemory,"00000000000000000000000000000000"); //fills memory with zeros to make it easier to work with and see
		//reads instruction file
		File instructions = new File("C:\\Users\\Omar\\CA\\CA\\Program 2");
		@SuppressWarnings("resource")
		Scanner fetch = new Scanner(instructions);
		
		//loads instruction into memory and gets total number of operations
		while(fetch.hasNext()) {
			String inst = fetch.nextLine();
			System.out.println(inst);
			String[] wow = inst.split(" ");
			
			String output = turn_to_inst(wow);
			
			M.load_instruction(no_of_inst, output);
			no_of_inst++;
			}
		
			int stay_in_loop = 7 + (no_of_inst - 1) * 2;
		int j = 1; // for now, j serves as the clock
		PC.setPC(0);
		
		int the_final_countdown = 0;
		fetched_instruction[0] = "";
		while(true) {
			
			
			System.out.println();
			System.out.println("Clock Cycle " +j);
			
			//Pay attention to what this writes, I would hate for you to understand this in any other way but right
			if(j % 2 == 1 && register_result != null) {
				write_back(register_result);
				register_result = null;
			}
			if(j % 2 == 1 && jumpsave[0]==1) {
				System.out.println("No Write Back Needed for Instruction " + jumpsave[1]);
				jumpsave[0]=0;
			}
			//I had a riddle for this one but I can't find it in my memory 
			if(j % 2 == 0 && register_result != null) {
				register_result = memory(register_result);
			}
			
			if(j % 2 == 0 && jumpsave[0]==1) {
				System.out.println("No Memory Access Needed for Instruction " + jumpsave[1]);
			}
			
			//riddle me this batman, you can probably gather from the name that this part is execute
			//but do you know which instruction would make it throw some instructions down a trash chute?
			
			int tempPC = PC.readPC();
			if(instdec!= null) {
				if(executing_cycles ==1) {
					register_result = execute(instdec);
					executing_cycles = 0;
					instdec = null;
				}else {
					System.out.println("Still executing instruction " + instdec[instdec.length-1]);
					executing_cycles++;
				}
			}
			//riddle me this batman, this part works for all instructions and not just load
			//just ask yourself, what is the thing we would want it to decode?
			
			if((fetched_instruction[0] != "")) {
				if(decoding_cycles == 1) {
					if(fetched_instruction[0]=="00000000000000000000000000000000") {
						stopFetching = true;
					}
					else {
						instdec = decode(fetched_instruction);
						decoding_cycles =0;
						fetched_instruction[0] = "";
					}
				}else {
					System.out.println("Still decoding instruction " + fetched_instruction[1]);
					decoding_cycles++;
				}
			}


			//riddle me this batman, this code is odd in more ways than one
			//but pay attention because this the start of the fun
			//why write clean code when I can give you a mop
			//just ask yourself, when would I want the fetching to stop?
			
			if (j%2==1 && fetched_instruction[0]=="" && !stopFetching) {
					if(jumpsave[0]==0) {
						fetched_instruction[1] = Integer.toString(PC.readPC() + 1);
						fetched_instruction[0] = fetch(PC.readPC());
					}else if(jumpsave[0]==1) {
						int they_shouldnt_leave_me_in_charge_of_writing_the_variable_names = tempPC + 1;
						System.out.println("Instruction No." + they_shouldnt_leave_me_in_charge_of_writing_the_variable_names + " fetched:");
						System.out.println(memory.read_instruction(tempPC));
					}
					
			}
			
			j++;
			
			//riddle me this batman, while this condition might seem dull
			//trust me, there's a reason to check for all those nulls
			//the jumpsave was not written in vain 
			//its purpose is to spare us a lot of pain
			//understand this part and the code won't disapoint
			//just answer this: why run the loop if there's no point?
			
			if(fetched_instruction[0]=="" && instdec == null && register_result==null&& jumpsave[0] == 0)
				break;
			
		}
		
		System.out.println("################################################### Simulation Terminated ###################################################");
		System.out.println();
		System.out.println();
		M.display();
		System.out.println();
		
		R.display();

//		String test = "j 85";
//		String [] Artest = test.split(" ");
//		System.out.println(Artest[0]+Artest[1]);
//		String testoutput = turn_to_inst(Artest);
//		System.out.println(testoutput);
		

		
	}
}
