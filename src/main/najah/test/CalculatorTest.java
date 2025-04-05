package main.najah.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import main.najah.code.Calculator;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Calculator Tests")
public class CalculatorTest {
	
    private	Calculator calc;
    

	@BeforeAll
    static void beforeAll() {
        System.out.println("Starting Calculator Tests...");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finished Calculator Tests.");
    }

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("Setup complete.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test finished.");
    }

	@Test
	@Order(1)
	@DisplayName("Test add Valid Inputs")
	void testAddValid() {
		int res= calc.add(1);
		assertEquals(1,res);
		int res1= calc.add(1,3);
		assertEquals(4,res1);
		int res2= calc.add(1,5,3);
		assertEquals(9,res2);
	}
	
	@Test
	@Order(2)
	@DisplayName("Divide Valid inputs")
	void testDivideValid() {
		assertEquals(2, calc.divide(10,5));
		assertEquals(3, calc.divide(15,5));
		assertEquals(100, calc.divide(500,5));
		assertEquals(10000, calc.divide(50000,5));
	}
	@Test
	@Order(3)
	@DisplayName("Divide by zero")
	void testDivideByZero() {
		assertThrows(ArithmeticException.class,()-> calc.divide(10000, 0));
	}	
	
	@Test
	@Order(4)
	@DisplayName("Factorial valid input")
	void testFactorial() {
		assertEquals(720, calc.factorial(6));
	}//factorial method has some problem with the large test like what if i want to calculate the factorial of the 100?
	
	@Test
	@Order(4)
	@DisplayName("Factorial invalid inputs Negative")
	void testFactorialOfNegativeNumbers() {
		assertThrows(IllegalArgumentException.class, ()->calc.factorial(-10));
	}
	 
	@ParameterizedTest
	@Order(6) 
	@DisplayName("Add valid input from the file add test with 3 int")
	@CsvFileSource(resources = "../../../data/addNumbers.csv", numLinesToSkip = 1) 
	void testAddParameterizedFromFile(int num1, int num2, int num3, int expected) { 
		assertEquals(expected, calc.add(num1, num2,num3));
	}
	
	
	@Test
	@Order(7)
	@DisplayName("Factorial test timeout with the factorial of large numbers")
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	void testTimeoutWithFactorailMethod() {
		assertEquals(39916800, calc.factorial(11));
	}
	@Test
	@Order(8)
	@DisplayName("add Faliure test")
	@Disabled
	void testAddFail() {
		assertEquals(10, calc.add(5,8));
	}
	
	@Test
	@Order(2)
	@DisplayName("Divide InValid inputs")
	@Disabled
	void testDivideInValid() {
		assertEquals(3.5, calc.divide(7,2));
	}
}
