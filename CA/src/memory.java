import java.util.Arrays;

public class memory {

	static Object mainmemory [] = new Object [2048];
	
	
	
	memory(Object c []) {
		mainmemory = c;
	}
	
	public static int read(int i) {
			return  Integer.parseInt(mainmemory[i].toString());
		
	}
	
	public static String read_instruction(int i) {
		return mainmemory[i].toString();
	
}
	
	public static void load_instruction (int i, String j) {
		if (i > 1024) {
			System.out.println("Unable to store data in the instructions section");
		}else {
			mainmemory[i] = j;

		}
		
	}
	public  void display() {
		System.out.println("The Contents of the Memory:");
		System.out.println();
		for (int i = 0; i < mainmemory.length; i++) {
			if(i==0) {
				System.out.println("Instruction Memory");
			}
			if(i==1024) {
				System.out.println("Data Memory:");
			}
			System.out.println("M["+i+"]: "+mainmemory[i].toString());
		}
	}
	
	public static void load (int i, int j) {
		if (i < 1024) {
			System.out.println("Unable to store data in the instructions section");
		}else {
			mainmemory[i] = j;
			
		}
		
	}
	public static void load (int i, String j) {
		if (i > 1024) {
			System.out.println("Unable to store data in the instructions section");
		}else {
			mainmemory[i] = j;

		}
		
	}
	
	public static String fetch(int i) {
		return read_instruction(i);
		
	}
	
	public static void main(String[] args) {
//	load_instruction(0,"00000000100010000110000000000000");
		System.out.println(mainmemory[2]);
	}
}
