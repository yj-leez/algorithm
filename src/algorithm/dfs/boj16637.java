package algorithm.dfs;
import java.util.*;
import java.io.*;
public class boj16637 {
    public static int max = -231;
    public static int[] nums;
    public static String[] op;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        nums = new int[N / 2 + 1];
        op = new String[N / 2];

        for (int i = 0; i < N; i++) {
            if(i % 2 == 0){
                nums[i/2] = Integer.parseInt(String.valueOf(s.charAt(i)));
            } else {
                op[i/2] = String.valueOf(s.charAt(i));
            }
        }

        dfs(0, nums[0]); //idx는 현재 op의 idx 값

        System.out.println(max);

    }

    public static void dfs(int idx, int sum){
        if(idx >= op.length){
            max = Math.max(max, sum);
            return;
        }

        // 괄호 안 치고
        dfs(idx + 1, cal(idx, sum, nums[idx + 1]));

        // 괄호 치고
        if(idx + 2 <= op.length){
            int tmp = cal(idx + 1, nums[idx + 1], nums[idx + 2]); // 뒤의 괄호 먼저 계산
            dfs(idx + 2, cal(idx, sum, tmp));
        }

    }

    public static int cal(int idx, int before, int after){
        if(op[idx].equals("+")){
            before += after;
        } else if(op[idx].equals("-")){
            before -= after;
        } else if(op[idx].equals("*")){
            before *= after;
        }
        return before;
    }
}
