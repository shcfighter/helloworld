package com.ecit.dp;

/**
 * 硬币组合
 * 1分2分5分的硬币，组成1角，共有多少种组合
 */
public class DpCombination {
    public static void main(String[] args) {
        int weight[] = {1,2,5};
        int[] dp = new int[11];
        dp[0] = 1;
        for(int i=0; i<3; i++){
            for(int j=weight[i]; j<=10; j++){
                dp[j] += dp[j - weight[i]];
            }
        }
        System.out.println(dp[10]);
    }
}
