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


package registerpack is

component RegisterFile is
port( 
read_sel1:in STD_LOGIC_VECTOR(4 downto 0);
read_sel2: in STD_LOGIC_VECTOR(4 downto 0);
write_sel: in STD_LOGIC_VECTOR(4 downto 0);
write_ena: in STD_LOGIC;
clk: in STD_LOGIC;
write_data: in STD_LOGIC_VECTOR(31 downto 0);
data1: out STD_LOGIC_VECTOR(31 downto 0);
data2: out STD_LOGIC_VECTOR(31 downto 0);
rst: in STD_LOGIC);

end component;

end registerpack;

