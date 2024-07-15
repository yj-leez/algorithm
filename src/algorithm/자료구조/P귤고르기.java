package algorithm.자료구조;

import java.util.*;

public class P귤고르기 {
    public int solution(int k, int[] tangerine) {
        int[] nums = new int[10_000_001];
        for(int i = 0; i < tangerine.length; i++){
            nums[tangerine[i]]++;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        for(int i = 0; i < 10_000_001; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int answer = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
            for(int i = 0; i < entry.getValue(); i++){
                k -= entry.getKey();
                answer++;
                if(k <= 0) return answer;
            }
        }

        return answer;
    }
}
class Solution{
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int t : tangerine){
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((o1, o2) -> map.get(o2) - map.get(o1));
        for(Integer key: list){
            k -= map.get(key);
            answer++;
            if(k<=0){
                break;
            }
        }
        return answer;
    }
}