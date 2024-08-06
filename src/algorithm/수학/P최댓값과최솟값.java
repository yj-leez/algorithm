package algorithm.수학;

import java.util.Arrays;

public class P최댓값과최솟값 {
    public String solution(String s) {
        String[] str = s.split(" ");
        int[] nums = new int[str.length];
        for(int i = 0; i < str.length; i++){
            nums[i] = Integer.parseInt(str[i]);
        }

        Arrays.sort(nums);
        StringBuilder ans = new StringBuilder();
        ans.append(nums[0] + " " + nums[nums.length - 1]);

        return ans.toString();
    }
}