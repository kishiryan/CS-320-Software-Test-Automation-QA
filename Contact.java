package contactServicePackage;

public class Contact {
	// Define included fields from the rubric 
	private final String uniqueContactID;
	private String firstName;
	private String lastName;
	private String Number;
	private String Address;

	public Contact(String uniqueContactID, String firstName, String lastName, String Number, String Address) {
		
		// Check that input parameters meet requirements before assigning values
		checkUniqueContactID(uniqueContactID);
		checkFirstName(firstName);
		checkLastName(lastName);
		checkNumber(Number);
		checkAddress(Address);
		
		// Assign values to fields after validation check
		this.uniqueContactID = uniqueContactID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.Number = Number;
		this.Address = Address;
	}
	// Include methods for each field (Getters and Setters)
	// Getters for each field to retrieve values.
	
	public String getUniqueContactID() {
		return uniqueContactID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getNumber() {
		return Number;
	}
	
	public String getAddress() {
		return Address;
	}
	
	// Check validation in setters to make sure that any updates also meet requirements.
	// Then update valid field if the check is passed.
	// Note: uniqueContactID has no setter: it is defined as "not updatable" in the requirements once it exists.
	public void setFirstName(String firstName) {
		checkFirstName(firstName);
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		checkLastName(lastName);
		this.lastName = lastName;
	}
	public void setNumber(String Number) {
		checkNumber(Number);
		this.Number = Number;
		
	}
	public void setAddress(String Address) {
		checkAddress(Address);
		this.Address = Address;
	}
	
	// Private helpers which check inputs against the rubric requirements.
	// If the check fails, it will throw an IlligalArgumentException describing the failure.
	private static void checkUniqueContactID(String uniqueContactID) {
		// i. The contact object shall have a required unique contact ID string that cannot be longer than 10 characters. The contact ID shall not be null and shall not be updatable.
		if (uniqueContactID == null || uniqueContactID.isEmpty() || uniqueContactID.length() > 10) {
			throw new IllegalArgumentException("Unique Contact ID shall not be null, empty, and must be no longer than 10 characters.");
		}
	}
	
	private static void checkFirstName(String firstName) {
		// ii. The contact object shall have a required firstName string field that cannot be longer than 10 characters. The firstName field shall not be null.
		if (firstName == null || firstName.isEmpty() || firstName.length() > 10) {
			throw new IllegalArgumentException("First Name shall not be null, empty, and must be no longer than 10 characters.");
		}
	}
	
	private static void checkLastName(String lastName) {
		// iii. The contact object shall have a required lastName string field that cannot be longer than 10 characters. The lastName field shall not be null.
		if (lastName == null || lastName.isEmpty() || lastName.length() > 10) {
			throw new IllegalArgumentException("Last Name shall not be null, empty, and must be no longer than 10 characters.");
		}
	}

	private static void checkNumber(String Number) {
		// iv. The contact object shall have a required phone String field that must be exactly 10 digits. The phone number field shall not be null.
		if (Number == null || Number.isEmpty() || !Number.matches("\\d{10}")) {
			throw new IllegalArgumentException("Phone number shall not be null, empty, and must be exactly 10 digits.");
		}
	}
	
	private static void checkAddress(String Address) {
		// v. The contact object shall have a required address String field that cannot be longer than 30 characters. The address field shall not be null.
		if (Address == null || Address.isEmpty() || Address.length() > 30) {
			throw new IllegalArgumentException("Address shall not be null, empty, and must be no longer than 30 characters.");
		}
	}
}
