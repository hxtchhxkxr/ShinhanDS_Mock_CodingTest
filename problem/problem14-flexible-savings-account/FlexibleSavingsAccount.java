import java.util.*;

public class FlexibleSavingsAccount {

    public static void main(String[] args) {
        FlexibleSavingsAccount flexibleSavingsAccount = new FlexibleSavingsAccount();

        String[] ledgers1 = {
                "01/01 4 50000",
                "01/11 6 3555",
                "02/01 0 -23555",
                "02/25 5 5000",
                "03/25 0 -15000",
                "06/09 8 43951",
                "12/30 9 99999"
        };

        System.out.println(flexibleSavingsAccount.solution(ledgers1));
        // result : 2983

        String[] ledgers2 = {
                "04/01 1 40000",
                "05/01 5 20000",
                "08/31 4 10000",
                "11/11 0 -45000"
        };

        System.out.println(flexibleSavingsAccount.solution(ledgers2));
        // result : 888
    }

    public int solution(String[] ledgers) {
        Stack<int[]> s = new Stack<>();
        int sum = 0;

        for (String ledger : ledgers) {
            // 거래일
            int num1 = calDays(ledger.substring(0, 5));
            // 금리
            int num2 = Integer.parseInt(ledger.substring(6, 7));
            // 입/출금액
            int num3 = Integer.parseInt(ledger.substring(8));

            // 입금
            if (num3 > 0) {
                s.push(new int[]{num1,num2,num3});
            }

            // 출금
            else if(num3<0){
                int money = -num3;

                while(money >0){
                    int[] deposit = s.pop();

                    int day = deposit[0];
                    int rate = deposit[1];
                    int amount = deposit[2];

                    int used = Math.min(amount, money);

                    sum += calInterest(num1 - day, rate, used);

                    amount -= used;
                    money -= used;

                    if(amount > 0){
                        s.push((new int[]{day, rate, amount}));
                    }
                }
            }
        }

        while(!s.empty()){
            int[] deposit = s.pop();

            int day = deposit[0];
            int rate = deposit[1];
            int amount = deposit[2];

            sum += calInterest(365 - day, rate, amount);
        }

        return sum;
    }

    private int calDays(String s) {
        int month = Integer.parseInt(s.substring(0, 2));
        int day = Integer.parseInt(s.substring(3, 5));

        int[] calender = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int days = 0;

        for (int i = 0; i < month - 1; i++) {
            days += calender[i];
        }

        days += day;

        return days;
    }

    // 예치기간, 금리, 금액
    private int calInterest(int num1, int num2, int num3) {
        return num3 * num2 * num1 / 100 / 365;
    }
}
