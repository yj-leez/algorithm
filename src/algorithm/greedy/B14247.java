package algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B14247 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long ans = 0;
        for(int i = 0; i < n; i++){
            ans += Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] increase = new int[n];
        for(int i = 0; i < n; i++){
            increase[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(increase);
        for(int i = 0; i < n; i++){
            ans += increase[i] * i;
        }

        System.out.println(ans);

    }
}
