package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class B14466 {
    /**
     * 메모리 초과는 길을 담는 배열때문에 났었다.
     * boolean[N][N][4]는 최악의 경우 100×100×4=40,000바이트 차지
     * List<int[]>는 최악의 경우 100×4×4=1,600바이트 차지하므로 훨씬 적게 차지하여 테스트를 통과할 수 있었다.
     */
    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<int[]> bridge;
    static List<int[]> cows;  // 소의 위치의 정보를 저장
    static int[][] map; // 같은 영역이라면 같은 숫자로 표기되어있음, 1부터 시작

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        bridge = new ArrayList<>(); // 길이 있다면 true


        // 길의 위치
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            bridge.add(new int[]{x1, y1, x2, y2});
        }

        // 소의 위치
        cows = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            cows.add(new int[]{x, y});
        }


        map = new int[N][N];
        for(int i = 0 ; i < K ; i++){ // 소가 있는 위치를 기준으로 탐색 시작
            if(map[cows.get(i)[0]][cows.get(i)[1]] == 0){  // 이미 탐색이 된 곳이라면 pass
                makeArea(cows.get(i), i + 1);
            }
        }

        int ans = 0;
        for (int i = 0; i < K; i++) {
            for (int j = i + 1; j < K; j++) {
                if (map[cows.get(i)[0]][cows.get(i)[1]] != map[cows.get(j)[0]][cows.get(j)[1]]){
                    ans++;
                }
            }
        }

        System.out.println(ans);

    }

    static void makeArea(int[] c, int area) {

        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(c);
        visited[c[0]][c[1]] = true;
        map[c[0]][c[1]] = area;

        while (!queue.isEmpty()) {

            int[] tmp = queue.poll();

            next:for (int i = 0; i < 4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue next;

                // 둘 사이에 다리가 있는지 확인
                for (int[] b: bridge){
                    if(b[0]==tmp[0] && b[1]==tmp[1] && b[2]==nx &&b[3]==ny) continue next;
                    if(b[0]==nx &&b[1]==ny && b[2]==tmp[0] &&b[3]==tmp[1]) continue next;
                }

                /**
                 * bfs에서 visited 배열에 체크해주는 행동은 queue에 add할 때 하는 것이 pop할 때 하는 것보다 시간이 덜 걸린다.
                 */
                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                map[nx][ny] = area; // 탐색한 자리의 영역을 표시
            }
        }
    }
}


class Failure {
    /**
     * 소들의 위치를 int[K][2]에 넣어서 소들의 쌍끼리 bfs로 길 없이 만날 수 있는지 확인 -> Queue에 매번 int[]쌍을 생성해 넣으므로 메모리 초과
     * 한 번의 bfs로 각 소들이 접근할 수 있는 영역을 숫자화하여 int[N][N]에 저장 -> 두 개의 소가 같은 영역에 있는지 확인 -> 결과: 메모리 초과
     */
    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][][] roads;
    static int[][] cows;
    static int[][] map; // 같은 영역이라면 같은 숫자로 표기되어있음, 1부터 시작
    static int areaIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        roads = new boolean[N][N][4]; // 길이 있다면 true


        // 길의 위치
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            int dir1 = -1; int dir2;
            for (int j = 0; j < 4; j++) {
                if (x1 + dx[j] == x2 && y1 + dy[j] == y2){
                    dir1 = j;
                    break;
                }
            }

            if (dir1 % 2 == 0) dir2 = dir1 + 1;
            else dir2 = dir1 - 1;

            roads[x1][y1][dir1] = true;
            roads[x2][y2][dir2] = true;
        }

        // 소의 위치
        cows = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            cows[i] = new int[]{x, y};
        }


        map = new int[N][N];
        areaIdx = 1;
        for (int i = 0; i < K; i++) {
            if (map[cows[i][0]][cows[i][1]] == 0){
                map[cows[i][0]][cows[i][1]] = areaIdx;
                makeArea(i);
                areaIdx++;
            }
        }

        int ans = 0;
        for (int i = 0; i < K; i++) {
            for (int j = i + 1; j < K; j++) {
                if (map[cows[i][0]][cows[i][1]] != map[cows[j][0]][cows[j][1]]){
                    ans++;
                }
            }
        }

        System.out.println(ans);

    }

    static void makeArea(int idx) {

        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();

        int[] cow = cows[idx];
        queue.add(cow);

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            map[tmp[0]][tmp[1]] = areaIdx; // 같은 영역에 숫자로 표기
            visited[tmp[0]][tmp[1]] = true;

            for (int i = 0; i < 4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;


                if (!roads[tmp[0]][tmp[1]][i] && !visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                }
            }
        }

    }
}
