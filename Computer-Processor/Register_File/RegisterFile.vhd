library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
USE IEEE.NUMERIC_STD.ALL;

use work.muxpack.all;
use work.decoderpack.all;
use work.floprpack.all;


entity RegisterFile is
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

end RegisterFile;


architecture Behavioral of RegisterFile is

-- signal y3ny selk fa lw 3ayzeen newasal kol reg 3la decoder yb2a 32 reg = 32 selk
-- ba3daha 3ayzeen newasal selk mn el decoder 3la el write
-- 32 register 3la mux yb2a 32 selk lel mux


signal d0, d1, d2, d3, d4, d5, d6, d7, d8, d9,d10,d11,d12,d13,d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d25,d26,d27,d28,d29,d30,d31: STD_LOGIC;  

Signal reg0,reg1,reg2,reg3,reg4,reg5,reg6,reg7,reg8,reg9,reg10,reg11,reg12,reg13,reg14,reg15,reg16,reg17,reg18,reg19,reg20,reg21,reg22,reg23,reg24,reg25,reg26,reg27,reg28,reg29,reg30,reg31: STD_LOGIC_vector(31 downto 0);

Signal wrt0,wrt1,wrt2,wrt3,wrt4,wrt5,wrt6,wrt7,wrt8,wrt9,wrt10,wrt11,wrt12,wrt13,wrt14,wrt15,wrt16,wrt17,wrt18,wrt19,wrt20,wrt21,wrt22,wrt23,wrt24,wrt25,wrt26,wrt27,wrt28,wrt29,wrt30,wrt31: STD_LOGIC;


begin

-- lazm n3ml 32 register (kol register 32 bit) 
--  input lel flopr ---> clock , reset , input , output , load (34an n3raf lw hanektb fel register)
-- talama fe input fel flopr yb2a lazm el awl ngeeb el wrt0 wrt1 w hakaza
-- wrt0 y3ny hal hanektb fel reg 0? n3raf ezay, ---> hana5od el write enable el m3ana w nda5alha 3la and m3 el selk el tale3 mn decoder0 , lw 1 yb2a hanektb fel register 0 w lw 0 yb2a m4 hanektb w hakaza


decoder0: decoder port map ( write_sel ,d0, d1, d2, d3, d4, d5, d6, d7, d8, d9,d10,d11,d12,d13,d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d25,d26,d27,d28,d29,d30,d31);

wrt0 <= write_ena and d0;
wrt1 <= write_ena and d1;
wrt2 <= write_ena and d2;
wrt3 <= write_ena and d3;
wrt4 <= write_ena and d4;
wrt5 <= write_ena and d5;
wrt6 <= write_ena and d6;
wrt7 <= write_ena and d7;
wrt8 <= write_ena and d8;
wrt9 <= write_ena and d9;
wrt10 <= write_ena and d10;
wrt11 <= write_ena and d11;
wrt12 <= write_ena and d12;
wrt13 <= write_ena and d13;
wrt14 <= write_ena and d14;
wrt15 <= write_ena and d15;
wrt16 <= write_ena and d16;
wrt17 <= write_ena and d17;
wrt18 <= write_ena and d18;
wrt19 <= write_ena and d19;
wrt20 <= write_ena and d20;
wrt21 <= write_ena and d21;
wrt22 <= write_ena and d22;
wrt23 <= write_ena and d23;
wrt24 <= write_ena and d24;
wrt25 <= write_ena and d25;
wrt26 <= write_ena and d26;
wrt27 <= write_ena and d27;
wrt28 <= write_ena and d28;
wrt29 <= write_ena and d29;
wrt30 <= write_ena and d30;
wrt31 <= write_ena and d31;

--  input lel flopr ---> clock , reset , input , output , load (34an n3raf lw hanektb fel register)


flopr0: flopr  port map (clk,rst, write_data, reg0,wrt0);
flopr1: flopr  port map (clk,rst, write_data, reg1,wrt1);
flopr2: flopr  port map (clk,rst, write_data, reg2,wrt2);
flopr3: flopr  port map (clk,rst, write_data, reg3,wrt3);
flopr4: flopr  port map (clk,rst, write_data, reg4,wrt4);
flopr5: flopr  port map (clk,rst, write_data, reg5,wrt5);
flopr6: flopr  port map (clk,rst, write_data, reg6,wrt6);
flopr7: flopr  port map (clk,rst, write_data, reg7,wrt7);
flopr8: flopr  port map (clk,rst, write_data, reg8,wrt8);
flopr9: flopr  port map (clk,rst, write_data, reg9,wrt9);
flopr10: flopr port map (clk,rst, write_data, reg10,wrt10);
flopr11: flopr port map (clk,rst, write_data, reg11,wrt11);
flopr12: flopr port map (clk,rst, write_data, reg12,wrt12);
flopr13: flopr port map (clk,rst, write_data, reg13,wrt13);
flopr14: flopr port map (clk,rst, write_data, reg14,wrt14);
flopr15: flopr port map (clk,rst, write_data, reg15,wrt15);
flopr16: flopr port map (clk,rst, write_data, reg16,wrt16);
flopr17: flopr port map (clk,rst, write_data, reg17,wrt17);
flopr18: flopr port map (clk,rst, write_data, reg18,wrt18);
flopr19: flopr port map (clk,rst, write_data, reg19,wrt19);
flopr20: flopr port map (clk,rst, write_data, reg20,wrt20);
flopr21: flopr port map (clk,rst, write_data, reg21,wrt21);
flopr22: flopr port map (clk,rst, write_data, reg22,wrt22);
flopr23: flopr port map (clk,rst, write_data, reg23,wrt23);
flopr24: flopr port map (clk,rst, write_data, reg24,wrt24);
flopr25: flopr port map (clk,rst, write_data, reg25,wrt25);
flopr26: flopr port map (clk,rst, write_data, reg26,wrt26);
flopr27: flopr port map (clk,rst, write_data, reg27,wrt27);
flopr28: flopr port map (clk,rst, write_data, reg28,wrt28);
flopr29: flopr port map (clk,rst, write_data, reg29,wrt29);
flopr30: flopr port map (clk,rst, write_data, reg30,wrt30);
flopr31: flopr port map (clk,rst, write_data, reg31,wrt31);

-- matensa4 en el reg0 de signal (el selka el mewasala mn el output bta3 el register lel mux fa de el hanb3atha lel mux)


mux0: Mux32 generic map (32) port map (reg0,reg1,reg2,reg3,reg4,reg5,reg6,reg7,reg8,reg9,reg10,reg11,reg12,reg13,reg14,reg15,reg16,reg17,reg18,reg19,reg20,reg21,reg22,reg23,reg24,reg25,reg26,reg27,reg28,reg29,reg30,reg31,read_sel1,data1);

mux1: Mux32 generic map (32) port map (reg0,reg1,reg2,reg3,reg4,reg5,reg6,reg7,reg8,reg9,reg10,reg11,reg12,reg13,reg14,reg15,reg16,reg17,reg18,reg19,reg20,reg21,reg22,reg23,reg24,reg25,reg26,reg27,reg28,reg29,reg30,reg31,read_sel2,data2);
end Behavioral;