import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;


class Carddeck {
    public static List<List<String>> list = new ArrayList<>();

    public Carddeck() {
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
                list.add(List.of(suit, String.valueOf(j+1)));
            }
        }
        Collections.shuffle(list);
    }

    public static void add_joker() {
        list.add(List.of("joker", "joker"));
        Collections.shuffle(list);
    }

    public static void visual_card(List<List<String>> hands){
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

    public static void sote_cards(List<List<String>> cards){
        int flag = 0;
        List<String> sort;
        int current;
        int next;
        for(int j=4; j>0; j--){
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

    public static void sote_cards_n(List<List<String>> cards){
        int flag = 0;
        List<String> sort;
        
        for(int j=4; j>0; j--){
            for(int i=0; i<j; i++){
                switch(cards.get(i).get(0)){
                    case "spade":
                        if((cards.get(i+1).get(0)=="spade") && ((Integer.parseInt(cards.get(i).get(1))) > (Integer.parseInt(cards.get(i+1).get(1))) && (Integer.parseInt(cards.get(i).get(1))!=1))){
                            flag = 1;
                        }
                        break;
                    case "heart":
                        if(cards.get(i+1).get(0)=="spade" || ((cards.get(i+1).get(0)=="heart") && ((Integer.parseInt(cards.get(i).get(1))) > (Integer.parseInt(cards.get(i+1).get(1)))) && (Integer.parseInt(cards.get(i).get(1))!=1))){
                            flag = 1;
                        }
                        break;
                    case "diamond":
                        if(cards.get(i+1).get(0)=="spade" || cards.get(i+1).get(0)=="heart" || ((cards.get(i+1).get(0)=="heart") && ((Integer.parseInt(cards.get(i).get(1))) > (Integer.parseInt(cards.get(i+1).get(1)))) && (Integer.parseInt(cards.get(i).get(1))!=1))){
                            flag = 1;
                        }
                        break;
                    case "club":
                        if(cards.get(i+1).get(0)=="spade" || cards.get(i+1).get(0)=="heart" || cards.get(i+1).get(0)=="diamond" || ((cards.get(i+1).get(0)=="heart") && ((Integer.parseInt(cards.get(i).get(1))) > (Integer.parseInt(cards.get(i+1).get(1)))) && (Integer.parseInt(cards.get(i).get(1))!=1))){
                            flag = 1;
                        }
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

class Trump5 extends Carddeck{
    public static List<List<String>> player = new ArrayList<>();
    public static List<List<String>> cpu1 = new ArrayList<>();
    public static List<List<String>> cpu2 = new ArrayList<>();
    public static List<List<String>> cpu3 = new ArrayList<>();
    public static List<List<String>> cpu4 = new ArrayList<>();

    public static void main(String[] args) {
        initializeGame5();
        System.out.println("player:"+ player);
        System.out.println("cpu1:"+ cpu1);
        System.out.println("cpu2:"+ cpu2);
        System.out.println("cpu3:"+ cpu3);
        System.out.println("cpu4:"+ cpu4);
    }
    public static Map<String, List<List<String>>> getplayers() {
        Map<String, List<List<String>>> playersMap = new HashMap<>();
        playersMap.put("player", player);
        playersMap.put("cpu1", cpu1);
        playersMap.put("cpu2", cpu2);
        playersMap.put("cpu3", cpu3);
        playersMap.put("cpu4", cpu4);
        return playersMap;
    }
    public static void initializeGame5() {
        add_joker();
        List<Integer> hand = Arrays.asList(10, 10, 11, 11, 11);
        Collections.shuffle(hand);

        int index = 0;
        player = list.subList(index, index += hand.get(0));
        cpu1 = list.subList(index, index += hand.get(1));
        cpu2 = list.subList(index, index += hand.get(2));
        cpu3 = list.subList(index, index += hand.get(3));
        cpu4 = list.subList(index, Math.min(index + hand.get(4), list.size()));
    }
}

class Trump2 extends Carddeck{
    public static List<List<String>> player = new ArrayList<>();
    public static List<List<String>> cpu = new ArrayList<>();

    public static void main(String[] args) {
        initializeGame2();
        System.out.println("player:"+ player);
        System.out.println("cpu:"+ cpu);
    
    }
    public Map<String, List<List<String>>> getplayers() {
        Map<String, List<List<String>>> playersMap = new HashMap<>();
        playersMap.put("player", player);
        playersMap.put("cpu", cpu);
        return playersMap;
    }
    public static void initializeGame2() {
        List<Integer> hand = Arrays.asList(5, 5);
        int index = 0;
        player = list.subList(index, index += hand.get(0));
        cpu = list.subList(index, index += hand.get(1));
    }
}

class Old_Maid extends Trump5{
    public static void main(String[] args) {
        Trump5 trump = new Trump5();
        trump.initializeGame5();
        Map<String, List<List<String>>> players = trump.getplayers();
        System.out.print("Player's first cards:");
        visual_card(players.get("player"));
        System.out.println("Discarding matching cards...");
        sleep(2000);

        List<String> keys = Arrays.asList("player", "cpu1", "cpu2", "cpu3", "cpu4");
        for (String key : keys) {
            for(int i=0; i<players.get(key).size(); i++){
                for(int j=0; j<players.get(key).size(); j++){
                    if(!(i == j) && players.get(key).get(i).get(1).equals(players.get(key).get(j).get(1))){
                        players.get(key).set(i, new ArrayList<>(Arrays.asList(players.get(key).get(i).get(0), "None")));
                        players.get(key).set(j, new ArrayList<>(Arrays.asList(players.get(key).get(j).get(0), "None")));
                        break;
                    }
                }
            }
        }
        
        List<List<String>> player = new ArrayList<>(players.get("player"));
        remove_none(player);
        List<List<String>> cpu1 = new ArrayList<>(players.get("cpu1"));
        remove_none(cpu1);
        List<List<String>> cpu2 = new ArrayList<>(players.get("cpu2"));
        remove_none(cpu2);
        List<List<String>> cpu3 = new ArrayList<>(players.get("cpu3"));
        remove_none(cpu3);
        List<List<String>> cpu4 = new ArrayList<>(players.get("cpu4"));
        remove_none(cpu4);

        Map<String, List<List<String>>> allPlayers = new LinkedHashMap<>();
        allPlayers.put("Player", player);
        allPlayers.put("cpu1", cpu1);
        allPlayers.put("cpu2", cpu2);
        allPlayers.put("cpu3", cpu3);
        allPlayers.put("cpu4", cpu4);

        List<String> turnOrder = new ArrayList<>(allPlayers.keySet());
        int turnIndex = 0;

        String[] ranking = new String[5];
        int ranking_num = 0;
        System.out.println("Card count, Player "+player.size()+", cpu1 "+cpu1.size()+", cpu2 "+cpu2.size()+", cpu3 "+cpu3.size()+", cpu4 "+cpu4.size());
        sleep(1000);

        while (turnOrder.size() > 1) {
            String current = turnOrder.get(turnIndex);
            String next = turnOrder.get((turnIndex + 1) % turnOrder.size());

            List<List<String>> currentHand = allPlayers.get(current);
            List<List<String>> nextHand = allPlayers.get(next);

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
                ranking[ranking_num] = next;
                ranking_num++;
            }
            if (currentHand.size() == 0) {
                turnOrder.remove(turnIndex);
                ranking[ranking_num] = current;
                ranking_num++;
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
            System.out.print(n+"."+ ranking[i]+", ");
        }
    }
    public static void remove_none(List<List<String>> list){
        for(int i=list.size()-1; i>=0; i--){
            if(list.get(i).get(1).equals("None")){
                list.remove(i);
            }
        }
    }

    public static void turn(String current_name, String next_name, List<List<String>> current_player, List<List<String>> next_player){
        Scanner stdIn = new Scanner(System.in);
        Random rand = new Random();
        int card_num;
        sleep(1000);
        System.out.println("★ "+current_name+"'s turn");
        sleep(1000);
        if(current_name.equals("Player")){ 
            System.out.print("Player's cards:");
            visual_card(current_player);
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

class Poker extends Trump2{
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Trump2 trump = new Trump2();
        trump.initializeGame2();
        Map<String, List<List<String>>> players = trump.getplayers();
        List<List<String>> player = new ArrayList<>(players.get("player"));
        List<List<String>> cpu = new ArrayList<>(players.get("cpu"));
        sote_cards(player);
        sote_cards(cpu);
        System.out.print("Your hands: ");
        visual_card(player);

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
        int super_flag=0;
        if (numbers.get(0)==998){
            super_flag = 1;
            numbers.set(0, 0);
            for(int i = 1; i<5; i++){
                numbers.add(i);
            }
        }
        
        int deck_top=10;
        if (super_flag == 0){
            for(int i=0; i<numbers.size(); i++){
            player.add(list.get(deck_top));
            deck_top++;
            }
        }
        
        
        for (int i=numbers.size()-1; i >= 0; i--) {
            int n = numbers.get(i);
            player.remove(n);
        }

        if(super_flag==1){
            player.add(List.of("heart", "10"));
            player.add(List.of("heart", "11"));
            player.add(List.of("heart", "12"));
            player.add(List.of("heart", "13"));
            player.add(List.of("heart", "1"));
        }

        sote_cards(player);
        System.out.print("Your hands: ");
        visual_card(player);
        System.out.print("Cpu's hands: ");
        visual_card(cpu);

        List<List<String>> player_hands = new ArrayList<>();
        List<List<String>> cpu_hands = new ArrayList<>();
        player_hands = Checking_hands(player);
        cpu_hands = Checking_hands(cpu);
        System.out.println("Your hand: "+player_hands.get(0).get(1)+", cpu's hand: "+cpu_hands.get(0).get(1));
        if (Integer.parseInt(player_hands.get(0).get(0)) < Integer.parseInt(cpu_hands.get(0).get(0))){
            System.out.println("You win!");
        }else if(Integer.parseInt(player_hands.get(0).get(0)) > Integer.parseInt(cpu_hands.get(0).get(0))){
            System.out.println("You lose...");
        }else{
            int player_kicker;
            int cpu_kicker;
            switch(Integer.parseInt(player_hands.get(0).get(0))){
                case 1:
                    System.out.println("Draw");
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    player_kicker = Integer.parseInt(player_hands.get(0).get(2))+1;
                    if (player_kicker==1){
                        player_kicker += 13;
                    }
                    cpu_kicker = Integer.parseInt(cpu_hands.get(0).get(2))+1;
                    if (cpu_kicker==1){
                        cpu_kicker += 13;
                    }
                    if(player_kicker > cpu_kicker){
                        System.out.println("You win!");
                    }else if(player_kicker < cpu_kicker){
                        System.out.println("You lose...");
                    }else{
                        System.out.println("Draw");
                    }
                    break;
                case 8:
                    for(int i=0; i<3; i++){
                        player_kicker = Integer.parseInt(player_hands.get(0).get(2+i))+1;
                        if (player_kicker==1){
                            player_kicker += 13;
                        }
                        cpu_kicker = Integer.parseInt(cpu_hands.get(0).get(2+i))+1;
                        if (cpu_kicker==1){
                            cpu_kicker += 13;
                        }
                        if(player_kicker > cpu_kicker){
                            System.out.println("You win!");
                            break;
                        }else if(player_kicker < cpu_kicker){
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
                        player_kicker = Integer.parseInt(player_hands.get(0).get(2+i))+1;
                        if (player_kicker==1){
                            player_kicker += 13;
                        }
                        cpu_kicker = Integer.parseInt(cpu_hands.get(0).get(2+i))+1;
                        if (cpu_kicker==1){
                            cpu_kicker += 13;
                        }
                        if(player_kicker > cpu_kicker){
                            System.out.println("You win!");
                            break;
                        }else if(player_kicker < cpu_kicker){
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
                        player_kicker = Integer.parseInt(player_hands.get(0).get(2+i))+1;
                        if (player_kicker==1){
                            player_kicker += 13;
                        }
                        cpu_kicker = Integer.parseInt(cpu_hands.get(0).get(2+i))+1;
                        if (cpu_kicker==1){
                            cpu_kicker += 13;
                        }
                        if(player_kicker > cpu_kicker){
                            System.out.println("You win!");
                            break;
                        }else if(player_kicker < cpu_kicker){
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
    
    public static List<List<String>> Checking_hands(List<List<String>> cards){
        List<List<String>> poker_hands = new ArrayList<>();
        int flag = 0;
        int[] same_cards = new int[13];
        for(int i=1; i<14; i++){
            for(int j=0; j<5; j++){
                if(Integer.parseInt(cards.get(j).get(1))==i){
                    same_cards[i-1]++;
                }
            }
        }

        int max_value = Arrays.stream(same_cards).max().getAsInt();
        List<String> max_list = IntStream.range(0, same_cards.length).filter(i -> same_cards[i] == max_value).mapToObj(String::valueOf).collect(Collectors.toList());
        if(Integer.parseInt(max_list.get(0))==0){
            max_list.add("0");
            max_list.remove(0);
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
                poker_hands.add(List.of("1", "Royal flush"));
            }else if(flag==1){
                poker_hands.add(List.of("2", "Straight flush", max_list.get(4)));
            }else{
                poker_hands.add(List.of("5", "Flush", max_list.get(4)));
            }
        }
        else{
            switch(Arrays.stream(same_cards).max().getAsInt()){
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
                        poker_hands.add(List.of("6", "Straight", max_list.get(4)));
                    }else{
                        poker_hands.add(List.of("10", "High Card", max_list.get(4), max_list.get(3), max_list.get(2), max_list.get(1), max_list.get(0)));
                    }
                    break;
                case 2:
                    int[] sorted = Arrays.stream(same_cards).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
                    int secondMax = sorted[1];
                    if(secondMax==2){
                        String non_match = "";
                        for(int i=0; i<13; i++){
                            if(same_cards[i]==1){
                                non_match = Integer.toString(i);
                                break;
                            }
                        }
                        poker_hands.add(List.of("8", "Two Pair", max_list.get(0), max_list.get(1), non_match));
                    }else{
                        String[] non_matchs = new String[3];
                        int non_matchs_count = 0;
                        for(int i=0; i<13; i++){
                            if(same_cards[i]==1){
                                non_matchs[non_matchs_count] = Integer.toString(i);
                                non_matchs_count++;
                            }
                        }
                        poker_hands.add(List.of("9", "One Pair", max_list.get(0), non_matchs[2], non_matchs[1], non_matchs[0]));
                    }
                    break;
                case 3:
                    sorted = Arrays.stream(same_cards).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
                    secondMax = sorted[1];
                    if(secondMax==2){
                        poker_hands.add(List.of("4", "Full House", max_list.get(0)));
                    }else{
                        poker_hands.add(List.of("7", "Three of a Kind", max_list.get(0)));
                    }
                    break;

                case 4:
                    poker_hands.add(List.of("3", "Four of a Kind", max_list.get(0)));
                    break;
            }
        }
        return poker_hands;
    }
        
}

