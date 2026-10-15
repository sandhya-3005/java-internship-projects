import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class LibraryManagementSystem{
 static ArrayList<Book> books=new ArrayList<>();
 static Scanner scanner=new Scanner(System.in);

 public static void addBook() {
     int id;
     String title;
     String author;

     while (true) {
         System.out.println("Enter book ID:");
         try {
             id = scanner.nextInt();
             scanner.nextLine();
             if (id <= 0) {
                 System.out.println("Book ID must be positive.Try again.");
                 continue;
             }
             break;
         } catch (InputMismatchException e) {
             System.out.println("Invalid input! Please enter a valid number");
             scanner.nextLine();
         }

     }
     for(Book book:books)
     {
         if(book.getId()==id)
         {
             System.out.println("Id already Existed.");
             return;
         }
     }

     while(true)
     {
         System.out.println("Enter title of the book:");
         title= scanner.nextLine();
         if(title.isEmpty())
         {
             System.out.println("Title cannot be empty.Try again.");
             continue;
         }
         break;
     }
     while(true)
     {
         System.out.println("Enter author name:");
         author=scanner.nextLine().trim();
         if(author.isEmpty()){
             System.out.println("Author name cannot be empty.Try again");
             continue;
         }
         break;
     }
     Book book=new Book(id,title,author);
     books.add(book);
     System.out.println("Book added successfully.");

 }



 public static void viewBooks() {
     if (books.isEmpty()) {
         System.out.println("No books Available..");
         return;
     }
     int bookCount = 1;
     for (Book book : books) {
         System.out.println("Book" + bookCount);
         System.out.println("Book ID: " + book.getId());
         System.out.println("Book name: " + book.getTitle());
         System.out.println("Author: " + book.getAuthor());
         System.out.println("Status " + (book.isIssued() ? "Issued" : "Available"));
         bookCount++;

     }
 }


     public static void removeBook()
     {
         int id;
         while(true) {
             System.out.println("Enter book ID to remove:");

             try {
                 id = scanner.nextInt();
                 scanner.nextLine();
                 break;
             } catch (InputMismatchException e) {
                 System.out.println("Invalid input! Please enter a valid number");
                 scanner.nextLine();
             }
         }

         Book bookToRemove=null;
         for(Book book:books)
         {
             if(book.getId()==id)
             {
                 bookToRemove=book;
                 break;
             }
         }
         if(bookToRemove==null)
         {
             System.out.println("Book not found.");
         }
         else{
             books.remove(bookToRemove);
             System.out.println("Book removed successfully.");

         }

     }
     public static void searchBook()
     {
         System.out.println("1.Search by book ID");
         System.out.println("2.Search by book Title");
         System.out.println("3.Enter your choice");
         int choice= scanner.nextInt();
         scanner.nextLine();
         switch(choice) {
             case 1:{
                 System.out.println("Enter book ID to search");
                 int id = scanner.nextInt();
                 scanner.nextLine();
                 boolean found = false;
                 for (Book book : books) {
                     if (book.getId() == id) {
                         System.out.println(":Book IB:" + book.getId());
                         System.out.println("Title: " + book.getTitle());
                         System.out.println("Author: " + book.getAuthor());
                         System.out.println("Status:" + (book.isIssued() ? "Issued" : "Available"));
                         found = true;
                         break;
                     }
                 }
                 if (!found) {
                     System.out.println("Book not found");

                 }
                 break;
         }
             case 2:{
                 System.out.println("Enter book title to search:");
                 String title= scanner.nextLine().trim();
                  boolean found=false;
                 for(Book book:books)
                 {
                     if(book.getTitle().equalsIgnoreCase(title))
                     {
                         System.out.println("Book ID:" + book.getId());
                         System.out.println("Title: " + book.getTitle());
                         System.out.println("Author: " + book.getAuthor());
                         System.out.println("Status:" + (book.isIssued() ? "Issued" : "Available"));
                         found = true;
                         break;
                     }
                 }
                 if(!found)
                 {
                     System.out.println("Book not found");

                 }
                 break;

                     }
             default:
                 System.out.println("Invalid choice!");
         }
         }
         public static void issueBook()
         {  int id;
             while(true){

             System.out.println("Enter book ID to issue:");
             try{
                 id=scanner.nextInt();
                 scanner.nextLine();
                 break;
             }
             catch(InputMismatchException e) {
                 System.out.println("Invalid input! Please enter a valid number.");
                 scanner.nextLine();
             }
             }
             Book bookToIssue=null;
             for(Book book:books)
             {
                 if(book.getId()==id)
                 {
                     bookToIssue=book;
                     break;
                 }
             }
             if(bookToIssue==null)
             {
                 System.out.println("Book not found!");
             }
             else if(bookToIssue.isIssued())
             {
                 System.out.println("Book is already issued!");
             }
             else {
                 bookToIssue.setIssued(true);
                 System.out.println("Book issued successfully");
             }
             }
         public static void returnBook()
         {
             int id;
             while(true)
             {
                 System.out.println("Enter book ID to return:");
                 try{
                     id=scanner.nextInt();
                     scanner.nextLine();
                     break;
                 }
                 catch(InputMismatchException e){
                     System.out.println("Invalid Input! please enter a valid number");
                     scanner.nextLine();

                 }
             }
             Book bookToReturn=null;
             for(Book book : books)
             {
                 if(book.getId()==id)
                 {
                     bookToReturn=book;
                     break;
                 }
             }
             if(bookToReturn==null)
             {
                 System.out.println("Book not found!");
             }
             else if(!bookToReturn.isIssued())
                 System.out.println(" This Book is not been issued.");
             else {
                 bookToReturn.setIssued(false);
                 System.out.println("Book returned successfully");

         }
    }
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Remove Book");
            System.out.println("4. Search Book");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewBooks();
                    break;

                case 3:
                    removeBook();
                    break;

                case 4:
                    searchBook();
                    break;

                case 5:
                    issueBook();
                    break;

                case 6:
                    returnBook();
                    break;

                case 7:
                    System.out.println("Thank you for using the Library Management System!");
                    return;

                default:
                    System.out.println("Invalid choice! Please select 1-7.");
            }
        }
    }


}





