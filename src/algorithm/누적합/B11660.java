package algorithm.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] nums = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2차원 배열의 부분합 구하기
        int[][] sum = new int[N][N];
        sum[0][0] = nums[0][0];
        for (int i = 1; i < N; i++) {
            sum[0][i] = sum[0][i - 1] + nums[0][i];
            sum[i][0] = sum[i - 1][0] + nums[i][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + nums[i][j];
            }
        }

        // 총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구하기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int startY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;

            int ans = sum[endX][endY];
            if (startX != 0){
                ans -= sum[startX - 1][endY];
            }
            if (startY != 0){
                ans -= sum[endX][startY - 1];
            }
            if (startX != 0 && startY != 0){
                ans += sum[startX - 1][startY - 1];
            }

            sb.append(ans);
            sb.append("\n");

        }
        System.out.println(sb);

    }
}
