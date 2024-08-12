package algorithm.누적합;

public class P숫자의표현 {
    public int solution(int n) {
        int[] prefix = new int[n + 1];
        for(int i = 1; i < n + 1; i++)
            prefix[i] = prefix[i - 1] + i;

        int left = 0; int right = 1;
        int answer = 0;
        while(true){
            if(prefix[right] - prefix[left] < n){
                right++;
            } else if(prefix[right] - prefix[left] > n){
                left++;
            } else {
                answer++;
                left++;
            }

            if(right == n && left == n) break;
        }


        return answer;
    }
}
