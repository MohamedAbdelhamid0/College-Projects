# Italian Alphabet Text Cipher

A C++ program that implements a simple text cipher based on the Italian alphabet. This cipher encrypts text using a shift cipher technique, making it an interesting way to learn how to manipulate strings and characters in C++.

---

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)


---

## Introduction

This program is a simple implementation of a cipher algorithm that uses the **Italian Alphabet** as the basis for encryption. The Italian alphabet consists of 21 letters (A, B, C, D, E, F, G, H, I, L, M, N, O, P, Q, R, S, T, U, V, Z) with certain letters excluded that are used in other languages but not native to Italian (like J, K, W, X, Y). <br> 
<br> 
Cipher Equation ---> C = ((Key1 * Index_of_Letter) + Key2) % Number_of_Alphabets <br>
Where: <br> 
***C*** is the new index of ciphered letter <br>
***Key1*** is the first key to be used <br>
***Index_of_Letter*** is the index of the current letter getting ciphered <br> 
***Key*** is the second key used <br> 
***Number_of_Alphabets*** is the number of letters in the alphabet used {which is 21 in the italian alphabet} <br> 


![image](https://github.com/user-attachments/assets/2b10f2e1-cacc-4b72-bed6-27a4769101a0)



---

## Features

- Encrypt messages using the Italian alphabet and a simple shift cipher.
- Support for customizable shift values.
- Handles both uppercase and lowercase characters.
- Decrypt functionality (Note: currently the decryption is not implemented but planned for future releases).

---

## Installation

To use the program, you can simply download the Main.cpp file and directly run the code or you can follow these steps:

1. Clone this repository:
   ```bash
   git clone https://github.com/MohamedAbdelhamid0/Ciphering.git


## Usage
1. After installing the Main.cpp file, read all comments in the code to understand it.
2. After running the code, you need to do 3 things: <br> 
   Enter text that will be ciphered <br>
   Enter key1 { Check [Introduction](#introduction) if you don't know this } <br>
   Enter key2 { Check [Introduction](#introduction) if you don't know this } <br>


---

## Examples

1. Encrypt the Italian sentence "Questi Dati Sono Classificati"
2. Assume Key1 = 7 and Key2 = 2

![image](https://github.com/user-attachments/assets/8a826cb1-c2c6-45b0-b690-cc144f4546f3)

![image](https://github.com/user-attachments/assets/616fc9a2-c588-4441-a0e4-b7d509129fa5)

![image](https://github.com/user-attachments/assets/77e12e33-a037-4e14-b110-7512375e9d4c)

![image](https://github.com/user-attachments/assets/d4b5bc5e-d9a5-4b4c-84fe-5e36b0aaf550)
