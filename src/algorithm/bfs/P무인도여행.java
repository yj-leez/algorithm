package algorithm.bfs;

import java.util.*;

public class P무인도여행 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int[] solution(String[] maps) {
        map = new int[maps.length][maps[0].length()];
        for(int i = 0; i < maps.length; i++){
            String[] str = maps[i].split("");
            for(int j = 0; j < maps[0].length(); j++){
                if(str[j].equals("X")) map[i][j] = 0;
                else map[i][j] = Integer.parseInt(str[j]);
            }
        }

        visited = new boolean[map.length][map[0].length];
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(!visited[i][j] && map[i][j] > 0){
                    int n = bfs(i, j);
                    if(n > 0) list.add(n);
                }
            }
        }
        Collections.sort(list);
        if(list.size() == 0) return new int[]{-1};

        int[] ans = list.stream().mapToInt(Integer::intValue).toArray();
        return ans;
    }

    static int bfs(int ix, int iy){
        int sum = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{ix, iy});
        visited[ix][iy] = true;

        while(!q.isEmpty()){
            int x = q.peek()[0];
            int y = q.poll()[1];

            sum += map[x][y];

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 <= nx && nx < map.length && 0 <= ny && ny < map[0].length && !visited[nx][ny] && map[nx][ny] > 0){
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return sum;
    }
}
