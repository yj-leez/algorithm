package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P15685 {
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] dragons;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dragons = new boolean[101][101];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            dragonCurve(y, x, d, g);
        }

        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(dragons[i][j] && dragons[i][j + 1] && dragons[i + 1][j] && dragons[i + 1][j + 1]) answer++;
            }
        }

        System.out.println(answer);
    }

    static void dragonCurve(int x, int y, int d, int g){
        List<Integer> directions = new ArrayList<>(); // 방향 기억하는 배열
        dragons[x][y] = true;

        x = x + dx[d];
        y = y + dy[d];
        dragons[x][y] = true;
        directions.add(d);

        for (int generate = 0; generate < g; generate++) {
            int size = directions.size();
            for (int i = size - 1; i >= 0; i--) {
                // 역순으로 K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에 붙임
                int nowD = directions.get(i) + 1 > 3? 0: directions.get(i) + 1;
                int nowX = x + dx[nowD];
                int nowY = y + dy[nowD];

                directions.add(nowD);
                dragons[nowX][nowY] = true;
                x = nowX;
                y = nowY;
            }
        }
    }
}
