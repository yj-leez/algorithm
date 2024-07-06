package algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B11501 {
    /**
     * 오늘 산 주식을 오늘 이후 가장 비싼 날에 팔기로 생각하였다. 하지만 오늘 '이후' 가장 비싼 날을 우선순위큐를 이용해 꺼내려했더니 메모리 초과가 떴다.
     * 그래서 뒤에서부터 계산하여 오늘 이후 가장 비싼 날을 저장하고 있는 방식으로 해결하였다.
     * 이익은 최대 1,000,000×10,000=10,000,000,000이므로 int의 범위를 넘는다. 체크하자.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++){

            int N = Integer.parseInt(br.readLine());
            int[] nums = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                nums[j] = Integer.parseInt(st.nextToken());
            }

            long sum = 0;
            int max = nums[N - 1];
            for(int j = N - 2; j >= 0; j--){
                if (max > nums[j]) sum += (max - nums[j]);
                else max = nums[j];
            }


            sb.append(sum);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
