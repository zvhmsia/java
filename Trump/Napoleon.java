package Trump;
import java.util.*;

class Napoleon extends Trump5{
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        Trump5 trump = new Trump5();
        int declaration;
        trump.initializeGame_napoleone();
        sortCardsSuit(player);
        sortCardsSuit(cpu1);
        sortCardsSuit(cpu2);
        sortCardsSuit(cpu3);
        sortCardsSuit(cpu4);
        System.out.print("Player's first cards:");
        visualiseCard(player);

        Map<String, ArrayList<ArrayList<String>>> allPlayers = new LinkedHashMap<>();
        allPlayers.put("Player", player);
        allPlayers.put("cpu1", cpu1);
        allPlayers.put("cpu2", cpu2);
        allPlayers.put("cpu3", cpu3);
        allPlayers.put("cpu4", cpu4);

        List<String> declare_players = new ArrayList<>(allPlayers.keySet());

        String napoleone="";
        int[] cpu1_points = handsPointCount(cpu1);
        int[] cpu2_points = handsPointCount(cpu2);
        int[] cpu3_points = handsPointCount(cpu3);
        int[] cpu4_points = handsPointCount(cpu4);
        int[] current_declare = new int[2];
        int[] current_declare_cpu = new int[2];

        visualiseCard(cpu1);
        System.out.println(cpu1_points[0] +", "+ cpu1_points[1]+", "+ cpu1_points[2]+", "+ cpu1_points[3]);
        visualiseCard(cpu2);
        System.out.println(cpu2_points[0] +", "+ cpu2_points[1]+", "+ cpu2_points[2]+", "+ cpu2_points[3]);
        visualiseCard(cpu3);
        System.out.println(cpu3_points[0] +", "+ cpu3_points[1]+", "+ cpu3_points[2]+", "+ cpu3_points[3]);
        visualiseCard(cpu4);
        System.out.println(cpu4_points[0] +", "+ cpu4_points[1]+", "+ cpu4_points[2]+", "+ cpu4_points[3]);
        System.out.print("Are you declaring Napoleon? Press 1 for Yes, 2 for No:");
        declaration = stdIn.nextInt();
        if(declaration==1){
            int declaration_cards;
            int declaration_suit;
            int declare_count = 11;
            int declare_suit = 0;
            int loop = 0;
            String d_suit = "";
            String print_suit = "";
            System.out.print("Which suit do you declare? Press 1 for spade, 2 for heart, 3 for diamond, 4 for club: ");
            declaration_suit = stdIn.nextInt();
            switch(declaration_suit){
                case 1:
                    d_suit = "spade";
                    break;
                case 2:
                    d_suit = "heart";
                    break;
                case 3:
                    d_suit = "diamond";
                    break;
                case 4:
                    d_suit = "club";
                    break;
            }
            while(declare_players.size()>1){
                switch(declare_suit){
                    case 1:
                        print_suit = "spade";
                        break;
                    case 2:
                        print_suit = "heart";
                        break;
                    case 3:
                        print_suit = "diamond";
                        break;
                    case 4:
                        print_suit = "club";
                        break;
                }
                for(String key : allPlayers.keySet()){
                    if(declare_players.contains(key)){
                        System.out.println(key);
                        switch(key){
                            case "player":
                                if (loop == 1){
                                    System.out.print(" The current declaration is "+ declare_count +" of "+ print_suit +". Do you continue to declare Napoleon? Press 1 for Yes, 2 for No:");
                                    declaration = stdIn.nextInt();
                                    if (declaration!=1){
                                        declare_players.remove("player");
                                        System.out.println("Pass");
                                        break;
                                    }
                                }
                                System.out.print("How many tricks do you declare? (More than "+ declare_count +"): ");
                                declaration_cards = stdIn.nextInt();
                                if(declaration_cards<declare_count){
                                    declaration_cards=declare_count+1;
                                }else if(declaration_cards>20){
                                    declaration_cards=20;
                                }
                                System.out.println("player's declaration [ suit : "+ d_suit +", tricks : "+ declaration_cards +" ]");
                                current_declare[0] = declaration_cards;
                                current_declare[1] = declaration_suit;
                                break;
                            case "cpu1":
                                current_declare_cpu=declareCpu(cpu1_points, current_declare[0], current_declare[1]);
                                break;
                            case "cpu2":
                                current_declare_cpu=declareCpu(cpu2_points, current_declare[0], current_declare[1]);
                                break;
                            case "cpu3":
                                current_declare_cpu=declareCpu(cpu3_points, current_declare[0], current_declare[1]);
                                break;
                            case "cpu4":
                                current_declare_cpu=declareCpu(cpu4_points, current_declare[0], current_declare[1]);
                                break;
                        }
                        if(current_declare_cpu[0]==-1){
                            declare_players.remove(key);
                        }else{
                            current_declare=current_declare_cpu;
                        }
                        System.out.println(current_declare[0]+", "+ current_declare[1]);
                    }
                }
                loop = 1;
            }
        }

        //cpu 宣言
        String adjutant = "";
        String adjutant_suit = "";
        int a_suit = 0;
        int adjutant_num = 0;
        if(napoleone=="player"){
            System.out.println("Please declare the Adjutant's card.");
            System.out.print("suit (Press 1 for spade, 2 for heart, 3 for diamond, 4 for club): ");
            a_suit = stdIn.nextInt();
            System.out.print("number: ");
            adjutant_num = stdIn.nextInt();
        }
        switch(a_suit){
                case 1:
                    adjutant_suit = "spade";
                    break;
                case 2:
                    adjutant_suit = "heart";
                    break;
                case 3:
                    adjutant_suit = "diamond";
                    break;
                case 4:
                    adjutant_suit = "club";
                    break;
        }

        for (String key : allPlayers.keySet()) {
            for(int j=0; j<allPlayers.get(key).size(); j++){
                if(allPlayers.get(key).get(j).get(0) == adjutant_suit && Integer.parseInt(allPlayers.get(key).get(j).get(1)) == adjutant_num){
                    adjutant=key;
                }
            }
        }

        System.out.println(adjutant);
        System.out.println("Napoleon may examine the cards in play and exchange any of them at will.");
        if(napoleone=="player"){

            player.addAll(exchange);

            sortCardsSuit(player);
            visualiseCard(player);
            stdIn.nextLine();
            System.out.print("Please choose and discard three cards. Please input the numbers from the left, separated by spaces:");
            ArrayList<Integer> numbers = new ArrayList<>();

            String input = stdIn.nextLine();
            String[] tokens = input.trim().split("\\s+");

            for (int i = 0; i < 3; i++) {
                try {
                    int number = Integer.parseInt(tokens[i]);
                    numbers.add(number-1);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + tokens[i]);
                }
            }

            Collections.sort(numbers);
            for(int i=2; i>=0; i--){
                int n = numbers.get(i);
                player.remove(n);
            }

            sortCardsSuit(player);
            visualiseCard(player);


        }
    }
    public static int[] handsPointCount(ArrayList<ArrayList<String>> cards){
        int[] hands_point = new int[4];
        int handSuit = 0;
        int handPoint = 0;
        boolean j_flag = false;
        boolean sa_flag = false;
        for(int i=0; i<cards.size(); i++){
            switch(cards.get(i).get(0)){
                case "spade":
                    handSuit = 0;
                    break;
                case "heart":
                    handSuit = 1;
                    break;
                case "diamond":
                    handSuit = 2;
                    break;
                case "club":
                    handSuit = 3;
                    break;
            }
            if(cards.get(i).get(1)!="joker"){
                switch(Integer.parseInt(cards.get(i).get(1))){
                    case 1:
                        handPoint = 3;
                        if(handSuit==0){
                            sa_flag = true;
                        }
                    case 13:
                    case 12:
                    case 10:
                        handPoint = 3;
                        break;
                    case 11:
                        handPoint = 4;
                        j_flag = true;
                        break;
                    case 9:
                    case 8:
                    case 7:
                    case 6:
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        handPoint = 2;
                        break;
                }
            }
            hands_point[handSuit] += handPoint;
            if(j_flag){
                switch(handSuit){
                    case 0:
                        hands_point[3] += 3;
                        break;
                    case 1:
                        hands_point[2] += 3;
                        break;
                    case 2:
                        hands_point[1] += 3;
                        break;
                    case 3:
                        hands_point[0] += 3;
                        break;
                }
                j_flag = false;
            }
            if(sa_flag){
                for (int j = 0; j < hands_point.length; j++) {
                    hands_point[j] += 1;
                }
                sa_flag = false;
            }
        }
        return hands_point;
    }
    public static int[] declareCpu(int[] hands_point, int declare_count, int declare_suit){
        int[] cpuDeclare = new int[2];
        int max_value = hands_point[0];
        int max_index = 0;

        for (int i = 1; i < hands_point.length; i++) {
            if (hands_point[i] > max_value) {
                max_value = hands_point[i];
                max_index = i;
            }
        }
        if (max_value >= declare_count){
            if(declare_suit > max_index){
            	cpuDeclare[0] = max_index;
            	cpuDeclare[1] = declare_count;
            }else{
            	cpuDeclare[0] = max_index;
            	cpuDeclare[1] = declare_count+1;
            }
        }else{
        	cpuDeclare[0] = -1;
        	cpuDeclare[1] = -1;
        }
        return cpuDeclare;
    }
}
