# ALU-Circuit
The following project is a ***2 inputs --- 3 bits **Signed** ALU Cicruit*** made on Logisim. We have 2 inputs **A**and **B**, each input has 3 bits (Xs -- X1 -- X0). 
Xs ---> The sign input of either A or B.
X1 ---> Bit 1 of input A or B.
X0 ---> Bit 0 of input A or B.

# Operations Avaliable:
1) Addition
2) Subtraction
3) Multiplication
4) Increment of Input "A"
5) More Operations will be added soon.


# How it Works
## Addition and Subtraction Module
![image](https://github.com/user-attachments/assets/ab3442cc-9502-4021-8add-04e80768200f)
### 1) Signed to 2s comp Module
![image](https://github.com/user-attachments/assets/a743a400-9f4b-4fc6-b6f9-1b71b80efe05)
This module will convert the signed inputs to 2s complement so that we can perform subtraction operation
1 + 3  === 1 + **2's complement**(3) ---> two complement of +ve number is equal the same number.

1 - 3 === 1 + **2's complement**(3) ---> two complement of -ve number is **not** equal the same number.

### 2) Controlled 4 Bits Adder
![image](https://github.com/user-attachments/assets/329fba3e-4516-4f15-9b24-283e86d1d4b3)

### 3) 2's Complement to Signed

After finishing the previous operation, we need to revert back to signed input.

![image](https://github.com/user-attachments/assets/6e06345c-4da6-45d4-a156-5439fdc02432)

--------------------------------------------------------------------------

## Multiplication Module

![image](https://github.com/user-attachments/assets/e3e67e95-45d1-4462-ac02-317864a72987)

### 1) 2 Bits Multiplier
![image](https://github.com/user-attachments/assets/c64d8104-191a-49c9-bc70-ff1734d0e809)

### 2) Sign Decider
![image](https://github.com/user-attachments/assets/3ab0327d-25d6-48dc-8270-8a1cdd158ac2)

--------------------------------------------------------------------------

## Increment Module
![image](https://github.com/user-attachments/assets/881ed447-d249-44c1-aa6a-4ac449daa2cb)


-----------------------------------------------------------------
# END OF EXPLANATION
-----------------------------------------------------------------

# Final Circuit of All Modules:


![image](https://github.com/user-attachments/assets/516c0da8-8e9f-44d7-a96e-31d2c3de4aeb)































