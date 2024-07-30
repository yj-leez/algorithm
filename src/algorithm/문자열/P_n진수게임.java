package algorithm.문자열;

public class P_n진수게임 {
    public String solution(int n, int t, int m, int p) {
        String str = "";
        int idx = 0;
        while(str.length() < t * m){
            str += Integer.toString(idx, n).toUpperCase();
            idx++;
        }
        System.out.println(str);

        String ans = "";
        String[] tmp = str.split("");
        for(int i = 1; i <= str.length(); i++){
            if(ans.length() >= t) break;
            if((i % m) == (p % m)) ans += tmp[i - 1];
        }

        return ans;
    }
}
