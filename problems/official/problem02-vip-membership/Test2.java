package MembershipVIP;

import java.util.Arrays;

public class Test2 {

    public static void main(String[] args) {

        test2 sol = new test2();

        // 테스트 케이스 1
        int[] periods1 = { 8, 23, 24 };

        int[][] payments1 = {
                { 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000 },
                { 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000 },
                { 350000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000 } };

        int[] estimates1 = { 100000, 100000, 100000 };

        int[] result1 = sol.solution(periods1, payments1, estimates1);

        System.out.println("테스트1 결과: " + Arrays.toString(result1));
        // 예상 결과: [1, 1]

        // 테스트 케이스 2
        int[] periods2 = { 24, 59, 59, 60 };

        int[][] payments2 = { { 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000 },
                { 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000 },
                { 350000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000 },
                { 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000 } };

        int[] estimates2 = { 350000, 50000, 40000, 50000 };

        int[] result2 = sol.solution(periods2, payments2, estimates2);

        System.out.println("테스트2 결과: " + Arrays.toString(result2));
        // 예상 결과: [2, 1]
    }

    /*
     * 고객들의 가입기간을 담은 1차원 정수 배열 periods 고객들의 납부 내역을 담은 2차원 정수 배열 payments 고객들의 납부 예정
     * 금액을 담은 1차원 정수 배열 estimates
     */

    /*
     * solution 함수 이번 달에는 VIP가 아니지만 다음 달에 VIP가 되는 고객의 수 이번 달에는 VIP지만 다음 달에는 VIP가 아니게
     * 되는 고객의 수 -> 정수 배열에 담아 return
     */
    public int[] solution(int[] periods, int[][] payments, int[] estimates) {
        int[] answer = { 0, 0 };

        for (int i = 0; i < periods.length; i++) {
            int currentPaymentSum = calPaymentSum(payments[i]);
            int nextPaymentSum = currentPaymentSum - payments[i][0] + estimates[i];

            boolean currentVIP = isVIP(periods[i], currentPaymentSum);
            boolean nextVIP = isVIP(periods[i] + 1, nextPaymentSum);

            if (!currentVIP && nextVIP) {
                answer[0]++;
            }

            if (currentVIP && !nextVIP) {
                answer[1]++;
            }
        }

        return answer;
    }

    public int calPaymentSum(int[] payments) {
        int sum = 0;

        for (int payment : payments) {
            sum += payment;
        }

        return sum;
    }

    // VIP인지
    public boolean isVIP(int period, int paymentSum) {
        if (period < 24) {
            return false;
        } else if (period >= 24 && period < 60) {
            if (paymentSum >= 900000) {
                return true;
            } else {
                return false;
            }
        } else {
            if (paymentSum >= 600000) {
                return true;
            } else {
                return false;
            }
        }
    }
}
