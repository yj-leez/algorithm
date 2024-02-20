package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj9095 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int max = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (max < arr[i]) max = arr[i];
        }


        int[] cal = new int[max];
        cal[0] = 1; cal[1] = 2; cal[2] = 4;

        for (int i = 3; i < max; i++) {
            cal[i] = cal[i - 1] + cal[i - 2] + cal[i - 3];
        }

        for (int i = 0; i < N; i++) {
            System.out.println(cal[arr[i] - 1]);
        }
    }
}
