package algorithm.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B21318 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] mistake = new int[N + 1];
        for (int i = 1; i < N ; i++) {
            if (nums[i] > nums[i  + 1]) mistake[i] = mistake[i - 1] + 1;
            else mistake[i] = mistake[i - 1];
        }
        mistake[N] = mistake[N - 1];


        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int ans = mistake[y] - mistake[x - 1];
            if (y < N && nums[y] > nums[y + 1]) ans--;

            sb.append(ans);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
