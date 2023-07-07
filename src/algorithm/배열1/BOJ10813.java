package algorithm.배열1;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10813 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i+1;
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int temp = arr[a-1];
            arr[a-1] = arr[b-1];
            arr[b-1] = temp;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int k = 0; k < n; k++) {
            bw.write(arr[k] + " ");
        }
        bw.flush();

        br.close();
        bw.close();

    }
}
