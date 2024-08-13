package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B17140 {
    static class Pair implements Comparable<Pair>{
        private int num;
        private int cnt;
        public Pair(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair p){
            if (this.cnt != p.cnt) return this.cnt - p.cnt;
            return this.num - p.num;
        }
    }
    static int[][] A;
    static int xLength;
    static int yLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());

        A = new int[100][100];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
            A[i][2] = Integer.parseInt(st.nextToken());
        }

        xLength = 3; yLength = 3;
        int answer = 0;
        while (true) {
            if (A[r][c] == k) {
                System.out.println(answer);
                return;
            }

            if (answer > 100) {
                System.out.println(-1);
                return;
            }

            if (xLength >= yLength) R();
            else C();
            answer++;
        }

    }

    static void R(){
        for (int i = 0; i < xLength; i++) {
            PriorityQueue<Pair> pQ = new PriorityQueue<>();
            Map<Integer, Integer> map = new HashMap<>();

            for (int j = 0; j < yLength; j++) {
                if (A[i][j] == 0) continue;
                map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
            }

            map.forEach((k, v) -> pQ.add(new Pair(k, v)));

            int idx = 0;
            while(!pQ.isEmpty()){
                Pair p = pQ.poll();
                A[i][idx++] = p.num;
                A[i][idx++] = p.cnt;
            }

            yLength = Math.max(yLength, idx);

            while (idx < 100) {
                A[i][idx++] = 0;
            }
        }
    }

    static void C(){
        for (int i = 0; i < yLength; i++) {
            PriorityQueue<Pair> pQ = new PriorityQueue<>();
            Map<Integer, Integer> map = new HashMap<>();

            for (int j = 0; j < xLength; j++) {
                if (A[j][i] == 0) continue;
                map.put(A[j][i], map.getOrDefault(A[j][i], 0) + 1);
            }

            map.forEach((k, v) -> pQ.add(new Pair(k, v)));

            int idx = 0;
            while(!pQ.isEmpty()){
                Pair p = pQ.poll();
                A[idx++][i] = p.num;
                A[idx++][i] = p.cnt;
            }

            xLength = Math.max(xLength, idx);

            while (idx < 100) {
                A[idx++][i] = 0;
            }
        }
    }
}
