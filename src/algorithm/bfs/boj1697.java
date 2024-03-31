package algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1697 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        if (x == y) {
            System.out.println(0);
            return;
        }

        //bfs
        Queue<Integer> queue= new LinkedList<>();
        boolean[] went = new boolean[1000001];
        int cnt = 0;
        queue.add(x);
        went[x] = true;
        int size;

        while (true){
            cnt++;
            size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.remove();
                went[node] = true;

                if (node-1 == y || node+1 == y || node*2 == y) {
                    System.out.println(cnt);
                    return;
                }
                if (node-1 >= 0 && !went[node-1]) {
                    went[node-1] = true;
                    queue.add(node-1);
                }
                if (node+1 <= 100000 && !went[node+1]) {
                    went[node+1] = true;
                    queue.add(node+1);
                }
                if (node*2 <= 100000 && !went[node*2]) {
                    went[node*2] = true;
                    queue.add(node*2);
                }

            }
        }
    }
}
