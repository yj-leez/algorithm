package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] reservation = new int[N + 1][2]; // [i][0]: 상담하는 기간, [i][1]: 상담 비용
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            reservation[i][0] = Integer.parseInt(st.nextToken());
            reservation[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        for(int i = 1; i <= N; i++){
            int day = i + reservation[i][0] - 1;
            dp[i] = Math.max(dp[i], dp[i - 1]);
            if(day <= N){
                dp[day] = Math.max(dp[day], dp[i - 1] + reservation[i][1]);
            }
        }

        System.out.println(dp[N]);
    }
}
