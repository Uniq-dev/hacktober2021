Class NQueens {

    public static void main(String[] args) {
        int n = 4;
        int[][] ar = new int[n][n];

        NQueens_Rec(0, ar, n);
    }

    static boolean NQueens_Rec(int col, int[][] ar, int N) {
        if (col == N) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(ar[i][j] + "  ");
                }
                System.out.println();
            }
            return true;
        }

        for (int row = 0; row < N; row++) {
            if (isSafe(row, col, ar, N)) {
                ar[row][col] = 1;
                if (NQueens_Rec(col + 1, ar, N)) return true;
                ar[row][col] = 0;
            }
        }

        return false;
    }

    static boolean isSafe(int row, int col, int[][] ar, int N) {
        // Checking all the directions that a Queen can attack
        for (int t = 1; t < N; t++) {
            if (col - t >= 0 && ar[row][col - t] == 1) return false;
            if (row - t >= 0 && col - t >= 0 && ar[row - t][col - t] == 1) return false;
            if (row + t < N && col - t >= 0 && ar[row + t][col - t] == 1) return false;
            if (row - t >= 0 && ar[row - t][col] == 1) return false;
            if (row + t < N && ar[row + t][col] == 1) return false;
            if (col + t < N && ar[row][col + t] == 1) return false;
            if (row + t < N && col + t < N && ar[row + t][col + t] == 1) return false;
            if (row - t >= 0 && col + t < N && ar[row - t][col + t] == 1) return false;
        }
        return true;
    }
}