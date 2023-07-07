package algorithm.배열1;

import java.io.*;

public class BOJ2562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] arr = new int[9];

        int maxIndex = -1;
        int max = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(arr[i] > max){
                max = arr[i];
                maxIndex = i;
            }
        }
        bw.write(max + "\n" + (maxIndex+1));
        br.close();
        bw.flush();
        bw.close();

    }
}
