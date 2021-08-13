package banking;

import banking.controller.Manager;
import banking.model.Account;
import banking.repository.AccountRepo;
import banking.repository.UtilDB;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int command;
        Scanner scanner = new Scanner(System.in);
        Manager manager = new Manager();
        AccountRepo ac = new AccountRepo(args[1]);
        ac.createTable();



        do{
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");
            command = scanner.nextInt();

            if(command>2){
                continue;
            }

            switch (command){
                case (0):

                    break;
                case (1):
                    Account registeredAccount = manager.registerAccount(ac);
                    System.out.println("Your card has been created");
                    System.out.println("Your card number:");
                    System.out.println(registeredAccount.getCard());
                    System.out.println("Your card PIN:");
                    System.out.println(registeredAccount.getId());
                    break;
                case (2):
                    System.out.println("Enter your card number:");
                    double card = scanner.nextDouble();
                    System.out.println("Enter your PIN:");
                    int id = scanner.nextInt();
                    Account account = manager.login(card, id);
                    if(account != null){
                        int innerCommand;
                        System.out.println("You have successfully logged in!");
                        do{
                            System.out.println("1. Balance");
                            System.out.println("2. Add income");
                            System.out.println("3. Do transfer");
                            System.out.println("4. Close account");
                            System.out.println("5. Log out");
                            System.out.println("0. Exit");
                            innerCommand =  scanner.nextInt();

                            if(innerCommand == 1){
                                String output = String.format("Balance: %.2f", account.getBalance());
                                System.out.println(output);
                            }else if(innerCommand == 2) {
                                System.out.println("Enter income:");
                                double valor = scanner.nextDouble();
                                if(account.retirada(valor)){
                                    System.out.println("Income was added!");
                                }else{
                                    System.out.println("Not enough money!");
                                }
                            }else if(innerCommand == 2) {
                                System.out.println("Transfer\n" +
                                        "Enter card number:");
                                double valor = scanner.nextDouble();
                                if(account.retirada(valor)){
                                    System.out.println("Income was added!");
                                }else{
                                    System.out.println("Not enough money!");
                                }
                            }else if(innerCommand == 0){
                                command = 0;
                                break;
                            }

                        }while(innerCommand < 3);
                    }else{
                        System.out.println("Wrong card number or PIN!");
                    }

                    break;
            }

        }while(command != 0);

        System.out.println("Bye!");
    }
}