package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B11058 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n <= 6) {
            System.out.println(n);
            return;
        }

        long[] arr = new long[n + 1];

        for (int i = 1; i < 7; i++) {
            arr[i] = i;
        }
        for (int i = 7; i <= n; i++) {
            long maxValue = Long.MIN_VALUE;
            for (int j = i - 6; j < i; j++) {
                maxValue = Math.max(maxValue, arr[j] * (i - j - 1));
            }
            arr[i] = maxValue;
        }

        System.out.println(arr[n]);

    }
}
