package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2294{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        int[] sum = new int[k+1];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(sum, k + 1);
        for (int i = 1; i <= k; i++) {
            if (coins[0] > k) break;
            if(i % coins[0] == 0) sum[i] = i / coins[0];
        }

        for (int i = 1; i < n; i++) {

            int coin = coins[i];
            if (coin > k) continue;
            sum[coin] = 1;

            for (int j = coin + 1; j <= k; j++) {
                if(sum[j] > sum[j- coin] + 1){
                    sum[j] = sum[j - coin] + 1;
                }
            }
        }

        System.out.println(sum[k] == k + 1 ? -1 : sum[k]);

    }
}
