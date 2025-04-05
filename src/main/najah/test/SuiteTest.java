package main.najah.test;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
@Suite
@SelectClasses({CalculatorTest.class,ProductTest.class,TestRecipeBook.class,UserServiceSimpleTest.class})
class SuiteTest {

}
