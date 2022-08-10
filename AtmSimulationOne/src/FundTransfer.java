import model.People;

import java.util.Scanner;

public class FundTransfer {
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
        goToFundTransfer3(people,peopleTransfer);
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
            Main.transactionScreen(people);
            checkedValid = false;
        }

        People secondPeople = new People();
        for(People resultPeople : Main.peoples){
            if(resultPeople.getAccountNumber().equals(peopleTransfer.getAccountNumber())){
                secondPeople = resultPeople;
            }
        }

        if (secondPeople == null){
            System.out.println("Invalid account");
            Main.transactionScreen(people);
            checkedValid = false;
        }

        if (peopleTransfer.getBalance() > 1000){
            System.out.println("Maximum amount to transfer is $1000");
            Main.transactionScreen(people);
            checkedValid = false;
        }

        if (peopleTransfer.getBalance() < 1){
            System.out.println("Minimum amount to transfer is $1");
            Main.transactionScreen(people);
            checkedValid = false;
        }

        if (peopleTransfer.getBalance() > people.getBalance()){
            System.out.println("Insufficient balance");
            Main.transactionScreen(people);
            checkedValid = false;

        }

        if (inputRefNumber == 0){
            System.out.println("Invalid Reference Number");
            Main.transactionScreen(people);
            checkedValid = false;
        }

        if(checkedValid){
            if(input == 1){
                goToFundTransferSummary(people,peopleTransfer,secondPeople,inputRefNumber);
            }else{
                Main.transactionScreen(people);
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

            Main.transactionScreen(people);
        }

        if (inputMenu == 2){
            Main.transactionScreen(people);
        }
    }
}
