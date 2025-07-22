package Trump;

import java.util.*;

public class CardDeck {
	public static ArrayList<ArrayList<String>> list = new ArrayList<>();

    public CardDeck() {
        String suit;
        for(int i=0; i<4; i++){
            for(int j=0; j<13; j++){
                if(i==0){
                    suit = "spade";
                }else if(i==1){
                    suit = "heart";
                }else if(i==2){
                    suit = "diamond";
                }else{
                    suit = "club";
                }
                list.add(new ArrayList<>(Arrays.asList(suit, String.valueOf(j+1))));
            }
        }
        Collections.shuffle(list);
    }

    public static void addJoker() {
        list.add(new ArrayList<>(Arrays.asList("joker", "joker")));
        Collections.shuffle(list);
    }

    public static void visualiseCard(ArrayList<ArrayList<String>> hands){
        String number;
        for (int i=0; i<hands.size(); i++){
            number = hands.get(i).get(1);
            switch(number){
                case "1":
                    number = "A";
                    break;
                case "11":
                    number = "J";
                    break;
                case "12":
                    number = "Q";
                    break;
                case "13":
                    number = "K";
                    break;
            }

            switch(hands.get(i).get(0)){
                case "spade":
                    System.out.print("[ \u2660 "+number+" ]");
                    break;
                case "heart":
                    System.out.print("[ \u2661 "+number+" ]");
                    break;
                case "diamond":
                    System.out.print("[ \u2662 "+number+" ]");
                    break;
                case "club":
                    System.out.print("[ \u2663 "+number+" ]");
                    break;
                case "joker":
                    System.out.print("[ JOKER ]");

            }
        }
        System.out.println("");
    }

    public static void sortCards(ArrayList<ArrayList<String>> cards){
        int flag = 0;
        ArrayList<String> sort;
        int current;
        int next;
        for(int j=cards.size()-1; j>0; j--){
            for(int i=0; i<j; i++){
                current=Integer.parseInt(cards.get(i).get(1));
                next=Integer.parseInt(cards.get(i+1).get(1));
                if(current==1){
                    current += 13;
                }
                if(next==1){
                    next += 13;
                }
                if(current > next){
                    sort = cards.get(i);
                    cards.set(i, cards.get(i+1));
                    cards.set(i+1, sort);
                }
                else if(current == next){
                    switch(cards.get(i+1).get(0)){
                        case "spade":
                            flag = 1;
                            break;
                        case "heart":
                            if(cards.get(i).get(0)!="spade"){
                                flag = 1;
                            }
                            break;
                        case "diamond":
                            if(cards.get(i+1).get(0)!="spade" && cards.get(i+1).get(0)!="heart"){
                                flag = 1;
                            }
                            break;
                    }
                }
                if(flag==1){
                    sort = cards.get(i);
                    cards.set(i, cards.get(i+1));
                    cards.set(i+1, sort);
                    flag=0;
                }
            }
        }
    }

    public static void sortCardsSuit(ArrayList<ArrayList<String>> cards){
        int flag = 0;
        ArrayList<String> sort;
        int current=0;
        int next=0;
        for(int j=cards.size()-1; j>0; j--){
            for(int i=0; i<j; i++){
                if(cards.get(i).get(0)!="joker" && cards.get(i+1).get(0)!="joker"){
                    current=Integer.parseInt(cards.get(i).get(1));
                    next=Integer.parseInt(cards.get(i+1).get(1));
                    if(current==1){
                        current += 13;
                    }
                    if(next==1){
                        next += 13;
                    }
                }
                switch(cards.get(i).get(0)){
                    case "spade":
                        if((cards.get(i+1).get(0)=="spade") && (current > next)){
                            flag = 1;
                        }
                        break;
                    case "heart":
                        if(cards.get(i+1).get(0)=="spade" || ((cards.get(i+1).get(0)=="heart") && (current > next))){
                            flag = 1;
                        }
                        break;
                    case "diamond":
                        if(cards.get(i+1).get(0)=="spade" || cards.get(i+1).get(0)=="heart" || ((cards.get(i+1).get(0)=="diamond") && (current > next))){
                            flag = 1;
                        }
                        break;
                    case "club":
                        if(cards.get(i+1).get(0)=="spade" || cards.get(i+1).get(0)=="heart" || cards.get(i+1).get(0)=="diamond" || ((cards.get(i+1).get(0)=="club") && (current > next))){
                            flag = 1;
                        }
                        break;
                    case "joker":
                        flag = 1;
                        break;
                }
                if(flag==1){
                    sort = cards.get(i);
                    cards.set(i, cards.get(i+1));
                    cards.set(i+1, sort);
                    flag=0;
                }

            }
        }
    }

    public static void sleep(int time){
        try {
            time=1;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
