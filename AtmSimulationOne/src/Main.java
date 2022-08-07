import model.People;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<People> peoples = new ArrayList<>();


    public static List<People> init(List<People> peoples){
        People people1 = new People("John Doe","012108",100,"112233");
        People people2 = new People("Jane Doe","932012",30,"112244");
        peoples.add(people1);
        peoples.add(people2);
        return peoples;
    }

    public static void main(String[] args) {

        peoples = init(peoples);
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
                    data = peoplefor;
                }
            }

            transactionScreen(data);
        }
    }

    public static void transactionScreen(People people){
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
            goToWithdraw(people);
        }

        if (input == 2){
            goToFundTransfer(people);
        }

        if(input == 3){
            welcomeScreen();
        }
    }

    public static void goToFundTransfer(People people){
        People peopleTransfer = new People();
        Scanner input = new Scanner(System.in);
        System.out.println("**Fund Transfer Screen 1 :**");
        System.out.println("Please enter destination account and \n" +
                "  press enter to continue or \n" +
                "  press cancel (Esc) to go back to Transaction:");

        String inputAccountNumberTf = input.nextLine();
        peopleTransfer.setAccountNumber(inputAccountNumberTf);
        goToFundTransfer2(people,peopleTransfer);

    }

    public static void goToFundTransfer2(People people,People peopleTransfer){
        Scanner input = new Scanner(System.in);
        System.out.println("**Fund Transfer Screen 2 :**");
        System.out.println("Please enter transfer amount and press enter to continue or \n" +
                "press enter to go back to Transaction:");

        int inputTransfer = input.nextInt();
        peopleTransfer.setBalance(inputTransfer);
    }

    public static void goToFundTransfer3(People people,People peopleTransfer){
        Scanner input = new Scanner(System.in);
        System.out.println("**Fund Transfer Screen 3 :**");
        System.out.println("Reference Number: \n" +
                "press enter to continue or press enter to go back to Transaction:");

        int inputRefNumber = input.nextInt();
        goToFundTransfer4(people,peopleTransfer, inputRefNumber);
    }

    public static void goToFundTransfer4(People people,People peopleTransfer,int inputRefNumber){
        Scanner input = new Scanner(System.in);
        System.out.println("Transfer Confirmation");
        System.out.println("Destination Account : "+peopleTransfer.getAccountNumber());
        System.out.println("Transfer Amount : "+peopleTransfer.getBalance());
        System.out.println("Reference Number : "+inputRefNumber);
        System.out.println();
        System.out.println();
        System.out.println("[1] Confirm Trx");
        System.out.println("[2] Cancel Trx");
        System.out.println("Choose option[2]: ");
        int inputMenuFundtransfer = input.nextInt();
        checkFundTransfer(inputMenuFundtransfer,people,peopleTransfer,inputRefNumber);

    }

    public static void checkFundTransfer(int input,People people,People peopleTransfer,int inputRefNumber){
        boolean checkedValid = true;
        if (!peopleTransfer.getAccountNumber().matches("[0-9]+")){
            System.out.println("Invalid account");
            transactionScreen(people);
            checkedValid = false;
        }

        People secondPeople = new People();
        for(People resultPeople : peoples){
            if(resultPeople.getAccountNumber().contains(peopleTransfer.getAccountNumber())){
                secondPeople = resultPeople;
            }else{
                System.out.println("Invalid account");
                transactionScreen(people);
                checkedValid = false;
            }
        }

        if (peopleTransfer.getBalance() > 1000){
            System.out.println("Maximum amount to transfer is $1000");
            transactionScreen(people);
            checkedValid = false;
        }

        if (peopleTransfer.getBalance() < 1){
            System.out.println("Minimum amount to transfer is $1");
            transactionScreen(people);
            checkedValid = false;
        }

        if (peopleTransfer.getBalance() > people.getBalance()){
            System.out.println("Insufficient balance");
            transactionScreen(people);
            checkedValid = false;

        }

        if (inputRefNumber == 0){
            System.out.println("Invalid Reference Number");
            transactionScreen(people);
            checkedValid = false;
        }

        if(checkedValid){
            if(input == 1){
                goToFundTransferSummary(people,peopleTransfer,secondPeople,inputRefNumber);
            }else{
                transactionScreen(people);
            }
        }
    }

    public static void goToFundTransferSummary(People people,People peopleTransfer,People secondPeople,int inputRefNumber){
        Scanner input = new Scanner(System.in);
        System.out.println("Fund Transfer Summary\n" +
                "  Destination Account : "+secondPeople.getAccountNumber()+"\n" +
                "  Transfer Amount     : $"+peopleTransfer.getBalance()+"\n" +
                "  Reference Number    : "+inputRefNumber+"\n" +
                "  Balance             : $"+people.getBalance());
        System.out.println();
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        int inputMenu = input.nextInt();

        checkFundTransferSummary(inputMenu,people,peopleTransfer,secondPeople,inputRefNumber);
    }

    public static void checkFundTransferSummary(int inputMenu,People people,People peopleTransfer,People secondPeople,int inputRefNumber){
        if(inputMenu == 1){
            int result1 = people.getBalance() - peopleTransfer.getBalance();
            people.setBalance(result1);

            int result2 = secondPeople.getBalance() + peopleTransfer.getBalance();
            secondPeople.setBalance(result2);

            transactionScreen(people);
        }

        if (inputMenu == 2){
            transactionScreen(people);
        }
    }

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
            goToSummaryScreen(100,people);
        }

        if(input == 5){
            transactionScreen(people);
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
            goToWithdrawScreen(people);
        }

        if(input % 10 != 0){
            System.out.println("Invalid ammount");
            goToWithdrawScreen(people);
        }

        if(input > people.getBalance()){
            System.out.println("Insufficient balance $"+people.getBalance());
            goToWithdrawScreen(people);
        }else{
            int result = people.getBalance() - input;
            people.setBalance(result);
            goToWithdrawScreen(people);
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
