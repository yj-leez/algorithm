package algorithm.programmers;

class 타겟넘버 {
    public static int[] nums;
    public static int cnt;
    public static int targetNum;

    public static void main(String[] args) {
        int[] numbers = {4, 1, 2, 1};
        int target = 4;
        System.out.println(solution(numbers, target));

    }
    public static int solution(int[] numbers, int target) {
        nums = numbers;
        cnt = 0;
        targetNum = target;

        dfs(numbers[0], 1, '+');
        dfs(numbers[0], 1, '-');
        dfs(0 - numbers[0], 1, '+');
        dfs(0 - numbers[0], 1, '-');



        return cnt;
    }

    public static void dfs(int cal,int idx, char operator){
        if(idx == nums.length){
            if(cal == targetNum && operator == '+'){
                cnt++;
            }
            return;
        }

        if(operator == '+'){
            dfs(cal+nums[idx], idx+1, '+');
            dfs(cal+nums[idx], idx+1, '-');
        } else if(operator == '-') {
            dfs(cal-nums[idx], idx+1, '+');
            dfs(cal-nums[idx], idx+1, '-');
        }

        return;
    }
}
