package algorithm.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 왕실의기사대결 {
    static class Knight{
        private int[][] scope;
        private int k;
        private int demage;

        public Knight(int r, int c, int h, int w, int k){
            scope = new int[h * w][2];
            for (int i = 0; i < h * w; i++) {
                scope[i][0] = (r - 1) + (i / w);
                scope[i][1] = (c - 1) + (i % w);
            }
            this.k = k;
        }

    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int L;
    static int N;
    static int Q;
    static int[][] board;
    static Knight[] knights;
    static int[][] knightInBoard;

    static boolean[] visited;

    static void move(int knightIdx, int d, boolean attacked){
        Knight knight = knights[knightIdx];
        int attackCnt = 0;

        for (int i = 0; i < knight.scope.length; i++) {
            int nx = knight.scope[i][0] + dx[d];
            int ny = knight.scope[i][1] + dy[d];

            // 만약 옮기려는 자리에 자기가 아닌 기사가 있다면 먼저 옮기고 오기
            if(knightInBoard[nx][ny] > 0 && knightInBoard[nx][ny] != knightIdx){
                move(knightInBoard[nx][ny], d, true);
            }

            // 보드 내 기사의 위치 옮기기
            knightInBoard[knight.scope[i][0]][knight.scope[i][1]] = 0;


            // 기사의 위치 옮기기
            knight.scope[i][0] = nx;
            knight.scope[i][1] = ny;


            // 자신의 바운더리 안에 함정 수 카운트
            if(board[nx][ny] == 1) {
                attackCnt++;
            }
        }

        /**
         * 틀렸던 점 :
         * 공간의 일부가 겹치는 상태로 이동하게 되는데
         * 한 루프안에서 이전 공간 = 0, 새로운 공간 = 기사 인덱스로 하게 되면
         * 바로 아래 공간을 똑같이 돌면서 새로운 공간 = 0이 되어버린다.
         */
        for (int i = 0; i < knight.scope.length; i++) {
            knightInBoard[knight.scope[i][0]][knight.scope[i][1]] = knightIdx;
        }


        if (attacked){
            knight.demage += attackCnt;
            if(knight.k <= knight.demage) died(knightIdx);
        }

    }

    static void died(int idx){
        for (int i = 0; i < knights[idx].scope.length; i++) {
            knightInBoard[knights[idx].scope[i][0]][knights[idx].scope[i][1]] = 0;
        }
    }

    static boolean checkForMove(int knightIdx, int d){
        Knight knight = knights[knightIdx];
        visited[knightIdx] = true;

        for (int i = 0; i < knight.scope.length; i++) {
            int nx = knight.scope[i][0] + dx[d];
            int ny = knight.scope[i][1] + dy[d];

            if (nx < 0 || ny < 0 || nx >= L || ny >= L){
                return false;
            }

            if (board[nx][ny] == 2){
                return false;
            }

            if(knightInBoard[nx][ny] > 0 && !visited[knightInBoard[nx][ny]] && knightInBoard[nx][ny] != knightIdx){
                if(!checkForMove(knightInBoard[nx][ny], d)){
                    return false;
                }
            }

        }

        return true;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        board = new int[L][L];
        knights = new Knight[N + 1];
        knightInBoard = new int[L][L];

        // 체스판 세팅
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 기사 위치 세팅
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            knights[i] = new Knight(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }


        for (int i = 1; i <= N; i++) {
            Knight knight = knights[i];
            for (int j = 0; j < knight.scope.length; j++) {
                knightInBoard[knight.scope[j][0]][knight.scope[j][1]] = i;
            }
        }



        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int knightIdx = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            visited = new boolean[N + 1];
            if (checkForMove(knightIdx, d) && knights[knightIdx].k > knights[knightIdx].demage){
                move(knightIdx, d, false);
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (knights[i].k > knights[i].demage){
                answer += knights[i].demage;
            }
        }
        System.out.println(answer);

    }
}
