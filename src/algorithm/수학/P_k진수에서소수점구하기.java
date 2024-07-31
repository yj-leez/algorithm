package algorithm.수학;

public class P_k진수에서소수점구하기 {
    public int solution(int n, int k) {
        String[] arr = Integer.toString(n, k).split("0"); // n을 k진수로 변환

        int ans = 0;
        for(String str: arr){
            if(str.equals("")) continue;
            if(isPrime(Long.parseLong(str))) ans++;
        }


        return ans;
    }

    static boolean isPrime(long n){
        if(n <= 1) return false;
        if(n == 2) return true;

        for(long i = 3 ; i <= Math.sqrt(n); i += 2){
            if(n % i == 0) return false;
        }

        return true;
    }
}
