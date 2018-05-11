import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws SQLException {
        DBTransactions transaction = new DBTransactions();
        TicketTransactions ticket = new TicketTransactions();
        Scanner sc = new Scanner(System.in);
        boolean start = true;

        // This is the main menu
        menu:while(start){
            System.out.println("-------------------WELCOME TO BUS MANAGEMENT SYSTEM---------------");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.ForgotPassword");
            System.out.println("4.AdminEntrance");
            System.out.println("9.Exit");
            int MenuChoice = sc.nextInt();
            switch (MenuChoice) {
                case 1:
                    String usernameLogin;
                    String passwordLogin;
                    System.out.println("----------------------------LOGIN---------------------");
                    System.out.println("Username : ");
                    sc.nextLine();
                    usernameLogin = sc.nextLine();
                    System.out.println("Password : ");
                    passwordLogin = sc.nextLine();
                    boolean successeed = transaction.Login(usernameLogin, passwordLogin);
                    if (successeed == true) {
                        System.out.println("You can enter the system.......");
                        try{
                            ticket.TicketOptions();
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("Wrong Password or Username. Please try again......");
                    }
                    break;
                case 2:
                    String fullName, username, password, email, telephone;
                    System.out.println("--------------REGISTER PAGE--------------------------------");
                    System.out.println("Full Name : ");
                    sc.nextLine();
                    fullName = sc.nextLine();
                    System.out.println("Username : ");
                    username = sc.nextLine();
                    System.out.println("Password : ");
                    password = sc.nextLine();
                    System.out.println("Email : ");
                    email = sc.nextLine();
                    System.out.println("Telephone : ");
                    telephone = sc.nextLine();

                    transaction.Register(fullName, username, password, email, telephone);
                    System.out.println("YOU HAVE REGISTERED TO SYSTEM................");
                    break;
                case 3:
                    System.out.println("------------FORGOT PASSWORD PAGE---------------------");
                    System.out.println("Please enter your email : ");
                    String forgotPasswordEmail;
                    String newPassword;
                    sc.nextLine();
                    forgotPasswordEmail = sc.nextLine();
                    System.out.println("Please enter your new password : ");
                    newPassword = sc.nextLine();
                    transaction.ForgotPassword(newPassword, forgotPasswordEmail);
                    break;
                case 4:
                    admin:for (;;) {
                        String adminPasswordTrue;
                        System.out.println("-----------------------ADMIN ENTERANCE-----------------");
                        System.out.println("Please enter the admin password : ");
                        sc.nextLine();
                        adminPasswordTrue = sc.nextLine();
                        if (adminPasswordTrue.equals("admin")) {
                            System.out.println("Admin password is correct");
                            ticket.AdminOptions();
                            continue menu;
                        }
                        else {
                            System.out.println("You have given a wrong password!!!!");
                            continue admin;
                        }
                    }
                case 9 : start = false; break;
                default:
                    System.out.println("Please enter an valid input!!!!");
            }
        }
    }
}
