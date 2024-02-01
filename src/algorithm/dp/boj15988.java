package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj15988 {
    static long dp[] = new long[1000001];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        dp[1] = 1; dp[2] = 2; dp[3] = 4;

        for (int i = 0; i < n; i++) {
            long cnt = dp(Integer.parseInt(br.readLine()));
            sb.append(cnt + "\n");
        }

        System.out.println(sb);
    }

    public static long dp(int n){
        if(dp[n] > 0) return dp[n]; // 값 존재 시 리턴

        dp[n] = (dp(n-1) + dp(n-2) + dp(n-3)) % 1000000009;
        return dp[n];
    }
}
