package algorithm.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1253 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int ans = 0;
        for(int i = 0; i < N; i++){
            int lo = 0;
            int hi = N - 1;
            while(true){
                if(lo == i) lo++;
                else if(hi == i) hi--;

                if(lo >= hi) break;

                if(nums[lo] + nums[hi] == nums[i]){
                    ans++;
                    break;
                } else if(nums[lo] + nums[hi] < nums[i]) lo++;
                else if(nums[lo] + nums[hi] > nums[i]) hi--;

            }
        }
        System.out.println(ans);
    }
}
