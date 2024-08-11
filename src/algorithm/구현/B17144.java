package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17144 {
    static int R;
    static int C;
    static int [][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[] down_dir = {0, 3, 2, 1};
    static int[] airCondition;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        airCondition = new int[2];
        int airConditionIdx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1){
                    map[i][j] = 0;
                    airCondition[airConditionIdx] = i;
                    airConditionIdx++;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            spread();
            cleanAir();
        }

        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) ans += map[i][j];
            }
        }

        System.out.println(ans);
    }

    static void spread(){
        int[][] mapCopy = new int[R][C];
        for(int i = 0; i < R; i++) {
            mapCopy[i] = map[i].clone();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] <= 0) continue;

                int spreadCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if (0 <= x && x < R && 0 <= y && y < C && !(y == 0 && (x == airCondition[0] || x == airCondition[1]))){
                        spreadCnt++;
                        mapCopy[x][y] += map[i][j]/5;
                    }
                }

                mapCopy[i][j] -= (map[i][j]/5) * spreadCnt;
            }
        }

        for(int i = 0; i < R; i++) {
            map[i] = mapCopy[i].clone();
        }
    }

    static void cleanAir(){
        // 윗 공기청정기
        int temp = map[0][0];
        int x = 0;
        int y = 0;
        for(int i = 0; i < 4; i++) {
            while (true) {
                if(x == 1 && y == 0) break;
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(ny < 0 || ny >= C || nx < 0 || nx >= airCondition[1]) {
                    break;
                }
                if(!(y == 0 && (x == airCondition[0] || x == airCondition[1]))) {
                    map[x][y] = map[nx][ny];
                }
                x = nx;
                y = ny;

            }
        }
        map[1][0] = temp;

        // 아랫 공기청정기
        temp = map[R - 1][0];
        x = R - 1;
        y = 0;

        for(int i = 0; i < 4; i++) {
            while (true) {
                if(x==(R-2) && y==0) break;
                int nx = x + dx[down_dir[i]];
                int ny = y + dy[down_dir[i]];

                if(ny < 0 || ny >= C || nx <= airCondition[0] || nx >= R) {
                    break;
                }

                if(!(y == 0 && (x == airCondition[0] || x == airCondition[1]))) {
                    map[x][y] = map[nx][ny];
                }
                x = nx;
                y = ny;

            }
        }
        map[R-2][0] = temp;
    }
}
