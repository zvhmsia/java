package Trump;
import java.util.*;

public class OldMaid extends Trump5{
    public static void main(String[] args) {
    	Trump5 trump = new Trump5();
    	trump.initializeGame5();
        Map<String, ArrayList<ArrayList<String>>> players = trump.getplayers();
        System.out.print("Player's first cards:");
        visualiseCard(players.get("player"));
        System.out.println("Discarding matching cards...");
        sleep(2000);

        ArrayList<String> keys = new ArrayList<>(Arrays.asList("player", "cpu1", "cpu2", "cpu3", "cpu4"));
        for (String key : keys) {
            for(int i=players.get(key).size()-1; i>=0; i--){
                for(int j=0; j<i; j++){
                    if(!(i == j) && players.get(key).get(i).get(1).equals(players.get(key).get(j).get(1))){
                        players.get(key).set(i, new ArrayList<>(Arrays.asList(players.get(key).get(i).get(0), "None")));
                        players.get(key).set(j, new ArrayList<>(Arrays.asList(players.get(key).get(j).get(0), "None")));
                        break;
                    }
                }
            }
        }

        player = new ArrayList<>(players.get("player"));
        removeNone(player);
        cpu1 = new ArrayList<>(players.get("cpu1"));
        removeNone(cpu1);
        cpu2 = new ArrayList<>(players.get("cpu2"));
        removeNone(cpu2);
        cpu3 = new ArrayList<>(players.get("cpu3"));
        removeNone(cpu3);
        cpu4 = new ArrayList<>(players.get("cpu4"));
        removeNone(cpu4);

        Map<String, ArrayList<ArrayList<String>>> allPlayers = new LinkedHashMap<>();
        allPlayers.put("Player", player);
        allPlayers.put("cpu1", cpu1);
        allPlayers.put("cpu2", cpu2);
        allPlayers.put("cpu3", cpu3);
        allPlayers.put("cpu4", cpu4);

        ArrayList<String> turnOrder = new ArrayList<>(allPlayers.keySet());
        int turnIndex = 0;

        String[] ranking = new String[5];
        int rankingNum = 0;
        System.out.println("Card count, Player "+player.size()+", cpu1 "+cpu1.size()+", cpu2 "+cpu2.size()+", cpu3 "+cpu3.size()+", cpu4 "+cpu4.size());
        sleep(1000);

        while (turnOrder.size() > 1) {
            String current = turnOrder.get(turnIndex);
            String next = turnOrder.get((turnIndex + 1) % turnOrder.size());

            ArrayList<ArrayList<String>> currentHand = allPlayers.get(current);
            ArrayList<ArrayList<String>> nextHand = allPlayers.get(next);

            if (nextHand.size() == 0) {
                turnIndex = (turnIndex + 1) % turnOrder.size();
                continue;
            }
            turn(current, next, currentHand, nextHand);

            if (nextHand.size() == 0) {
                if(turnIndex+1==turnOrder.size()){
                    turnOrder.remove(0);
                }else{
                    turnOrder.remove(turnIndex+1);
                }
                ranking[rankingNum] = next;
                rankingNum++;
            }
            if (currentHand.size() == 0) {
                turnOrder.remove(turnIndex);
                ranking[rankingNum] = current;
                rankingNum++;
            }
            if(turnIndex >= turnOrder.size()){
                turnIndex = 0;
            }
            else{
                turnIndex = (turnIndex + 1) % turnOrder.size();
            }

            System.out.println("Card count, Player "+player.size()+", cpu1 "+cpu1.size()+", cpu2 "+cpu2.size()+", cpu3 "+cpu3.size()+", cpu4 "+cpu4.size());
        }
        ranking[4] = turnOrder.get(0);
        int n;
        System.out.print("Ranking: ");
        for(int i=0; i<5; i++){
            n = i+1;
            System.out.print(n+"."+ ranking[i]);
            if(i != 4){
                System.out.print(", ");
            }
        }
    }
    public static void removeNone(ArrayList<ArrayList<String>> list){
        for(int i=list.size()-1; i>=0; i--){
            if(list.get(i).get(1).equals("None")){
                list.remove(i);
            }
        }
    }

    public static void turn(String current_name, String next_name, ArrayList<ArrayList<String>> current_player, ArrayList<ArrayList<String>> next_player){
        Scanner stdIn = new Scanner(System.in);
        Random rand = new Random();
        int card_num;
        sleep(1000);
        System.out.println("★ "+current_name+"'s turn");
        sleep(1000);
        if(current_name.equals("Player")){
            System.out.print("Player's cards:");
            visualiseCard(current_player);
            System.out.print("Which card will you take?(number 1 to " + next_player.size() +"): ");
            card_num = stdIn.nextInt();

            if(card_num>next_player.size()){
                sleep(1000);
                System.out.print("Select the card you want to take again(number 1 to " + next_player.size() +"): ");
                card_num = stdIn.nextInt();
            }

            if(card_num>next_player.size()){
                sleep(1000);
                System.out.print("Take a card at random\n");
                card_num = rand.nextInt(next_player.size())+1;
            }
            card_num--;
        }
        else{
            card_num = rand.nextInt(next_player.size());
        }
        current_player.add(next_player.get(card_num));
        next_player.remove(card_num);
        if(next_player.size()==0){
            sleep(2000);
            System.out.println(next_name+"'s last card of a has been drawn.");
            System.out.println("☆ "+next_name+" WIN!");
        }

        int card_count=current_player.size();
        for(int i=0; i<card_count-1; i++){
            if((current_player.get(card_count-1).get(1)).equals((current_player.get(i).get(1)))){
                current_player.remove(card_count-1);
                current_player.remove(i);
                sleep(2000);
                System.out.println(current_name+": Making a match!");
                if(current_player.size()==0){
                    sleep(1000);
                    System.out.println("☆ "+current_name+" WIN!");
                }
                break;
            }
        }
        if(card_count==current_player.size()){
            sleep(2000);
            System.out.println(current_name+": Failing to match");
        }
    }

}

