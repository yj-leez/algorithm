package algorithm.배열1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10818 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        bw.write(String.valueOf(arr[0])+ " " + String.valueOf(arr[n-1]));



        br.close();
        bw.flush();
        bw.close();
    }
}
