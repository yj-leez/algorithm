package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15686 {
    static int M;
    static List<int[]> houses;
    static List<int[]> chickens;
    static boolean[] pickedChickens;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시 크기
        M = Integer.parseInt(st.nextToken()); // 남아 있는 치킨 집 개수

        int[][] map = new int[N][N];
        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1) houses.add(new int[]{i, j});
                else if(map[i][j] == 2) chickens.add(new int[]{i, j});
            }
        }

        pickedChickens = new boolean[chickens.size()];
        result = Integer.MAX_VALUE;
        backtracking(0, 0);
        System.out.println(result);

    }

    static void backtracking(int cnt, int idx){
        if(cnt == M){
            int total = 0;
            for(int[] house: houses){
                int min = Integer.MAX_VALUE;

                for (int i = 0; i < chickens.size(); i++) {
                    if(pickedChickens[i]){
                        min = Math.min(min, Math.abs(house[0] - chickens.get(i)[0]) + Math.abs(house[1] - chickens.get(i)[1]));
                    }
                }
                total += min;
            }
            result = Math.min(result, total);
            return;
        }


        // 남아있을 치킨집을 선택
        for (int i = idx; i < chickens.size(); i++) {
            if(pickedChickens[i] == false) {
                pickedChickens[i] = true;
                backtracking(cnt + 1, i + 1);
                pickedChickens[i] = false;
            }
        }
    }
}
