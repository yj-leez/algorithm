package algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B15591 {
    static int N;
    static int k;

    static boolean[] visited;
    static List<int[]>[] adj;

    /**
     * 간선 값을 2차원 배열에 저장하여 bfs시 매번 탐색하는 것이 아니라,
     * 노드 배열로 저장하여 연결된 노드들만 탐색하여 시간을 줄인다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N];

        for (int i = 0; i < N; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken()) - 1;
            int q = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken());
            adj[p].add(new int[]{q, r});
            adj[q].add(new int[]{p, r});
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {

            visited = new boolean[N];
            st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()) - 1; // 동영상 인덱스 번호


            sb.append(bfs(v));
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static int bfs(int idx){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(idx);
        int cnt = 0;


        while(!queue.isEmpty()){
            int p = queue.poll();

            visited[p] = true;
            for (int[] a : adj[p]) {
                if (!visited[a[0]] && a[1] >= k) {
                    queue.add(a[0]);
                    visited[a[0]] = true;
                    cnt++;
                }
            }
        }

        return cnt;

    }
}
/**
 * 시간초과
 * -> 간선 값을 찾는 과정없이 바로 BFS 탐색을 한다.
 *    어차피 두 쌍 사이의 USADO 값은 그 경로의 모든 연결의 USADO 값 가운데 최솟값이 되기 때문에,
 *    K 값 보다 작은 경우라면, 최솟값을 찾았을 때 어차피 범위안에 들어오지 못하게 되므로 K 보다 이상인 애들만 카운트 함으로써 시간 초과를 해결할 수 있다.
 */
//public class B15591 {
//    static int N;
//    static int k;
//
//    static boolean[] visited;
//    static int[][] USADO;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        int Q = Integer.parseInt(st.nextToken());
//
//
//
//        USADO = new int[N][N]; // USADO의 값을 저장하는 배열
//        for (int i = 0; i < N - 1; i++) {
//            st = new StringTokenizer(br.readLine());
//
//            int n1 = Integer.parseInt(st.nextToken()) - 1;
//            int n2 = Integer.parseInt(st.nextToken()) - 1;
//            int usado = Integer.parseInt(st.nextToken());
//
//            USADO[n1][n2] = usado;
//            USADO[n2][n1] = usado;
//        }
//
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < Q; i++) {
//
//            visited = new boolean[N];
//            st = new StringTokenizer(br.readLine());
//
//            k = Integer.parseInt(st.nextToken());
//            int v = Integer.parseInt(st.nextToken()) - 1; // 동영상 인덱스 번호
//
//
//            sb.append(bfs(v));
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//
//    }
//
//    static int bfs(int idx){
//        Queue<Integer> queue = new LinkedList<>();
//        queue.add(idx);
//        int cnt = 0;
//
//
//        while(!queue.isEmpty()){
//            int p = queue.poll();
//
//            visited[p] = true;
//
//            for (int i = 0; i < N; i++) {
//                if (i != p && !visited[i] && USADO[i][p] >= k){
//                    queue.add(i);
//                    cnt++;
//                }
//            }
//        }
//
//        return cnt;
//
//    }
//}
/**
 * 시간초과
 */
//public class B15591 {
//    static int N;
//    static int min;
//    static boolean[] visited;
//    static int[][] USADO;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        int Q = Integer.parseInt(st.nextToken());
//
//        USADO = new int[N][N]; // USADO의 값을 저장하는 배열
//        for (int i = 0; i < N - 1; i++) {
//            st = new StringTokenizer(br.readLine());
//
//            int n1 = Integer.parseInt(st.nextToken()) - 1;
//            int n2 = Integer.parseInt(st.nextToken()) - 1;
//            int usado = Integer.parseInt(st.nextToken());
//
//            USADO[n1][n2] = usado;
//            USADO[n2][n1] = usado;
//        }
//
//        // 배열을 탐색하면서 만약 USADO 값이 존재하지 않는다면 bfs로 USADO를 계산한다.
//        visited = new boolean[N];
//        for (int i = 0; i < N; i++) {
//            for (int j = i; j < N; j++) {
//                if (i == j) continue;
//
//                if (USADO[i][j] == 0){
//                    min = Integer.MAX_VALUE;
//                    dfs(i, j, Integer.MAX_VALUE);
//                    USADO[i][j] = -min;
//                    USADO[j][i] = USADO[i][j];
//                }
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < Q; i++) {
//            st = new StringTokenizer(br.readLine());
//
//            int k = Integer.parseInt(st.nextToken());
//            int v = Integer.parseInt(st.nextToken()) - 1; // 동영상 인덱스 번호
//
//            int cnt = 0;
//            for (int j = 0; j < N; j++) {
//                if (v == j) continue;
//
//                if (Math.abs(USADO[v][j]) >= k) cnt++;
//            }
//
//            sb.append(cnt);
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//
//    }
//
//    static void dfs(int idx, int end, int minValue){
//        if (idx == end){
//            min = min > minValue? minValue: min;
//            return;
//        }
//
//        visited[idx] = true;
//
//        for (int i = 0; i < N; i++) {
//            if (i != idx && !visited[i] && USADO[i][idx] > 0){
//                dfs(i, end, minValue > USADO[i][idx]? USADO[i][idx]: minValue);
//            }
//        }
//        visited[idx] = false;
//
//    }
//}
