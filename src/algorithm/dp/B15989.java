package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        int maxNum = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            maxNum = maxNum < nums[i]? nums[i]: maxNum;
        }

        maxNum = maxNum < 3? 3: maxNum;
        int[][] cal = new int[maxNum + 1][3];
        cal[1][0] = 1;
        cal[2][0] = 1; cal[2][1] = 1;
        cal[3][0] = 1; cal[3][1] = 1; cal[3][2] = 1;

        for (int i = 4; i <= maxNum; i++) {
            cal[i][0] = cal[i - 1][0];
            cal[i][1] = cal[i - 2][0] + cal[i - 2][1];
            cal[i][2] = cal[i - 3][0] + cal[i - 3][1] + cal[i - 3][2];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(cal[nums[i]][0] + cal[nums[i]][1] + cal[nums[i]][2]);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
