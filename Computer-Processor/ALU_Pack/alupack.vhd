--
--	Package File Template
--
--	Purpose: This package defines supplemental types, subtypes, 
--		 constants, and functions 
--
--   To use any of the example code shown below, uncomment the lines and modify as necessary
--

library IEEE;
use IEEE.STD_LOGIC_1164.all;
USE IEEE.NUMERIC_STD.ALL;


package alupack is

component ALU is
port(
data1: in STD_LOGIC_VECTOR(31 downto 0);
data2: in STD_LOGIC_VECTOR(31 downto 0);
aluop: in STD_LOGIC_VECTOR(3 downto 0);
dataout: out STD_LOGIC_VECTOR(31 downto 0);
zflag: out STD_LOGIC);

end component;

end alupack;

