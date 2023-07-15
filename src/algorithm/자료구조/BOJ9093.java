package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9093 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()) {
                String s = st.nextToken();
                for (int k = 1; k <= s.length(); k++) {
                    sb.append(s.charAt(s.length()-k));
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
