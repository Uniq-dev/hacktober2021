import java.util.Arrays;
public class searchIn2DArray {
    public static void main(String[] args) {
        int[][] arr = {
                        {1, 2, 3, 4},
                        {5, 6},
                        {7, 8, 9}
                      };
        int[] res = search(arr , 4);
        System.out.println(Arrays.toString(res));
                    }
    static int[] search(int[][] arr, int target){

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                if(target == arr[row][col]){
                    return new int[]{row ,col};
                }
            }
          
        }
        return new int[]{-1,-1};
    }
}
