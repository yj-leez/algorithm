package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BFS -> 시간초과
 * dp -> dp[곡의순서][볼륨]
 */
public class B1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 곡 개수
        int S = Integer.parseInt(st.nextToken()); // 시작 볼륨
        int M = Integer.parseInt(st.nextToken()); // 최대 볼륨 크기

        int[] sounds = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            sounds[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N][M + 1];

        if(S + sounds[0] <= M) dp[0][S + sounds[0]] = true;
        if(S - sounds[0] >= 0) dp[0][S - sounds[0]] = true;
        for(int i = 1; i < N; i++){
            for(int j = 0; j <= M; j++){
                if(dp[i - 1][j]) {
                    if (j + sounds[i] <= M) dp[i][j + sounds[i]] = true;
                    if (j - sounds[i] >= 0) dp[i][j - sounds[i]] = true;
                }
            }
        }

        int ans = - 1;
        for(int i = M; i >= 0; i--){
            if(dp[N - 1][i]){
                ans = i;
                break;
            }
        }

        System.out.println(ans);

    }
}

class Failure {

    static int[] sounds;
    static int ans;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 곡 개수
        int S = Integer.parseInt(st.nextToken()); // 시작 볼륨
        M = Integer.parseInt(st.nextToken()); // 최대 볼륨 크기

        sounds = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            sounds[i] = Integer.parseInt(st.nextToken());
        }

        ans = -1;
        dfs(S, 0);
        System.out.println(ans);
    }
    static void dfs(int n, int idx){
        if(idx == sounds.length && 0 <= n && n <= M){
            ans = Math.max(ans, n);
            return;
        }

        if(0 <= n + sounds[idx] && n + sounds[idx] <= M){
            dfs(n + sounds[idx], idx + 1);
        }
        if(0 <= n - sounds[idx] && n - sounds[idx] <= M){
            dfs(n - sounds[idx], idx + 1);
        }
    }
}
