import static org.junit.Assert.*;

import org.junit.Test;

public class TestMain {
       private Main m = new Main();
	// Checks is if the Main class exists or not
	@Test
	public final void test() throws Exception{
		try {
			Main mtest = new Main();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Main failed");
			fail();
		}
		return;
	}

	// This is not a system related testt
	//  Causes a test to fail if it takes longer than a specified amount of clock time (measured in milliseconds)
	@Test (timeout = 100)
	public void testPerformanceNotTimeout() {
		try {
			Thread.sleep(50); // Placeholder code for a network activity with latency
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test (timeout = 100)
    public void testPerformanceTimeout() {
        try {
            Thread.sleep(101); // Placeholder code for a network activity with latency
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
