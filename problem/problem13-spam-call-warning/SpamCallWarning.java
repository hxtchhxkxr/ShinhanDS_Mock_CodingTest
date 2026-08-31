import java.util.*;

public class SpamCallWarning {

    public static void main(String[] args) {
        SpamCallWarning spamCallWarning = new SpamCallWarning();

        String[] approved1 = {"123-4567", "451-2314", "015-1643"};
        String[] spams1 = {"111-1111"};
        String[] calls1 = {
                "123-4567",
                "000-0022",
                "015-1643",
                "000-0022",
                "111-1111",
                "000-0022",
                "111-1111",
                "111-1111"
        };
        int k1 = 2;

        System.out.println(Arrays.toString(spamCallWarning.solution(approved1, spams1, calls1, k1)));
        // result : [0, 1, 0, 1, 1, 0, 1, 1]

        String[] approved2 = {"123-1000"};
        String[] spams2 = {"456-2000"};
        String[] calls2 = {
                "456-2000",
                "456-2000",
                "123-1000",
                "123-1000",
                "789-3000",
                "789-3000",
                "789-3000",
                "789-3000",
                "789-3000"
        };
        int k2 = 3;

        System.out.println(Arrays.toString(spamCallWarning.solution(approved2, spams2, calls2, k2)));
        // result : [1, 1, 0, 0, 1, 1, 1, 0, 0]
    }

    public int[] solution(String[] approved, String[] spams, String[] calls, int k) {
        int[] result = new int[calls.length];
        HashMap<String, Integer> callNum = new HashMap<>();

        for (int i = 0; i < calls.length; i++) {
            String call = calls[i];

            if (isContain(call, spams)) {
                result[i] = 1;
                continue;
            }

            if (isContain(call, approved)) {
                result[i] = 0;
                continue;
            }

            callNum.put(call, callNum.getOrDefault(call, 0) + 1);

            if (callNum.get(call) <= k) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }

        return result;
    }

    private boolean isContain(String call, String[] nums) {
        for (String num : nums) {
            if (num.equals(call)) {
                return true;
            }
        }
        return false;
    }
}
