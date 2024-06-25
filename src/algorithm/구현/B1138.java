package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1138 {
    /**
     * 키가 작은 순서대로 자기 자리를 찾아간다.
     * 만약 왼쪽에 자기보다 큰 사람이 n명이라면 왼쪽에서 n번째 자리에 선다.
     * 하지만 그 자리에 이미 누가 있다면 한 칸 오른쪽으로 이동하여 선다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N]; // 1 ~ N까지 있음
        for (int i = 1; i <= N; i++) {
            int tallerCnt = Integer.parseInt(st.nextToken()); // 왼쪽에 있는 큰 사람 수
            int idx = 0;

            while (tallerCnt > 0){
                while(arr[idx] != 0){
                    idx++;
                }

                idx++;
                tallerCnt--;
            }

            if (tallerCnt == 0){
                while(arr[idx] != 0){
                    idx++;
                }
                arr[idx] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i] + " ");
        }

        System.out.println(sb);

    }
}
