package algorithm.구현;

import java.util.*;

public class P캐시 {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        if(cacheSize == 0){
            return cities.length * 5;
        }

        for(int i = 0; i < cities.length; i++){
            String city = cities[i].toLowerCase();
            if(set.size() < cacheSize){
                if(set.contains(city)){
                    list.remove(city);
                    list.add(city);
                    answer++;
                } else {
                    list.add(city);
                    set.add(city);
                    answer += 5;
                }
            } else {

                if(set.contains(city)){
                    list.remove(city);
                    list.add(city);
                    answer++;
                } else {
                    set.remove(list.get(0));
                    set.add(city);
                    list.remove(0);
                    list.add(city);
                    answer += 5;
                }

            }
        }

        return answer;
    }
}
