package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 우선순위큐를 쓰지 않고 일반 큐를 써도 결과에는 차이가 없지만 실행 시간에서 차이가 난다.
 * 우선순위큐를 사용하면 큐에서 노드를 꺼내오는 횟수와 우선순위 큐의 갱신 횟수가 줄어든다.
 */
public class B1753 {
    static class Node implements Comparable<Node>{
        private int v; // 도착 정점 번호
        private int w; // 가중치
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w; // 음수 - 현재 객체가 비교 객체보다 작음
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수
        int K = Integer.parseInt(br.readLine()); // 시작 정점 번호

        ArrayList<Node>[] lists = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) lists[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 간선 시작 정점
            int v = Integer.parseInt(st.nextToken()); // 간선 도착 정점
            int w = Integer.parseInt(st.nextToken()); // 간선 가중치

            lists[u].add(new Node(v, w));
        }

        int[] dijk = new int[V + 1];
        Arrays.fill(dijk, Integer.MAX_VALUE);
        PriorityQueue<Node> pQ = new PriorityQueue<>();

        dijk[K] = 0;
        pQ.add(new Node(K, dijk[K]));
        while(!pQ.isEmpty()){
            Node past = pQ.poll();

            for(Node node : lists[past.v]){
                if (dijk[past.v] + node.w < dijk[node.v]){
                    dijk[node.v] = dijk[past.v] + node.w;
                    pQ.add(new Node(node.v, dijk[node.v]));
                }
            }
        }

        for (int i = 1; i < dijk.length; i++) {
            System.out.println(dijk[i] == Integer.MAX_VALUE? "INF": dijk[i]);
        }

    }
}

class Failure {
    static class Node{
        private int v; // 도착 정점 번호
        private int w; // 가중치
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수
        int K = Integer.parseInt(br.readLine()); // 시작 정점 번호

        ArrayList<Node>[] lists = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) lists[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 간선 시작 정점
            int v = Integer.parseInt(st.nextToken()); // 간선 도착 정점
            int w = Integer.parseInt(st.nextToken()); // 간선 가중치

            lists[u].add(new Node(v, w));
        }

        int[] dijk = new int[V + 1];
        Arrays.fill(dijk, Integer.MAX_VALUE);
        Queue<Integer> q = new ArrayDeque<>();

        dijk[K] = 0;
        q.add(K);
        while(!q.isEmpty()){
            int now = q.poll();

            for(Node node : lists[now]){
                if (dijk[now] + node.w < dijk[node.v]){
                    dijk[node.v] = dijk[now] + node.w;
                    q.add(node.v);
                }
            }
        }

        for (int i = 1; i < dijk.length; i++) {
            System.out.println(dijk[i] == Integer.MAX_VALUE? "INF": dijk[i]);
        }

    }
}
