
public class program_counter {
	static int PC=0;
	
	program_counter(int PC){
		this.PC=PC;
	}
	
	public static void setPC(int x) {
		PC = x;
	}
	
	public static int readPC() {
		return PC;
		
	}
	 public static void incPC() {
		 PC ++;
	 }
	public static void incPC(int x) {
		PC = PC + x;
	}
	
}
