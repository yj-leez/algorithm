package algorithm.수학;

public class P멀리뛰기 {
    public long solution(int n) {
        long[] cnts = new long[n + 1];
        cnts[0] = 1; cnts[1] = 1;

        for(int i = 2; i <= n; i++){
            cnts[i] = (cnts[i - 1] + cnts[i - 2]) % 1234567;
        }

        long answer = cnts[n];
        return answer;
    }
}
