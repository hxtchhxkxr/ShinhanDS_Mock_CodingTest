package ex3;

import java.util.Arrays;

class TicketSelling {
	public static void main(String[] args) {

		TicketSelling sol = new TicketSelling();

		// tickets : 한정된 티켓의 개수
		// requests : 티켓을 구매하려는 사람들이 담긴 배열 [회원 등급, 티켓 구매 수량]

		int tickets1 = 10;
		int[][] requests1 = { { 2, 3 }, { 1, 7 }, { 2, 4 }, { 3, 5 } };

		int tickets2 = 8;
		int[][] requests2 = { { 1, 9 }, { 3, 6 }, { 2, 5 } };

		int tickets3 = 20000;
		int[][] requests3 = { { 3, 1 }, { 2, 5 }, { 2, 10 }, { 3, 8 }, { 1, 2 } };

		System.out.println(sol.solution(tickets1, requests1)); // 10
		System.out.println(sol.solution(tickets2, requests2)); // 5
		System.out.println(sol.solution(tickets3, requests3)); // 26
	}

	// tickets : 한정된 티켓의 개수
	// requests : 티켓을 구매하려는 사람들이 담긴 배열 [회원 등급, 티켓 구매 수량]

	// soldTickets : 판매된 총 티켓 수

	/*
	 * 1. 등급순 오름차순 정렬 2. 같은 등급이면 티켓 구매 수량 내림차순 정렬 3. 만약 soldTickets >= tickets 면 반복문
	 * 탈출하고 이전 soldTickets 값 반환
	 */
	
	public int solution(int tickets, int[][] requests) {
		int soldTickets = 0;

		Arrays.sort(requests, (o1, o2) -> {
			if (o1[0] == o2[0]) {
				return o2[1] - o1[1];
			}
			return o1[0] - o2[0];
		});

		for (int[] request : requests) {
			if (soldTickets + request[1] <= tickets) {
				soldTickets += request[1];
				//System.out.println("판매 티켓 수: " + soldTickets);
			}
		}

		return soldTickets;
	}
}
