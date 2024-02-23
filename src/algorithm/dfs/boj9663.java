package algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj9663 {

    public static int[] arr; // 체스판에 놓은 퀸의 열에 대한 위치
    public static int N; // 체스판의 크기
    public static int cnt = 0; // 가능한 경우의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        nQueen(0);
        System.out.println(cnt);
    }

    public static void nQueen(int depth){
        // 제일 아래 열까지 간 경우
        if (N == depth) {
            cnt++;
            return;
        }

        // 아닌 경우 아래 노드 검사
        for (int i = 0; i < N; i++) {
            arr[depth] = i;

            if (available(depth)){
                nQueen(depth + 1);
            }
        }
    }

    public static boolean available(int depth){
        for (int i = 0; i < depth; i++) {
            // 이전의 행과 대각선, 세로가 겹치는 지 확인
            if(arr[i] == arr[depth] || Math.abs(depth - i) == Math.abs(arr[depth] - arr[i])) {
                return false;
            }
        }
        return true;
    }
}
