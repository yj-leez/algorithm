package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2606 {
    static boolean[][] computer;
    static int cnt;
    static boolean[] attacked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        computer = new boolean[N + 1][N + 1];
        attacked = new boolean[N + 1];

        // 연결된 컴퓨터 true 처리
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            computer[a][b] = true;
            computer[b][a] = true;
        }

        cnt = -1; // 1번은 카운트에서 제외해야하므로 먼저 -1 처리
        dfs(1);
        System.out.println(cnt);
    }

    public static void dfs(int num){
        // 감염된 적 없다면 감염 처리
        if(!attacked[num]){
            attacked[num] = true;
            cnt++;
        }

        // 연결되어 있는 컴퓨터 호출
        for (int i = 1; i < computer.length; i++) {
            if(computer[num][i] && !attacked[i]){
                dfs(i);
            }
        }

    }
}
