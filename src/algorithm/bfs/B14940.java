package algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14940 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] answer = new int[N][M];
//        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                answer[i][j] = -1;
                if (map[i][j] == 2){
                    answer[i][j] = 0;
                    q.add(new int[]{i, j});
                }
                if (map[i][j] == 0){
                    answer[i][j] = 0;
                }
            }
        }

        while(!q.isEmpty()){
            int x = q.peek()[0];
            int y = q.poll()[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 0 || answer[nx][ny] > -1) continue;

                answer[nx][ny] = answer[x][y] + 1;
                q.add(new int[]{nx, ny});
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }
}
