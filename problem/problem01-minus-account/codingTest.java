package ch05.practice;

public class codingTest {

	public static void main(String[] args) {
		Solution s = new Solution();
		int result = s.solution(5000, new int[] {100,2000});
		System.out.println(result);
		
		// 이미지 첫 번째 예시
        int m1 = 5000;
        int[] ledger1 = {10000, -13000, -4000, -2000, 6500, -20000};

        System.out.println(s.solution(m1, ledger1));
        // 결과: 1500


        // 이미지 두 번째 예시
        int m2 = 34151;
        int[] ledger2 = {-34152, -40000, -50000};

        System.out.println(s.solution(m2, ledger2));
        // 결과: 0

	}
}

class Solution{
	public int solution(int m, int[] ledger) {
		int account = 0;	// 초기 잔고액
		
		// 풀이 작성
		for(int i=0;i<ledger.length;i++) {			
			if(ledger[i]<0) {
				if(account + ledger[i] < -m) {
					continue;
				}
				else {
					account += ledger[i];
				}
			}
			else {
				account += ledger[i];
			}
		}
		
		return account;
	}
}
