package algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj17281 {
    public static int [][] house;
    public static int N;
    public static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        house = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        dfs(0, 1, 0);

        System.out.println(answer);

    }

    // 0: 가로, 1: 세로, 2: 대각선
    public static void dfs(int x, int y, int direction){
        if(x == N-1 && y == N-1){
            answer++;
            return;
        }

        switch (direction){
            case 0: // 가로인 경우
                if(y+1 < N && house[x][y+1] == 0){ // 가로로 한 칸 이동
                    dfs(x, y+1, 0);
                }
                break;
            case 1: // 세로인 경우
                if(x+1 < N && house[x+1][y] == 0){
                    dfs(x+1, y, 1);
                }
                break;
            case 2:
                if(y+1 < N && house[x][y+1] == 0){ // 가로로 한 칸 이동
                    dfs(x, y+1, 0);
                }
                if(x+1 < N && house[x+1][y] == 0){ // 세로로 한 칸 이동
                    dfs(x+1, y, 1);
                }
                break;

        }

        // 대각선은 항상 검사하여 이동
        if(x+1 < N && y+1 < N && house[x+1][y] == 0 && house[x][y+1] == 0 && house[x+1][y+1] == 0){
            dfs(x+1, y+1, 2);
        }

        return;

    }


}