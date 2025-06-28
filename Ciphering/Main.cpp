#include <iostream>
#include <string>
using namespace std;

/*
Error Handling in This Program :

1) If the user enters a small alphabet, it will be automatically changed to capital letter
using the (toupper(x))

2) Check of coprime {between Key1 and Key2}
examples of the coprime check:

-----------------------------------------
key1 = any alphabet -----> print error
-----------------------------------------
key1 = 20 -----> print error

and so on.
*/

/*
Italian alphabet is slightly different than english alphabet so we will make string
that has the italian alphabet shown below in "string alpha"
*/ 
string alpha = " ABCDEFGHILMNOPQRSTUVZ";


int find_index(char x) {
    x = toupper(x);
    for (int i = 0; i < 22; i++) { 
        if (x == alpha[i]) {
            return i;
        }
    }
    return -1;
}

/*
Explanation of the find_index function
---> This function will take a parameter (char(x)) and then inside the function, we
will keep looping on the global string called "alpha". While looping, we will keep comparing
the passed character to "each" letter in the string. As soon as character x == letter in string,
we will return the index. This index will be used in the cipher operation

---> If character was not found, the return will be "-1" which means this character is not 
avaliable in italian alphabet
*/


char find_letter(int x) {
    if (x >= 0 && x < 22) { 
        return alpha[x];
    }
    return '?';
}

/*
The function "find_letter" is responsible for finding a specific character with a parameter
interger x. When we pass integer x to the function, the function will get alpha[x]. For example:
when we pass integer (5), we will get the letter (E).
*/


int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

bool is_valid_key(int key) {
    return gcd(key, 22) == 1;
}

string cipher(string x, int key1, int key2) {
    string result = "";
    for (int i = 0; i < x.size(); i++) {
        char temp1 = x[i];
        int temp2 = find_index(temp1);

        if (temp2 == -1) { 
            result += temp1;
            continue;
        }

        int temp3 = ((key1 * temp2) + key2) % 22; 
        if (temp3 < 0) {
            temp3 += 22;
        }

        char temp4 = find_letter(temp3);
        result += temp4;
    }
    return result;
}

/*
---> In the cipher function, it takes 3 parameters (i- string that will be cipherd) 
(ii- key1) and (iii- key2).

---> Ciphering (C) works with a specific function that is:
C = ((Key1 * Index_of_Current_Letter) + key2) % number of alphabets (which is 22 in our case)

---> now that we have a number stored in "C", we want to know what is current letter. So we will
take the number stored in C and send it to the function "find_letter". This function will take
the value {index} stored in C and then returns the corrsesponding letter.

---> The returned letter will be the new letter 
for example, if you enter "Q" , the new letter will be "U" 
*/


int main() {
    cout<< endl << endl;
    cout<< "Welcome to our ciphering program" << endl;
    cout<< "This program uses the Italian alphabet" << endl << endl;
    string x;
    int key1, key2;
    cout << "Enter the string that should be ciphered:" << endl;
    getline(cin, x);

    cout << "Enter the first key (A):" << endl;
    cin >> key1;
    if (!is_valid_key(key1)) {
        cout << "Error: Key1 must be coprime with 22. Please enter a valid key." << endl;
        return 1;
    }

    cout << "Enter the second key (B):" << endl;
    cin >> key2;
    if ((key2 < 0 || key2 >= 22) ) {
        cout << "Error: Key2 must be between 0 and 21 inclusive." << endl;
        return 1;
    }

    cout << endl;
    cout << "The Encrypted Text Will Be:" << cipher(x, key1, key2) << endl;

}
