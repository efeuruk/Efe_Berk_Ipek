import static org.junit.Assert.*;

import org.junit.Test;

public class testFactorial {

	@Test
	public void testFactorialTrue() {
		
		assertEquals("No solution",120,Junit.factorial(5));
		assertEquals("No solution",24,Junit.factorial(4));
	}
	
	@Test
	public void testFactorialFail() {
		assertNotSame("No solution",12,Junit.factorial(3));
	}

}
