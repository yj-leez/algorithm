package algorithm.심화1단계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ25206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        double total = 0.0;
        double sum = 0.0;
        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            double v = Double.parseDouble(st.nextToken());

            total += v;
            switch (st.nextToken()){
                case "A+":
                    sum += 4.5 * v; break;
                case "A0":
                    sum += 4.0 * v; break;
                case "B+":
                    sum += 3.5 * v; break;
                case "B0":
                    sum += 3.0 * v; break;
                case "C+":
                    sum += 2.5 * v; break;
                case "C0":
                    sum += 2.0 * v; break;
                case "D+":
                    sum += 1.5 * v; break;
                case "D0":
                    sum += 1.0 * v; break;
                case "P":
                    total -= v;
            }

        }
        System.out.println(sum/total);
    }
}
