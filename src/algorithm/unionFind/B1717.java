package algorithm.unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1717 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // n + 1개의 집합 존재, {0}, {1}, ... , {N}
        int M = Integer.parseInt(st.nextToken()); // 입력으로 주어지는 연산의 개수

        parents = new int[N + 1];
        for(int i = 0; i < N + 1; i++)
            parents[i] = i;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(cmd == 0){
                // 합집합
                union(a, b);
            } else if(cmd == 1){
                // 같은 집합에 포함되어 있는지를 확인
                if(IsSameParents(a, b)) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }

        System.out.print(sb);
    }

    static boolean IsSameParents(int a, int b){
        int parentOfA = find(a);
        int parentOfB = find(b);

        if(parentOfA == parentOfB) return true;
        return false;
    }

    static void union(int a, int b){
        int parentOfA = find(a);
        int parentOfB = find(b);

        if(parentOfA != parentOfB){
            if(parentOfA < parentOfB){
                parents[parentOfB] = parentOfA;
            } else {
                parents[parentOfA] = parentOfB;
            }
        }
    }

    static int find(int x){
        if(x == parents[x]){
            return x;
        }

        return parents[x] = find(parents[x]);
    }

}
