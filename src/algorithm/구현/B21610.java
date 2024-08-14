package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B21610 {
    static int N;
    static int[][] map;
    static List<int[]> clouds;
    static boolean[][] wasCloud;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds = new ArrayList<>();
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 2, 1});
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 1, 1});

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            moveClouds(d, s);
            raining();
            copyWater();
            makeClouds();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += map[i][j];
            }
        }

        System.out.println(answer);

    }

    static void moveClouds(int d, int s){
        // 1. 모든 구름이 d 방향으로 s칸 이동한다.
        wasCloud = new boolean[N][N];
        for (int i = 0; i < clouds.size(); i++) {
            int nx = (clouds.get(i)[0] + dx[d] * s + N * s) % N;
            int ny = (clouds.get(i)[1] + dy[d] * s + N * s) % N;

            clouds.set(i, new int[]{nx, ny});
            wasCloud[nx][ny] = true;
        }
    }

    static void raining(){
        // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
        for(int[] cloud : clouds){
            map[cloud[0]][cloud[1]]++;
        }
    }

    static void copyWater(){
        // 3. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전
        for(int[] cloud : clouds){
            int cnt = 0;
            for(int i = 1; i < 8; i += 2){
                int nx = cloud[0] + dx[i];
                int ny = cloud[1] + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < N && map[nx][ny] > 0) cnt++;
            }

            map[cloud[0]][cloud[1]] += cnt;
        }
    }

    static void makeClouds(){
        // 4. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
        List<int[]> newClouds = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] >= 2 && !wasCloud[i][j]){
                    newClouds.add(new int[]{i, j});
                    map[i][j] -= 2;
                }
            }
        }

        clouds = newClouds;
    }
}
