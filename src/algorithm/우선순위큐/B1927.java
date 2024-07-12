package algorithm.우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(br.readLine());

            if (t == 0){
                if (pQ.isEmpty()) sb.append(0 + "\n");
                else sb.append(pQ.poll() + "\n");
            } else {
                pQ.add(t);
            }
        }

        System.out.println(sb);
    }
}
