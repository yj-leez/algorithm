package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj2579 {

    public static int[] stair;
    public static int[] answer;
    public static int N;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stair = new int[N + 1];
        answer = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        } // 1 ~ N까지 값 저장

        answer[0] = 0;
        answer[1] = stair[1];

        if (N >= 2) answer[2] = stair[2] + answer[1];
        if (N >= 3) dp();

        System.out.println(answer[N]);

    }

    public static void dp(){
        for (int i = 3; i <= N; i++) {
            answer[i] = Math.max(answer[i - 3] + stair[i - 1], answer[i - 2]) + stair[i];
        }
    }
}
