package main.najah.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.UserService;

@DisplayName("User Serivces")
class UserServiceSimpleTest {
	private UserService service;

	@BeforeEach
	void setUp() throws Exception {
		service=new UserService();
	}

	@Test
	@DisplayName("Test Valid Email")
	void testValidEmail() {
		assertTrue(service.isValidEmail("Email1234@gmail.com"));
	}
	
	@Test
	@DisplayName("Test Null")
	void testNullEmail() {
		assertFalse(service.isValidEmail(null));
	}
	
	@ParameterizedTest
	@DisplayName("test valid and non valid emails from a file")
	@CsvFileSource(resources = "../../../data/emailValidationTest.csv",numLinesToSkip = 1)
	void testEmails(String email,boolean expected) {
		assertEquals(expected, service.isValidEmail(email));
	}
	@Test
	@DisplayName("auth test")
	void testAuthentication() {
		assertTrue(service.authenticate("admin", "1234"));
	}
	
	@ParameterizedTest
	@CsvSource({
		"'admin', 'wrong'",
        "'wrong', '1234'",
        "'', ''",
        "'user', 'pass'",
        "'ADMIN', '1234'"
	})
	@DisplayName("Incorrect auth")
	void testIncorrectAuthentication(String userName,String password) {
		assertFalse(service.authenticate(userName, password));
	}
	
	@Test
	@DisplayName("Test invaild email")
	@Disabled
	void invalidEmail() {
		assertTrue(service.isValidEmail("asdasdjas@maill.imt"));
	}//this code is given true but in fact it is not a true email
}
