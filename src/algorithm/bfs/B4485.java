package algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * dfs로 실패하고 bfs + 우선순위 큐를 사용하여 풀이했더니 통과하였다.
 * 하지만 visited를 활용하여 풀이하였기 때문에 최소비용보다 해당 칸에 먼저 도착하느냐를 따지지 않을까라는 생각이 들었다.
 * 그래서 최소비용을 담는 dijk 배열을 만들어 현재까지의 최소비용보다 더 작은 경우에만 우선순위큐에 넣는 조건으로 바꾸었다.
 */
public class B4485 {
    /**
     * Version2. bfs + 우선순위큐
     */
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int idx = 1;
        while(N != 0){
            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            // {현재까지의 금액 합, x, y}
            PriorityQueue<int[]> pQ = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0], o2[0]); // 첫번째 원소의 오름차순
                }
            });
            boolean[][] visited = new boolean[N][N];
            int min = Integer.MAX_VALUE;

            pQ.add(new int[]{map[0][0] ,0, 0});
            visited[0][0] = true;

            while(!pQ.isEmpty()){
                int sum = pQ.peek()[0];
                int x = pQ.peek()[1];
                int y = pQ.poll()[2];

                if (sum >= min) continue;

                if(x == N - 1 && y == N - 1){
                    min = Math.min(min, sum);
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    pQ.add(new int[]{sum + map[nx][ny], nx, ny});
                }
            }


            sb.append("Problem " + idx + ": " + min);
            sb.append("\n");

            N = Integer.parseInt(br.readLine());
            idx++;
        }

        System.out.println(sb);
    }

}

class UseDijkstra{
    /**
     * Version3. bfs + 우선순위큐 + dijk 배열
     */
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int idx = 1;
        while(N != 0){
            int[][] map = new int[N][N];
            int[][] dijk = new int[N][N]; // 최소 비용을 저장

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dijk[i][j] = Integer.MAX_VALUE;
                }
            }
            dijk[0][0] = map[0][0];


            // {현재까지의 금액 합, x, y}
            PriorityQueue<int[]> pQ = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0], o2[0]); // 첫번째 원소의 오름차순
                }
            });

            pQ.add(new int[]{map[0][0] ,0, 0});

            while(!pQ.isEmpty()){
                int sum = pQ.peek()[0];
                int x = pQ.peek()[1];
                int y = pQ.poll()[2];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                    if (dijk[x][y] + map[nx][ny] < dijk[nx][ny]){
                        pQ.add(new int[]{dijk[x][y] + map[nx][ny], nx, ny});
                        dijk[nx][ny] = dijk[x][y] + map[nx][ny];
                    }
                }
            }


            sb.append("Problem " + idx + ": " + dijk[N - 1][N - 1]);
            sb.append("\n");

            N = Integer.parseInt(br.readLine());
            idx++;
        }

        System.out.println(sb);
    }
}

class Failure {
    /**
     * Version3. dfs
     */
    static int N;
    static int[][] map;
    static int min;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    /**
     * 목적지까지 가는데 최소 금액으로 이동하는 경로를 구해야하기 때문에 DFS로 경로를 찾고 계산해서 최소값을 얻는 것이라 생각했다. -> 시간초과
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int idx = 1;
        while(N != 0){
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[N][N];
            visited[0][0] = true;
            min = Integer.MAX_VALUE;
            dfs(0, 0, map[0][0]);

            sb.append("Problem " + idx + ": " + min);
            sb.append("\n");

            N = Integer.parseInt(br.readLine());
            idx++;
        }

        System.out.println(sb);
    }

    static void dfs(int x, int y, int sum){
        if (sum >= min) return;

        if(x == N - 1 && y == N - 1){
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}
