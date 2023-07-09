package algorithm.문자열;

import java.io.*;

public class BOJ9086 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            bw.write(s.charAt(0));
            bw.write(s.charAt(s.length()-1) + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
/**
 * s.charAt(0) + s.charAt(s.length()-1) + "\n"를 한 번에 write하면 char 2개를 더한 아스키 코드 값이 출력됨.
 */