package algorithm.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2512 {
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[N];
        int sum = 0;
        int hi = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            sum += nums[i];
            hi = Math.max(hi, nums[i]);
        }
        int M = Integer.parseInt(br.readLine());

        if (sum <= M){
            System.out.println(hi);
            return;
        }

        int lo = 1;
        while(lo < hi - 1){
            int mid = (lo + hi)/ 2;

            if (cal(mid) > M) hi = mid;
            else lo = mid;
        }
        System.out.println(lo);
    }

    static int cal(int n){
        int sum = 0;
        for(int i: nums){
            sum += Math.min(i, n);
        }
        return sum;
    }
}
