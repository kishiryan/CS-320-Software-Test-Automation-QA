package contactServicePackage;


// Import necessary classes for testing
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactTest {

	@Test // Test for a contact that can be created with valid parameters and then retrieved
	void constructor_setsFields_whenValid() {
		Contact c = new Contact("123", "Ryan", "Kishi", "1234567890", "123 Main St");
		assertEquals("123", c.getUniqueContactID());
		assertEquals("Ryan", c.getFirstName());
		assertEquals("Kishi", c.getLastName());
		assertEquals("1234567890", c.getNumber());
		assertEquals("123 Main St", c.getAddress());
	}

	@Test // Test for a contact that cannot be created with an invalid ID (null or too long)
	void constructor_throws_whenIdNullOrTooLong() {
		assertThrows(IllegalArgumentException.class,
				() -> new Contact(null, "Ryan", "Kishi", "1234567890", "123 Main St"));
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("12345678901", "Ryan", "Kishi", "1234567890", "123 Main St"));
	}

	@Test // Test for a contact that cannot be created with an invalid phone number
	void constructor_throws_whenPhoneInvalid() {
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "Kishi", "123", "123 Main St"));
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "Kishi", "12345678901", "123 Main St"));
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "Kishi", "12345abcde", "123 Main St"));
	}
	
	@Test // Test contact ID exactly at 10 character limit
	void constructor_succeeds_whenIdAt10Chars() {
		Contact c = new Contact("1234567890", "Ryan", "Kishi", "1234567890", "123 Main St");
		assertEquals(10, c.getUniqueContactID().length());
	}
	
	@Test // Test first name null or empty
	void constructor_throws_whenFirstNameNullOrEmpty() {
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", null, "Kishi", "1234567890", "123 Main St"));
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "", "Kishi", "1234567890", "123 Main St"));
	}
	
	@Test // Test first name too long
	void constructor_throws_whenFirstNameTooLong() {
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "12345678901", "Kishi", "1234567890", "123 Main St"));
	}
	
	@Test // Test first name exactly at 10 character limit
	void constructor_succeeds_whenFirstNameAt10Chars() {
		Contact c = new Contact("123", "1234567890", "Kishi", "1234567890", "123 Main St");
		assertEquals(10, c.getFirstName().length());
	}
	
	@Test // Test last name null, empty, or too long
	void constructor_throws_whenLastNameInvalid() {
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", null, "1234567890", "123 Main St"));
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "", "1234567890", "123 Main St"));
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "12345678901", "1234567890", "123 Main St"));
	}
	
	@Test // Test phone number null or empty
	void constructor_throws_whenPhoneNullOrEmpty() {
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "Kishi", null, "123 Main St"));
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "Kishi", "", "123 Main St"));
	}
	
	@Test // Test phone number with non-numeric characters
	void constructor_throws_whenPhoneHasNonNumeric() {
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "Kishi", "123456789A", "123 Main St"));
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "Kishi", "123-456-7890", "123 Main St"));
	}
	
	@Test // Test address null, empty, or too long
	void constructor_throws_whenAddressInvalid() {
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "Kishi", "1234567890", null));
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "Kishi", "1234567890", ""));
		String longAddress = "1234567890123456789012345678901"; // 31 characters
		assertThrows(IllegalArgumentException.class,
				() -> new Contact("123", "Ryan", "Kishi", "1234567890", longAddress));
	}
	
	@Test // Test address exactly at 30 character limit
	void constructor_succeeds_whenAddressAt30Chars() {
		String address30 = "123456789012345678901234567890"; // 30 characters
		Contact c = new Contact("123", "Ryan", "Kishi", "1234567890", address30);
		assertEquals(30, c.getAddress().length());
	}
	
	@Test // Test setters with valid values
	void setters_succeed_whenValid() {
		Contact c = new Contact("123", "Ryan", "Kishi", "1234567890", "123 Main St");
		c.setFirstName("John");
		c.setLastName("Doe");
		c.setNumber("9876543210");
		c.setAddress("456 Oak Ave");
		
		assertEquals("John", c.getFirstName());
		assertEquals("Doe", c.getLastName());
		assertEquals("9876543210", c.getNumber());
		assertEquals("456 Oak Ave", c.getAddress());
	}
	
	@Test // Test setters with invalid values
	void setters_throw_whenInvalid() {
		Contact c = new Contact("123", "Ryan", "Kishi", "1234567890", "123 Main St");
		
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName(""));
		assertThrows(IllegalArgumentException.class, () -> c.setFirstName("12345678901"));
		
		assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
		assertThrows(IllegalArgumentException.class, () -> c.setLastName("12345678901"));
		
		assertThrows(IllegalArgumentException.class, () -> c.setNumber(null));
		assertThrows(IllegalArgumentException.class, () -> c.setNumber("123"));
		assertThrows(IllegalArgumentException.class, () -> c.setNumber("12345678901"));
		assertThrows(IllegalArgumentException.class, () -> c.setNumber("abcdefghij"));
		
		assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
		assertThrows(IllegalArgumentException.class, () -> c.setAddress("1234567890123456789012345678901"));
	}
}
