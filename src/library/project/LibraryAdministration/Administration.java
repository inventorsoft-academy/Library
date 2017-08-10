package library.project.LibraryAdministration;


import library.project.model.Book;
import library.project.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Administration {

    public static List<User> allUsers;

    public static List<Book> allBooks;

    public void checkMyBooks(User user) {
        if (!user.readersListOfBooks.isEmpty()) {
            for (Book a : user.readersListOfBooks) {
                System.out.println("Name: " + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getName()
                        + "\nAuthor: " + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getAuthor()
                        + "\n Rent starts: " + allBooks.get(allBooks.indexOf(a)).getRentStart()
                        + "\n Rent expires: " + allBooks.get(allBooks.indexOf(a)).getRentExpires());
            }
        } else
            System.out.println("You do not reading now!");
        openUserPanel(user);
    }

    public void getAllUsers() {
        for (User a : allUsers) {
            System.out.println("Reader name: " + allUsers.get(allUsers.indexOf(a)).getFirstName()
                    + "  " + allUsers.get(allUsers.indexOf(a)).getLastName()
                    + " ID: " + allUsers.get(allUsers.indexOf(a)).getReaderId());
        }
    }

    public void getAllBooks(User user) {

        for (Book a : allBooks) {
            if (a.quantity != 0) System.out.println("Name: " + allBooks.get(allBooks.indexOf(a)).getName()
                    + "  Author: " + allBooks.get(allBooks.indexOf(a)).getAuthor()
                    + "   Quantity: " + allBooks.get(allBooks.indexOf(a)).getQuantity());
        }
        openUserPanel(user);
    }

    public void rentSomeBook(User user) {
        for (Book a : allBooks) {
            if (allBooks.get(allBooks.indexOf(a)).getQuantity() != 0)
                System.out.println("( " + (allBooks.indexOf(a)) + " ) " + "Name: " + allBooks.get(allBooks.indexOf(a)).getName()
                        + "  Author: " + allBooks.get(allBooks.indexOf(a)).getAuthor()
                        + "   Quantity: " + allBooks.get(allBooks.indexOf(a)).getQuantity());
        }
        System.out.println("Which book u want to take:");
        Scanner scanner = new Scanner(System.in);
        int bookNum = scanner.nextInt();

        user.readersListOfBooks.add(allBooks.get(bookNum));
        rentStarts(user.readersListOfBooks.get(user.readersListOfBooks.indexOf(allBooks.get(bookNum))));
        allBooks.get(bookNum).setQuantity(allBooks.get(bookNum).getQuantity() - 1);
        System.out.println("The book was added to your reading list!");

        System.out.println("Return to user panel(1) \nExit(2)?");
        int thisWayOut = scanner.nextInt();
        switch (thisWayOut) {
            case 1:
                openUserPanel(user);
            case 2:
                break;
        }

    }

    public void returnSomeBook(User user) {
        if (!user.readersListOfBooks.isEmpty()) {
            for (Book a : user.readersListOfBooks) {
                System.out.println("( " + (user.readersListOfBooks.indexOf(a)) + " ) " + " Name: " + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getName()
                        + "  Author: " + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getAuthor()
                        + "\n Rent starts: " + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getRentStart()
                        + "\n Rent expires: " + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getRentExpires());
            }

            System.out.println("Which book u want to return:");
            Scanner scanner = new Scanner(System.in);
            int bookNum = scanner.nextInt();


            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).setQuantity(allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).getQuantity() + 1);
            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).rentStarts = null;
            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).rentExpires = null;
            user.readersListOfBooks.remove(user.readersListOfBooks.get(bookNum));
            System.out.println("Nice boy! You returned a book!");
            openUserPanel(user);
        } else {
            System.out.println("Your reading list is empty!");
            openUserPanel(user);
        }

    }

    private void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user first name:");
        String fname = scanner.nextLine();
        System.out.println("Enter user last name:");
        String lname = scanner.nextLine();
        System.out.println("Enter your passport number:");
        String passportNum = scanner.nextLine();
        User user = new User(fname, lname, passportNum);
        allUsers.add(user);
        System.out.println("User added!");
        startScreen();

    }

    private static void rentStarts(Book book) {
        book.rentStarts = new Date();
        book.rentExpires = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(book.rentStarts);
        book.rentStarts = c.getTime();
        LocalDateTime ldt = LocalDateTime.ofInstant(book.rentStarts.toInstant(), ZoneId.systemDefault()).plusDays(7);
        book.rentExpires = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());


    }

    public void startScreen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Register new user(1) \nLogin(2)");
        int a = sc.nextInt();
        switch (a) {
            case 1:
                registerUser();
                break;
            case 2:
                enterByUser();
                break;
        }
    }

    private void enterByUser() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter user last name:");
        String lastName = scanner.nextLine();

        for (int i = 0; i < allUsers.size(); i++) {
            if ((allUsers.get(i).getFirstName().equals(firstName)) && (allUsers.get(i).getLastName().equals(lastName))) {
                System.out.println("You logged as: " + allUsers.get(i).getFirstName() + " " + allUsers.get(i).getLastName());
                openUserPanel(allUsers.get(i));
                break;
            } else if ((firstName.equals("admin")) && (lastName.equals("admin"))) {
                System.out.println("You logged as: admin ");
                openAdminPanel();
                break;

            } else if (allUsers.size() == i + 1) {
                System.out.println("There is no such user!");
                break;
            }
        }

    }

    private void openAdminPanel() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Add new user(1) " +
                        "\nUpdate user(2) " +
                        "\nDelete user(3) " +
                        "\nAdd new book(4)" +
                        "\nUpdate book(5)" +
                        "\nDelete book(6)" +
                        "\nUpload book's list from file(7)" +
                        "\nUpload user's list from file(8)" +
                        "\nSave book's list to file(9)" +
                        "\nSave user's list to file(0)" +
                        "\nExit(10)");

        int a = sc.nextInt();
       /* switch (a) {
            case 1:
                addUser();
                break;
            case 2:
                updateUser();
                break;
            case 3:
                deleteUser();
                break;
            case 4:
                addBook();
                break;
            case 5:
                updateBook();
                break;
            case 6:
                deleteBook();
                break;
            case 7:
                uploadBooksListFromFile();
                break;
            case 8:
                uploadUsersListFromFile();
                break;
            case 9:
                saveBooksListToFile();
                break;
            case 0:
               { saveUsersListToFile();
                break;
                case 10:
                break;
        }*/

        System.out.println("Admin panel");
    }


    private void openUserPanel(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Check aviable book's(1) \nOrder book(2) \nReturn book(3) \nCheck my book's(4)\nExit(5)");
        int a = sc.nextInt();
        switch (a) {
            case 1:
            {getAllBooks(user);}

            case 2:
            {rentSomeBook(user);}

            case 3:
            { returnSomeBook(user);}

            case 4:
            {checkMyBooks(user);}

            case 5:
            {break;}
        }

    }


}
