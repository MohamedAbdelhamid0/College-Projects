----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    12:57:18 08/11/2024 
-- Design Name: 
-- Module Name:    decoder - Behavioral 
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
USE IEEE.NUMERIC_STD.ALL;


-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity decoder is
 Port ( s : in  STD_LOGIC_VECTOR (4 downto 0);
			  l0: out STD_LOGIC;
			  l1: out STD_LOGIC;
			  l2: out STD_LOGIC;
			  l3: out STD_LOGIC;
			  l4: out STD_LOGIC;
			  l5: out STD_LOGIC;
			  l6: out STD_LOGIC;
			  l7: out STD_LOGIC;
			  l8: out STD_LOGIC;
			  l9: out STD_LOGIC;
			  l10: out STD_LOGIC;
			  l11: out STD_LOGIC;
			  l12: out STD_LOGIC;
			  l13: out STD_LOGIC;
			  l14: out STD_LOGIC;
			  l15: out STD_LOGIC;
			  l16: out STD_LOGIC;
			  l17: out STD_LOGIC;
			  l18: out STD_LOGIC;
			  l19: out STD_LOGIC;
			  l20: out STD_LOGIC;
			  l21: out STD_LOGIC;
			  l22: out STD_LOGIC;
			  l23: out STD_LOGIC;
			  l24: out STD_LOGIC;
			  l25: out STD_LOGIC;
			  l26: out STD_LOGIC;
			  l27: out STD_LOGIC;
			  l28: out STD_LOGIC;
			  l29: out STD_LOGIC;
			  l30: out STD_LOGIC;
			  l31: out STD_LOGIC );	  

end decoder;

architecture Behavioral of decoder is

begin
l0 <= '1' when s = "00000" ELSE
	'0';
l1 <= '1' when s = "00001" ELSE
	'0';
l2 <= '1' when s = "00010" ELSE
	'0';
l3 <= '1' when s = "00011" ELSE
	'0';
l4 <= '1' when s = "00100" ELSE
	'0';
l5 <= '1' when s = "00101" ELSE
	'0';
l6 <= '1' when s = "00110" ELSE
	'0';
l7 <= '1' when s = "00111" ELSE
	'0';
l8 <= '1' when s = "01000" ELSE
	'0';
l9 <= '1' when s = "01001" ELSE
	'0';
l10 <= '1' when s = "01010" ELSE
	'0';
l11 <= '1' when s = "01011" ELSE
	'0';
l12 <= '1' when s = "01100" ELSE
	'0';
l13 <= '1' when s = "01101" ELSE
	'0';
l14 <= '1' when s = "01110" ELSE
	'0';
l15 <= '1' when s = "01111" ELSE
	'0';
l16 <= '1' when s = "10000" ELSE
	'0';
l17 <= '1' when s = "10001" ELSE
	'0';
l18 <= '1' when s = "10010" ELSE
	'0';
l19 <= '1' when s = "10011" ELSE
	'0';
l20 <= '1' when s = "10100" ELSE
	'0';
l21 <= '1' when s = "10101" ELSE
	'0';
l22 <= '1' when s = "10110" ELSE
	'0';
l23 <= '1' when s = "10111" ELSE
	'0';
l24 <= '1' when s = "11000" ELSE
	'0';
l25 <= '1' when s = "11001" ELSE
	'0';
l26 <= '1' when s = "11010" ELSE
	'0';
l27 <= '1' when s = "11011" ELSE
	'0';
l28 <= '1' when s = "11100" ELSE
	'0';
l29 <= '1' when s = "11101" ELSE
	'0';
l30 <= '1' when s = "11110" ELSE
	'0';
l31 <= '1' when s = "11111" ELSE
	'0';

end Behavioral;