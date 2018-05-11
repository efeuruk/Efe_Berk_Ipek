import static org.junit.Assert.*;

import org.junit.Test;

public class testOdds {

	@Test
	public void testOddsTrue() {
		assertEquals("No solution",25,Junit.sumOdds(10));
		assertEquals("No solutiýn",36,Junit.sumOdds(12));
	}

	public void testOddsFail() {
		assertNotSame("No solution",46,Junit.sumOdds(13));
	}
}
