package banking.model;

public class Account {
    String card;
    String id;
    double balance;

    public Account() {
        this.balance = 0;
    }

    public Account(String id, String card) {
        this.card = card;
        this.id = id;
        this.balance = 0;
    }

    public boolean compareId(String id){
        if(this.id.equals(id)){
            return true;
        }else{
            return false;
        }
    }

    public boolean compareCard(String card){
        if(this.card.equals(card)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "card='" + card + '\'' +
                ", id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }

    //getters and setters
    public String getCard() {
        return card;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}