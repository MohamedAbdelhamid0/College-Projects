----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:13:07 08/11/2024 
-- Design Name: 
-- Module Name:    datapath - Behavioral 
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
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.NUMERIC_STD.ALL;
use work.alupack.all;
use work.registerpack.all;


entity datapath is
port(
clk,reset: in STD_LOGIC;
instr: in STD_LOGIC_VECTOR(31 downto 0);
aluoperation: in STD_LOGIC_VECTOR(3 downto 0);
zero: out STD_LOGIC;
regwrite: in STD_LOGIC;
aluout: buffer STD_LOGIC_VECTOR(31 downto 0));
end datapath;

architecture Behavioral of datapath is

signal writereg: std_logic_vector(4 downto 0);
signal write_data_signal, result , data2s , tempaluout , data1s, writedata: STD_LOGIC_VECTOR(31 downto 0);

begin

reg0: RegisterFile port map(
clk => clk,
rst => reset,
		read_sel1 => instr(25 downto 21), 
		read_sel2 => instr(20 downto 16), 
		write_sel => writereg,
		write_ena => regwrite,
		write_data => write_data_signal,
		data1 => data1s,
		data2 => data2s

);


alu1: ALU port map (data1s,data2s,aluoperation,tempaluout,zero);

writedata <= data2s;
aluout <= tempaluout;






end Behavioral;

