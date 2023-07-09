package algorithm.문자열;

import java.io.*;

public class BOJ10809 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        int[] arr = new int[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = -1;
        }

        for (int j = 0; j < s.length(); j++) {
            int index = s.charAt(j) - 'a';
            if(arr[index] == -1) arr[index] = j;
        }

        for (int i : arr) {
            bw.write(i +" ");
        }
        bw.flush();
        br.close();
        bw.close();

    }
}
