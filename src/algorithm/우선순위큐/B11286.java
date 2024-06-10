package algorithm.우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class B11286 {
    /**
     * compare 참고
     * https://st-lab.tistory.com/243
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pQ = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                /**
                 * 양수가 나오면 선행과 후행의 위치를 바꾼다.
                 * 음수가 나오면 선행과 후행의 위치를 바꾸지 않는다.
                 */
                if (Math.abs(o1) > Math.abs(o2)){
                    return 1;
                } else if (Math.abs(o1) == Math.abs(o2)){
                    return o1 - o2;
                } else {
                    return -1;
                }
            }
        });

        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp == 0){
                if (pQ.isEmpty()) sb.append("0\n");
                else {
                   sb.append(pQ.poll() + "\n");
                }
            } else {
                pQ.add(tmp);
            }
        }

        System.out.println(sb);

    }
}
