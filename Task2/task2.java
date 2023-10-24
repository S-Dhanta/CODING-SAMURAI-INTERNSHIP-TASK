import java.util.*;

public class task2 {
    //start() function for cmd-line
    public static void start(){
        //Scanner Class for taking input
        Scanner in = new Scanner(System.in);

        System.out.println("---CALCULATOR---"+
                "\n1. Addition"+
                "\n2. Subtraction"+
                "\n3. Multiplication"+
                "\n4. Division"+
                "\n5. EXIT");
        System.out.print("Enter your choice(1..5): ");
        String n=in.next();
        //'n' will store the choice of the user for the function to be performed
        //Type is 'String' so that the cmd-line does not stop when user enters a non-integer value
        switch (n){
            case "1":
                //Making the call to the function that will perform addition
                addition();
                break;

            case "2":
                //Making the call to the function that will perform subtraction
                subtraction();
                break;

            case "3":
                //Making the call to the function that will perform multiplication
                multiplication();
                break;

            case "4":
                //Making the call to the function that will perform division
                division();
                break;

            case "5":
                System.out.println("Exiting...");
                System.out.println("Calculator has stopped.");
                //Exiting the calculator using System.exit()
                System.exit(0);
                break;

            default:
                //Performing input validation using default case
                System.out.println("Invalid choice...\nEnter a number between 1 and 5.\n");
                //Recursive call to keep the cmd-line running even after incorrect input
                start();
                break;
        }

        in.close();
    }
    //inp() function is responsible for taking input and returning it in the form of an array
    public static int[] inp(){
        //Scanner Class for taking input
        Scanner in = new Scanner(System.in);

        System.out.println("--Input--");
        //Declaring array for length 2 that will store the two input values
        int[] nums = new int[2];
        System.out.print("Number 1 -> ");
        nums[0] = in.nextInt();
        System.out.print("Number 2 -> ");
        nums[1] = in.nextInt();

        return nums;
    }
    public static void addition(){
        //Performing addition
        int[] nums = inp();
        System.out.println("--Output--");
        System.out.println(nums[0]+" + "+nums[1]+" = "+(nums[0]+nums[1])+"\n");
        //Call to the start() function to keep the command line running
        start();
    }
    public static void subtraction(){
        //Performing subtraction
        int[] nums = inp();
        System.out.println("--Output--");
        System.out.println(nums[0]+" - "+nums[1]+" = "+(nums[0]-nums[1])+"\n");
        //Call to the start() function to keep the command line running
        start();
    }
    public static void multiplication(){
        //Performing multiplication
        int[] nums = inp();
        System.out.println("--Output--");
        System.out.println(nums[0]+" x "+nums[1]+" = "+(nums[0]*nums[1])+"\n");
        //Call to the start() function to keep the command line running
        start();
    }
    public static void division(){
        //Performing division
        int[] nums = inp();
        System.out.println("--Output--");
        //To handle divide-by-zero case
        if(nums[1] == 0){
            System.out.println("**ILLEGAL INPUT**");
            System.out.println("Denominator cannot be \"0\"\n");
            //Call to the start() function to keep the command line running
            start();
        }
        System.out.println(nums[0]+" ÷ "+nums[1]+" = "+((double)nums[0]/(double)nums[1])+"\n");
        //Call to the start() function to keep the command line running
        start();
    }

    //main() function for running the program
    public static void main(String[] args){
        //Call to start the cmd-line
        start();
    }
}
