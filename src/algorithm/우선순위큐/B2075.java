package algorithm.우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 우선순위큐에 삽입하는 과정은 O(log(N^2))이고 이것을 N*N번 반복하므로
 * 대략 49,050,000 걸리고, 1초 미만을 만족한다.
 */
public class B2075 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] nums = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                pQ.add(nums[i][j]);
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = pQ.poll();
        }
        System.out.println(ans);

    }
}

/**
 * 맨 처음에 메모리를 최적화시킨다고 열이 아닌 행 순으로 읽도록 한 다음
 * queue를 활용해서 비교하는 방식으로 풀었으나 당연하게도 시간 초과가 났다.
 * queue로 정렬하는 과정에서 O(N^3), 즉 최악의 경우 30초이상 걸린다.
 */
class Fail {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] nums = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] changedNums = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                changedNums[i][j] = nums[j][i];
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            queue.add(changedNums[0][i]);
        }

        for (int i = 1; i < N; i++) {
            int idx = 0;
            for (int j = 0; j < (i + 1) * N; j++) {

                if (idx >= N) {
                    queue.add(queue.poll());
                    continue;
                }

                int tmp = queue.peek();
                if (changedNums[i][idx] < tmp){
                    queue.add(changedNums[i][idx]);
                    idx++;
                } else{
                    queue.add(queue.poll());
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < N * N - N + 1; i++) {
            ans = queue.poll();
        }

        System.out.println(ans);


    }
}
