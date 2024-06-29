package algorithm.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11441 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            if (start == 0){
                sb.append(nums[end]);
                sb.append("\n");
            } else {
                sb.append(nums[end] - nums[start - 1]);
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
