import java.io.PrintStream;
import java.util.*;   

interface Insertele
{
    void insert(int ele, int n, int capacity, int[] array);
}

interface Sortarr
{
    void sort(int n, int[] array);
}

interface Displayarr
{
    void display(int n, int[] array);
}

class ins_sort implements Insertele, Sortarr, Displayarr
{
    PrintStream out = System.out;
    public void insert(int ele, int n, int capacity, int[] array)
    {
        if (n <= capacity) 
        { 
            int i;
            for (i = n - 1; (i >= 0 && array[i] > ele); i--) 
                array[i + 1] = array[i]; 
    
            array[i + 1] = ele; 
        }
    }

    public void sort(int n, int[] array)
    {
        Arrays.sort(array, 0,n);
    }

    public void display(int n, int[] array)
    {
        out.print("Elements of array: ");  
        for (int i = 0; i < n; i++)   
        {       
            out.print(array[i]);   
        }
    }
}
public class insert_sort 
{
    public static void main(String[] args)   
    {   
        PrintStream out = System.out;
        Scanner scanner = new Scanner(System.in);

        int[] array = new int[20];  
        int n;
        int ele;
        int capacity=array.length;

        out.print("Enter the number of elements you want to store: ");  
        n=scanner.nextInt(); 
        
        out.print("Enter the elements of the array: ");  
        for(int i=0; i<n; i++)  
        {  
            array[i]=scanner.nextInt();  
        }  
        ins_sort obj= new ins_sort();
        obj.sort(n, array);
        out.print("Elements of sorted array: ");
        obj.display(n, array);

        out.print("Enter the element you want to insert: ");  
        ele=scanner.nextInt();         
        obj.insert(ele, n, capacity, array);
        n=n+1;
        obj.display(n, array);
    } 
}
