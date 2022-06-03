
public class instructionset {
	
	
	public static String bin(int r) {
		String res = Integer.toBinaryString(r);
		while(res.length()!=32)
			res = "0" + res;
		return res;
	}
	
	public static String bintoo(int r) {
		String res = Integer.toBinaryString(r);
		while(res.length()!=28)
			res = "0" + res;
		return res;
	}
	
	
	public static int [] execute(int [] inst) {      //returns value of executed ALU operation in an array like this [opcode, destination, value, R2, immediate, and riddle me this batman what else?] dont ask me to explian lol
		//System.out.println("Executing:");
		int [] result = new int [6];
		result [0] =  inst [0];
		result [1] =  inst [1];
		if (inst.length== 6) {
			System.out.println("Executing Instruction " + inst[5]);
			result [2] = perform_Rtype(inst[0],inst[1],inst[2],inst[3],inst[4]);
			result[5] = inst[5];
			return result;
		}else if (inst.length == 5) {
			System.out.println("Executing Instruction " + inst[4]);
			result [2] =  perform_Itype(inst[0],inst[1],inst[2],inst[3]);
			result [3] = inst[2];
			result [4] = inst[3];
			result[5] = inst[4];
			if(inst[0] == 4) {
				return null;
			}
			return result;
		}else if (inst.length == 3) {
			System.out.println("Executing Instruction " + inst[2]);
			result[5] = inst[2];
			CPU.jumpsave[0] = 1;
			CPU.jumpsave[1] = inst[2];
			perform_Jump(inst[1]);
			return null;
		}
		if(inst[0] == 4) {
			return null;
		}
		return result;
	}
	
	public static void executej() {        //does the jumping 
		
	}
	
	
	// sends appropriate inputs to ALU of RType operation returning the value of the result
	public static int perform_Rtype(int opcode, int R1, int R2, int R3, int shift_amount){  
		if (opcode == 0) {
			return ALU.add(R1, R2, R3);
		}else if (opcode == 1) {
			return ALU.sub(R1, R2, R3);
		}else if (opcode == 8) {
			return ALU.shift_left(R1, R2, shift_amount);
		}else if (opcode == 9) {
			return ALU.shift_right(R1, R2, shift_amount);
		}
		return 0;
		
		
	}
	
	

	// sends appropriate inputs to ALU of IType operation returning the value of the result, branches
	public static int perform_Itype(int opcode, int R1, int R2, int immediate) { 
	
		if (opcode == 2) {
			return ALU.muli(R1, R2, immediate);
		}else if(opcode == 3) {
			return ALU.addi(R1, R2, immediate);
		}else if (opcode == 4) {
			branch(R1,R2,immediate);
		}else if (opcode == 5) {
			return ALU.andi(R1, R2, immediate);
		}else if(opcode == 6) {
			return ALU.ori(R1, R2, immediate);
		}
//		
		return 0;
	}
	
	public static void branch(int R1, int R2, int immediate) {
		if(gp_registers.readregister(R1)!=gp_registers.readregister(R2)) {
			CPU.jumpsave[0] = 1;
			CPU.jumpsave[1] = program_counter.readPC() - 1;
			
			CPU.clear();
			int na = program_counter.readPC() + immediate;
			
			if(na > CPU.no_of_inst) {
				CPU.done = true;
			}
			program_counter.setPC(na);
			System.out.println("Contents of R" + R1 + " did not equal to R" + R2+ ", so the PC was incremented by " + immediate);
		}else {
			System.out.println("Contents of R" + R1 + " was equal to R" + R2 + ", so the PC stayed as is");
		}
	}
	
	public static void load_word(int R1, int R2, int immediate) { //loads contents of memory in register (obsolete)
		int src = gp_registers.readregister(R2);
		int address = src +immediate;
		gp_registers.loadregister(R1, memory.read(address));
		System.out.println("The contents of R" + R1+ " is now " + gp_registers.readregister(R1));
		
	}
	
	public static int load(int R1, int R2, int immediate) { //returns the value needed to be stored in the register
		System.out.println("Value retrieved from the memory");
		int src = gp_registers.readregister(R2);
		int address = src +immediate;
		return memory.read(address);
	}
	
	public static int store_word(int R1, int R2, int immediate) { //stores content of register in memory, also returns R1
		int src = gp_registers.readregister(R2);
		int address = src +immediate;
		memory.load(address, gp_registers.readregister(R1));
		System.out.println("The contents of memory[" + address+ "] is now " + memory.read(address));		
		return gp_registers.readregister(R1);
	}
	

	public static void write_back(int [] c) { // loads given value into Register R1
		System.out.println("Write Back:");
		gp_registers.loadregister(c [1], c[2]);
		System.out.println("The contents of R" + c[1]+ " is now " + gp_registers.readregister(c[1]));
	}
	
	
	// returns value that is needed to be put into the register or if its a store returns the value that was 
	// supposed to be stored after storing it
	public static int [] memory(int [] x) { 
		int y;
		int z;
		System.out.println("Memory:");
		if(x[0]==10) {
			y =  load(x[1],x[3],x[4]);
			x[2] = y;
			return x;
		}else  {
			z =  store_word(x[1],x[3],x[4]);
			x[1] = z;
			return x;
		}
	}

	
	//Jump
	public static void perform_Jump(int address) {
		CPU.clear();
		String PC = bin(program_counter.readPC()-1);
		String new_address = PC.substring(0, 4)+ bintoo(address);
		System.out.println("Jumping to address: " + new_address +" (" + address + ")");
		int na = Integer.parseInt(new_address,2);
		program_counter.setPC(na);
		
	}
	
	
}
