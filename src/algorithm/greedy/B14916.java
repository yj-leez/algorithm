package algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B14916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int ans = 0;
        while(n >= 5){
            n -= 5;
            ans++;
        }

        while(n % 2 != 0){
            n += 5;
            ans--;
            if (ans < 0) {
                System.out.println(-1);
                return;
            }
        }

        ans += n / 2;

        System.out.println(ans);
    }
}
