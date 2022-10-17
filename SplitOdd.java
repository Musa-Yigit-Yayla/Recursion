import java.util.ArrayList;

public class SplitOdd {
    public boolean helper(int start, ArrayList<Integer> list1, ArrayList<Integer> list2, int[] nums){
        
        if(list1.size() + list2.size() == nums.length){
            return checkConditions(list1, list2); //base case
        }
        //either include the start in list1 or list2 recursively
        ArrayList<Integer> list1Clone = listCopier(list1);
        ArrayList<Integer> list2Clone = listCopier(list2);
        int current = nums[start];

        list1Clone.add(current);
        list2Clone.add(current);
        if(helper(start + 1, list1Clone, list2, nums)){
            return true;
        }
        if(helper(start + 1, list2Clone, list1, nums)){
            return true;
        }
        return false;
    }
    public boolean checkConditions(ArrayList<Integer> list1, ArrayList<Integer> list2){
        int sum1 = 0, sum2 = 0;
        for(int i = 0; i < list1.size(); i++){
            sum1 += list1.get(i);
        }
        for(int i = 0; i < list2.size(); i++){
            sum2 += list2.get(i);
        }
        return (sum1 % 10 == 0 && sum2 % 2 == 1) || (sum1 % 10 == 0 && sum2 % 2 == 1);
    }
    public ArrayList<Integer> listCopier(ArrayList<Integer> list){
        ArrayList<Integer> clone = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            int curr = list.get(i);
            clone.add(curr);
        }
        return clone;
    }

    //OTHER PROGRAM
    //Split array
    public boolean helper2(int start, ArrayList<Integer> list1, ArrayList<Integer> list2, int[] nums){
        if(getSum(list1) == getSum(list2) && (list1.size() + list2.size()) == nums.length){
            return true;
        }
        if(start < nums.length){
            ArrayList<Integer> list1Clone = listCopier(list1);
        ArrayList<Integer> list2Clone = listCopier(list2);
        int current = nums[start];

        list1Clone.add(current);
        list2Clone.add(current);

            if(helper(start + 1, list1Clone, list2, nums)){
                return true;
            }
            if(helper(start + 1, list1, list2Clone, nums)){
                return true;
            }
        }
        return false;
    }
    public int getSum(ArrayList<Integer> list){
        int sum = 0;
        for(Integer i: list){
            sum += i;
        }
        return sum;
    }
}
