public class orderAgonistic {
    public static void main(String[] args) {
        int[] arr ={59, 45 , 39, 35,18,0,-6};
        int target = 0;
        int ans= searchAgonistic(arr, target);
        System.out.println(ans);
    }
    static int searchAgonistic(int[] arr , int target){
        int start =0;
        int end = arr.length-1;
        
        boolean bool = arr[start] < arr[end];
       
         
        while(start<=end){

            int mid = start + (end- start)/2;
            if(arr[mid] == target){
                 return mid;
            }
            if(bool){
                if(arr[mid]<target){
                    start= mid+1;
                }else{
                    end = mid-1;
                }

            }else{
                if(arr[mid]<target){
                    end = mid-1;
                }else{
                    start = mid+1;
                }
            }
        }
        return -1;

    }
}
