package contactServicePackage;

import java.util.HashMap;
import java.util.Map;

public class ContactService {

	// Deploy a hash map to store contact info with a unique contact ID key and object value
	private final Map<String, Contact> contacts = new HashMap<>();
	
	// The service shall be able to add contacts with a unique ID.
	public void addContact(Contact ctt) {
		if (ctt == null) {
			throw new IllegalArgumentException("Entry cannot be null.");
		}
		
		String id = ctt.getUniqueContactID();
		if (contacts.containsKey(id)) {
			throw new IllegalArgumentException("Unique Contact ID already exists: " + id);
		}

		contacts.put(id,  ctt);
	}

	// The contact service shall be able to delete contacts per contact ID.
	public void deleteContact(String contactID) {
		if (contactID == null) {
			throw new IllegalArgumentException("Contact ID cannot be null.");
		}
		
		if (!contacts.containsKey(contactID)) {
			throw new IllegalArgumentException("Contact ID not found: " + contactID);
		}

		// Actually remove the contact once validation passes.
		contacts.remove(contactID);
	}
	
	// The contact service shall be able to update contact fields per contact ID. 
	// firstName update method
	public void updateFirstName(String contactID, String firstName) {
		Contact c = getContactOrThrowE(contactID);
		c.setFirstName(firstName);
	}
	
	// lastName update method
	public void updateLastName(String contactID, String lastName) {
		Contact c = getContactOrThrowE(contactID);
		c.setLastName(lastName);
	}
	
	// Number update method
	public void updateNumber(String contactID, String Number) {
		Contact c = getContactOrThrowE(contactID);
		c.setNumber(Number);
	}
	
	// Address update method
	public void updateAddress(String contactID, String Address) {
		Contact c = getContactOrThrowE(contactID);
		c.setAddress(Address);
	}
	
	// Helper to automate get retrieval and check validation without repeating code function in each update
	public Contact getContactOrThrowE(String contactID) {
		if (contactID == null) {
			throw new IllegalArgumentException("contactID cannot be null.");
		}
		
		Contact c = contacts.get(contactID);
		if (c == null) {
			throw new IllegalArgumentException("Contact ID not found: " + contactID);
		}
		return c;
	}
}
