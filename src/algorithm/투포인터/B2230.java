package algorithm.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2230 {
    /**
     * 투포인터
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = 0;
        int answer = Integer.MAX_VALUE;
        while(start < n && end < n){

            if(arr[end] - arr[start] >= m){
                answer = Math.min(answer, arr[end] - arr[start]);
                start++;
            } else {
                end++;
            }

        }

        System.out.println(answer);
    }
}
