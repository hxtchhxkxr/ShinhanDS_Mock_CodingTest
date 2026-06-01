import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NumberSearch {

    public static void main(String[] args) {
        NumberSearch ns = new NumberSearch();

        String[] numstrs1 = { "ZASSETE", "S4Z537B", "7_ASZEYB" };
        String[] words1 = { "2455373", "425", "373", "378" };
        System.out.println("result1: " + Arrays.toString(ns.solution(numstrs1, words1)));
        // result1: [3, 2, 3, 2]

        String[] numstrs2 = { "ZAZZ373"};
        String[] words2 = { "2422373", "5455373", "2455373"};
        System.out.println("result2: " + Arrays.toString(ns.solution(numstrs2, words2)));
        // result2: [1, 1, 0]
    }

    public int[] solution(String[] numstrs, String[] words) {
        int[] result = new int[words.length];

        // ArrayList<Integer> result = new ArrayList<>();

        // 각 words에 대해 검사하기
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            System.out.println("==== word 검사 시작: " + word + " ====");

            // 해당 word가 numstrs의 원소 중 몇개랑 포함되는지
            for (String numstr : numstrs) {
                result[i] += numOfWordtoSign(numstr,word);
            }
        }

        return result;
    }

    public static HashMap<String, String[]> numToSign = new HashMap<>();
    static {
            numToSign.put("0", new String[] { "0","O", "()" });
            numToSign.put("1", new String[] { "1", "I" });
            numToSign.put("2", new String[] { "2", "Z", "S", "7_" });
            numToSign.put("3", new String[] { "3", "E", "B" });
            numToSign.put("4", new String[] { "4", "A" });
            numToSign.put("5", new String[] { "5", "Z","S" });
            numToSign.put("6", new String[] { "6", "b", "G" });
            numToSign.put("7", new String[] { "7", "T" ,"Y"});
            numToSign.put("8", new String[] { "8", "B" ,"E3"});
            numToSign.put("9", new String[] { "9", "g", "q" });
    }

    public static void make(String word, int idx, String current, ArrayList<String> result){
        if(idx == word.length()){
            result.add(current);
            return;
        }

        String[] signs = numToSign.get((String.valueOf(word.charAt(idx))));

        for (String sign : signs){
            make(word, idx+1,current+sign,result);
        }
    }

    public static int numOfWordtoSign(String numstr, String word) {
        int result = 0;

        ArrayList<String> possibleSign = new ArrayList<>();
        make(word, 0, "", possibleSign);

        System.out.println("\nword: " + word);
        System.out.println("numstr: " + numstr);
        System.out.println("possibleSign: " + possibleSign);

        for (String ps : possibleSign) {
            if (numstr.contains(ps)) {
                result++;
                System.out.println("매칭됨: " + ps);
                break;
            }
        }

        System.out.println("return result: " + result);

        return result;
    }

}
