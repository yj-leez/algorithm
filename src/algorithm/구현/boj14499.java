package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 맨 처음에는 주사위가 굴러가는 방향을 배열로 만들어서 방향이 바뀔 때마다 tracking하려했는데
 * 동서남북 네 방향밖에 없으므로 방향에 따라 주사위 그 자체의 배열 위치를 바꿔주는 것이 효율적임
 */
public class boj14499 {
    public static int[][] map;
    // 윗면, 바닥면, 앞면, 뒷면, 왼쪽면, 오른쪽면
    public static int[] dice = {0, 0, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int commend = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 동, 서, 북, 남
        int[] idxX = {0, 0, -1, 1};
        int[] idxY = {1, -1, 0, 0};

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < commend; i++) {
            int now = Integer.parseInt(s[i]);
            if(0 <= x+idxX[now-1] && x+idxX[now-1] < N && 0 <= y+idxY[now-1] && y+idxY[now-1] < M){
                // 주사위 이동
                switch(now){
                    case 1: // 동쪽
                        moveRight();
                        break;
                    case 2: // 서쪽
                        moveLeft();
                        break;
                    case 3: // 북쪽
                        moveUp();
                        break;
                    case 4: // 남쪽
                        moveDown();
                        break;
                }

                // 말 이동
                x = x + idxX[now-1];
                y = y + idxY[now-1];

                // 칸이 0이라면 주사위 바닥의 수 복사 아니라면 칸의 숫자 주사위 바닥에 복사
                if(map[x][y] == 0){
                    map[x][y] = dice[1];
                } else {
                    dice[1] = map[x][y];
                    map[x][y] = 0;
                }

                // 주사위 윗면 출력
                System.out.println(dice[0]);
            }
        }


    }

    public static void moveRight(){
        int tmp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[1];
        dice[1] = dice[5];
        dice[5] = tmp;
    }

    public static void moveLeft(){
        int tmp = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[1];
        dice[1] = dice[4];
        dice[4] = tmp;
    }

    public static void moveUp(){
        int tmp = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[1];
        dice[1] = dice[3];
        dice[3] = tmp;
    }

    public static void moveDown(){
        int tmp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[1];
        dice[1] = dice[2];
        dice[2] = tmp;
    }
}
