package algorithm.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2607 {
    static String key;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        key = br.readLine();


        String[] words = new String[N - 1];
        for (int i = 0; i < N - 1; i++) {
            words[i] = br.readLine();
        }

        int ans = 0;
        for (int i = 0; i < N - 1; i++) {
            System.out.println(words[i]);
            if (isSimilar(words[i])){
                ans++;
            }
        }

        System.out.println(ans);
    }

    static boolean isSimilar(String str){
        int[] chars = new int[26];
        for (int i = 0; i < str.length(); i++) {
            chars[str.charAt(i) - 'A']++;
        }

        int cnt = 0; // 같은 단어 개수
        for (int i = 0; i < key.length(); i++) {
            if (chars[key.charAt(i) - 'A'] > 0){
                System.out.println(key.charAt(i));
                cnt++;
                chars[key.charAt(i) - 'A']--;
            }
        }


        if (str.length() == key.length() && (key.length() == cnt || key.length() == cnt + 1)){
            // 비교 단어와 key 단어의 길이가 일치하고, 비교 단어의 모든 글자와 key 단어의 모든 글자가 일치하거나 한 글자만 다른 경우
            return true;
        } else if (str.length() + 1 == key.length() && str.length() == cnt) {
            // 비교 단어가 한 글자 더 적고, 비교 단어의 모든 글자가 key 단어에 다 있는 경우
            return true;
        } else if (str.length() == key.length() + 1 && str.length() - 1 == cnt) {
            // 비교 단어가 한 글자 더 많고, key 단어의 모든 글자가 비교 단어에 다 있는 경우
            return true;
        }

        return false;
    }
}
