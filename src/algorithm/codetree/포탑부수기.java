package algorithm.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 포탄 공격 시 공격자 제외 누락했었음
 * 2. 공격 횟수가 끝나지 않았는데 포탑이 한 개 남은 경우 고려하지않았었음
 * 3. bfs시 경로 저장 시간 오래 걸림
 */
public class 포탑부수기 {

    static class Tower{
        private int power;
        private int attackingTurn;

        public Tower(int power){
            this.power = power;
            attackingTurn = 0;
        }

    }

    static int N;
    static int M;
    static int K; // 턴 횟수
    static Tower[][] towers;
    // 우, 하, 좌, 상, 대각선
    static int[] dx = {0, 1, 0, -1, -1, 1, 1, -1};
    static int[] dy = {1, 0, -1, 0, 1, 1, -1, -1};
    static int[][][] parent;
    static boolean[][] attacked;
    static List<int[]> live;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        towers = new Tower[N][M];
        live = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                towers[i][j] = new Tower(Integer.parseInt(st.nextToken()));
                live.add(new int[]{i, j});
            }
        }

        for (int i = 0; i < K; i++) {
            int[] weakTower = chooseWeakTower();
            int[] strongTower = chooseStrongTower();
//            System.out.println("약한 타워 " + weakTower[0] + " "+ weakTower[1]);
//            System.out.println("강한 타워 " + strongTower[0] + " "+ strongTower[1]);

            towers[weakTower[0]][weakTower[1]].attackingTurn = i + 1; // 공격한 타워의 공격 턴 넘버 세팅
            towers[weakTower[0]][weakTower[1]].power += (N + M);

            if(checkForLaser(weakTower, strongTower)){
                // 레이저 공격
//                System.out.println("레이저");
                attackWithLaser(weakTower, strongTower);
            } else {
                // 포탄 공격
//                System.out.println("포ㄷ");
                attackWithBomb(weakTower, strongTower);
            }

            // 포탑 정비
            // 부서지지 않은 포탑 중 공격과 무관했던 포탑은 공격력이 1씩 증가
            int liveTower = 0;
            for (int j = 0; j < live.size(); j++) {
                int nx = live.get(j)[0];
                int ny = live.get(j)[1];
                if(towers[nx][ny].power > 0 && !attacked[nx][ny]){
                    towers[nx][ny].power++;
                }
                if(towers[nx][ny].power > 0) liveTower++;
            }


            //확인
//            for (int j = 0; j < N; j++) {
//                for (int k = 0; k < M; k++) {
//                    System.out.print(towers[j][k].power + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("================");

            if (liveTower == 1) break;


        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < live.size(); i++) {
            int nx = live.get(i)[0];
            int ny = live.get(i)[1];

            if(towers[nx][ny].power > max){
                max = towers[nx][ny].power;
            }
        }

        System.out.println(max);
    }

    static int[] chooseWeakTower() {
        int min = Integer.MAX_VALUE;
        int x = 0, y = 0;

        for (int k = 0; k < live.size(); k++) {
            int i = live.get(k)[0];
            int j = live.get(k)[1];

            if(towers[i][j].power <= 0) continue;

            if(towers[i][j].power < min){
                min = towers[i][j].power;
                x = i; y = j;
            } else if (towers[i][j].power == min && towers[i][j].attackingTurn > towers[x][y].attackingTurn){
                min = towers[i][j].power;
                x = i; y = j;
            } else if(towers[i][j].power == min && towers[i][j].attackingTurn == towers[x][y].attackingTurn
                    && (i+j) > (x+y)){
                min = towers[i][j].power;
                x = i; y = j;
            } else if (towers[i][j].power == min && towers[i][j].attackingTurn == towers[x][y].attackingTurn
                    && (i+j) == (x+y) && j > y){
                min = towers[i][j].power;
                x = i; y = j;
            }
        }

        return new int[]{x, y};
    }

    static int[] chooseStrongTower() {
        int max = Integer.MIN_VALUE;
        int x = 0, y = 0;

        for (int k = 0; k < live.size(); k++) {
            int i = live.get(k)[0];
            int j = live.get(k)[1];

            if(towers[i][j].power > max){
                max = towers[i][j].power;
                x = i; y = j;
            } else if (towers[i][j].power == max && towers[i][j].attackingTurn < towers[x][y].attackingTurn){
                max = towers[i][j].power;
                x = i; y = j;
            } else if(towers[i][j].power == max && towers[i][j].attackingTurn == towers[x][y].attackingTurn
                    && (i+j) < (x+y)){
                max = towers[i][j].power;
                x = i; y = j;
            } else if (towers[i][j].power == max && towers[i][j].attackingTurn == towers[x][y].attackingTurn
                    && (i+j) == (x+y) && j < y){
                max = towers[i][j].power;
                x = i; y = j;
            }
        }

        return new int[]{x, y};
    }



    // bfs로 탐색
    static boolean checkForLaser(int[] start, int[] end){

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        parent = new int[N][M][2]; // 부모 셀을 추적하기 위한 배열

        queue.add(start);
        visited[start[0]][start[1]] = true;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            if(x == end[0] && y == end[1]){ return true; }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0) nx += N;
                else if(nx >= N) nx -= N;

                if(ny < 0) ny += M;
                else if(ny >= M) ny -= M;

                if(towers[nx][ny].power > 0 && !visited[nx][ny]){
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    // 부모 셀 정보 저장
                    parent[nx][ny] = new int[]{x, y};
                }
            }
        }

        return false;
    }

    static void attackWithLaser(int[] start, int[] end) {
        attacked = new boolean[N][M];
        attacked[start[0]][start[1]] = true;

        // 공격 당한 포탑의 공격력 감소
        int x, y;
        int nx = end[0];
        int ny = end[1];
        towers[nx][ny].power -= towers[start[0]][start[1]].power;
        attacked[nx][ny] = true;


        // 경로에 존재하는 포탑의 공격력 감소
        while(true){
            x = nx; // 이전 포탑
            y = ny;

            nx = parent[x][y][0]; // 이전 포탑의 부모 찾기
            ny = parent[x][y][1];
//            System.out.println(x + " " + y + " 의 부모는 " + nx + " " + ny);

            if (nx == start[0] && ny == start[1]) break;

            attacked[nx][ny] = true;
            towers[nx][ny].power -= (towers[start[0]][start[1]].power/2);
        }
    }

    static void attackWithBomb(int[] start, int[] end) {
        attacked = new boolean[N][M];
        attacked[start[0]][start[1]] = true;

        // 공격 당한 포탑의 공격력 감소
        int nx = end[0];
        int ny = end[1];
        towers[nx][ny].power -= towers[start[0]][start[1]].power;
        attacked[nx][ny] = true;


        // 그 주위의 포탑 공격력 감소
        for (int i = 0; i < 8; i++) {
            nx = end[0] + dx[i];
            ny = end[1] + dy[i];

            if(nx < 0) nx += N;
            else if(nx >= N) nx -= N;

            if(ny < 0) ny += M;
            else if(ny >= M) ny -= M;

            if (towers[nx][ny].power <= 0 || (nx == start[0] && ny == start[1])) continue;

            attacked[nx][ny] = true;
            towers[nx][ny].power -= towers[start[0]][start[1]].power/2;
        }
    }


}
