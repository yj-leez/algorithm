package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2491 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] smaller = new int[N];
        int[] bigger = new int[N];
        smaller[0] = 1;
        bigger[0] = 1;
        int ans = 1;
        for (int i = 1; i < N; i++) {
            smaller[i] = 1;
            bigger[i] = 1;

            if (nums[i - 1] <= nums[i]) bigger[i] = Math.max(bigger[i], bigger[i - 1] + 1);
            if (nums[i - 1] >= nums[i]) smaller[i] = Math.max(smaller[i], smaller[i - 1] + 1);

            ans = Math.max(ans, bigger[i]);
            ans = Math.max(ans, smaller[i]);
        }
        System.out.println(ans);
    }
}
