import static org.junit.Assert.*;

import org.junit.*;

import java.sql.*;

public class TestDBTransaction {
    private DBTransactions dbttest = new DBTransactions();
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //16 Tests

    // Checks if there is a customer with same email is registered to the system
    @Test
    public void testRegisterExistedFields() {
        String email = "ata";
        try {
            String url="jdbc:mysql://"+ DB.host+":"+ DB.port+"/"+ DB.db_name+"?useUnicode=true&characterEncoding=utf8";
            con = DriverManager.getConnection(url, DB.username, DB.password);
            String sql = "Select Email from customers where Email=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            assertEquals("This email is registred",true,rs.next());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Checks if there is a customer with same email is not registered to the system
    @Test
    public void testRegisterNotExistedFields() {
        String email = "ata12";
        try {
            String url="jdbc:mysql://"+ DB.host+":"+ DB.port+"/"+ DB.db_name+"?useUnicode=true&characterEncoding=utf8";
            con = DriverManager.getConnection(url, DB.username, DB.password);
            String sql = "Select Email from customers where Email=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            assertNotEquals("This email is registred",true,rs.next());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Checks if the username and passwords are matching with the database
    @Test
   public void testUserNamePassword() {
       String userName = "efe";
       String password = "efe";
       try {
           Assert.assertEquals(true,dbttest.Login(userName, password));
       }catch (Exception e) {
           e.printStackTrace();
       }
   }

    // Checks if the username and passwords are not matching with the database
   @Test
   public void testFailUserNamePassword() {
       String userName = "efeeee";
       String password = "efe";
       try {
           assertNotSame(true,dbttest.Login(userName, password));
       }catch (Exception e) {
           e.printStackTrace();
       }
   }

    // Checks if the Driver object can be created
    @Test
    public void testDBTransactions() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Checks is if the DBTransactions class exists
   @Test
	public void testTransactions() {
		try {
			DBTransactions Ttest = new DBTransactions();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("DBTransactions is failed");
			fail();
		}
		return;
	}
	// Followings are tests for parameter checking of methods
   @Test
   public final void testRegister() {
	   try {
	   String fullName = "";
	   String password = "";
	   String userName = "";
	   String email = "";
	   String telephone = "";

	   dbttest.Register(fullName, userName, password, email, telephone);
	   }
	   catch(Exception e) {
		   e.printStackTrace();
		   System.out.println("Register is failed");
		   fail();
	   }
	    return;
   }

   @Test
   public final void testForgotPassword() {
       try {
           String newPassword = "";
           String email = "";
           dbttest.ForgotPassword(newPassword, email);
       }catch (Exception e) {
           e.printStackTrace();
           System.out.println("ForgotPassword is failed");
           fail();
       }
       return;
   }

   @Test
    public final void testLogin() {
       try {
           String userName = "";
           String password = "";
           dbttest.Login(userName, password);
       }catch (Exception e) {
           e.printStackTrace();
           System.out.println("Login is failed");
       }
   }

    @Test
    public final void testRemove() {
        try {
            String email = "";
            dbttest.Remove(email);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Remove is failed");
        }
    }

    // Checks if there is a customer with that email can be removed from the system
    @Test
    public final void testRemoveFalse() {
        try {
            String email = "fefefe";
            dbttest.Remove(email);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Remove is failed");
        }
    }

    // Checks if there is a bus with 0 id
    @Test
    public final void testShowBuses() {
        try {
            int id = 0;
            dbttest.ShowBuses(id);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ShowBuses is failed");
        }
    }

    // Checks if there is a bus with 0 id
    @Test
    public final void testTicketBooking() {
        try {
            int id = 0;
            dbttest.TicketBooking(id);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("TicketBooking is failed");
        }
    }

    // Checks if the user can do payment
    @Test
    public final void testPayment() {
        try {
            String userName = "";
            dbttest.Payment(userName);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Payment is failed");
        }
    }

    // Checks if this customer can do purchase, is he/she eligible to do that
    @Test
    public final void testPaymentFail() {
        try {
            String userName = "fefe";
            dbttest.Payment(userName);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Payment is failed");
        }
    }

    // Checks if the user can add balance to his/her account
    @Test
    public final void testAddBalance() {
        try {
            int value = 0;
            String userName = "";
            dbttest.AddBalance(value,userName);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("AddBalance is failed");
        }
    }

}
