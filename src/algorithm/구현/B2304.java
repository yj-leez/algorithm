package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B2304 {
    static int[] pillars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 기둥 개수

        List<int[]> cmd = new ArrayList<>();
        StringTokenizer st;
        int max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cmd.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            max = cmd.get(i)[0] > max? cmd.get(i)[0]: max;
        }

        pillars = new int[max + 1];
        for (int i = 0; i < N; i++) {
            pillars[cmd.get(i)[0]] = cmd.get(i)[1];
        }

        int leftIdx = 0;
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        while(leftIdx < max){
            if (pillars[leftIdx] <= pillars[leftIdx + 1]) {
                leftIdx++;
                set.add(leftIdx);
                continue;
            }

            int rightIdx = leftIdx + 1;
            for (int i = rightIdx; i < max + 1; i++) {
                if (pillars[rightIdx] < pillars[i]) rightIdx = i;
                if (pillars[rightIdx] >= pillars[leftIdx]) break;
            }

            set.add(leftIdx);
            set.add(rightIdx);
            sum += calculate(leftIdx, rightIdx);
            leftIdx = rightIdx;
        }

        for (Integer i :set) {
            sum += pillars[i];
        }

        System.out.println(sum);
    }

    static int calculate(int st, int en){
        int height = Math.min(pillars[st], pillars[en]);
        int width = en - st - 1;
//        System.out.println("+ " + (height * width));

        return height * width;
    }

}
