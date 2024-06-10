package algorithm.우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pQ = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            pQ.add(Integer.parseInt(br.readLine()));
        }

        // 한 개가 남을 때까지 제일 작은 두 개의 값을 더하기
        int ans = 0;
        while(true){

            int num1 = pQ.poll();

            if (pQ.isEmpty()){
                System.out.println(ans);
                break;
            }

            int num2 = pQ.poll();
            ans += num1 + num2;
            pQ.add(num1 + num2);

        }
    }
}
