import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Cards {

    HashMap<String, Image> cardMap;

    public Cards(){
        this.cardMap = new HashMap<>();
        this.loadImages();

    }

    void loadImages(){
        try{
            this.cardMap.put("2C",new Image(new FileInputStream("resources/cards/2C.png")));
            this.cardMap.put("2D",new Image(new FileInputStream("resources/cards/2D.png")));
            this.cardMap.put("2H",new Image(new FileInputStream("resources/cards/2H.png")));
            this.cardMap.put("2S",new Image(new FileInputStream("resources/cards/2S.png")));
            this.cardMap.put("3C",new Image(new FileInputStream("resources/cards/3C.png")));
            this.cardMap.put("3D",new Image(new FileInputStream("resources/cards/3D.png")));
            this.cardMap.put("3H",new Image(new FileInputStream("resources/cards/3H.png")));
            this.cardMap.put("3S",new Image(new FileInputStream("resources/cards/3S.png")));
            this.cardMap.put("4C",new Image(new FileInputStream("resources/cards/4C.png")));
            this.cardMap.put("4D",new Image(new FileInputStream("resources/cards/4D.png")));
            this.cardMap.put("4H",new Image(new FileInputStream("resources/cards/4H.png")));
            this.cardMap.put("4S",new Image(new FileInputStream("resources/cards/4S.png")));
            this.cardMap.put("5C",new Image(new FileInputStream("resources/cards/5C.png")));
            this.cardMap.put("5D",new Image(new FileInputStream("resources/cards/5D.png")));
            this.cardMap.put("5H",new Image(new FileInputStream("resources/cards/5H.png")));
            this.cardMap.put("5S",new Image(new FileInputStream("resources/cards/5S.png")));
            this.cardMap.put("6C",new Image(new FileInputStream("resources/cards/6C.png")));
            this.cardMap.put("6D",new Image(new FileInputStream("resources/cards/6D.png")));
            this.cardMap.put("6H",new Image(new FileInputStream("resources/cards/6H.png")));
            this.cardMap.put("6S",new Image(new FileInputStream("resources/cards/6S.png")));
            this.cardMap.put("7C",new Image(new FileInputStream("resources/cards/7C.png")));
            this.cardMap.put("7D",new Image(new FileInputStream("resources/cards/7D.png")));
            this.cardMap.put("7H",new Image(new FileInputStream("resources/cards/7H.png")));
            this.cardMap.put("7S",new Image(new FileInputStream("resources/cards/7S.png")));
            this.cardMap.put("8C",new Image(new FileInputStream("resources/cards/8C.png")));
            this.cardMap.put("8D",new Image(new FileInputStream("resources/cards/8D.png")));
            this.cardMap.put("8H",new Image(new FileInputStream("resources/cards/8H.png")));
            this.cardMap.put("8S",new Image(new FileInputStream("resources/cards/8S.png")));
            this.cardMap.put("9C",new Image(new FileInputStream("resources/cards/9C.png")));
            this.cardMap.put("9D",new Image(new FileInputStream("resources/cards/9D.png")));
            this.cardMap.put("9H",new Image(new FileInputStream("resources/cards/9H.png")));
            this.cardMap.put("9S",new Image(new FileInputStream("resources/cards/9S.png")));
            this.cardMap.put("10C",new Image(new FileInputStream("resources/cards/10C.png")));
            this.cardMap.put("10D",new Image(new FileInputStream("resources/cards/10D.png")));
            this.cardMap.put("10H",new Image(new FileInputStream("resources/cards/10H.png")));
            this.cardMap.put("10S",new Image(new FileInputStream("resources/cards/10S.png")));
            this.cardMap.put("JC",new Image(new FileInputStream("resources/cards/JC.png")));
            this.cardMap.put("JD",new Image(new FileInputStream("resources/cards/JD.png")));
            this.cardMap.put("JH",new Image(new FileInputStream("resources/cards/JH.png")));
            this.cardMap.put("JS",new Image(new FileInputStream("resources/cards/JS.png")));
            this.cardMap.put("QC",new Image(new FileInputStream("resources/cards/QC.png")));
            this.cardMap.put("QD",new Image(new FileInputStream("resources/cards/QD.png")));
            this.cardMap.put("QH",new Image(new FileInputStream("resources/cards/QH.png")));
            this.cardMap.put("QS",new Image(new FileInputStream("resources/cards/QS.png")));
            this.cardMap.put("KC",new Image(new FileInputStream("resources/cards/KC.png")));
            this.cardMap.put("KD",new Image(new FileInputStream("resources/cards/KD.png")));
            this.cardMap.put("KH",new Image(new FileInputStream("resources/cards/KH.png")));
            this.cardMap.put("KS",new Image(new FileInputStream("resources/cards/KS.png")));
            this.cardMap.put("AC",new Image(new FileInputStream("resources/cards/AC.png")));
            this.cardMap.put("AD",new Image(new FileInputStream("resources/cards/AD.png")));
            this.cardMap.put("AH",new Image(new FileInputStream("resources/cards/AH.png")));
            this.cardMap.put("AS",new Image(new FileInputStream("resources/cards/AS.png")));

            this.cardMap.put("blue_back",new Image(new FileInputStream("resources/cards/blue_back.png")));
            this.cardMap.put("purple_back",new Image(new FileInputStream("resources/cards/purple_back.png")));
            this.cardMap.put("red_back",new Image(new FileInputStream("resources/cards/red_back.png")));
            this.cardMap.put("yellow_back",new Image(new FileInputStream("resources/cards/yellow_back.png")));
            this.cardMap.put("gray_back",new Image(new FileInputStream("resources/cards/gray_back.png")));
            this.cardMap.put("green_back",new Image(new FileInputStream("resources/cards/green_back.png")));

        }catch(Exception ecp){
            ecp.printStackTrace();
        }
    }
}
