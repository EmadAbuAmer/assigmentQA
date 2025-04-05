package main.najah.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;
@DisplayName("Recipe book")
class TestRecipeBook {
	
	 private RecipeBook recipeBook;
	 private Recipe firstRecipe;
	 private Recipe secondRecipe;

	@BeforeEach
	void test() {
		recipeBook = new RecipeBook();
		
		firstRecipe=new Recipe();
		firstRecipe.setName("Tea");
		
		secondRecipe=new Recipe();
		secondRecipe.setName("Coffee");
		
	}
	@Test
	@DisplayName("test add recipe ")
	void testAddNewRecipe() {
		assertTrue(recipeBook.addRecipe(firstRecipe));
	}
	
	@Test
	@DisplayName("Test duplicate add")
	void testAddDuplicate() {
		assertTrue(recipeBook.addRecipe(secondRecipe));
		assertFalse(recipeBook.addRecipe(secondRecipe));
	}
	
	@Test
	@DisplayName("test addition limit")
	void testAddLimit() {
		for (int i = 0; i < 4; i++) {
			Recipe recipe= new Recipe();
			recipe.setName("Recipe" +i);
			assertTrue(recipeBook.addRecipe(recipe));
		}
		assertFalse(recipeBook.addRecipe(firstRecipe));
	}
	
	@Test
	@DisplayName("Delete Recipe")
	void testDeleteRecipe() {
		recipeBook.addRecipe(firstRecipe);
		String deleteString=recipeBook.deleteRecipe(0);
		System.out.println(deleteString);
		assertEquals("Tea", deleteString);
//		assertNull(recipeBook.getRecipes()[0].getName());
		assertEquals("", recipeBook.getRecipes()[0].getName());
	}
	
	@Test
	@DisplayName("tried to delete non exising recipe")
	void testDeleteNonExisingRecipe() {
		assertNull(recipeBook.deleteRecipe(1));
	}
	@Test
	@DisplayName("Edit Recipe")
	void testEditRecipe() {
		recipeBook.addRecipe(firstRecipe);
		
		Recipe editedRecipe= new Recipe();
		
		editedRecipe.setName("cappuccino");
		
		String updatedName = recipeBook.editRecipe(0, editedRecipe);
		assertEquals("Tea", updatedName);
		 assertEquals("", recipeBook.getRecipes()[0].getName());
	}
	@Test
	@DisplayName("edit exising recipe")
	void testEditNonExisingRecipe() {
		Recipe editRecipe=new Recipe();
		editRecipe.setName("Latte");
		assertNull(recipeBook.editRecipe(3, editRecipe));
	}
	@Test
	@DisplayName("Test get recipe")
	void testGetRecipe() {
		Recipe[] recipes= recipeBook.getRecipes();
		assertNotNull(recipes);
		assertEquals(4, recipes.length);
	}
	
	@Test
	@DisplayName("test setting price")
	void testSetPrice() throws RecipeException {
		Recipe testRecipe= new Recipe();
		testRecipe.setName("cappuccino");
		testRecipe.setPrice("50");
		assertEquals(50, testRecipe.getPrice());	
	}
	@Test
	@DisplayName("setting negative price")
	void testNegativePrice() throws RecipeException {
		assertThrows(RecipeException.class,()->firstRecipe.setPrice("-10") );
	}
	
	@Test
	@DisplayName("test valid sugar")
	void testValidSugar() throws RecipeException {
		firstRecipe.setAmtSugar("2");
		assertEquals(2, firstRecipe.getAmtSugar());
	}
	@Test
	@DisplayName("test invalid sugar")
	void testInvalidSugar() throws RecipeException {
		assertThrows(RecipeException.class,()->firstRecipe.setAmtSugar("fify") );
	}
	@Test
	@DisplayName("test valid Milk")
	void testValidMilk() throws RecipeException {
		firstRecipe.setAmtMilk("50");
		assertEquals(50, firstRecipe.getAmtMilk());
	}
	@Test
	@DisplayName("test invalid Milk")
	void testInvalidMilk() throws RecipeException {
		assertThrows(RecipeException.class,()->firstRecipe.setAmtMilk("fify") );
	}
	@Test
	@DisplayName("test valid Coffee")
	void testValidCoffee() throws RecipeException {
		firstRecipe.setAmtCoffee("15");
		assertEquals(15, firstRecipe.getAmtCoffee());
	}
	@Test
	@DisplayName("test invalid Coffee")
	void testInvalidCoffee() throws RecipeException {
		assertThrows(RecipeException.class,()->firstRecipe.setAmtCoffee("fify") );
	}
	@Test
	@DisplayName("test valid Chocolate")
	void testValidChocolate() throws RecipeException {
		firstRecipe.setAmtChocolate("15");
		assertEquals(15, firstRecipe.getAmtChocolate());
	}
	@Test
	@DisplayName("test invalid Chocolate")
	void testInvalidChocolate() throws RecipeException {
		assertThrows(RecipeException.class,()->firstRecipe.setAmtChocolate("fify") );
	}
}
