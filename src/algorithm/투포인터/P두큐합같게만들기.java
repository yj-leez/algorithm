package algorithm.투포인터;

public class P두큐합같게만들기 {

    static int solution(int[] queue1, int[] queue2) {
        // 두 큐 합치기
        long sum = 0;
        int[] q = new int[queue1.length + queue2.length];
        for(int i = 0; i < queue1.length; i++){
            q[i] = queue1[i];
            sum += queue1[i];
        }
        long queue1Sum = sum;
        for(int j = 0; j < queue2.length; j++){
            q[queue1.length + j] = queue2[j];
            sum += queue2[j];
        }

        // 투 포인터로 합이 절반이 되는 부분이 있는지 확인
        if(sum % 2 != 0) return -1;
        sum /= 2;

        int left = 0;
        int right = queue1.length;
        int cnt = 0;
        while(true){
            if(cnt > queue1.length * 3) return -1;

            if(queue1Sum == sum){
                break;
            } else if(queue1Sum < sum){
                queue1Sum += q[right++];
                if(right >= q.length) right = 0;
                cnt++;
            } else if(queue1Sum > sum){
                queue1Sum -= q[left++];
                cnt++;
            }
        }

        return cnt;
    }
}