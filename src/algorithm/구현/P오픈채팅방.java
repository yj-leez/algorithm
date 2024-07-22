package algorithm.구현;

import java.util.*;

public class P오픈채팅방 {
    public String[] solution(String[] record) {
        List<String> nickname = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i = record.length - 1; i >= 0; i--){
            String[] str = record[i].split(" ");
            if(str[0].equals("Enter") || str[0].equals("Change")){
                if(!map.containsKey(str[1])){
                    nickname.add(str[2]);
                    map.put(str[1], nickname.size() - 1);
                }
            }
        }

        List<String> answer = new ArrayList<>();
        for(int i = 0; i < record.length; i++){
            String[] str = record[i].split(" ");
            if(str[0].equals("Enter")){
                answer.add(nickname.get(map.get(str[1])) + "님이 들어왔습니다.");
            }
            if(str[0].equals("Leave")){
                answer.add(nickname.get(map.get(str[1])) + "님이 나갔습니다.");
            }
        }
        // 원래 answer.stream().toArray(String[]::new);로 했었는데 이것보다는 아래처럼 맨 처음에 개수를 정해줌으로써 더 최적화할 수 있다.
        String[] array = answer.toArray(new String[answer.size()]);
        return array;
    }
}