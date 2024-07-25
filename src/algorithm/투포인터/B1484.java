package algorithm.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1484 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long past = 1;
        long now = 2; // 현재 몸무게는 기억하는 몸무게보다 크다.
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        while(true){
            if(Math.pow(now, 2) - Math.pow(past, 2) == N){
                sb.append(now + "\n");
                flag = true;
                now++;
            } else if(Math.pow(now, 2) - Math.pow(past, 2) < N){
                now++;
            } else {
                // 두 수의 제곱 차가 더 큰 경우
                past++;
            }

            if(past >= now) break;
        }

        if(flag) System.out.print(sb);
        else System.out.println(-1);
    }
}
