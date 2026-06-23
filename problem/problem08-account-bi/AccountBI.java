import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AccountBI {
	public static void main(String[] args) {

		AccountBI accountBI = new AccountBI();

		// expected : [3, 2, 1]
		System.out.println(Arrays.toString(accountBI.solution(new String[] { "4514--234495-1", "305-44-291501",
				"1-2-34-495-8623", "492134545151", "623-421523-67341", "-5439-59639921", "6235-7X3+47-7456",
				"98-76-543-210", "512-73-634901", "000-999999-22555", "064-82-792561" })));

		// expected : [4, 2, 2, 1]
		System.out.println(Arrays.toString(accountBI.solution(new String[] { "1-2-3-456789012", "582845-385823",
				"48572-39485-89012", "4-5-2-593328484", "4958-392945123-", "49582039415423", "7-3-7-000000000",
				"485723-693812", "39482746582734", "1-1-1-111111111", "A4944-5095-4951", "4851293412223" })));

		// expected : [1]
		System.out.println(
				Arrays.toString(accountBI.solution(new String[] { "592356-5345", "49-694-4495-64", "5923565345%" })));
	}

	public int[] solution(String[] accounts) {
		ArrayList<String> list = new ArrayList<>();

		// 조건을 만족하는 계좌 배열
		for (String account : accounts) {
			if (isSatisfied(account)) {
				list.add(account);
			}
		}

		HashMap<String, Integer> map = new HashMap<>();

		// 은행별로 분류한 계좌번호의 개수(은행 : 개수)
		for (String account : list) {
			boolean found = false;

			for (String key : map.keySet()) {
				if (isSameBank(account, key)) {
					map.put(key, map.get(key) + 1);
					found = true;
					break;
				}
			}

			if (!found) {
				map.put(account, 1);
			}
		}

		// 은행별로 분류한 계좌번호의 개수 배열
		int[] result = new int[map.size()];

		int idx = 0;
		for (int value : map.values()) {
			result[idx++] = value;
		}

		// 배열 내림차순 정렬
		Arrays.sort(result);

		for (int i = 0; i < result.length / 2; i++) {
			int temp = result[i];
			result[i] = result[result.length - 1 - i];
			result[result.length - 1 - i] = temp;
		}

		return result;
	}

	public boolean isSatisfied(String account) {
		int numCnt = 0;
		int hyphenCnt = 0;

		for (char c : account.toCharArray()) {
			// 규칙 1 : 계좌번호는 0 ~ 9 사이의 숫자와 특수문자 -로만 이루어짐
			if (!(c >= '0' && c <= '9') && c != '-') {
				return false;
			}

			if (c >= '0' && c <= '9') {
				numCnt++;
			}

			if (c == '-') {
				hyphenCnt++;
			}
		}

		// 규칙 2 : 계좌번호에 포함된 숫자의 개수는 11개 이상 14개 이하
		if (numCnt < 11 || numCnt > 14) {
			return false;
		}

		// 규칙 3 : 계좌번호에 포함된 -의 개수는 0개 이상 3개 이하
		if (hyphenCnt > 3) {
			return false;
		}

		// 규칙 4 : 계좌번호에 포함된 -는 연속해서 나타날 수 없고, 계좌번호의 처음이나 마지막 자리에 나타날 수 없음
		if (account.contains("--") || account.charAt(0) == '-' || account.charAt(account.length() - 1) == '-') {
			return false;
		}

		return true;
	}

	public boolean isSameBank(String account1, String account2) {
		// 계좌의 길이가 다르면
		if (account1.length() != account2.length()) {
			return false;
		}

		// '-'의 위치가 다르면
		for (int i = 0; i < account1.length(); i++) {
			if ((account1.charAt(i) == '-') != (account2.charAt(i) == '-')) {
				return false;
			}
		}

		return true;
	}
}
