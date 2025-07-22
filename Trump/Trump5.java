package Trump;
import java.util.*;

public class Trump5 extends CardDeck{
	public static ArrayList<ArrayList<String>> player = new ArrayList<>();
    public static ArrayList<ArrayList<String>> cpu1 = new ArrayList<>();
    public static ArrayList<ArrayList<String>> cpu2 = new ArrayList<>();
    public static ArrayList<ArrayList<String>> cpu3 = new ArrayList<>();
    public static ArrayList<ArrayList<String>> cpu4 = new ArrayList<>();
    public static ArrayList<ArrayList<String>> exchange = new ArrayList<>();

    public static void main(String[] args) {
        initializeGame5();
        System.out.println("player:"+ player);
        System.out.println("cpu1:"+ cpu1);
        System.out.println("cpu2:"+ cpu2);
        System.out.println("cpu3:"+ cpu3);
        System.out.println("cpu4:"+ cpu4);
    }
    public static Map<String, ArrayList<ArrayList<String>>> getplayers() {
        Map<String, ArrayList<ArrayList<String>>> playersMap = new HashMap<>();
        playersMap.put("player", player);
        playersMap.put("cpu1", cpu1);
        playersMap.put("cpu2", cpu2);
        playersMap.put("cpu3", cpu3);
        playersMap.put("cpu4", cpu4);
        return playersMap;
    }
    public static void initializeGame5() {
        addJoker();
        List<Integer> hand = Arrays.asList(10, 10, 11, 11, 11);
        Collections.shuffle(hand);

        int index = 0;
        player = new ArrayList(list.subList(index, index += hand.get(0)));
        cpu1 = new ArrayList(list.subList(index, index += hand.get(1)));
        cpu2 = new ArrayList(list.subList(index, index += hand.get(2)));
        cpu3 = new ArrayList(list.subList(index, index += hand.get(3)));
        cpu4 = new ArrayList(list.subList(index, Math.min(index + hand.get(4), list.size())));
    }

    public static void initializeGame_napoleone() {
        addJoker();

        player = new ArrayList(list.subList(0, 10));
        cpu1 = new ArrayList(list.subList(10, 20));
        cpu2 = new ArrayList(list.subList(20, 30));
        cpu3 = new ArrayList(list.subList(30, 40));
        cpu4 = new ArrayList(list.subList(40, 50));
        exchange = new ArrayList(list.subList(50, 53));
    }
}

