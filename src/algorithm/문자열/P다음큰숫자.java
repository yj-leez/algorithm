package algorithm.문자열;

public class P다음큰숫자 {
    public int solution(int n) {
        int nCnt = 0;
        String nTmp = Integer.toBinaryString(n);
        for(int i = 0; i < nTmp.length(); i++){
            if(nTmp.charAt(i) == '1') nCnt++;
        }

        int answer = n + 1;
        while(true){
            String tmp = Integer.toBinaryString(answer);
            int cnt = 0;
            for(int i = 0; i < tmp.length(); i++){
                if(tmp.charAt(i) == '1') cnt++;
            }

            if(cnt == nCnt) return answer;

            answer++;
        }
    }
}
