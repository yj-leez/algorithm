package algorithm.이진검색트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class B7662 {

    static String[] str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            str = new String[k];

            for (int j = 0; j < k; j++) {
                str[j] = br.readLine();
            }

            sb.append(calculate() + "\n");
        }

        System.out.println(sb);
    }

    static String calculate(){
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < str.length; i++) {
            int num = Integer.parseInt(str[i].substring(2));
            if (str[i].charAt(0) == 'I'){
                map.put(num, map.getOrDefault(num, 0) + 1);
            } else if (str[i].charAt(0) == 'D'){
                if (map.size() == 0) continue;
                int key;

                if (num == -1){
                    key = map.firstKey(); // 최솟값
                } else {
                    key = map.lastKey(); // 최댓값
                }

                map.put(key, map.get(key) - 1);
                if (map.get(key) == 0){
                    map.remove(key);
                }
            }
        }

        if (map.isEmpty()){
            return "EMPTY";
        }

        return String.valueOf(map.lastKey()) + " " + String.valueOf(map.firstKey());

    }

}
