import static org.junit.Assert.*;

import org.junit.Test;

public class TestTicketTransactions {
	private TicketTransactions t = new TicketTransactions();

	// Checks is if the TicketTransactions class exists
	@Test
    public final void testTicketTransactions() throws Exception {
	    try {
		TicketTransactions ttest = new TicketTransactions();   
		}
	catch (Exception e) {
		e.printStackTrace();
		System.out.println("TicketTransactions failed");
		fail();
	}
	    return;
 	}

	
}
