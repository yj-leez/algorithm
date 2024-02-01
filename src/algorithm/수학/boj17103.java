package algorithm.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj17103 {
    public int [] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int total=0;

            int a = Integer.parseInt(br.readLine());
            boolean[] prime = get_prime(a);


            for (int j = 2; j <= a / 2; j++) {
                if(!prime[j] && !prime[a-j]) {
                    total++;
                }

            }
            sb.append(total+"\n");
        }

        System.out.println(sb);
    }

    public static boolean[] get_prime(int a) {

        boolean[] prime = new boolean[a + 1]; //기본값 false
        if(a < 2) return prime;

        prime[0] = prime[1] = true;

        for(int i = 2; i <= Math.sqrt(a); i++) {
            if(prime[i] == true) continue;

            for(int j = i * i; j < prime.length; j = j+i) {
                prime[j] = true;
            }
        }
        return prime;
    }
}
