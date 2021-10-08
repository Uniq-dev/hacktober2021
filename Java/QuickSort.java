import java.util.Arrays;
public class QuickSortInJava
{
   int partition(int[] arrNumbers, int low, int high)
   {
      int pivot = arrNumbers[high];
      // smaller element index
      int a = (low - 1);
      for(int b = low; b < high; b++)
      {
         // if current element is smaller than the pivot
         if(arrNumbers[b] < pivot)
         {
            a++;
            // swap arrNumbers[a] and arrNumbers[b]
            int temp = arrNumbers[a];
            arrNumbers[a] = arrNumbers[b];
            arrNumbers[b] = temp;
         }
      }
      // swap arrNumbers[a + 1] and arrNumbers[high] or pivot
      int temp = arrNumbers[a + 1];
      arrNumbers[a + 1] = arrNumbers[high];
      arrNumbers[high] = temp;
      return a + 1;
   }
   void sort(int[] arrNumbers, int low, int high)
   {
      if (low < high)
      {
         int p = partition(arrNumbers, low, high);
         /* recursive sort elements before
         partition and after partition */
         sort(arrNumbers, low, p - 1);
         sort(arrNumbers, p + 1, high);
      }
   }
   static void displayArray(int[] arrNumbers)
   {
      int s = arrNumbers.length;
      for(int a = 0; a < s; ++a)
         System.out.print(arrNumbers[a] + " ");
      System.out.println();
   }
   public static void main(String[] args)
   {
      int[] arrNumbers = {59, 74, 85, 67, 56, 29, 68, 34};
      int s = arrNumbers.length;
      QuickSortInJava obj = new QuickSortInJava();
      obj.sort(arrNumbers, 0, s - 1);
      System.out.println("After sorting array: ");
      displayArray(arrNumbers);
   }
}