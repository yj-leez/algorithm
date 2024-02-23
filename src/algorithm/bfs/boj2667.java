package algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj2667 {

    public static boolean[][] checked;
    public static int [][] house;
    public static int houseNum;
    public static int[] houseNums;
    public static int complexNum = 0;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        house = new int[N][N]; checked = new boolean[N][N];
        houseNums = new int[N * N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!checked[i][j] && house[i][j] == 1){
                    houseNum = 0;
                    bfs(i, j);
                    houseNums[complexNum] = houseNum;
                    complexNum++;
                }
            }
        }

        // 정렬
        int[] resultArray = Arrays.stream(houseNums)
                .filter(value -> value != 0)
                .sorted()
                .toArray();

        System.out.println(complexNum);
        for (int i = 0; i < complexNum; i++) {
            System.out.println(resultArray[i]);
        }


    }
    public static void bfs(int i, int j){
        checked[i][j] = true;
        houseNum++;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});

        while (!queue.isEmpty()){
            int curX = queue.peek()[0];
            int curY = queue.peek()[1];
            queue.poll();

            // 아래쪽
            if(curX + 1 < N && house[curX + 1][curY] == 1 && !checked[curX + 1][curY]){
                checked[curX + 1][curY] = true;
                queue.add(new int[]{curX + 1, curY});
                houseNum++;
            }
            // 위쪽
            if(curX - 1 >= 0 && house[curX - 1][curY] == 1 && !checked[curX - 1][curY]){
                checked[curX - 1][curY] = true;
                queue.add(new int[]{curX - 1, curY});
                houseNum++;
            }
            // 왼쪽
            if(curY - 1 >= 0 && house[curX][curY - 1] == 1 && !checked[curX][curY - 1]){
                checked[curX][curY - 1] = true;
                queue.add(new int[]{curX, curY - 1});
                houseNum++;
            }
            // 오른쪽
            if(curY + 1 < N && house[curX][curY + 1] == 1 && !checked[curX][curY + 1]){
                checked[curX][curY + 1] = true;
                queue.add(new int[]{curX, curY + 1});
                houseNum++;
            }

        }

    }


}
