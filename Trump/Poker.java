package Trump;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Poker extends Trump2{
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Trump2 trump = new Trump2();
        trump.initializeGame2();
        Map<String, ArrayList<ArrayList<String>>> players = trump.getplayers();
        ArrayList<ArrayList<String>> player = new ArrayList<>(players.get("player"));
        ArrayList<ArrayList<String>> cpu = new ArrayList<>(players.get("cpu"));
        sortCards(player);
        sortCards(cpu);
        System.out.print("Your hands: ");
        visualiseCard(player);

        System.out.print("Which card will you exchange? Please input the numbers from the left, separated by spaces:");
        ArrayList<Integer> numbers = new ArrayList<>();

        String input = stdIn.nextLine();
        String[] tokens = input.trim().split("\\s+");

        for (int i = 0; i < tokens.length && i < 5; i++) {
            try {
                int number = Integer.parseInt(tokens[i]);
                try {
                    if (number > 5 && number != 999) {
                        throw new IllegalArgumentException();
                    }
                } catch (Exception e) {
                    System.out.println("Number must be less than 5. Actual value: " + number);
                    System.exit(1);
                }
                numbers.add(number-1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + tokens[i]);
            }
        }
        int superFlag=0;
        if (numbers.get(0)==998){
            superFlag = 1;
            numbers.set(0, 0);
            for(int i = 1; i<5; i++){
                numbers.add(i);
            }
        }

        int deckTop=10;
        if (superFlag == 0){
            for(int i=0; i<numbers.size(); i++){
                player.add(list.get(deckTop));
                deckTop++;
            }
        }


        for (int i=numbers.size()-1; i >= 0; i--) {
            int n = numbers.get(i);
            player.remove(n);
        }

        if(superFlag==1){
            player.add(new ArrayList<>(Arrays.asList("heart", "10")));
            player.add(new ArrayList<>(Arrays.asList("heart", "11")));
            player.add(new ArrayList<>(Arrays.asList("heart", "12")));
            player.add(new ArrayList<>(Arrays.asList("heart", "13")));
            player.add(new ArrayList<>(Arrays.asList("heart", "1")));
        }

        sortCards(player);
        System.out.print("Your hands: ");
        visualiseCard(player);
        System.out.print("Cpu's hands: ");
        visualiseCard(cpu);

        ArrayList<ArrayList<String>> playerHands = new ArrayList<>();
        ArrayList<ArrayList<String>> cpuHands = new ArrayList<>();
        playerHands = CheckingHands(player);
        cpuHands = CheckingHands(cpu);
        System.out.println("Your hand: "+playerHands.get(0).get(1)+", cpu's hand: "+cpuHands.get(0).get(1));
        if (Integer.parseInt(playerHands.get(0).get(0)) < Integer.parseInt(cpuHands.get(0).get(0))){
            System.out.println("You win!");
        }else if(Integer.parseInt(playerHands.get(0).get(0)) > Integer.parseInt(cpuHands.get(0).get(0))){
            System.out.println("You lose...");
        }else{
            int playerKicker;
            int cpuKicker;
            switch(Integer.parseInt(playerHands.get(0).get(0))){
                case 1:
                    System.out.println("Draw");
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    playerKicker = Integer.parseInt(playerHands.get(0).get(2))+1;
                    if (playerKicker==1){
                        playerKicker += 13;
                    }
                    cpuKicker = Integer.parseInt(cpuHands.get(0).get(2))+1;
                    if (cpuKicker==1){
                        cpuKicker += 13;
                    }
                    if(playerKicker > cpuKicker){
                        System.out.println("You win!");
                    }else if(playerKicker < cpuKicker){
                        System.out.println("You lose...");
                    }else{
                        System.out.println("Draw");
                    }
                    break;
                case 8:
                    for(int i=0; i<3; i++){
                        playerKicker = Integer.parseInt(playerHands.get(0).get(2+i))+1;
                        if (playerKicker==1){
                            playerKicker += 13;
                        }
                        cpuKicker = Integer.parseInt(cpuHands.get(0).get(2+i))+1;
                        if (cpuKicker==1){
                            cpuKicker += 13;
                        }
                        if(playerKicker > cpuKicker){
                            System.out.println("You win!");
                            break;
                        }else if(playerKicker < cpuKicker){
                            System.out.println("You lose...");
                            break;
                        }else{
                            if(i==2){
                                System.out.println("Draw");
                                break;
                            }
                        }
                    }
                    break;
                case 9:
                    for(int i=0; i<4; i++){
                        playerKicker = Integer.parseInt(playerHands.get(0).get(2+i))+1;
                        if (playerKicker==1){
                            playerKicker += 13;
                        }
                        cpuKicker = Integer.parseInt(cpuHands.get(0).get(2+i))+1;
                        if (cpuKicker==1){
                            cpuKicker += 13;
                        }
                        if(playerKicker > cpuKicker){
                            System.out.println("You win!");
                            break;
                        }else if(playerKicker < cpuKicker){
                            System.out.println("You lose...");
                            break;
                        }else{
                            if(i==3){
                                System.out.println("Draw");
                                break;
                            }
                        }
                    }
                    break;
                case 10:
                    for(int i=0; i<5; i++){
                        playerKicker = Integer.parseInt(playerHands.get(0).get(2+i))+1;
                        if (playerKicker==1){
                            playerKicker += 13;
                        }
                        cpuKicker = Integer.parseInt(cpuHands.get(0).get(2+i))+1;
                        if (cpuKicker==1){
                            cpuKicker += 13;
                        }
                        if(playerKicker > cpuKicker){
                            System.out.println("You win!");
                            break;
                        }else if(playerKicker < cpuKicker){
                            System.out.println("You lose...");
                            break;
                        }else{
                            if(i==4){
                                System.out.println("Draw");
                                break;
                            }
                        }
                    }
                    break;
            }
        }

    }

    public static ArrayList<ArrayList<String>> CheckingHands(ArrayList<ArrayList<String>> cards){
    	ArrayList<ArrayList<String>> pokerHands = new ArrayList<>();
        int flag = 0;
        int[] sameCards = new int[13];
        for(int i=1; i<14; i++){
            for(int j=0; j<5; j++){
                if(Integer.parseInt(cards.get(j).get(1))==i){
                    sameCards[i-1]++;
                }
            }
        }

        int max_value = Arrays.stream(sameCards).max().getAsInt();
        ArrayList<String> maxList = new ArrayList<>(IntStream.range(0, sameCards.length).filter(i -> sameCards[i] == max_value).mapToObj(String::valueOf).collect(Collectors.toList()));
        if(Integer.parseInt(maxList.get(0))==0){
            maxList.add("0");
            maxList.remove(0);
        }

        if(cards.get(0).get(0)==cards.get(1).get(0) && cards.get(1).get(0)==cards.get(2).get(0) && cards.get(2).get(0)==cards.get(3).get(0) && cards.get(3).get(0)==cards.get(4).get(0)){
            for(int i=0; i<4; i++){
                if((Integer.parseInt(cards.get(i).get(1))-Integer.parseInt(cards.get(i+1).get(1)))==-1 || (Integer.parseInt(cards.get(i).get(1))-Integer.parseInt(cards.get(i+1).get(1)))==12){
                    flag = 1;
                }
                else{
                    flag = 0;
                    break;
                }
            }
            if(flag==1 && Integer.parseInt(cards.get(0).get(1))==10){
                pokerHands.add(new ArrayList<>(Arrays.asList("1", "Royal flush")));
            }else if(flag==1){
                pokerHands.add(new ArrayList<>(Arrays.asList("2", "Straight flush", maxList.get(4))));
            }else{
                pokerHands.add(new ArrayList<>(Arrays.asList("5", "Flush", maxList.get(4))));
            }
        }
        else{
            switch(Arrays.stream(sameCards).max().getAsInt()){
                case 1:
                    flag = 0;
                    for(int i=0; i<4; i++){
                        if((Integer.parseInt(cards.get(i).get(1))-Integer.parseInt(cards.get(i+1).get(1)))==-1 || (Integer.parseInt(cards.get(i).get(1))-Integer.parseInt(cards.get(i+1).get(1)))==12){
                            flag = 1;
                        }
                        else{
                            flag = 0;
                            break;
                        }
                    }
                    if(flag==1){
                        pokerHands.add(new ArrayList<>(Arrays.asList("6", "Straight", maxList.get(4))));
                    }else{
                        pokerHands.add(new ArrayList<>(Arrays.asList("10", "High Card", maxList.get(4), maxList.get(3), maxList.get(2), maxList.get(1), maxList.get(0))));
                    }
                    break;
                case 2:
                    int[] sorted = Arrays.stream(sameCards).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
                    int secondMax = sorted[1];
                    if(secondMax==2){
                        String non_match = "";
                        for(int i=0; i<13; i++){
                            if(sameCards[i]==1){
                                non_match = Integer.toString(i);
                                break;
                            }
                        }
                        pokerHands.add(new ArrayList<>(Arrays.asList("8", "Two Pair", maxList.get(0), maxList.get(1), non_match)));
                    }else{
                        String[] non_matchs = new String[3];
                        int non_matchs_count = 0;
                        for(int i=0; i<13; i++){
                            if(sameCards[i]==1){
                                non_matchs[non_matchs_count] = Integer.toString(i);
                                non_matchs_count++;
                            }
                        }
                        pokerHands.add(new ArrayList<>(Arrays.asList("9", "One Pair", maxList.get(0), non_matchs[2], non_matchs[1], non_matchs[0])));
                    }
                    break;
                case 3:
                    sorted = Arrays.stream(sameCards).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
                    secondMax = sorted[1];
                    if(secondMax==2){
                        pokerHands.add(new ArrayList<>(Arrays.asList("4", "Full House", maxList.get(0))));
                    }else{
                        pokerHands.add(new ArrayList<>(Arrays.asList("7", "Three of a Kind", maxList.get(0))));
                    }
                    break;

                case 4:
                    pokerHands.add(new ArrayList<>(Arrays.asList("3", "Four of a Kind", maxList.get(0))));
                    break;
            }
        }
        return pokerHands;
    }
}
