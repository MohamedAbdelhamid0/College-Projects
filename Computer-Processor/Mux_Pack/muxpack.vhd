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


package muxpack is

component Mux32 is
Generic (n: integer := 32 );
PORT(I0 ,I1 ,I2 ,I3 ,I4 ,I5 ,I6 ,I7 ,I8 ,I9 ,I10 ,I11 ,I12 ,I13 ,I14 ,I15 ,I16 ,I17 ,I18 ,I19 ,I20 ,I21 ,I22 ,I23 ,I24 ,I25 ,I26 ,I27 ,I28 ,I29 ,I30 ,I31 : IN STD_LOGIC_VECTOR (n-1 DOWNTO 0);
S:IN STD_LOGIC_VECTOR (4 DOWNTO 0);
y:OUT STD_LOGIC_VECTOR (n-1 DOWNTO 0));
end component;

end muxpack;

