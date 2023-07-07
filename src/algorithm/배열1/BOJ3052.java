package algorithm.배열1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3052 {
    public static void main(String[] args) throws IOException {
        boolean[] arr = new boolean[42];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            int a = (Integer.parseInt(br.readLine()) % 42);
            arr[a] = true;
        }

        int cnt = 0;
        for (int j = 0; j < 42; j++) {
            if(arr[j]) cnt++;
        }
        System.out.println(cnt);
    }
}
