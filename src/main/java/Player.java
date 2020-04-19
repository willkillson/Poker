public class Player {
    String email;
    int cash;

    public Player(String email,int startingBank){
        this.email = email;
        this.cash = startingBank;
    }

    public int chargePlayer(int amount){
        this.cash -= amount;
        return amount;
    }

    public void payPlayer(int amount){
        this.cash += amount;
    }

    @Override
    public String toString() {
        return "Player{" +
                "email='" + email + '\'' +
                ", cash=" + cash +
                '}';
    }
}
