package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2565 {
    /**
     * a의 전봇대 번호를 배열의 인덱스로 놓고, b의 전봇대 번호를 배열의 값으로 넣는다.
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[501];
        int lastIdx = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lastIdx = Math.max(lastIdx, a > b? a: b);
            nums[a] = b;
        }

        int[] dp = new int[lastIdx + 1];

        dp[1] = 1;
        int answer = 1;
        for (int i = 2; i < lastIdx + 1; i++) {
            if (nums[i] == 0) continue;

            dp[i] = 1;

            for (int j = 1; j < i; j++) {
                if (nums[j] == 0) continue;
                if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(N - answer);
    }
}
