package algorithm.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 싸움땅 {

    static class Cell{
        private List<Integer> guns;

        public Cell(int gun){
            guns = new ArrayList<>();
            if(gun != 0) guns.add(gun);
        }
    }

    static class Human{
        private int x;
        private int y;
        private int d;
        private int s;
        private int gun;
        private int point;
        // x, y, d, s
        public Human(int x, int y, int d, int s){
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
            this.gun = 0;
            this.point = 0;
        }
    }
    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N; // 격자 크기
    static int M; // 플레이어 수
    static int K; // 라운드 수
    static Cell[][] board;
    static Human[] humans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new Cell[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = new Cell(Integer.parseInt(st.nextToken()));
            }
        }

        // x, y, d, s가 공백을 사이에 두고 주어집니다. (x, y)는 플레이어의 위치, d는 방향, s는 플레이어의 초기 능력치를 의미
        humans = new Human[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            humans[i] = new Human(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            // 한 칸씩 모두 이동
            for (int j = 0; j < humans.length; j++) {
                move(j);
            }
        }

        // 출력
        for (int i = 0; i < M; i++) {
            System.out.print(humans[i].point + " ");
        }
        System.out.println();


    }

    static void move(int idx){
        // 해당 방향으로 나갈 때 격자를 벗어나는 경우에는 정반대 방향으로 방향을 바꾸기
        Human human = humans[idx];
        int x = human.x + dx[human.d];
        int y = human.y + dy[human.d];

        if (x < 0 || x >= N || y < 0 || y >= N){
            switch (human.d){
                case 0:
                    human.d = 2;
                    break;
                case 1:
                    human.d = 3;
                    break;
                case 2:
                    human.d = 0;
                    break;
                case 3:
                    human.d = 1;
                    break;
            }
        }

        // 1칸 전진
        human.x += dx[human.d];
        human.y += dy[human.d];


        boolean isPlayer = false;
        int player = -1;
        for (int i = 0; i < M; i++) {
            if(human.x == humans[i].x && human.y == humans[i].y && idx != i){
                isPlayer = true;
                player = i;
            }
        }

        // 플레이어가 있는 경우에는 싸움
        if (isPlayer){
            fight(player, idx);
        } else {
            // 플레이어가 없다면 해당 칸에 총이 있는지 확인
            // 총이 있다면 해당 플레이어는 공격력이 가장 높은 총을 획득 -> 나머지 총들은 해당 격자에
            int max = human.gun;
            int maxIdx = -1;
            for (int i = 0; i < board[human.x][human.y].guns.size(); i++) {
                if (board[human.x][human.y].guns.get(i) > max){
                    maxIdx = i;
                    max = board[human.x][human.y].guns.get(i);
                }
            }

            // 해당 칸에 공격력이 가장 높은 총이 있다면
            if (maxIdx != -1){
                int tmp = human.gun;
                human.gun = board[human.x][human.y].guns.get(maxIdx);
                board[human.x][human.y].guns.remove(maxIdx);
                if(tmp != 0) board[human.x][human.y].guns.add(tmp);
            }
        }


    }

    static void fight(int a, int b){

        // 해당 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합을 비교하여 더 큰 플레이어가 이김
        // 수치가 같은 경우에는 플레이어의 초기 능력치가 높은 플레이어가 승리
        Human winner, loser;
        int loserIdx = -1;
        if (humans[a].s + humans[a].gun > humans[b].s + humans[b].gun ||
                ((humans[a].s + humans[a].gun == humans[b].s + humans[b].gun) && (humans[a].s > humans[b].s))){
            winner = humans[a];
            loser = humans[b];
            loserIdx = b;
        } else {
            winner = humans[b];
            loser = humans[a];
            loserIdx = a;
        }

        // 이긴 플레이어는 각 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합의 차이만큼을 포인트로 획득
        winner.point += ((winner.s + winner.gun) - (loser.s + loser.gun));

        // 진 플레이어는 본인이 가지고 있는 총을 해당 격자에 내려놓고, 해당 플레이어가 원래 가지고 있던 방향대로 한 칸 이동
        if (loser.gun != 0) {
            board[loser.x][loser.y].guns.add(loser.gun);
            loser.gun = 0;
        }

        // 만약 이동하려는 칸에 다른 플레이어가 있거나 격자 범위 밖인 경우에는 오른쪽으로 90도씩 회전하여 빈 칸이 보이는 순간 이동
        int x = loser.x + dx[loser.d];
        int y = loser.y + dy[loser.d];

        while(x < 0 || x >= N || y < 0 || y >= N || isHuman(x, y, loserIdx)){
            if (loser.d + 1 > 3) loser.d = 0;
            else loser.d++;

            x = loser.x + dx[loser.d];
            y = loser.y + dy[loser.d];
        }

        loser.x = x;
        loser.y = y;

        // 패배자가 이동한 곳에 총이 있다면
        if (board[loser.x][loser.y].guns.size() != 0){
            int max = board[loser.x][loser.y].guns.get(0);
            int maxIdx = 0;
            for (int i = 0; i < board[loser.x][loser.y].guns.size(); i++) {
                if (board[loser.x][loser.y].guns.get(i) > max){
                    maxIdx = i;
                    max = board[loser.x][loser.y].guns.get(i);
                }
            }

            // 공격력이 가장 높은 총 챙기기
            loser.gun = board[loser.x][loser.y].guns.get(maxIdx);
            board[loser.x][loser.y].guns.remove(maxIdx);
        }


        // 이긴 플레이어는 승리한 칸에 떨어져 있는 총들과 원래 들고 있던 총 중 가장 공격력이 높은 총을 획득하고, 나머지 총들은 해당 격자에 놓음
        int max = winner.gun;
        int maxIdx = -1;
        for (int i = 0; i < board[winner.x][winner.y].guns.size(); i++) {
            if (board[winner.x][winner.y].guns.get(i) > max){
                maxIdx = i;
                max = board[winner.x][winner.y].guns.get(i);
            }
        }

        // 해당 칸에 공격력이 가장 높은 총이 있다면
        if (maxIdx != -1){
            int tmp = winner.gun;
            winner.gun = board[winner.x][winner.y].guns.get(maxIdx);
            board[winner.x][winner.y].guns.remove(maxIdx);
            if(tmp != 0) board[winner.x][winner.y].guns.add(tmp);
        }

    }

    static boolean isHuman(int x, int y, int idx){
        for (int i = 0; i < M; i++) {
            if (x == humans[i].x && y == humans[i].y && idx != i){
                return true;
            }
        }
        return false;
    }
}
