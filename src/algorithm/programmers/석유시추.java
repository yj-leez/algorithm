package algorithm.programmers;

import java.util.*;

public class 석유시추 {
//    public int solution(int[][] land) {
//        private int[] dx = new int[]{0,1,0,-1};
//        private int[] dy = new int[]{-1,0,1,0};
//        private Map<Integer,Integer> answerMap =new HashMap<>();
//
//        public int solution(int[][] land) {
//            int color = 2;
//            for(int i=0;i<land.length;i++){
//                for(int j=0;j<land[i].length;j++){
//                    if(land[i][j] == 1){
//                        Set<Integer> ys = new HashSet<>();
//                        bfs(land,new int[]{i,j},ys,color);
//                    }
//                }
//            }
//            return answerMap.values()
//                    .stream()
//                    .mapToInt(i->i)
//                    .max()
//                    .getAsInt();
//        }
//        private static void bfs(int[][] map, int[] now, Set<Integer> ys, int color){
//            int size = 1;
//            Queue<int[]> queue = new LinkedList<>();
//            queue.add(now);
//            ys.add(now[1]);
//            map[now[0]][now[1]] = color;
//            while(!queue.isEmpty()){
//                int[] next = queue.poll();
//                int x = next[0];
//                int y = next[1];
//                for(int i=0;i<4;i++){
//                    int nx = x+dx[i];
//                    int ny = y+dy[i];
//                    if(nx>=0 && nx < map.length && ny>=0 && ny< map[0].length && map[nx][ny]==1){
//                        map[nx][ny] = color;
//                        queue.add(new int[]{nx,ny});
//                        ys.add(ny);
//                        size++;
//                    }
//                }
//            }
//            for(int y : ys){
//                answerMap.put(y,answerMap.getOrDefault(y,0) + size);
//            }
//        }
//    }
}
