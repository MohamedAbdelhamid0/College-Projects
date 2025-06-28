----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    12:59:05 08/11/2024 
-- Design Name: 
-- Module Name:    flopr - Behavioral 
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

entity flopr is
    Port ( clk , rst : in  STD_LOGIC;
	        d : in  STD_LOGIC_VECTOR (31 downto 0);
           q : out  STD_LOGIC_VECTOR (31 downto 0);
           load : in  STD_LOGIC);
end flopr;

architecture Behavioral of flopr is

signal temp: STD_LOGIC_VECTOR (31 downto 0);

begin

process(rst,clk,load)
begin
if(rst='1') then temp<=(others=>'0');
elsif(clk'event and clk='1' and load='1') then temp <= d;
end if;
end process;
q<=temp;
end Behavioral;