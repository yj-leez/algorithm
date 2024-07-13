package algorithm.slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * string을 순회하면서 알파벳의 위치(인덱스)를 배열에 저장한다.
 * 만약 알파벳 a의 위치를 담은 배열이 arr = {1,3,4,6}이고, k=2라면 arr[i+1] - a[i]가 문자를 k개 포함한 문자열의 길이가 된다.
 * 여기서 문자를 k개 포함한 문자열의 길이의 최솟값과 최댓값을 구한다.
 */
public class B20437 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());

            // 계산
            List<Integer>[] idxList = new List[26]; // 알파벳이 몇 번 인덱스에 있는지 확인하기 위한 List 배열
            Set<Integer> cnts = new HashSet<>(); // 문자의 개수가 k 이상인 알파벳들의 집합
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < w.length(); j++) {
                int idx = w.charAt(j) - 'a';

                if (idxList[idx] == null) idxList[idx] = new ArrayList<>();

                idxList[idx].add(j);
                if (idxList[idx] != null && idxList[idx].size() >= k){
                    cnts.add(idx);
                }
            }

            // 문자의 개수가 k 이상인 알파벳이 있을 때
            if (cnts.size() > 0){
                for(int n: cnts){
                    for (int j = 0; j <= idxList[n].size() - k; j++) {
                        int tmp = idxList[n].get(j + k - 1)- idxList[n].get(j) + 1; // k개인 두 인덱스의 차이

                        if (tmp < min) min = tmp;
                        if (tmp > max) max = tmp;
                    }
                }
                sb.append(min + " " + max + "\n");
            } else {
                // 문자의 개수가 k 이상인 알파벳이 없을 때
                sb.append(-1 + "\n");
            }
        }
        System.out.println(sb);
    }

}
/**
 * 문자열의 각 알파벳 개수만 카운트하고, 문자를 k개 포함한 문자열의 길이는 직접 순회하면서 확인한다.
 * 위의 방법보다 메모리를 더 적게 사용하지만, 시간은 조금 더 걸린다.
 */
class Solution{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());

            if(k == 1) { //k가 1일때
                sb.append("1 1\n");
                continue;
            }

            int[] alpha = new int[26];
            for (int j = 0; j < w.length(); j++) {
                alpha[w.charAt(j) - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = -1;
            for (int j = 0; j < w.length(); j++) {
                if (alpha[w.charAt(j) - 'a'] < k) continue;

                int cnt = 1;
                for (int l = j + 1; l < w.length(); l++) {
                    if (w.charAt(j) == w.charAt(l)) cnt++;

                    if (cnt == k){
                        min = Math.min(min, l - j + 1);
                        max = Math.max(max, l - j + 1);
                        break;
                    }
                }
            }
            if(min == Integer.MAX_VALUE || max == -1) sb.append("-1\n");
            else sb.append(min + " " + max + "\n");
        }
        System.out.println(sb);
    }
}
