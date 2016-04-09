
public class Register {
	public static final int NUM_PLACES  =100;
	private boolean[] soldPlaces;
	private static Register theRegister = new Register();
	private Register() {
		soldPlaces = new boolean[NUM_PLACES];
	}
	public boolean sellTicket(int placeNumber) {
		if(!soldPlaces[placeNumber]){
			soldPlaces[placeNumber] = true;
			return true;
		}
		return false;
	}
	public static Register getInstance(){
		return theRegister;
	}

}
