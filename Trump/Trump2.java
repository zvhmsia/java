package Trump;

import java.util.*;

public class Trump2 extends CardDeck{
    public static ArrayList<ArrayList<String>> player = new ArrayList<>();
    public static ArrayList<ArrayList<String>> cpu = new ArrayList<>();

    public static void main(String[] args) {
        initializeGame2();
        System.out.println("player:"+ player);
        System.out.println("cpu:"+ cpu);

    }
    public Map<String, ArrayList<ArrayList<String>>> getplayers() {
        Map<String, ArrayList<ArrayList<String>>> playersMap = new HashMap<>();
        playersMap.put("player", player);
        playersMap.put("cpu", cpu);
        return playersMap;
    }
    public static void initializeGame2() {
        List<Integer> hand = Arrays.asList(5, 5);
        int index = 0;
        player = new ArrayList<>(list.subList(index, index += hand.get(0)));
        cpu = new ArrayList<>(list.subList(index, index += hand.get(1)));
    }
}
