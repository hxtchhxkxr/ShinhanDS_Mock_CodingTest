import java.util.*;

public class StockPurchase {

    public static void main(String[] args) {
        StockPurchase stockPurchase = new StockPurchase();

        int[] prices1 = {1, 2, 3, 4};
        System.out.println(stockPurchase.solution(prices1));
        // result : 4

        int[] prices2 = {1, 2, 4, 1, 2, 3};
        System.out.println(stockPurchase.solution(prices2));
        // result : 5

        int[] prices3 = {4, 3, 2, 1, 4};
        System.out.println(stockPurchase.solution(prices3));
        // result : 3

        int[] prices4 = {4, 3, 2, 1};
        System.out.println(stockPurchase.solution(prices4));
        // result : 0

        int[] prices5 = {1, 10, 5, 11, 7};
        System.out.println(stockPurchase.solution(prices5));
        // result : 15
    }

    public int solution(int[] prices) {
        int max1 = calMaxOne(prices);
        int max2 = calMaxTwo(prices);

        return Math.max(max1, max2);
    }

    private int calMaxOne(int[] prices) {
        int max = 0;

        for (int buy = 0; buy < prices.length - 1; buy++) {
            for (int sell = 1; sell < prices.length; sell++) {
                if (buy < sell) {
                    int profit = prices[sell] - prices[buy];
                    max = Math.max(profit, max);
                }
            }
        }

        return max;
    }

    private int calMaxTwo(int[] prices) {
        int max = 0;

        for (int buy1 = 0; buy1 < prices.length; buy1++) {
            for (int buy2 = 0; buy2 < prices.length; buy2++) {
                for (int sell1 = 1; sell1 < prices.length; sell1++) {
                    for (int sell2 = 1; sell2 < prices.length; sell2++) {
                        if(buy1 < sell1 && buy2<sell2){
                            if(buy1 != buy2 && sell1 != sell2 && buy1 != sell2 && buy2 != sell1) {
                                int profit1 = prices[sell1] - prices[buy1];
                                int profit2 = prices[sell2] - prices[buy2];
                                max = Math.max(max, profit1 + profit2);
                            }
                        }
                    }
                }
            }
        }

        return max;
    }
}
