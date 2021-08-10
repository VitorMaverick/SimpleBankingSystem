package banking.repository;

import banking.model.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountRepo {
    UtilDB db;

    public AccountRepo(String args) {
        this.db = new UtilDB(args);
    }

    public void createTable(){

        try(Connection conn = db.getConn()){
            try(Statement statement = conn.createStatement()){
                int result = statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER PRIMARY KEY," +
                        "pin TEXT NOT NULL," +
                        "number TEXT NOT NULL," +
                        "balance INTEGER DEFAULT (0))");


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void put(Account ac){


        try(Connection conn = db.getConn()){
            try(Statement statement = conn.createStatement()){
                String stmt = String.format("INSERT INTO CARD (pin, number) VALUES ('%s', '%s')", ac.getId(), ac.getCard());
                int result = statement.executeUpdate(stmt);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
