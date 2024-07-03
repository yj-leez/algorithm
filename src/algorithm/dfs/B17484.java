package algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17484 {
    static int N;
    static int M;
    static int[][] moon;
    static int[] dy = {-1, 0, 1}; // 방향
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        moon = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                moon[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            dfs(0, i, -1, moon[0][i]);
        }

        System.out.println(min);

    }

    static void dfs(int x, int y, int direction, int sum){

        if (x == N - 1){
            min = min > sum? sum: min;
            return;
        }

        for (int d = 0; d < 3; d++) {
            if (d != direction && 0 <= y + dy[d] && y + dy[d] < M){
                dfs(x + 1, y + dy[d], d, sum + moon[x + 1][y + dy[d]]);
            }
        }

    }
}
