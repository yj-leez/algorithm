package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        int[] sum = new int[k + 1];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }


        // 첫번째 코인으로 만들 수 있는 경우의 수 저장
        for (int i = coins[0]; i <= k; i++) {
            if (coins[0] > k) break;

            if (i % coins[0] == 0){
                sum[i]++;
            }
        }


        // 두번째 코인부터 마지막 코인까지 만들 수 있는 경우의 수 저장
        for (int i = 1; i < n; i++) {



            // 해당 코인의 가치 == 가치의 합인 가치의 합에서는 + 1
            int coin = coins[i];
            if (coin > k) continue;

            sum[coin]++;

            for (int j = coin + 1; j <= k; j++) {
                sum[j] += sum[j - coin];
            }

        }

        System.out.println(sum[k]);
    }
}
