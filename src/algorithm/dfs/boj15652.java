package algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj15652 {
    public static int[] arr;
    public static boolean[] contain;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 한 노드에서 자식 노드의 개수
        int m = Integer.parseInt(st.nextToken()); // 높이

        arr = new int[m];

        dfs(n, m, 0);
        System.out.println(sb);
    }

    public static void dfs(int n, int m, int depth){
        if(depth == m){
            for (int i: arr) {
                sb.append(i).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 0; i < n; i++) {
            if(depth == 0 || ((arr[depth - 1] <= i + 1) && depth > 0) ) {
                arr[depth] = i + 1;
                dfs(n, m, depth+1);
            }

        }
    }
}
