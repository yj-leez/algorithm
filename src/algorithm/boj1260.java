package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1260 {

    public static boolean[][] edges;
    public static int edgeNum;
    public static int nodeNum;
    public static int cnt;
    public static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        edges = new boolean[nodeNum + 1][nodeNum + 1];
        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edges[a][b] = true;
            edges[b][a] = true;
        }

        visited = new boolean[nodeNum + 1];
        dfs(startNode);
        System.out.println();

        Arrays.fill(visited, false);
        bfs(startNode);
        System.out.println();

    }

    public static void dfs(int v){
        visited[v] = true;
        System.out.print(v + " ");
        if (cnt == nodeNum){
            return;
        }
        cnt++;

        for (int i = 1; i <= nodeNum; i++) {
            if (edges[i][v] && !visited[i]){
                dfs(i);
                cnt--;
            }
        }
    }

    public static void bfs(int v){
        visited[v] = true;
        Queue<Integer> queue = new LinkedList<>();
        System.out.print(v + " ");
        queue.offer(v);

        while(!queue.isEmpty()){
            int node = queue.remove();


            for (int i = 1; i <= nodeNum; i++) {
                if (edges[node][i] == true && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    System.out.print(i + " ");
                }
            }
        }
    }

}
