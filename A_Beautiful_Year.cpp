/*---------------------------------------------------------------------------------------------------------*/
    // It seems like the year of 2013 came only yesterday. Do you know a curious fact? The year of 2013
    //  is the first year after the old 1987 with only distinct digits.
    // Now you are suggested to solve the following problem: given a year number, find the minimum year number 
    // which is strictly larger than the given one and has only distinct digits.

    //Input
        // The single line contains integer y (1000 ≤ y ≤ 9000) — the year number.

    // Output
        // Print a single integer — the minimum year number that is strictly larger than y and all it's 
        // digits are distinct. It is guaranteed that the answer exists.
/*---------------------------------------------------------------------------------------------------------*/

#include<iostream>
#include<vector>
#include<map>
#include<list>
#include<set>
#include<algorithm>
#include<queue>
#include<stack>
#include<cstring>
#include<cmath>
#include<string>
#include<unordered_set>
#include<unordered_map>
#include<cstdlib>
using namespace std;

int main(){
    int year;
    cin >> year;
    while(true){
        year = year + 1;
        int a = year/1000; //first digit
        int b = year/100 % 10; //second digit
        int c = year/10 %10; // third digit
        int d = year %10; // last digit

        if(a != b && a!= c && a!= d && b!=c && b!=d && c!=d){
            cout << year;
            return 0;
        }
    }
}