package algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B13549 {
    /**
     * 4-> 5 -> 6은 2초인데  4-> 3 -> 6보다 queue에 먼저 넣어지므로
     * 동생 자리에 도착하자마자 리턴하면 그 오래 걸리는 초를 출력하게 된다.
     * 그래서 queue가 빌 때까지 계속해서 반복문을 돈다.
     * 시간복잡도는 BFS의 시간복잡도 O(V+E) = 100,001 + 3×100,001 (각 노드마다 최대 3개의 간선이 있으므로)이므로 1초미만이므로 가능하다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[100001];
        q.add(new int[]{N, 0});
        visited[N] = true;

        int min = Integer.MAX_VALUE;
        while (!q.isEmpty()){
            int x = q.peek()[0];
            int s = q.poll()[1];
            visited[x] = true;

            if (x == K){
                min = min > s? s: min;
            }

            if (2 * x <= 100000 && !visited[2 * x]){
                q.add(new int[]{2 * x, s});
            }
            if (x + 1 <= 100000 && !visited[x + 1]){
                q.add(new int[]{x + 1, s + 1});
            }
            if (x - 1 >= 0 && !visited[x - 1]){
                q.add(new int[]{x - 1, s + 1});
            }


        }
        System.out.println(min);
    }
}
