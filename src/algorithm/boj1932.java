package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1932 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] nums = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] cal = new int[n][n];
        for (int i = 0; i < n; i++) {
            cal[n-1][i] = nums[n-1][i];
        }

        // 아랫변의 대각선 두 값 중 더 큰 값을 더해주면 윗 꼭대기에는 총 합 중 가장 큰 값이 들어간다.
        if (n > 1) {
            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    cal[i][j] = Math.max(cal[i+1][j], cal[i+1][j+1]) + nums[i][j];
                }
            }
        }

        System.out.println(cal[0][0]);
    }
}
