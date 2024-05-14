package algorithm.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1806 {
    /**
     * 투포인터
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stz.nextToken());
        int m = Integer.parseInt(stz.nextToken());

        int[] arr = new int[n];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }


        int st = 0;
        int en = 0;
        int total = arr[0];
        int ans = Integer.MAX_VALUE; // 더 작은 길이

        /**
         * total이 m보다 작다면 en 증가,
         * total이 m보다 큰 순간에는 길이 비교해서 ans 값 확인, total -= arr[st], st 한 칸 증가, en은 그대로
         */
        while(st < n){
            if (total < m){
                en++;
                if(en < n){
                    total += arr[en];
                } else {
                    break;
                }

            } else {
                ans = Math.min(en - st + 1, ans);
                total -= arr[st];

                st++;
            }

        }


        if (ans == Integer.MAX_VALUE){
            System.out.println(0);
        } else {
            System.out.println(ans);
        }

    }
}
