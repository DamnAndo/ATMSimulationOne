import model.People;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    public static List<People> peoples = new ArrayList<>();


    public static void init(List<People> peoples){
        People people1 = new People("John Doe","012108",100,"112233");
        People people2 = new People("Jane Doe","932012",30,"112244");
        peoples.add(people1);
        peoples.add(people2);
    }

    public static void main(String[] args) {

        init(peoples);
        while (true){
            welcomeScreen();
        }
    }

    public static void welcomeScreen(){
        Scanner input = new Scanner(System.in);
        System.out.println("=== Welcome Screen ===");
        System.out.println("Enter Account Number: ");
        String accountNumber = input.nextLine();
        System.out.println("Enter PIN: ");
        String pin = input.nextLine();

        checkAccount(accountNumber,pin);

    }

    public static void checkAccount(String accountNumber,String pin){
        if(accountNumber.length() != 6){
            System.out.println("Account Number should have 6 digits length");
            welcomeScreen();
        }

        if(pin.length() != 6){
            System.out.println("PIN should have 6 digits length");
            welcomeScreen();
        }
        if(!accountNumber.matches("[0-9]+")){
            System.out.println("Account Number should only contains numbers");
            welcomeScreen();
        }

        if(!pin.matches("[0-9]+")){
            System.out.println("PIN should only contains numbers");
            welcomeScreen();
        }

        if(accountNumber.length() == 6 && pin.length() == 6){
            People data = new People();
            for (People peoplefor : peoples){
                if(peoplefor.getAccountNumber().equals(accountNumber) && peoplefor.getPin().equals(pin)){
                    System.out.println("Succes Login");
                    data = peoplefor;
                }
            }

            if(data != null){
                transactionScreen(data);
            }else{
                welcomeScreen();
            }

        }
    }

    public static void transactionScreen(People people){
        System.out.println("Transaction Screen People "+people.toString());
        Scanner input = new Scanner(System.in);
        System.out.println("=== Transaction Screen ===");

        System.out.println("[1] Withdraw");
        System.out.println("[2] Fund Transfer");
        System.out.println("[3] Exit");
        System.out.println("---------------------");
        System.out.print("Choose menu > ");


        int inputMenuTransaction = input.nextInt();
        checkTransactionScreen(inputMenuTransaction,people);

    }

    public static void checkTransactionScreen(int input,People people){
        if (input == 1){
            Withdraw.goToWithdraw(people);
        }

        if (input == 2){
            FundTransfer.goToFundTransfer(people);
        }

        if(input == 3){
            welcomeScreen();
        }
    }



}
