package algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B2668 {
    static int N;
    static int[] nums;
    static boolean[] visited;
    static List<Integer> ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N + 1];
        ans = new ArrayList<>();

        for(int i = 1; i <= N; i++){
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;

        }

        System.out.println(ans.size());
        for(int n : ans){
            System.out.println(n);
        }
    }
     static void dfs(int idx, int target){
        if (nums[idx] == target){
            ans.add(target);
        }
        if (!visited[nums[idx]]){
            visited[nums[idx]] = true;
            dfs(nums[idx], target);
            visited[nums[idx]] = false;
        }
     }
}
