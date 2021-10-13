import java.util.*;
public class Largest_Concatenated_Number{

    private static String largest(List<Integer> A) {
        ArrayList<String> arr = new ArrayList<>();
        for(int i: A) arr.add(String.valueOf(i));

        Collections.sort(arr, new Comparator<String>(){
            public int compare(String a, String b){
                return (b+a).compareTo(a+b);
            }
        });
        StringBuffer sb = new StringBuffer();

        for (String s : arr) {
            if(Integer.parseInt(s)!=0)
                sb.append(s);
        }
        if(sb.toString().equals(""))    return "0";
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 5
        List<Integer> li = new ArrayList<>();
        li.add(3);
        li.add(30);
        li.add(34);
        li.add(5);
        li.add(9);

        System.out.println(largest(li));
    }
}