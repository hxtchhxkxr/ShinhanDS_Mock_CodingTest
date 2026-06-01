package ex4;

import java.util.Arrays;

public class WinningBidAmount {

	public static void main(String[] args) {
		WinningBidAmount sol = new WinningBidAmount();

		int n1 = 4;
		Integer[] amounts1 = { 1000000, 490000, 700000, 290000 };

		int n2 = 6;
		Integer[] amounts2 = { 30000, 70000, 10000 };

		System.out.println(Arrays.toString(sol.solution(n1, amounts1)));
		// result: [710000, 500000, 300000, 290000]

		 System.out.println(Arrays.toString(sol.solution(n2, amounts2)));
		// result: [40000, 30000, 20000, 10000, 10000, 0]
	}

	// n : 경매 물품의 수
	// amounts : 참가자 m명의 초기 자본을 담은 1차원 정수 배열

	// answer : 물품 번호가 낮은 순으로 낙찰된 금액을 담은 길이가 n인 1차원 정수 배열
	public int[] solution(int n, Integer[] amounts) {
		int[] answer = new int[n];

		for (int i = 0; i < n; i++) {
			Arrays.sort(amounts, (o1, o2) -> o2 - o1);

			// 자본이 가장 많이 남은 참가자가 2명 이상인 경우
			if (amounts[0].equals(amounts[1])) {
				answer[i] = amounts[0];
				amounts[0] = 0;
			// 낙찰 금액 자본이 가장 많이 남은 참가자가 1명인 경우
			} else {
				answer[i] = amounts[1] + 10000;
				amounts[0] -= answer[i];
			}
		}

		return answer;
	}
}
