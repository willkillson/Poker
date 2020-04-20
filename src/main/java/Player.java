import java.util.ArrayList;

public class Player {

    ArrayList<String> currentHand;

    String email;
    int cash;

    public Player(String email,int startingBank){
        this.email = email;
        this.cash = startingBank;
        this.currentHand = new ArrayList<>();
    }

    public int chargePlayer(int amount){
        this.cash -= amount;
        return amount;
    }

    public void payPlayer(int amount){
        this.cash += amount;
    }

    public void call(){}

    public void bet(){}

    public void fold(){}

    public void check(){}

    @Override
    public String toString() {
        return "Player{" +
                "email='" + email + '\'' +
                ", cash=" + cash +
                '}';
    }
}
