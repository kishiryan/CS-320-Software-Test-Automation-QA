package contactServicePackage;

// Import necessary classes for testing
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

	@Test // Test for a contact that can be added and retrieved
	void addContact_whenCheckValid() {
		ContactService cs = new ContactService();
		Contact c = new Contact("123", "Ryan", "Kishi", "1234567890", "123 Main St");
	
		cs.addContact(c);
	
		assertNotNull(cs.getContactOrThrowE("123"));
		assertEquals("Ryan", cs.getContactOrThrowE("123").getFirstName());
		
	}

	@Test // Test for adding a contact with a duplicate ID 
	void addContact_whenDuplicateId() {
		ContactService cs = new ContactService();
		Contact c1 = new Contact("123", "Ryan", "Kishi", "1234567890", "123 Main St");
		Contact c2 = new Contact("123", "Kaylee", "Kishi", "0987654321", "999 Doodoo St");
	
		cs.addContact(c1);
	
		assertThrows(IllegalArgumentException.class, () -> cs.addContact(c2));
	}

	@Test // Test for deleting a contact and then trying to retrieve it
	void deleteContact_whenCheckValid() {
		ContactService cs = new ContactService();
		Contact c = new Contact("123", "Ryan", "Kishi", "1234567890", "123 Main St");
	
		cs.addContact(c);
		cs.deleteContact("123");
	
		assertThrows(IllegalArgumentException.class, () -> cs.getContactOrThrowE("123"));
	}
	
	@Test // Test for updating contact fields
	void updateContactFields_whenCheckValid() {
		ContactService cs = new ContactService();
		Contact c = new Contact("123", "Ryan", "Kishi", "1234567890", "123 Main St");
	
		cs.addContact(c);
	
		cs.updateFirstName("123", "Kaylee");
		cs.updateLastName("123", "Smith");
		cs.updateNumber("123", "0987654321");
		cs.updateAddress("123", "456 Oak Ave");
	
		assertEquals("Kaylee", cs.getContactOrThrowE("123").getFirstName());
		assertEquals("Smith", cs.getContactOrThrowE("123").getLastName());
		assertEquals("0987654321", cs.getContactOrThrowE("123").getNumber());
		assertEquals("456 Oak Ave", cs.getContactOrThrowE("123").getAddress());
	}
	
	@Test // Test adding null contact
	void addContact_throws_whenNull() {
		ContactService cs = new ContactService();
		assertThrows(IllegalArgumentException.class, () -> cs.addContact(null));
	}
	
	@Test // Test deleting with null ID
	void deleteContact_throws_whenNullId() {
		ContactService cs = new ContactService();
		assertThrows(IllegalArgumentException.class, () -> cs.deleteContact(null));
	}
	
	@Test // Test deleting non-existent contact
	void deleteContact_throws_whenNonExistent() {
		ContactService cs = new ContactService();
		assertThrows(IllegalArgumentException.class, () -> cs.deleteContact("NONEXIST"));
	}
	
	@Test // Test updating non-existent contact
	void updateContact_throws_whenNonExistent() {
		ContactService cs = new ContactService();
		assertThrows(IllegalArgumentException.class, () -> cs.updateFirstName("NONEXIST", "Test"));
		assertThrows(IllegalArgumentException.class, () -> cs.updateNumber("NONEXIST", "1234567890"));
	}
	
	@Test // Test updating with invalid values
	void updateContact_throws_whenInvalidValues() {
		ContactService cs = new ContactService();
		Contact c = new Contact("123", "Ryan", "Kishi", "1234567890", "123 Main St");
		cs.addContact(c);
		
		// Invalid first name (null, empty, too long)
		assertThrows(IllegalArgumentException.class, () -> cs.updateFirstName("123", null));
		assertThrows(IllegalArgumentException.class, () -> cs.updateFirstName("123", ""));
		assertThrows(IllegalArgumentException.class, () -> cs.updateFirstName("123", "12345678901"));
		
		// Invalid phone number (wrong length, contains letters)
		assertThrows(IllegalArgumentException.class, () -> cs.updateNumber("123", "123"));
		assertThrows(IllegalArgumentException.class, () -> cs.updateNumber("123", "12345678901"));
		assertThrows(IllegalArgumentException.class, () -> cs.updateNumber("123", "abcdefghij"));
		
		// Invalid address (too long)
		assertThrows(IllegalArgumentException.class, () -> cs.updateAddress("123", "1234567890123456789012345678901"));
	}
	
	@Test // Test delete and re-add with same ID
	void deleteAndReAdd_succeeds_withSameId() {
		ContactService cs = new ContactService();
		Contact c1 = new Contact("123", "Ryan", "Kishi", "1234567890", "123 Main St");
		cs.addContact(c1);
		cs.deleteContact("123");
		
		// Should be able to add new contact with same ID
		Contact c2 = new Contact("123", "Kaylee", "Smith", "0987654321", "456 Oak Ave");
		cs.addContact(c2);
		
		assertEquals("Kaylee", cs.getContactOrThrowE("123").getFirstName());
	}
	
	@Test // Test update with boundary values
	void updateWithBoundaryValues_succeeds() {
		ContactService cs = new ContactService();
		Contact c = new Contact("123", "Ryan", "Kishi", "1234567890", "123 Main St");
		cs.addContact(c);
		
		// Update to max lengths
		cs.updateFirstName("123", "1234567890"); // 10 chars
		cs.updateLastName("123", "0987654321"); // 10 chars
		cs.updateAddress("123", "123456789012345678901234567890"); // 30 chars
		
		assertEquals(10, cs.getContactOrThrowE("123").getFirstName().length());
		assertEquals(10, cs.getContactOrThrowE("123").getLastName().length());
		assertEquals(30, cs.getContactOrThrowE("123").getAddress().length());
	}
}
