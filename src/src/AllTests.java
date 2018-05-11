import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestDBTransaction.class, TestMain.class, TestTicketTransactions.class })
public class AllTests {

}
