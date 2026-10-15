
class Problem2 {
    static void warehouseSummary(int[][] grid) {
        int totalItems = 0;
        int maxItems = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int value = grid[row][col];
                totalItems += value;

                if (value > maxItems) {
                    maxItems = value;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        System.out.println("(" + totalItems + ", ("
                + maxRow + ", " + maxCol + "))");
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        warehouseSummary(grid);
    }
}
