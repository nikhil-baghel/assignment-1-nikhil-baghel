import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        MyContactList C = new MyContactList();

        Scanner sc = new Scanner(System.in);

        int choice = 0;
        while (choice != 5) {
            System.out.println("Welcome to Nikhil's Contact List\nPress 1 to ADD.\nPress 2 to VIEW all.\nPress 3 to SEARCH.\nPress 4 to DELETE.\nPress 5 to EXIT.");

            choice = sc.nextInt();

            switch (choice) {
                case 5:
                    break;

                case 1:

                    C.addContact();
                    break;

                case 2:
                    C.display();
                    break;

                case 3:
                    System.out.print("Enter first name to search :");
                    sc.nextLine();
                    C.search(sc.nextLine());
                    break;

                case 4:
                    C.deleteContact();
                    break;
            }
        }
    }
}