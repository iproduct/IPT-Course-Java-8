package invoicing.model;

import java.io.InputStream;
import java.util.Scanner;

public class Company extends Contragent {
	private boolean vatRegistered;
	private String accountablePerson;
	private String bic;
	private String iban;
	
	public Company() {
	}

	public Company(long idNumber, String name, String address) {
		super(idNumber, name, address);
	}

	public Company(long idNumber, String name, String address, boolean organization) {
		super(idNumber, name, address, organization);
	}

	public Company(long idNumber, String name, String address, String phone, boolean organization) {
		super(idNumber, name, address, phone, organization);
	}

	public Company(long idNumber, String name, String address, String phone, boolean organization, 
			boolean vatRegistered, String accountablePerson, String bic, String iban) {
		super(idNumber, name, address, phone, organization);
		this.vatRegistered = vatRegistered;
		this.accountablePerson = accountablePerson;
		this.bic = bic;
		this.iban = iban;
	}

	public boolean isVatRegistered() {
		return vatRegistered;
	}

	public void setVatRegistered(boolean vatRegistered) {
		this.vatRegistered = vatRegistered;
	}

	public String getAccountablePerson() {
		return accountablePerson;
	}

	public void setAccountablePerson(String accountablePerson) {
		this.accountablePerson = accountablePerson;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company [vatRegistered=").append(vatRegistered).append(", accountablePerson=")
				.append(accountablePerson).append(", bic=").append(bic).append(", iban=").append(iban)
				.append(", isVatRegistered()=").append(isVatRegistered()).append(", getAccountablePerson()=")
				.append(getAccountablePerson()).append(", getBic()=").append(getBic()).append(", getIban()=")
				.append(getIban()).append("]");
		return builder.toString();
	}
	
	public void input(InputStream inStream) {
		Scanner in = new Scanner(inStream);
		String input;
		
		super.input(inStream);

		//is VAT registered
		System.out.println("Is VAT registered [yes OR no]: ");
		boolean valid = false;
		do {
			input = in.nextLine().trim().toLowerCase();		
			if ( input.matches("yes|no") ) {
			 	setVatRegistered(input.equals("yes"));
				valid = true;
			} else
				System.err.println("yes or no");
		} while (!valid);
		
		//input accountable person name
		System.out.println("Accountable Person Name: ");
		do {
			input = in.nextLine();
			if ( !input.isEmpty() )
			 	setName(input);
			else
				System.err.println("Name should not be empty.");
		} while (getName() == null);
		
		//input BIC
		System.out.println("BIC: ");
		do {
			input = in.nextLine();
			if (input.matches("(\\w{6}") )
			 	setIdNumber(Long.parseLong(input));
			else
				System.err.println("BIC should be 6 characters.");
		} while (getIdNumber() <= 0);
		
		//input IBAN
		System.out.println("IBAN: ");
		do {
			input = in.nextLine();
			if (input.matches("(\\w{10,16}") )
			 	setIdNumber(Long.parseLong(input));
			else
				System.err.println("IBAN should be 10 to 16 characters.");
		} while (getIdNumber() <= 0);
				
	}

}
