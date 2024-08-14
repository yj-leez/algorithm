package algorithm.수학;

public class P피보나치의수 {
    public int solution(int n) {
        int[] sum = new int[n + 1];

        sum[0] = 0;
        sum[1] = 1;
        for(int i = 2; i <= n; i++){
            sum[i] = (sum[i - 1] + sum[i - 2]) % 1234567;
        }

        return sum[n];
    }
}
