package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj3190 {
    public static int[] x = {0, 1, 0, -1};
    public static int[] y = {1, 0, -1, 0}; // 동 남 서 북
    public static Map<Integer, String> cmdMap;
    public static boolean[][] apple;
    public static List<int[]> snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        apple = new boolean[N][N];
        int appleCnt = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < appleCnt; i++){
            st = new StringTokenizer(br.readLine());
            apple[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
        }

        int cmdCnt = Integer.parseInt(br.readLine());

        cmdMap = new HashMap<>(cmdCnt);
        for(int i = 0; i < cmdCnt; i++){
            st = new StringTokenizer(br.readLine());
            cmdMap.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        System.out.println(move());
    }

    public static int move(){
        snake = new ArrayList<>();

        int s = 0; // 현재 초
        int direction = 0; // 방향 인덱스
        int cx = 0; int cy = 0;
        snake.add(new int[]{0, 0});

        while(true){
            s++;

            int nx = cx + x[direction];
            int ny = cy + y[direction];

            if(isEnd(nx, ny)) return s;

            if(apple[nx][ny]){
                // 사과가 존재한다면
                apple[nx][ny] = false;
                snake.add(new int[]{nx, ny});
            } else {
                // 사과가 존재하지 않는다면
                snake.add(new int[]{nx, ny});
                snake.remove(0);
            }

            if(cmdMap.containsKey(s)){
                // 회전하기
                if(cmdMap.get(s).equals("D")){
                    direction++;
                    if(direction > 3) direction -= 4;
                } else if(cmdMap.get(s).equals("L")){
                    direction--;
                    if(direction < 0) direction = 3;
                }

            }
            // 현재 값 업데이트
            cx = nx;
            cy = ny;
        }

    }
    public static boolean isEnd(int nx, int ny){
        // 벽에 부딪히면
        if(nx < 0 || nx >= apple.length || ny < 0 || ny >= apple.length){
            return true;
        }

        // 자기 몸통에 부딪히면
        for(int i  = 0; i < snake.size(); i++){
            if(snake.get(i)[0] == nx && snake.get(i)[1] == ny){
                return true;
            }
        }

        return false;
    }
}
