public class BestTimeToBuyAndSellStock {

    static int maxProfit(int[] prices) {

        int lowest = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] < lowest) {
                lowest = prices[i];
            }

            int profit = prices[i] - lowest;

            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};

        System.out.println(maxProfit(prices));
    }
}
