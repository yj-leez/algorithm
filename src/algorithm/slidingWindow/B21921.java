package algorithm.slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()); // X일동안 방문자의 수가 가장 큰 구간

        int[] visitors = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visitors[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0; int right = X - 1;

        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += visitors[i];
        }

        int maxSum = sum;
        int cnt = 1;
        while(right < N - 1){
            sum -= visitors[left++];
            sum += visitors[++right];

            if(maxSum == sum){
                cnt++;
            } else if(sum > maxSum){
                maxSum = sum;
                cnt = 1;
            }
        }

        if(sum == 0){
            System.out.println("SAD");
            return;
        }

        System.out.println(maxSum);
        System.out.println(cnt);

    }
}
