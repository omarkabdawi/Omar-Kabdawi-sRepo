
public class gp_registers {
	static int registers [] = new int [32];
	
	gp_registers(int c []) {
		registers = c;
	} 
	public static int readregister(int i) {
		
		
		if(i < 32 && i != 0) {
			return registers[i]; // 31 general purpose registers 
		}else if(i == 0) {
			return 0 ; //zero register
		}else {
			System.out.println("This register does not exist");
			return 0; 
		}
		
		
	}
	
	public static void loadregister(int i, int j) {
		registers[i] = j;
	}
		
	
	public static void main(String[] args) {
		registers[0]= 0;	

	}
	
	public  void display() {
		System.out.println("The contents of the Registers: ");
		System.out.println();
		for (int i = 0; i < 32; i++) {
			if(i == 0) {
				System.out.println("Register "+i+": "+readregister(i)+ " (Hard wired)");
				continue;
			}
			System.out.println("Register "+i+": "+readregister(i));
		}
		
		System.out.println("Program Counter (PC): " + program_counter.readPC());
	}

}
