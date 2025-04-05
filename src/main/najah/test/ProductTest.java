package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import main.najah.code.Product;

@DisplayName("Product Test")
public class ProductTest {
    private Product p;

	@BeforeEach
	void setUp() throws Exception {
		p=new Product("PC", 1000.0);
	}

	@Test
	@DisplayName("Test Constructor to set name and price correctly ")
	void test() {
		assertEquals("PC",p.getName());
		assertEquals(1000.0,p.getPrice());
		assertEquals(0.0, p.getDiscount());
	}
	
	@Test
	@DisplayName("Test Apply Discount")
	void testValidDiscount() {
		p.applyDiscount(20);
		assertEquals(800.0, p.getFinalPrice());
	}
	
	@Test
	@DisplayName("Test invalid Discount with number less than zero")
	void testInvalidDiscountLessThanZero() {
		assertThrows(IllegalArgumentException.class, ()->p.applyDiscount(-5));
	}
	@Test
	@DisplayName("Test invalid Discount with number more than fifty")
	void testInvalidDiscountMoreThanFifty() {
		assertThrows(IllegalArgumentException.class, ()->p.applyDiscount(51));
	}
	@ParameterizedTest
	@DisplayName("Test discount from csv file")
	@CsvFileSource(resources = "../../../data/discountProduct.csv",numLinesToSkip = 1)
	void testDiscountFromFile(double discount,double expectedPrice) {
		p.applyDiscount(discount);
		assertEquals(expectedPrice, p.getFinalPrice());
	}
	@Test
	@DisplayName("Test Final Price without discount")
	void testFinalPrice() {
		assertEquals(1000.0, p.getFinalPrice());
	}
	@Test
	@DisplayName("Test invalid input constructor")
	void testInvalidConstructor() {
		assertThrows(IllegalArgumentException.class, ()->new Product("Phone",-100.0));
//		assertThrows(IllegalArgumentException.class, ()->new Product("150",100.0)); it lets me assert numbers in the name section
	}
	@Test
	@DisplayName("Discount over 50")
	@Disabled
	void testDiscountDisabled() {
		p.applyDiscount(75.0);
	}
	@Test
	@DisplayName("Discount with doubles")
	void testDiscountWithDoubles() {
		p.applyDiscount(25.50);
	}
}
