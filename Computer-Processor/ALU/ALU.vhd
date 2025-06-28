----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    14:06:53 08/11/2024 
-- Design Name: 
-- Module Name:    ALU - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL ;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity ALU is
port(
data1: in STD_LOGIC_VECTOR(31 downto 0);
data2: in STD_LOGIC_VECTOR(31 downto 0);
aluop: in STD_LOGIC_VECTOR(3 downto 0);
dataout: out STD_LOGIC_VECTOR(31 downto 0);
zflag: out STD_LOGIC);

end ALU;

architecture Behavioral of ALU is
signal s,g,temp: STD_LOGIC_VECTOR (31 downto 0);


begin
s<= data1 - data2;
g <= data1+data2;
temp <=
(data1) and (data2) when aluop (3 downto 0) = "0000" else
 (data1) or  (data2) when aluop (3 downto 0) = "0001" else
g when aluop (3 downto 0) = "0010" else
s when aluop (3 downto 0) = "0110" else
 ("0000000000000000000000000000000" & S(31)) when aluop (3 downto 0) = "0111" else
not((data1) or (data2)) when aluop (3 downto 0) = "1100" else
x"00000000";

dataout <= std_logic_vector(temp);
 zflag <= '1' when temp= x"00000000" else
 '0'; 


end Behavioral;

