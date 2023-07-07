package algorithm.배열1;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double[] arr = new double[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        double max =0;
        for (int i = 0; i < n; i++) {
            arr[i] = Double.parseDouble(st.nextToken());
            if(arr[i] > max) max = arr[i];
        }
        double total = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / max * 100;
            total += arr[i];
        }
        System.out.println(total/n);
        br.close();

    }
}
