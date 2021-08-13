package banking.repository;

import banking.model.Account;

import java.sql.*;
import java.util.ArrayList;

public class AccountRepo {
    UtilDB db;

    public AccountRepo(){
        this.db = new UtilDB();
    }

    public AccountRepo(String args) {
        this.db = new UtilDB(args);
    }

    public boolean createTable() {
        try(Connection conn = db.getConn()){
            try(Statement statement = conn.createStatement()){
                int result = statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER PRIMARY KEY," +
                        "pin TEXT NOT NULL," +
                        "number TEXT NOT NULL," +
                        "balance INTEGER DEFAULT (0))");

                return true;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean put(Account ac) {
        try(Connection conn = db.getConn()){
            try(Statement statement = conn.createStatement()){
                String stmt = String.format("INSERT INTO CARD (pin, number) VALUES ('%s', '%s')", ac.getId(), ac.getCard());
                int result = statement.executeUpdate(stmt);
                return true;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void query(String cardNumber){
        ResultSet resultset = null;
        ArrayList<Account> list = new ArrayList<>();

        try(Connection conn = db.getConn()){
            try(PreparedStatement statement = conn.prepareStatement("SELECT * FROM card WHERE number = ?")){

                statement.setString(1, cardNumber);
                resultset =  statement.executeQuery();
                while(resultset.next()){
                    System.out.println(resultset.getString(2));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ArrayList<Account> queryArray(String cardNumber){
        ResultSet resultset = null;
        ArrayList<Account> list = new ArrayList<>();

        try(Connection conn = db.getConn()){
            try(PreparedStatement statement = conn.prepareStatement("SELECT * FROM card WHERE number = ?")){

                statement.setString(1, cardNumber);
                resultset =  statement.executeQuery();
                while(resultset.next()){
                    String pin = resultset.getString(2);
                    String number = resultset.getString(3);
                    Account ac = new Account(pin, number);
                    list.add(ac);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public Account queryPin(String cardNumber){

        try(Connection conn = db.getConn()){
            try(PreparedStatement statement  = conn.prepareStatement("SELECT * FROM CARD WHERE number = ?")){
                statement.setString(1, cardNumber);
                ResultSet rs = statement.executeQuery();
                if(rs.next()){
                    String pin = rs.getString(2);
                    String number = rs.getString(3);
                    int balance = rs.getInt(4);
                    Account ac = new Account( pin, number, balance);
                    return ac;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean updateBalance(String cardNumber, double valor){
        try(Connection conn = db.getConn()){
            try(PreparedStatement statement = conn.prepareStatement("UPDATE CARD SET balance = ? WHERE cardNumber = ?")){

                statement.setDouble(1, valor);
                statement.setString(2, cardNumber);
                int resul = statement.executeUpdate();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}

