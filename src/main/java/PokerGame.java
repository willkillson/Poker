import java.util.*;

public class PokerGame implements Runnable {

    Cards cards;
    boolean updateScreen;

    public ArrayList<String> deltCards;

    HashMap<String, Player> playerMap;
    Queue<Player> playerQueue;
    Stack<Player> turnStack;

    int maxSize = 10;
    int currentBlinds = 10;

    int currentPot = 0;

    public PokerGame(Cards cards){
        this.cards = cards;
        this.playerQueue = new LinkedList<>();
        this.turnStack = new Stack<>();
        this.playerMap = new HashMap<>();
        this.deltCards = new ArrayList<>();

        Player p1 = new Player("kevin@gmail.com",1000);
        Player p2 = new Player("rita@gmail.com",1000);
        Player p3 = new Player("kelly@gmail.com",1000);

        playerMap.put(p1.email,p1);
        playerMap.put(p2.email,p2);
        playerMap.put(p3.email,p3);

        playerQueue.add(p1);
        playerQueue.add(p2);
        playerQueue.add(p3);

    }

    @Override
    synchronized public void run() {

        //big blinds
        //little blinds
        while(playerMap.size()>=2){
            //start of turn
            createTurnStack();
            System.out.println("");
            System.out.println("Hand start");
            this.deltCards = deal(52);

            String bigBlinds = this.turnStack.pop().email;
            String smallBlinds = this.turnStack.pop().email;

            System.out.println("BigBlinds: "+bigBlinds);
            this.currentPot += playerMap.get(bigBlinds).chargePlayer(currentBlinds);
            System.out.println("SmallBlinds: "+smallBlinds);
            this.currentPot += playerMap.get(smallBlinds).chargePlayer(currentBlinds/2);

            this.turnStack.push(playerMap.get(smallBlinds));
            this.turnStack.push(playerMap.get(bigBlinds));

            while(!this.turnStack.isEmpty()){
                //play out the betting process
                String email = this.turnStack.pop().email;
                System.out.println("Turn: "+email);
            }
            //decide the winner
            String winner = decideWinner();
            playerMap.get(winner).payPlayer(this.currentPot);
            System.out.println("Winning player: "+winner);
            this.currentPot = 0;
            //remove losers
            for(String s: playerMap.keySet()){
                if(playerMap.get(s).cash<=0){
                    Player re = playerMap.remove(s);
                    playerQueue.remove(re);
                }
            }

            printStandings();
            try{
                this.wait(2000);

            }catch(InterruptedException ecp){
                ecp.printStackTrace();
            }

            //game is over, reorder the playerQueue
            setPlayerQueue();
        }
    }

    ArrayList<String> deal(int amount){

        Set<String> cards = this.cards.cardMap.keySet();
        ArrayList<String> cardList = new ArrayList<>();//fulldeck
        for(String s:cards){
            cardList.add(s);
        }

        ArrayList<String> hand = new ArrayList<>();
        for(int i = 0;i<amount;i++){

            int random = (int) Math.floor(Math.random()*cardList.size());
            Collections.shuffle(cardList);
            for(int j = 0;j< cardList.size();j++){
                if(j==random){
                    hand.add(cardList.get(j));
                    cardList.remove(j);
                }
            }
        }

        System.out.println(hand.size());

        return hand;

    }

    void createTurnStack(){
        for(Player p:playerQueue){
            this.turnStack.add(p);
        }
    }

    void setPlayerQueue(){
        Player p = playerQueue.remove();
        playerQueue.add(p);
    }

    String decideWinner(){
        Set<String> keys = this.playerMap.keySet();
        int i = 0;
        int winner = (int) Math.floor(Math.random()*this.playerQueue.size());
        for(String s:keys){
            if(winner==i){
                return s;
            }
            i++;
        }
        return "";
    }

    void printStandings(){
        for(String s: playerMap.keySet())
        {
           System.out.println(playerMap.get(s).toString());
        }
    }


}
