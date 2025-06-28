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


package floprpack is

component flopr is
    Port ( clk , rst : in  STD_LOGIC;
	        d : in  STD_LOGIC_VECTOR (31 downto 0);
           q : out  STD_LOGIC_VECTOR (31 downto 0);
           load : in  STD_LOGIC);
end component;

end floprpack;

