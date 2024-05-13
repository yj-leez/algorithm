package algorithm.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1920 {

    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(bs(target) + "\n");
        }
        System.out.println(sb);
    }

    static int bs(int k){
        int st = 0;
        int en = nums.length - 1;

        while(st <= en){
            int mid = (st + en) / 2;

            if (k == nums[mid]) return 1;
            else if (k < nums[mid]) {
                en = mid - 1;
            } else if (nums[mid] < k){
                st = mid + 1;
            }

        }
        return 0;
    }

}
