package Poker;


import java.awt.Color;
import java.util.*;

public class PokerGame implements Runnable {

    Cards cards;
    boolean updateScreen;

    public ArrayList<String> deltCards;
    String winner;
    String pBBlinds;
    String pSBlinds;


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
        winner = "";
        pBBlinds = "";
        pSBlinds = "";

        //big blinds
        //little blinds
        while(playerMap.size()>=2){

            //start of turn
            createTurnStack();

            ArrayList<String> deck = cards.getFullDeck();
            this.deltCards = cards.deal(5,deck);

            String bigBlinds = this.turnStack.pop().email;
            String smallBlinds = this.turnStack.pop().email;

            pBBlinds = "BigBlinds: "+bigBlinds;
            this.currentPot += playerMap.get(bigBlinds).chargePlayer(currentBlinds);
            pSBlinds = "SmallBlinds: "+smallBlinds;

            printBlinds();

            this.currentPot += playerMap.get(smallBlinds).chargePlayer(currentBlinds/2);
            this.turnStack.push(playerMap.get(smallBlinds));
            this.turnStack.push(playerMap.get(bigBlinds));

            //decide the winner
            winner = decideWinner();
            playerMap.get(winner).payPlayer(this.currentPot);
            this.currentPot = 0;
            //remove losers
            for(String s: playerMap.keySet()){
                if(playerMap.get(s).cash<=0){
                    Player re = playerMap.remove(s);
                    playerQueue.remove(re);
                }
            }

            try{
                this.wait(2000);

            }catch(InterruptedException ecp){
                ecp.printStackTrace();
            }

            //game is over, reorder the playerQueue
            setPlayerQueue();



            print();

        }
    }


    void print(){
        Main.myConsole.clear();//clear the console
        printWinner();
        printStandings();
        printTurnStack();
    }

    void printBlinds(){
        int row = 1;


        Main.myConsole.setCursorPos(0,row);
        Main.myConsole.write(pBBlinds, Color.WHITE, Color.BLACK);
        Main.myConsole.setCursorPos(0,row+1);
        Main.myConsole.write(pSBlinds, Color.WHITE, Color.BLACK);


    }
    void printStandings(){
        int row = 10;

        for(String s: playerMap.keySet())
        {
            Main.myConsole.setCursorPos(0,row);
            //System.out.println(playerMap.get(s).toString());
            Main.myConsole.write(playerMap.get(s).toString(), Color.GREEN, Color.BLACK);
            row++;
        }
    }

    void printWinner(){
        Main.myConsole.setCursorPos(0,0);
        Main.myConsole.write("Winning player: "+winner, Color.RED, Color.BLACK);
    }

    void printTurnStack(){
        int row = 3;
        while(!this.turnStack.isEmpty()){
            String email = this.turnStack.pop().email;
            Main.myConsole.setCursorPos(0,row);
            Main.myConsole.write("Turn: "+email, Color.CYAN, Color.BLACK);
            row++;
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



}
