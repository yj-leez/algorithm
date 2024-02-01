package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1463 {
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n+1];
        System.out.println(bottomUp(n));

    }
    public static int bottomUp(int n){
        dp[1] = 0;
        int[] compare = new int[3];

        for (int i = 2; i <= n; i++) {
            compare[0]=100000;
            compare[1]=100000;
            compare[2]=100000;


            if(i % 3 == 0) compare[0] = dp[i/3] + 1;
            if(i % 2 == 0) compare[1] = dp[i/2] + 1;
            compare[2] = dp[i - 1] + 1;

            dp[i] = Arrays.stream(compare).min().getAsInt();
        }

        return dp[n];

    }
}
