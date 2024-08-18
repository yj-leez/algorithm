package algorithm.수학;

/**
 * dp로 계산했더니 효율성테스트에서 틀림.
 * 시작점부터 계산하는 것이 아니라 도착점부터 계산하는 방식으로 해결.
 */
public class P점프와순간이동 {
    public int solution(int n) {
        int answer = 0;

        while (n > 0) {
            if (n % 2 == 0) n /= 2;
            else {
                n--;
                answer++;
            }
        }

        return answer;
    }
}