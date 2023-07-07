package algorithm.배열1;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10807 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int compare = Integer.parseInt(br.readLine());
        int count = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(compare == arr[i]) count++;
        }

        bw.write(String.valueOf(count)); //bw는 숫자에 따른 아스키코드 출력 -> String으로 형변환하여 출력해야함

        br.close();
        bw.flush();
        bw.close();
    }
}
