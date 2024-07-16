package algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B1987 {
    static int R;
    static int C;
    static String[][] board;
    static Set<String> set;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new String[R][C];

        for (int i = 0; i < R; i++) {
            String[] tmp = br.readLine().split("");
            board[i] = tmp;
        }

        set = new HashSet<>();
        max = Integer.MIN_VALUE;

        set.add(board[0][0]);
        dfs(0, 0, 1);

        System.out.println(max);
    }
    static void dfs(int r, int c, int cnt){
        if (cnt > max) max = cnt;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C || set.contains(board[nr][nc])) continue;

            set.add(board[nr][nc]);
            dfs(nr, nc, cnt + 1);
            set.remove(board[nr][nc]);
        }
    }
}

/**
 * HashSet으로 이미 지나갔는지 확인하면 메모리, 시간 측면에서 더 걸린다.
 * 알파벳을 숫자화해서 boolean 배열로 확인하면 시간도 단축되고 메모리도 덜 사용한다.
 */
class Solution {
    static int R;
    static int C;
    static String[][] board;
    static boolean[] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new String[R][C];

        for (int i = 0; i < R; i++) {
            String[] tmp = br.readLine().split("");
            board[i] = tmp;
        }

        visited = new boolean[26];
        max = Integer.MIN_VALUE;

        visited[board[0][0].charAt(0) - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(max);
    }
    static void dfs(int r, int c, int cnt){
        if (cnt > max) max = cnt;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[board[nr][nc].charAt(0) - 'A']) continue;

            visited[board[nr][nc].charAt(0) - 'A'] = true;
            dfs(nr, nc, cnt + 1);
            visited[board[nr][nc].charAt(0) - 'A'] = false;
        }
    }
}

