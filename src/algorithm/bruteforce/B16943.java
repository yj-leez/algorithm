package algorithm.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class B16943 {
    static String[] a;
    static int b;
    static boolean[] visited;
    static int answer;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = st.nextToken().split("");
        Arrays.sort(a, Collections.reverseOrder());

        b = Integer.parseInt(st.nextToken());

        answer = -1;
        flag = false;
        visited = new boolean[a.length];
        calculate("", 0);

        System.out.println(answer);
    }

    static void calculate(String s, int size){
        if (size == a.length && Integer.parseInt(s) < b && s.charAt(0) != '0'){
            answer = Integer.parseInt(s);
            flag = true;
            return;
        }

        for (int i = 0; i < a.length; i++) {
            if (!visited[i] && !flag){
                visited[i] = true;
                calculate(s + a[i], size + 1);
                visited[i] = false;
            }
        }
    }
}
