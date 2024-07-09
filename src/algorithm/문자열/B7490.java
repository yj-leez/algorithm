package algorithm.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B7490 {
    static Set<Integer> set;
    static int maxValue;
    static Map<Integer, List<String>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        set = new HashSet<>();
        map = new HashMap<>();
        for (int i = 0; i < T; i++) {
            int tmp = Integer.parseInt(br.readLine());
            set.add(tmp);
            maxValue = Math.max(maxValue, tmp);
            map.put(tmp, new ArrayList<>());
        }

        dfs("+1+", 2);
        dfs("+1-", 2);
        dfs("+1 ", 2);

        for(List<String> list: map.values()){
            Collections.sort(list); // ASCII 코드 순으로 정렬
            for(String s: list){
                System.out.println(s.substring(1)); // 맨 앞 임의로 넣어놨던 '+' 제거
            }
            System.out.println();
        }

    }

    static void dfs(String s, int n){
        if(set.contains(n)){
            // 만약 계산 결과 값이 0이라면 해당 N을 키로 가지고 있는 배열에 문자열을 추가한다.
            if(calculate(s + n) == 0) map.computeIfAbsent(n, k -> new ArrayList<>()).add(s + n);
        }

        if (n == maxValue) return;

        dfs(s+n+"+", n+1);
        dfs(s+n+"-", n+1);
        dfs(s+n+" ", n+1);

    }

    static int calculate(String s){
        int ans = 0;
        int tmp = 0;
        for(int i = 1; i < s.length(); i++){
            if(i % 2 == 1 && (i == s.length() - 1 || s.charAt(i + 1) != ' ')){
                // 바로 뒤 기호가 ' '이 아니라면 현재까지의 값을 계산한다.
                int symbolIdx = i - 1;
                if (tmp != 0){
                    symbolIdx = i - (2 * String.valueOf(tmp).length() + 1);
                }

                // 임시 저장한 값이 나오기 전의 기호로 값을 계산한다.
                switch (s.charAt(symbolIdx)){
                    case '+':
                        ans += (tmp * 10 + Character.getNumericValue(s.charAt(i))); break;
                    case '-':
                        ans -= (tmp * 10 + Character.getNumericValue(s.charAt(i))); break;
                }

                tmp = 0;
            } else if(i % 2 == 1 && s.charAt(i + 1) == ' '){
                // 바로 뒤 기호가 ' '이라면 tmp에 값을 임시 저장한다.
                tmp = tmp * 10 + Character.getNumericValue(s.charAt(i));
            }
        }

        return ans;
    }
}
