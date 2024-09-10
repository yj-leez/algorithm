package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long[][] sum = new long[n - 1][21];

        sum[0][nums[0]] = 1;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (sum[i - 1][j] > 0) {
                    int plus = j + nums[i];
                    int minus = j - nums[i];

                    if (0 <= plus && plus <= 20) {
                        sum[i][plus] += sum[i - 1][j];
                    }

                    if (0 <= minus && minus <= 20) {
                        sum[i][minus] += sum[i - 1][j];
                    }
                }

            }
        }

        System.out.println(sum[n - 2][nums[n - 1]]);

    }
}
