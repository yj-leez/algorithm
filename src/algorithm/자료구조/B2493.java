package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B2493 {
    /**
     * 이전의 탑 중에 자기보다 크거나 같은 탑을 찾는다.
     * 만약 이전의 탑이 자기보다 크거나 같다면 -> ans 배열에 답을 넣는다. 작다면 -> 해당 탑의 ans 배열의 값에 해당하는 탑으로 간다.
     * 이런 식으로 시간 복잡도를 줄일 수 있다.
     *
     * 다른 풀이를 보면 스택을 사용한 풀이도 있다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] towers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }

        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            int j = i - 1;
            while (j >= 0){
                if (towers[i] <= towers[j]){
                    ans[i] = j + 1;
                    break;
                } else {
                    j = ans[j] - 1;
                }
            }
        }

        for(int n: ans){
            System.out.print(n + " ");
        }
    }
}
class Solution_B2493{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<int[]> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(st.nextToken());
            boolean flag = false;

            while(!stack.isEmpty()){
                if (stack.peek()[1] >= k){
                    flag = true;
                    sb.append(stack.peek()[0] + " ");
                    stack.add(new int[]{i + 1, k});
                    break;
                }

                stack.pop();
            }

            if (!flag) {
                sb.append("0 ");
                stack.add(new int[]{i + 1, k});
            }
        }
        System.out.println(sb);
    }
}
