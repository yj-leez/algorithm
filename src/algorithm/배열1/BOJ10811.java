package algorithm.배열1;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10811 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            arr[i] = i;
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for (int k = 0; k < (end - start + 1)/2; k++) {
                int temp = arr[start + k];
                arr[start + k] = arr[end - k];
                arr[end - k] = temp;
            }
        }

        for (int i = 1; i <= n; i++) {
            bw.write(arr[i] + " ");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
