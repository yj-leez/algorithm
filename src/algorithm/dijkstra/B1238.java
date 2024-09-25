package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1238 {
    static int N;
    static int M;
    static int X;
    static class Node implements Comparable<Node>{
        int end;
        int weight;
        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 노드 개수
        M = Integer.parseInt(st.nextToken()); // 엣지 개수
        X = Integer.parseInt(st.nextToken()); // 시작 노드 번호

        ArrayList<Node>[] edges = new ArrayList[N + 1]; // 연결 엣지 배열
        ArrayList<Node>[] reverseEdges = new ArrayList[N + 1]; // 반대로(돌아오는 값 체크하기 위해)

        for (int i = 0; i < N + 1; i++) {
            edges[i] = new ArrayList<>();
            reverseEdges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[start].add(new Node(end, weight));
            reverseEdges[end].add(new Node(start, weight));
        }

        int[] dijk = dijkstra(edges);
        int[] reverseDijk = dijkstra(reverseEdges);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
            max = Math.max(max, dijk[i] + reverseDijk[i]);
        }

        System.out.println(max);
    }

    static int[] dijkstra(ArrayList<Node>[] a){
        PriorityQueue<Node> pQ = new PriorityQueue<>();
        int[] dijk = new int[N + 1]; // 1 ~ N
        boolean[] visited = new boolean[N + 1]; // 방문했는지 확인

        Arrays.fill(dijk, Integer.MAX_VALUE);
        dijk[X] = 0;
        pQ.add(new Node(X, 0));

        while (!pQ.isEmpty()){
            Node poll = pQ.poll();
            int now = poll.end;

            if (!visited[now]){
                visited[now] = true;

                for(Node n : a[now]){
                    if (!visited[n.end] && dijk[n.end] > dijk[now] + n.weight){
                        dijk[n.end] = dijk[now] + n.weight;
                        pQ.add(new Node(n.end, dijk[now] + n.weight));
                    }
                }
            }
        }

        return dijk;
    }
}
