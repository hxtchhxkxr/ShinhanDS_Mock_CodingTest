public class QuotientRemainder {
    public static void main(String[] args) {
        QuotientRemainder sol = new QuotientRemainder();

        int n1 = 2;
        System.out.println(sol.solution(n1));
        // result : 3

        int n2 = 3;
        System.out.println(sol.solution(n2));
        // result : 12
    }

    public int solution(int n){
        int sum = 0;

        for(int i=1; i <= n-1; i++){
            int answer = n * i + i;
            sum += answer;
        }

        return sum;
    }
}
