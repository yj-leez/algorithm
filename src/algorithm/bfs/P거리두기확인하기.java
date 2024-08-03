package algorithm.bfs;

import java.util.*;

public class P거리두기확인하기 {
    /**
     * bfs로 거리가 2이하인 곳에 사람이 있는지 확인
     * X(가림판)이 있다면 더 이상 확인하지 않아도 된다. -> 그 다음에 사람이 있어도 괜찮기 때문
     */

    static int[] dx = {-1, 0 , 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static String[][] room;
    static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        int idx = 0;
        for(String[] place : places){
            // String[][] 배열로 변환
            room = new String[5][5];
            for(int i = 0; i < 5; i++){
                room[i] = place[i].split("");
            }

            // P을 찾으면 가능한지 판별
            outerLoop:
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(room[i][j].equals("P")){
                        if(isPossible(i, j) == 0){
                            answer[idx] = 0;
                            break outerLoop;
                        }
                    }
                }
            }
            idx++;
        }

        return answer;
    }

    static int isPossible(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];

        q.add(new int[]{x, y, 0});
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                int cnt = now[2] + 1;

                if(nx < 0|| nx >= 5|| ny < 0 || ny >= 5 || visited[nx][ny]) continue;
                if(room[nx][ny].equals("P") && cnt <= 2){
                    return 0; // 불가능
                } else if(room[nx][ny].equals("O") && cnt <= 2){
                    q.add(new int[]{nx, ny, cnt});
                    visited[nx][ny] = true;
                }

            }
        }

        return 1;
    }


}
