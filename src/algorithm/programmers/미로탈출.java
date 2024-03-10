package algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 미로탈출 {

    public static void main(String[] args) {
        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        System.out.println(solution(maps));
    }
    public static int[] idxX = {-1, 0, 1, 0};
    public static int[] idxY = {0, 1, 0, -1};
    public static String[][] maze;

    public static int solution(String[] maps) {

        maze = new String[maps.length][maps[0].length()];
        int[] start = new int[2];
        int[] lever = new int[2];

        for (int i = 0; i < maps.length; i++) {
            String[] tmp = maps[i].split("");

            for (int j = 0; j < tmp.length; j++) {
                maze[i][j] = tmp[j];

                if(maze[i][j].equals("S")){
                    start[0] = i;
                    start[1] = j;
                }

                if(maze[i][j].equals("L")){
                    lever[0] = i;
                    lever[1] = j;
                }
            }
        }

        // bfs
        int result = bfs(start, "L");
        if(result == -1) return -1;

        int result2 = bfs(lever, "E");
        if(result2 == -1) return -1;

        return result + result2;
    }

    public static int bfs(int[] start, String target){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 0});

        boolean[][] checked = new boolean[maze.length][maze[0].length];
        checked[start[0]][start[1]] = true;

        while (!queue.isEmpty()){
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            int count = queue.peek()[2];
            checked[x][y] = true;

            // 주소 값 비교가 아닌 데이터 비교하기 위해 equals 사용
            if (maze[x][y].equals(target)){
                return count;
            }

            queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + idxX[i];
                int ny = y + idxY[i];

                if(nx>=0 && nx<maze.length && ny>=0 && ny< maze[0].length && !checked[nx][ny]){
                    if(!maze[nx][ny].equals("X")){
                        checked[nx][ny] = true;
                        queue.add(new int[]{nx, ny, count + 1});
                    }

                }

            }

        }

        return -1;
    }
    /**
     * 주의: String 값 비교할 때는 주소 비교(==)가 아닌 값 비교를 위해 .equals() 사용하자
     */
}