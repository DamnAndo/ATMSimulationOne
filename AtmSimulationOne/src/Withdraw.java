import model.People;

import java.time.LocalDateTime;
import java.util.Scanner;


public class Withdraw {

    public static void goToWithdraw(People people){
        Scanner input = new Scanner(System.in);
        System.out.println("=== Withdraw Screen ===");

        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.println("---------------------");
        System.out.print("Choose menu > ");
        int inputMenuWithdraw = input.nextInt();
        checkWithdrawScreen(inputMenuWithdraw,people);
    }

    public static void checkWithdrawScreen(int input,People people){
        if(input == 1){
            goToSummaryScreen(10,people);
        }
        if(input == 2){
            goToSummaryScreen(50,people);
        }
        if(input == 3){
            goToSummaryScreen(100,people);
        }

        if(input == 4){
            goToWithdrawScreen(people);
        }

        if(input == 5){
            Main.transactionScreen(people);
        }
    }

    public static void goToWithdrawScreen(People people){
        Scanner input = new Scanner(System.in);
        System.out.println("Other Withdraw ");
        System.out.println("Enter amount to withdraw: ");
        int inputOtherWithdraw = input.nextInt();
        checkOtherWithdraw(inputOtherWithdraw,people);
    }

    public static void checkOtherWithdraw(int input, People people){

        if(input > 1000){
            System.out.println("Maximum amount to withdraw is $1000");
            Main.transactionScreen(people);
        }

        if(input % 10 != 0){
            System.out.println("Invalid ammount");
            Main.transactionScreen(people);
        }

        if(input > people.getBalance()){
            System.out.println("Insufficient balance $"+people.getBalance());
            Main.transactionScreen(people);
        }else{
            int result = people.getBalance() - input;
            people.setBalance(result);
            Main.transactionScreen(people);
        }

    }

    public static void goToSummaryScreen(int withdraw,People people){
        Scanner input = new Scanner(System.in);
        System.out.println("Summary ");
        System.out.println("Date: "+ LocalDateTime.now());
        System.out.println("Withdraw : $"+withdraw);
        System.out.println("Balance : $"+people.getBalance());
        System.out.println();
        System.out.println();
        System.out.println("1. Transaction");
        System.out.println("2. Exit");

        int inputOption = input.nextInt();
        checkSummaryScreen(inputOption,withdraw,people);
    }

    public static void checkSummaryScreen(int input,int withdraw,People people){
        if(input == 1){
            if(withdraw > people.getBalance()){
                System.out.println("Insufficient balance $"+people.getBalance());
                goToWithdraw(people);
            }else{
                int result = people.getBalance() - withdraw;
                people.setBalance(result);
                goToWithdraw(people);
            }
        }

        if(input == 2){
            goToWithdraw(people);
        }
    }
}
