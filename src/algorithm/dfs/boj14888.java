package algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj14888 {

    public static int[] operators = new int[4];
    public static int[] numbers;
    public static int MAX = Integer.MIN_VALUE;	// 최댓값
    public static int MIN = Integer.MAX_VALUE;  // 최솟값
    public static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 높이


        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }



        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        dfs(numbers[0], 1);

        System.out.println(MAX);
        System.out.println(MIN);


    }
    public static void dfs(int num, int idx){
        if(N == idx){
            MAX = Math.max(num, MAX);
            MIN = Math.min(num, MIN);
            return;
        }

        /**
         * 덧셈, 뺄셈, 곱셈, 나눗셈으로 각각 계산한 자식 노드(총 4개) 생성
         */
        for (int i = 0; i < 4; i++) {

            // 만약 해당 부호가 아직 존재한다면
            if (operators[i] > 0) {
                operators[i]--;
                switch (i){
                    case 0: dfs(num + numbers[idx], idx + 1); break;
                    case 1: dfs(num - numbers[idx], idx + 1); break;
                    case 2: dfs(num * numbers[idx], idx + 1); break;
                    case 3: dfs(num / numbers[idx], idx + 1); break;
                }

                operators[i]++; // 다시 회복
            }
        }
    }
}
