package algorithm.문자열;

public class P이진변환반복하기 {
    public int[] solution(String s) {
        int changeCnt = 0;
        int zeroCnt = 0;
        while(!s.equals("1")){
            String tmp = "";
            // 0 제거
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '0') zeroCnt++;
                else if(s.charAt(i) == '1') tmp += '1';
            }

            // 이진 변환
            s = Integer.toBinaryString(tmp.length());
            changeCnt++;
        }
        int[] answer = {changeCnt, zeroCnt};
        return answer;
    }
}
