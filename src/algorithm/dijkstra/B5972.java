package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B5972 {
    static class Node implements Comparable<Node>{
        private int end;
        private int weight;

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

        int N = Integer.parseInt(st.nextToken()); // 노드의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수

        ArrayList<Node>[] lists = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) lists[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            lists[start].add(new Node(end, weight));
            lists[end].add(new Node(start, weight));
        }

        // 시작 노드: 1, 도착 노드: N
        PriorityQueue<Node> pQ = new PriorityQueue<>();
        int[] dijk = new int[N + 1];
        Arrays.fill(dijk, Integer.MAX_VALUE);

        dijk[1] = 0;
        pQ.add(new Node(1, dijk[1]));
        while(!pQ.isEmpty()){
            int start = pQ.poll().end;

            if (start != N){
                for(Node now : lists[start]){
                    if (dijk[now.end] > dijk[start] + now.weight){
                        dijk[now.end] = dijk[start] + now.weight;
                        pQ.add(new Node(now.end, dijk[now.end]));
                    }
                }
            }
        }
        System.out.println(dijk[N]);
    }
}
