package algorithm.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 코드트리빵 {
    /**
     * 변수 오류가 너무 많았다 -> 이름을 좀 더 명확히
     * bfs 시 경로 저장하는 방법 다시 한 번 더 복기
     * 최단 길이가 같다면 저장 후 행열 비교 또 했어야 했다
     */

    static class Human{
        private int x;
        private int y;
        private int goalX;
        private int goalY;
        private boolean arriving;

        public Human(int goalX, int goalY){
            this.goalX = goalX;
            this.goalY = goalY;
            arriving = false;
        }

        public boolean checkArriving(){
            if(x == goalX && y == goalY) {
                arriving = true;
                return true;
            }

            return false;
        }
    }

    static int N;
    static int M;
    static int[][] board;
    static int[][] baseCampMap;
    static List<int[]> basecamp;
    static Human[] humans;
    // 상 좌 우 하
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static boolean[][] visited;
    static int[][][] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N]; // 맨 처음에 다 0으로 초기화
        basecamp = new ArrayList<>();
        humans = new Human[M];
        baseCampMap = new int[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if(Integer.parseInt(st.nextToken()) == 1){
                    basecamp.add(new int[]{i, j});
                    baseCampMap[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            humans[i] = new Human(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }



        int answer = 0;
        int arrivingCnt = 0;
        while(true){
            // 만약 사람이 다 도착한다면 break
            if(arrivingCnt == M) break;

            // 아니라면 초 증가
            answer++;

            // 격자 내에 있는 사람들 편의점으로 한 칸씩 이동
            int max = answer-1 < M? answer - 1: M;

//            System.out.println("max 값: "+ max);
            for (int i = 0; i < max; i++) {
                if (humans[i].arriving) continue;
                visited = new boolean[N][N];
                parents = new int[N][N][2];
                move(i);
            }

            // 만약 편의점에 도착한다면 도착 처리 -> 보드에 표시
            for (int i = 0; i < max; i++) {
                if (humans[i].arriving) continue;

                if (humans[i].checkArriving()){
                    board[humans[i].goalX][humans[i].goalY] = 1;
                    arrivingCnt++;
                }
            }


            // 시각 t이고 t ≤ M이라면
            // t번 사람은 자신이 가고 싶은 편의점과 가장 가까이 있는 베이스 캠프에 들어감
            // 앞으로 이 베이스 캠프 또한 지나가지 못함 -> 보드에 표시
            if(max < M){
                visited = new boolean[N][N];
                findBaseCamp(answer-1);
            }

        }
        System.out.println(answer);

    }



    // bfs
    static void move(int idx){
        // 최단거리가 되는 방향 찾고 이동하기
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{humans[idx].x, humans[idx].y});
        visited[humans[idx].x][humans[idx].y] = true;

        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
//            System.out.println("bfs: " + x + " " + y);

            if (x == humans[idx].goalX && y == humans[idx].goalY) break;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 1 || visited[nx][ny]) continue;

                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                parents[nx][ny][0] = x;
                parents[nx][ny][1] = y;
            }
        }

        int x = humans[idx].goalX;
        int y = humans[idx].goalY;

        int nx = parents[x][y][0];
        int ny = parents[x][y][1];

        while(true){
            if(nx == humans[idx].x && ny == humans[idx].y){
                humans[idx].x = x;
                humans[idx].y = y;
                break;
            }

            x = nx; // 이전 값 저장
            y = ny;

            nx = parents[x][y][0]; // 이전 값의 부모 찾기
            ny = parents[x][y][1];
        }

    }

    static void findBaseCamp(int idx){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{humans[idx].goalX, humans[idx].goalY, 0});
        visited[humans[idx].goalX][humans[idx].goalY] = true;
        int x = humans[idx].goalX;
        int y = humans[idx].goalY;
        int cnt = 0;

        List<int[]> tmpAnswer = new ArrayList<>(); // bfs로 최단거리를 찾되 길이가 같은 위치는 저장한 뒤 행, 열 비교
        boolean getAnswer = false;
        int nowIdx = 0;
        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            x = tmp[0];
            y = tmp[1];
            cnt = tmp[2];

            if (nowIdx != cnt && getAnswer){
                break;
            }
            nowIdx = cnt;

            if(board[x][y] == 0 && baseCampMap[x][y] == 1){
                tmpAnswer.add(new int[]{x, y});
                getAnswer = true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 1 || visited[nx][ny]) continue;

                queue.add(new int[]{nx, ny, cnt + 1});
                visited[nx][ny] = true;
            }
        }



        // 최단 길이인 베이스 캠프가 하나라면
        if (tmpAnswer.size() == 1){
            // 보드판에 움직이지 못하는 곳이라 표시
            board[tmpAnswer.get(0)[0]][tmpAnswer.get(0)[1]] = 1;
            // 사람의 시작지점 베이스캠프로 표시
            humans[idx].x = tmpAnswer.get(0)[0];
            humans[idx].y = tmpAnswer.get(0)[1];
        } else { // 최단 길이인 베이스 캠프가 여러개라면
            x = tmpAnswer.get(0)[0];
            y = tmpAnswer.get(0)[1];
            for (int i = 1; i < tmpAnswer.size(); i++) {
                if (tmpAnswer.get(i)[0] < x){
                    x = tmpAnswer.get(i)[0];
                    y = tmpAnswer.get(i)[1];
                } else if(tmpAnswer.get(i)[0] == x && tmpAnswer.get(i)[1] < y){
                    x = tmpAnswer.get(i)[0];
                    y = tmpAnswer.get(i)[1];
                }
            }
            // 보드판에 움직이지 못하는 곳이라 표시
            board[x][y] = 1;
            // 사람의 시작지점 베이스캠프로 표시
            humans[idx].x = x;
            humans[idx].y = y;
        }

    }
}
