package algorithm.bruteforce;

public class P카펫 {
    public int[] solution(int brown, int yellow) {
        // 높이는 3 이상이고 brown + yellow로 나누어 떨어져야한다.
        int h = 3;
        int w = 3;
        while(true){
            if((brown + yellow) % h == 0){
                // (w - 2) * (h - 2)는 yellow 개수랑 같아야한다.
                w = (brown + yellow) / h;
                if((h - 2) * (w - 2) == yellow) break;
                else h++;
            } else h++;
        }
        int[] answer = {w, h};
        return answer;
    }
}
