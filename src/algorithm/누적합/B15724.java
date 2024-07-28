package algorithm.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 행
        int M = Integer.parseInt(st.nextToken()); // 열

        int[][] map = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // (1, 1)부터 (N, M)까지의 누적합 구하기
        int[][] sum = new int[N + 1][M + 1];
        sum[1][1] = map[1][1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + map[i][j];
            }
        }

        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());

            int X1 = Integer.parseInt(st.nextToken());
            int Y1 = Integer.parseInt(st.nextToken());
            int X2 = Integer.parseInt(st.nextToken());
            int Y2 = Integer.parseInt(st.nextToken());

            sb.append(sum[X2][Y2] - sum[X2][Y1 - 1] - sum[X1 - 1][Y2] + sum[X1 - 1][Y1 - 1]);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
