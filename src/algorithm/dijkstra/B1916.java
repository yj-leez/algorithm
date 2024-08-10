package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * if (!check[cur]) 블록 내에서만 이웃 노드를 우선순위 큐에 추가하기 때문에, 불필요한 노드가 큐에 추가되지 않도록 함
 */
public class B1916 {
    static class Node implements Comparable<Node> {

        private int end;
        private int weight;

        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight; // 오름차순 정렬
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 도시의 개수
        int M = Integer.parseInt(br.readLine()); // 버스의 개수

        ArrayList<Node>[] buses = new ArrayList[N + 1]; // 1 ~ N에서 출발하는 버스의 리스트
        for (int i = 1; i <= N; i++)
            buses[i] = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            buses[start].add(new Node(end, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dijk = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dijk, Integer.MAX_VALUE);
        PriorityQueue<Node> pQ = new PriorityQueue<>();

        dijk[start] = 0;
        pQ.add(new Node(start, dijk[start]));
        while(!pQ.isEmpty()){
            Node past = pQ.poll();

            if(!visited[past.end]){
                visited[past.end] = true;

                for(Node n: buses[past.end]){
                    if(!visited[n.end] && dijk[n.end] > dijk[past.end] + n.weight){
                        dijk[n.end] = dijk[past.end] + n.weight;
                        pQ.add(new Node(n.end, dijk[n.end]));
                    }
                }
            }
        }

        System.out.println(dijk[end]);
    }
}
