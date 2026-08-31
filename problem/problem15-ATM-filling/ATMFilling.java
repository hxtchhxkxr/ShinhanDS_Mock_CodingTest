import java.util.*;

public class ATMFilling {

    public static void main(String[] args) {
        ATMFilling atmFilling = new ATMFilling();

        int n1 = 2;
        int money1 = 4;
        int t1 = 1;
        int k1 = 3;

        System.out.println(atmFilling.solution(n1, money1, t1, k1));
        // result : 3

        int n2 = 4;
        int money2 = 7;
        int t2 = 2;
        int k2 = 4;

        System.out.println(atmFilling.solution(n2, money2, t2, k2));
        // result : 28

        int n3 = 4;
        int money3 = 7;
        int t3 = 2;
        int k3 = 3;

        System.out.println(atmFilling.solution(n3, money3, t3, k3));
        // result : 0
    }

    int cnt;

    public int solution(int n, int money, int t, int k) {
        cnt = 0;

        int[] current = new int[n];

        backtrack(0, money, n, t, k, current);

        return cnt;
    }

    private void backtrack(
            int depth,
            int remain,
            int n,
            int t,
            int k,
            int[] current
    ) {
        // 종료 조건
        if (depth == n) {
            if (remain == 0) {
                // 유효한 경우 1개 발견
                cnt++;
            }
            return;
        }

        // 현재 ATM에 넣을 금액 선택
        for (int money = 0; money <= remain; money++) {
            // 현재 ATM에 금액 저장
            current[depth] = money;

            // 연속 t개 합 조건 검사
            if (isValid(depth, t, k, current)) {
                // 다음 ATM으로 이동
                backtrack(depth + 1, remain - money, n, t, k, current);
            }
        }
    }

    private boolean isValid(
            int depth,
            int t,
            int k,
            int[] current
    ) {
        // 아직 t개의 ATM이 채워지지 않았다면 true
        if (depth + 1 < t) {
            return true;
        }

        // 최근 t개의 ATM 금액 합 계산
        int sum = 0;

        for (int i = depth - t + 1; i <= depth; i++) {
            sum += current[i];
        }

        // 합이 k 이하인지 반환
        return sum <= k;
    }
}
