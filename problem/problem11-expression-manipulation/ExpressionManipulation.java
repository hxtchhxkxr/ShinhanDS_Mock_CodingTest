import java.util.*;

public class ExpressionManipulation {
    public static void main(String[] args) {
        ExpressionManipulation sol = new ExpressionManipulation();

        String expression1 = "2-1x5-4x3+2";
        System.out.println(sol.solution(expression1));
        // result : 11

        String expression2 = "2x3-1";
        System.out.println(sol.solution(expression2));
        // result : 5
    }

    public int solution(String expression) {
        return search(expression);
    }

    private int search(String expression) {
        int max = Integer.MIN_VALUE;

        for (int start = 0; start < expression.length(); start += 2) {
            for (int end = start + 2; end < expression.length(); end += 2) {
                int result = calculate(expression, start, end);
                max = Math.max(max, result);
            }
        }

        return max;
    }

    private int calculate(String expression, int start, int end) {
        String innerExpression = expression.substring(start, end + 1);
        int innerEval = evaluate(innerExpression);

        String outerExpression = expression.substring(0, start) + String.valueOf(innerEval) + expression.substring(end + 1);
        int outerEval = evaluate(outerExpression);

        return outerEval;
    }

    private int evaluate(String expression) {
        Stack<Integer> num = new Stack<>();

        int i = 0;

        // 첫 번째 숫자 읽기
        int sign = 1;

        if (expression.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        int first = 0;

        while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
            first = first * 10 + (expression.charAt(i) - '0');
            i++;
        }

        num.push(first * sign);

        // 연산자 + 다음 숫자 처리
        while (i < expression.length()) {
            char operator = expression.charAt(i);
            i++;

            // 다음 숫자가 음수인지 확인
            sign = 1;

            if (i < expression.length() && expression.charAt(i) == '-') {
                sign = -1;
                i++;
            }

            // 다음 숫자 읽기
            int next = 0;

            while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                next = next * 10 + (expression.charAt(i) - '0');
                i++;
            }

            next *= sign;

            if (operator == '+') {
                num.push(next);
            } else if (operator == '-') {
                num.push(-next);
            } else if (operator == 'x') {
                int previous = num.pop();
                num.push(previous * next);
            }
        }

        int sum = 0;

        while (!num.isEmpty()) {
            sum += num.pop();
        }

        return sum;
    }
}
