package algorithm.심화1단계;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ3003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 6; i++) {
            int a = Integer.parseInt(st.nextToken());
            if(i < 2) bw.write((1 - a) + " ");
            else if (i < 5) bw.write((2 - a) + " ");
            else bw.write((8 - a) + "");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
