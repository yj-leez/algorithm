package algorithm.투포인터;

class P연속된부분수열의합 {
    public static int[] solution(int[] sequence, int k) {
        int left = 0;
        int right = left;
        int[] min = new int[]{0, sequence.length - 1};
        int sum = 0;

        while(left < sequence.length){
            if(sum < k && right < sequence.length){
                sum += sequence[right];
                right++;
            } else if(sum > k){
                sum -= sequence[left];
                left++;
            } else if (sum == k){
                if(min[1] - min[0] > right-1 - left){
                    min[1] = right-1;
                    min[0] = left;
                }

                sum -= sequence[left];
                left++;
                if(left > right) break;
            } else {
                break;
            }

        }

        return min;
    }
}
