import static org.junit.Assert.*;

import org.junit.Test;

public class testString {

	@Test
	public void stringTrue() {
		assertEquals("No solution","berkefetestingisgood",Junit.concateText("berk","efe"));
		assertEquals("No solution","ozanserhattestingisgood",Junit.concateText("ozan","serhat"));
	}
	
	public void stringFalse() {
		assertNotSame("No solution","serhathocadamdýr",Junit.concateText("serhat", "hoca"));
	}

}
