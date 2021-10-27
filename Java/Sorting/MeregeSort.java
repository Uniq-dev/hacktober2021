import java.util.Scanner;

public class MergeSort {
    static int pass = 1;

    static void dis(int[] a){

        for(int element:a){
            System.out.print(element + "  ");
        }
    }
    static void merge_sort(int[] a, int left, int right){
        if(left<right) {
            int mid = (left + right) / 2;
            merge_sort(a, left, mid);
            merge_sort(a, (mid + 1), right);
            merge(a, left, mid, right);
            System.out.println("\nMerge " + pass++ + ": ");
            dis(a);
        }
    }

    static void merge(int[] a, int left, int mid, int right){
        int[] temp = new int[right + 1 - left];
        int i = left; int j = mid +1;
        int index = 0;
        while(i<=mid && j<= right){
            if(a[i]<a[j]){
                temp[index++] = a[i++];
            }else{
                temp[index++] = a[j++];
            }
        }

        while(i<=mid){
            temp[index++] = a[i++];
        }
        while(j<=right){
            temp[index++] = a[j++];
        }

        for(int i1 = left;i1<=right;i1++){
            a[i1] = temp[i1-left];
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Give Size of array");
        int n = sc.nextInt();

        int[] a = new int[n];
        System.out.println("Give Array");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        merge_sort(a,0,n-1);

        System.out.println("\nArray after sorting");
        dis(a);
    }

}
