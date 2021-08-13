package banking.controller;

import banking.model.Account;
import banking.repository.AccountRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryTest {
    public static void main(String[] args) throws SQLException {
        Account account = new Account();
        account.setId("0001");
        account.setCard("0000000000000002");

        AccountRepo repo = new AccountRepo();

        repo.createTable();
        repo.put(account);

        Account newAc = repo.queryPin("0000000000000002");
        System.out.println(newAc.getBalance());

        //String result = repo.queryPin("0000000000000001");
        //System.out.println(result);

        //repo.query("0000000000000002");
    /*
        ArrayList<Account> list = repo.queryArray("0000000000000002");
        for(Account ac: list){
            System.out.println(ac.getId());
        }

     */



    }
}
