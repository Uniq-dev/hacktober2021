class Knapsack{
    public static void main(String[] args) {
        long N = 4, W = 8;

        long[] wt = {3, 4, 4, 5};
        long[] val = {30, 50, 55, 60};

	/* for sample lets take dp size as N*100000 */

        long[][] dp = new long[(int) N][(int) 1e5];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(Knapsack_DP(N, W, wt, val, dp, 0, 0));
    }

    private static long Knapsack_DP(long N, long W, long[] wt, long[] val, long[][] dp, int idx, long w) {
        if (w > W) return (long) -1e14;
        if (idx >= N) return 0;

        if (dp[idx][(int) w] != -1) return dp[idx][(int) w];

        long steal = val[idx] + Knapsack_DP(N, W, wt, val, dp, idx + 1, wt[idx] + w);
        long no_steal = Knapsack_DP(N, W, wt, val, dp, idx + 1, w);
        return dp[idx][(int) w] = Math.max(steal, no_steal);
    }
}