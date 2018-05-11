import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTransactions {
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public DBTransactions() {
        String url="jdbc:mysql://"+DB.host+":"+DB.port+"/"+DB.db_name+"?useUnicode=true&characterEncoding=utf8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception ex) {
            System.out.println("Driver can't be found....");
            System.exit(0);
        }
        try {
            con = DriverManager.getConnection(url,DB.username,DB.password);
            System.out.println("Connection is succeed....");
        }catch(Exception ex) {
            System.out.println("Connection is failed....");
            System.exit(0);
        }
    }

    // The method that registers the customer to the system
    public void Register(String fullName,String userName,String password,String email,String telephone) throws SQLException {
        String query = "Insert into customers(fullName,userName,password,email,telephone) values (?,?,?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1, fullName);
        ps.setString(2, userName);
        ps.setString(3, password);
        ps.setString(4, email);
        ps.setString(5, telephone);
        ps.executeUpdate();
    }

    // If customers forget their passwords, they can use this module
    public void ForgotPassword(String newPassword,String email) throws SQLException {
        String checkPasswordExistence = "Select email,password from customers where password = ?";
        ps = con.prepareStatement(checkPasswordExistence);
        ps.setString(1,newPassword);
        ResultSet rs = ps.executeQuery();
        if (rs.next() == true) {
            System.out.println("Your password can't be same with your old password");
        }
        else {
            String query = "UPDATE customers SET password = ? WHERE Email = ?";
            ps = con.prepareStatement(query);
            System.out.println("Password has changed");
            ps.setString(1,newPassword);
            ps.setString(2,email);
            ps.executeUpdate();
        }

    }

    // The login module for customers
    public boolean Login(String userName, String password) throws SQLException {
        String query = "Select * from customers where userName=? and password=?";
        ps = con.prepareStatement(query);
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    // Remove a customer from the system, this is for the admin
    public void Remove(String email) throws SQLException {
        String checkCustomerExistence = "Select email from customers where email = ?";
        ps = con.prepareStatement(checkCustomerExistence);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        if (rs.next() == false) {
                System.out.println("There is no customer with this email registred to system");
        }
        else {
            String query = "Delete from customers where email = ?";
            ps = con.prepareStatement(query);
            ps.setString(1,email);
            ps.executeUpdate();
            System.out.println("Customer is deleted");
        }
    }

    // Indentation of hours and cities of bus
    private void IndentOfHours() throws SQLException {
        System.out.println("City \t Capacity \t Hour");
        //System.out.println("---- \t -------- \t ----");
        while (rs.next()) {
            System.out.print(rs.getString(1) + "\t\t");
            System.out.print(rs.getString(2) + "\t\t");
            System.out.println(rs.getString(3));
        }
    }

    // The method that shows the buses that they're available
    public void ShowBuses(int id) throws SQLException {
        statement = con.createStatement();
        if (id == 1){
            rs = statement.executeQuery("Select * from buses where id = 1");
            IndentOfHours();
        }
        if (id == 2){
            rs = statement.executeQuery("Select * from buses where id = 2");
            IndentOfHours();
        }
        if (id == 3){
            rs = statement.executeQuery("Select * from buses where id = 3");
            IndentOfHours();
        }
        if (id == 4){
            rs = statement.executeQuery("Select * from buses where id = 4");
            IndentOfHours();
        }
    }

    // Ticket Booking Module for the customer to book a seat from a bus
    public void TicketBooking(int id) throws SQLException {
        String query = "Update buses Set Capacity = Capacity - 1 Where id=?";
        ps = con.prepareStatement(query);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    // The Payment Module for the customers to pay their fees for booking a seat
    public void Payment(String username) throws SQLException {
        String checkBalance = "Select * from customers where balance<10 and userName=?";
        ps = con.prepareStatement(checkBalance);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        if (rs.next() == true) {
            System.out.println("You can't buy a ticket please add some money to your account");
        }
        else {
            String query = "Update customers Set Balance=Balance-10 Where Username=?";
            ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.executeUpdate();
            System.out.println("Taken "+10+" Turkish liras from your account.");
            System.out.println("Your place is reserved. Thank you for choosing us.");
        }
    }

    // If customers need to add balance to their account they can use this module
    public void AddBalance(int value,String username) throws SQLException {
            String customerExistence = "Select ? from customers where Username = ?";
            ps = con.prepareStatement(customerExistence);
            ps.setInt(1,value);
            ps.setString(2,username);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) {
                System.out.println("Please enter your username correctly");
            }
            else {
                String query = "Update customers Set Balance=Balance+? Where Username=?";
                ps = con.prepareStatement(query);
                ps.setInt(1,value);
                ps.setString(2,username);
                ps.executeUpdate();
                System.out.println("You have added "+value+" to your account.");
            }

    }

    // Checking all customers, performed by admin
    public void TicketChecking() throws SQLException {
        String query = "Select * from customers";
        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        System.out.println("Fullname \t\t Username \t\t Password \t\t Email \t\t Telephone \t\t Balance");
        System.out.println("-------- \t\t -------- \t\t -------- \t\t ----- \t\t --------- \t\t -------");
        for (;rs.next();) {
            System.out.print(rs.getString(2) + "\t\t");
            System.out.print(rs.getString(3) + "\t\t\t");
            System.out.print(rs.getString(4) + "\t\t");
            System.out.print(rs.getString(5) + "\t");
            System.out.print(rs.getString(6) + "\t\t");
            System.out.print(rs.getString(7)+"\n");
        }
    }
}
