
public class SellPoint {
	private Register register;
	public SellPoint(){
		register = Register.getInstance();
	}
	public Register getRegister() {
		return register;
	}

	public static void main(String[] args) {
		//Sell Point 1
		SellPoint sellPoint1 = new SellPoint();
		//Sell Point 2
		SellPoint sellPoint2 = new SellPoint();
		
		//are there two registers?
		System.out.println("Same registers = " + (
				sellPoint1.getRegister() == sellPoint2.getRegister()));
		
		
	}

}
