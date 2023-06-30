package algorithm.반복문;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10951 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        String line;
        while ((line=br.readLine()) != null){
            st = new StringTokenizer(line);
            bw.write((Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken()))+"\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
//EOF 입력 command + D -> BufferedReader는 null을 반환
