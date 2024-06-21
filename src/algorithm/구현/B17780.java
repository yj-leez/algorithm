package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B17780 {
    static class Horse {
        int x;
        int y;

        int d;

        public Horse(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N]; // 0: 흰색, 1: 빨간색, 2: 파란색
        LinkedList<Integer>[][] state = new LinkedList[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                state[i][j] = new LinkedList<>();
            }
        }


        Horse[] horses = new Horse[K + 1];

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            horses[i] = new Horse(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1);
            state[horses[i].x][horses[i].y].add(i);
        }

        int sec = 0;
        boolean flag = true;
        while(flag){

            sec++;
            if (sec > 1000) break;
//            System.out.println(sec + "초");

            for (int i = 1; i <= K; i++) {
                Horse horse = horses[i];
                int x = horse.x;
                int y = horse.y;

                if(state[x][y].get(0) != i) continue;

                int nx = x + dx[horse.d];
                int ny = y + dy[horse.d];

                // 체스판을 벗어나거나 이동하려는 칸이 파란색인 경우 방향을 바꾸고 한 칸 이동
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2){
                    // 이동 방향을 반대로
                    if(horse.d % 2 == 0) horse.d++;
                    else horse.d--;

                    nx = x + dx[horse.d];
                    ny = y + dy[horse.d];
                }


                // 이동하려는 칸이 파란 색인 경우
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2){
                    if(horse.d % 2 == 0) horse.d++;
                    else horse.d--;
                    continue;
                }  else if(map[nx][ny] == 1){ // 이동하려는 칸이 빨강 색인 경우
                    for (int j = state[x][y].size() - 1; j >= 0; j--) {
                        int tmp = state[x][y].get(j);
                        state[nx][ny].add(tmp);
                        horses[tmp].x = nx;
                        horses[tmp].y = ny;
                    }
                    state[x][y].clear();
                } else if(map[nx][ny] == 0){ // 이동하려는 칸이 하얀 색인 경우
                    for (int j = 0; j < state[x][y].size(); j++) {
                        int tmp = state[x][y].get(j);
                        state[nx][ny].add(tmp);
                        horses[tmp].x = nx;
                        horses[tmp].y = ny;
                    }
                    state[x][y].clear();
                }

                if (state[nx][ny].size() >= 4){
                    flag = false;
                    break;
                }

            }

        }

        System.out.println(flag? -1: sec);
    }
}
