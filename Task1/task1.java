//NOTE: I have used MySQL for storing the task data instead of an ordinary ArrayList
//So I am using JDBC driver for making Java and MySQL connection
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class task1 {

    //tasks() function for cmd-line and input of choice
    public static void tasks(){
        //Scanner Class for taking input
        Scanner in = new Scanner(System.in);

        System.out.print("Commands:-\n" +
                "1. View tasks\n" +
                "2. Add task\n" +
                "3. Mark task as complete\n" +
                "4. Remove tasks\n" +
                "5. Exit\n" +
                "Enter your choice(1..5): ");
        //'n' will store the choice of the user for the function to be performed
        //Type is 'String' so that the cmd-line does not stop when user enters a non-integer value
        String n=in.next();
        //Using switch case to make the respective function calls as per the input
            switch (n){
                case "1":
                    //Making the call to the function that will perform the view task list operation
                    viewTasks();
                    break;

                case "2":
                    //Making the call to the function that will add the task to the database
                    addTask();
                    break;

                case "3":
                    //Making the call to the function that will mark a specific task as completed
                    markTask();
                    break;

                case "4":
                    //Making the call to the function that delete a specific task
                    deleteTask();
                    break;

                case "5":
                    System.out.println("Exiting...");
                    //Exiting the cmd-line using System.exit()
                    System.exit(0);
                    break;

                default:
                    //Performing input validation using default case
                    System.out.println("Invalid choice...\nEnter a number between 1 and 5.\n");
                    //Recursive call to keep the cmd-line running even after incorrect input
                    tasks();
                    break;
            }
    }

    //viewTask() function for viewing the task list
    public static void viewTasks(){
        //Try and Catch block is needed when making JDBC connections
        try
        {
            //We make the connection to the 'codingsamurai' schema on MySQL
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/codingsamurai", "root", "sql4data@Dhanta4Saatvik");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = statement.executeQuery("select * from task1");

            //Printing the output to view the task list
            System.out.println("Task List:");
            System.out.println("   |   Task Name   |   Description   |   Due Date   |   Completed   |");
            System.out.println("   +---------------+-----------------+--------------+---------------+");
            while (resultSet.next()) {
                System.out.print("-> ");
                System.out.println("|  "+resultSet.getString("taskname")+
                        "  |  " + resultSet.getString("description")+
                        "  |  " + resultSet.getString("due_date")
                        +"  |  "+resultSet.getString("completed") + " |");
            }
            System.out.println("\n--------------------------------------------------------------------------");

            //Recursive call to keep the cmd-line running and not end after finishing its function
            tasks();

            //Closing the objects because we cannot make new objects with the same name in other methods
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //addTask() function for adding new task to the list
    public static void addTask(){
        //Same try and catch block for JDBC
        try
        {
            //Same connection made to MySQL
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/codingsamurai", "root", "sql4data@Dhanta4Saatvik");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = statement.executeQuery("select * from task1");

            //Scanner class for input
            Scanner in = new Scanner(System.in);
            System.out.print("Enter task name: ");
            //'name' stores the 'taskname'
            String name = in.next();
            System.out.print("Enter task description: ");
            //'desc' stores the 'description'
            String desc = in.next();
            System.out.print("Enter due date: ");
            //'date' stores the 'due_date'
            String date = in.next();
            //'compl' stores the 'completed'
            //By default the completed status is 'Not Completed'
            String compl = "Not Completed";
            //Storing the MySQL command in variable 's' to insert a new row in the table with the values stored in the above variables
            String s = "insert into task1(taskname, description, due_date, completed) values(\"" + name + "\",\"" + desc + "\",\"" + date + "\",\""+ compl + "\")";
            //Executing the command which stored as string in 's'
            statement.executeUpdate(s);
            //Call to the tasks() function to keep the command line running
            tasks();

            //Objects closed
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    //markTask() function for marking a specific task as complete
    public static void markTask(){
        //Try and catch for JDBC
        try
        {
            //Same connection for JDBC
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/codingsamurai", "root", "sql4data@Dhanta4Saatvik");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = statement.executeQuery("select * from task1");

            //Scanner for input
            Scanner in = new Scanner(System.in);
            //'i' is the counter variable so the task names can be printed with numbering
            int i=1;
            //Printing the task names with numbering
            while (resultSet.next()) {
                System.out.print(i+": ");
                i++;
                System.out.println(resultSet.getString("taskname"));
            }
            System.out.print("Enter task name which you want to mark as complete: ");
            //'name' stores the task name that will be marked as complete
            String name = in.next();
            //'s' stores the MySQL query for value update
            String s = "update task1 set completed = \"Completed\" where taskname=\""+name+"\"";
            //Executing the query stored in 's'
            statement.executeUpdate(s);
            System.out.println("Task updated successfully...\n");
            //Call to keep the cmd-line running
            tasks();

            //Objects closed
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //deleteTask() function for deleting a specific task
    public static void deleteTask(){
        //Try and catch for JDBC
        try
        {
            //Same connection for JDBC
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/codingsamurai", "root", "sql4data@Dhanta4Saatvik");

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = statement.executeQuery("select * from task1");

            //Scanner for input
            Scanner in = new Scanner(System.in);
            //'i' is the counter variable so the task names can be printed with numbering
            int i=1;
            //Printing the task names with numbering
            while (resultSet.next()) {
                System.out.print(i+": ");
                i++;
                System.out.println(resultSet.getString("taskname"));
            }
            System.out.print("Enter task name which you want to remove: ");
            //'name' stores the task name
            String name = in.next();
            //'s' stores the MySQL query for deleting a row that contains the task
            String s = "delete from task1 where taskname = \""+name+"\"";
            //Executing the query stored in 's'
            statement.executeUpdate(s);
            System.out.println("Task removed successfully...\n");
            //Call to keep the cmd-line running
            tasks();

            //Objects closed
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //main() function for running the program
    public static void main(String[] args){
        //Call to start the cmd-line
        tasks();
    }
}
