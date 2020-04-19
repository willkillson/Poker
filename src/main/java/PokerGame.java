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
        Player p4 = new Player("somers@gmail.com",1000);
        Player p5 = new Player("petrone@gmail.com",1000);
        Player p6 = new Player("mackadocious@gmail.com",1000);

        loginPlayer(p1);
        loginPlayer(p2);
        loginPlayer(p3);
        loginPlayer(p4);
        loginPlayer(p5);
        loginPlayer(p6);


    }

    public void loginPlayer(Player player){
        playerMap.put(player.email,player);
        playerQueue.add(player);
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

            ArrayList<String> deck = cards.getFullDeck();
            this.deltCards = cards.deal(5,deck);

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
