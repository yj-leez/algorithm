package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            
            int[] novel = new int[K]; // 파일의 크기
            int[] prefixSum = new int[K]; // 누적합

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                novel[j] = Integer.parseInt(st.nextToken());
                
                if (j == 0) prefixSum[j] = novel[j];
                else prefixSum[j] = novel[j] + prefixSum[j - 1];
            }

            int[][] dp = new int[K][K]; // dp[i][j] = i부터 j까지 합치는데 드는 최소 비용
            for (int j = 1; j < K; j++) {
                for (int from = 0; from + j < K; from++) {
                    int to = from +  j;

                    dp[from][to] = Integer.MAX_VALUE;
                    for (int d = from; d < to; d++) {
                        // (from ~ d까지 합치는데 드는 최소 비용 + d ~ to까지 합치는데 드는 최소 비용 + from ~ to까지 파일의 합)의 최소값을 탐색
                        dp[from][to]= Math.min(dp[from][to],
                                dp[from][d] + dp[d + 1][to] + prefixSum[to] - (from == 0? 0 :prefixSum[from - 1]));
                    }
                }
            }

            sb.append(dp[0][K - 1] + "\n");
        }

        System.out.print(sb);
    }
}
