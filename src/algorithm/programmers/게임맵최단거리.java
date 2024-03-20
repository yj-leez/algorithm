package algorithm.programmers;

import java.util.*;

class 게임맵최단거리 {


    public int solution(int[][] maps) {
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        int[] x = {-1, 0, 1, 0};
        int[] y = {0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        while(!queue.isEmpty()){
            // 꺼내서 방문
            int nowX = queue.peek()[0];
            int nowY = queue.peek()[1];
            int cnt = queue.peek()[2];
            // visited[nowX][nowY] = true;

            if(nowX == maps.length -1 && nowY == maps[0].length-1){
                return cnt;
            }

            queue.poll();

            for(int i = 0; i < 4; i++){
                int newX = nowX + x[i];
                int newY = nowY + y[i];
                if(newX >= 0 && newX < maps.length && newY >= 0 && newY < maps[0].length && !visited[newX][newY]){
                    if(maps[newX][newY] == 1){
                        visited[newX][newY] = true;
                        /**
                         * visited 표시를 queue에서 뺄 때 하는 것이 아니라 queue에 넣을 때하면 효율성을 높일 수 있다.
                         */
                        queue.add(new int[]{newX, newY, cnt + 1});
                    }
                }
            }
        }

        return -1;
    }

}
