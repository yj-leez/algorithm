package algorithm.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B2015 {
    /**
     * i번째의 부분합을 계산한 후에 1 ~ i-1까지의 부분합을 빼서 K랑 같은지 검사 -> 시간 초과
     * i번째의 부분합을 계산한 후에 i번째의 부분합-K의 값을 가지고 있는 그 전의 부분합이 있는지 HashMap으로 확인 -> 문제 해결
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long[] sum = new long[N]; // 누적합
        Map<Long, Integer> set = new HashMap<>();
        long ans = 0;

        st = new StringTokenizer(br.readLine());
        sum[0] = Long.parseLong(st.nextToken());

        set.put(0L, 1);

        ans += set.getOrDefault(sum[0] - K, 0); // 이 부분 빼먹어서 계속 실패떴었음
        set.put(sum[0], set.getOrDefault(sum[0], 0) + 1);

        for(int i = 1; i< N; i++){
            sum[i] = sum[i - 1] + Long.parseLong(st.nextToken());
            ans += set.getOrDefault(sum[i] - K, 0);

            set.put(sum[i], set.getOrDefault(sum[i], 0) + 1);
        }

        System.out.println(ans);
    }
}
