import java.awt.print.Book;
import java.sql.SQLException;
import java.util.Scanner;
public class TicketTransactions {
    // This is the class for ticket transactions and admin options
    DBTransactions t = new DBTransactions();
    Scanner sc = new Scanner(System.in);

    // Book a seat from the bus
    protected void BookingABus(int id) throws SQLException {
        System.out.println("Do you want to book this bus? Y/N");
        sc.nextLine();
        String yesOrNo = sc.nextLine();
        if (yesOrNo.equalsIgnoreCase("Y")){
            System.out.println("Please enter your username : ");
                String username = sc.nextLine();
                t.Payment(username);
                t.TicketBooking(id);
        }
    }

    // This method comes, when the customer logins the system
    public void TicketOptions() throws SQLException {
        System.out.println("-------------OPTIONS--------------------");
        System.out.println("1.Ticket Booking");
        System.out.println("2.Add Balance");
        int option = sc.nextInt();
        switch (option) {
            case 1 :
                System.out.println("Where do you want to go?");
                System.out.println("1.Istanbul");
                System.out.println("2.Ankara");
                System.out.println("3.İzmir");
                System.out.println("4.Konya");
                int busChoice = sc.nextInt();
                System.out.println("----SHOWING THE BUSES----");
                switch (busChoice){
                    case 1:
                        t.ShowBuses(1);
                        BookingABus(1);
                        break;
                    case 2:
                        t.ShowBuses(2);
                        BookingABus(2);
                        break;
                    case 3:
                        t.ShowBuses(3);
                        BookingABus(3);
                        break;
                    case 4:
                        t.ShowBuses(4);
                        BookingABus(4);
                        break;
                    default:
                        System.out.println("Wrong choice please enter a valid number!");
                }
                break;
            case 2:
                // Adding balance
                try {
                    System.out.println("Please enter your username : ");
                    sc.nextLine();
                    String username = sc.nextLine();
                    System.out.println("Please enter the amount you want to add to your account : ");
                    int amount = sc.nextInt();
                    t.AddBalance(amount,username);
                }catch (Exception ex) {
                    System.out.println("Please enter valid  username or valid amount");
                }

                break;

            default:
                System.out.println("Wrong choice please enter a valid number!");
        }
    }

    // Admin's previliges are stored in this method
    public void AdminOptions() throws SQLException {
        System.out.println("---------------ADMIN PANEL---------");
        System.out.println("1.Remove a customer");
        System.out.println("2.Check customers");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("Please enter the customer's email that you want to delete from the system: ");
                sc.nextLine();
                String email = sc.nextLine();
                t.Remove(email);
                break;
            case 2:
                t.TicketChecking();
                break;
            default:
                System.out.println("Please enter a valid option."); break;
        }
    }
}
