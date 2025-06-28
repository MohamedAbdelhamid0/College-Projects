/**********************************************************************/
/*   ____  ____                                                       */
/*  /   /\/   /                                                       */
/* /___/  \  /                                                        */
/* \   \   \/                                                       */
/*  \   \        Copyright (c) 2003-2009 Xilinx, Inc.                */
/*  /   /          All Right Reserved.                                 */
/* /---/   /\                                                         */
/* \   \  /  \                                                      */
/*  \___\/\___\                                                    */
/***********************************************************************/

/* This file is designed for use with ISim build 0x7708f090 */

#define XSI_HIDE_SYMBOL_SPEC true
#include "xsi.h"
#include <memory.h>
#ifdef __GNUC__
#include <stdlib.h>
#else
#include <malloc.h>
#define alloca _alloca
#endif
static const char *ng0 = "C:/Users/Windows/Downloads/ALUTest-2.vhd";



static void work_a_1599699025_2372691052_p_0(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;
    int64 t9;
    unsigned char t10;
    unsigned char t11;
    unsigned int t12;
    unsigned char t13;
    unsigned char t14;

LAB0:    t1 = (t0 + 2832U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(60, ng0);
    t2 = (t0 + 5465);
    t4 = (t0 + 3216);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(61, ng0);
    t2 = (t0 + 5497);
    t4 = (t0 + 3280);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(62, ng0);
    t2 = (t0 + 5529);
    t4 = (t0 + 3344);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 4U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(63, ng0);
    t9 = (10 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB6:    *((char **)t1) = &&LAB7;

LAB1:    return;
LAB4:    xsi_set_current_line(64, ng0);
    t2 = (t0 + 5533);
    xsi_report(t2, 5U, 0);
    xsi_set_current_line(65, ng0);
    t2 = (t0 + 1512U);
    t3 = *((char **)t2);
    t2 = (t0 + 5538);
    t11 = 1;
    if (32U == 32U)
        goto LAB13;

LAB14:    t11 = 0;

LAB15:    if (t11 == 1)
        goto LAB10;

LAB11:    t10 = (unsigned char)0;

LAB12:    if (t10 == 0)
        goto LAB8;

LAB9:    xsi_set_current_line(67, ng0);
    t9 = (1 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB21:    *((char **)t1) = &&LAB22;
    goto LAB1;

LAB5:    goto LAB4;

LAB7:    goto LAB5;

LAB8:    t7 = (t0 + 5570);
    xsi_report(t7, 6U, (unsigned char)2);
    goto LAB9;

LAB10:    t7 = (t0 + 1672U);
    t8 = *((char **)t7);
    t13 = *((unsigned char *)t8);
    t14 = (t13 == (unsigned char)2);
    t10 = t14;
    goto LAB12;

LAB13:    t12 = 0;

LAB16:    if (t12 < 32U)
        goto LAB17;
    else
        goto LAB15;

LAB17:    t5 = (t3 + t12);
    t6 = (t2 + t12);
    if (*((unsigned char *)t5) != *((unsigned char *)t6))
        goto LAB14;

LAB18:    t12 = (t12 + 1);
    goto LAB16;

LAB19:    xsi_set_current_line(70, ng0);
    t2 = (t0 + 5576);
    t4 = (t0 + 3216);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(71, ng0);
    t2 = (t0 + 5608);
    t4 = (t0 + 3280);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(72, ng0);
    t2 = (t0 + 5640);
    t4 = (t0 + 3344);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 4U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(73, ng0);
    t9 = (10 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB25:    *((char **)t1) = &&LAB26;
    goto LAB1;

LAB20:    goto LAB19;

LAB22:    goto LAB20;

LAB23:    xsi_set_current_line(74, ng0);
    t2 = (t0 + 5644);
    xsi_report(t2, 5U, 0);
    xsi_set_current_line(75, ng0);
    t2 = (t0 + 1512U);
    t3 = *((char **)t2);
    t2 = (t0 + 5649);
    t11 = 1;
    if (32U == 32U)
        goto LAB32;

LAB33:    t11 = 0;

LAB34:    if (t11 == 1)
        goto LAB29;

LAB30:    t10 = (unsigned char)0;

LAB31:    if (t10 == 0)
        goto LAB27;

LAB28:    xsi_set_current_line(77, ng0);
    t9 = (1 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB40:    *((char **)t1) = &&LAB41;
    goto LAB1;

LAB24:    goto LAB23;

LAB26:    goto LAB24;

LAB27:    t7 = (t0 + 5681);
    xsi_report(t7, 6U, (unsigned char)2);
    goto LAB28;

LAB29:    t7 = (t0 + 1672U);
    t8 = *((char **)t7);
    t13 = *((unsigned char *)t8);
    t14 = (t13 == (unsigned char)2);
    t10 = t14;
    goto LAB31;

LAB32:    t12 = 0;

LAB35:    if (t12 < 32U)
        goto LAB36;
    else
        goto LAB34;

LAB36:    t5 = (t3 + t12);
    t6 = (t2 + t12);
    if (*((unsigned char *)t5) != *((unsigned char *)t6))
        goto LAB33;

LAB37:    t12 = (t12 + 1);
    goto LAB35;

LAB38:    xsi_set_current_line(80, ng0);
    t2 = (t0 + 5687);
    t4 = (t0 + 3216);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(81, ng0);
    t2 = (t0 + 5719);
    t4 = (t0 + 3280);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(82, ng0);
    t2 = (t0 + 5751);
    t4 = (t0 + 3344);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 4U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(83, ng0);
    t9 = (10 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB44:    *((char **)t1) = &&LAB45;
    goto LAB1;

LAB39:    goto LAB38;

LAB41:    goto LAB39;

LAB42:    xsi_set_current_line(84, ng0);
    t2 = (t0 + 5755);
    xsi_report(t2, 5U, 0);
    xsi_set_current_line(85, ng0);
    t2 = (t0 + 1512U);
    t3 = *((char **)t2);
    t2 = (t0 + 5760);
    t11 = 1;
    if (32U == 32U)
        goto LAB51;

LAB52:    t11 = 0;

LAB53:    if (t11 == 1)
        goto LAB48;

LAB49:    t10 = (unsigned char)0;

LAB50:    if (t10 == 0)
        goto LAB46;

LAB47:    xsi_set_current_line(87, ng0);
    t9 = (1 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB59:    *((char **)t1) = &&LAB60;
    goto LAB1;

LAB43:    goto LAB42;

LAB45:    goto LAB43;

LAB46:    t7 = (t0 + 5792);
    xsi_report(t7, 6U, (unsigned char)2);
    goto LAB47;

LAB48:    t7 = (t0 + 1672U);
    t8 = *((char **)t7);
    t13 = *((unsigned char *)t8);
    t14 = (t13 == (unsigned char)2);
    t10 = t14;
    goto LAB50;

LAB51:    t12 = 0;

LAB54:    if (t12 < 32U)
        goto LAB55;
    else
        goto LAB53;

LAB55:    t5 = (t3 + t12);
    t6 = (t2 + t12);
    if (*((unsigned char *)t5) != *((unsigned char *)t6))
        goto LAB52;

LAB56:    t12 = (t12 + 1);
    goto LAB54;

LAB57:    xsi_set_current_line(90, ng0);
    t2 = (t0 + 5798);
    t4 = (t0 + 3216);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(91, ng0);
    t2 = (t0 + 5830);
    t4 = (t0 + 3280);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(92, ng0);
    t2 = (t0 + 5862);
    t4 = (t0 + 3344);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 4U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(93, ng0);
    t9 = (10 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB63:    *((char **)t1) = &&LAB64;
    goto LAB1;

LAB58:    goto LAB57;

LAB60:    goto LAB58;

LAB61:    xsi_set_current_line(94, ng0);
    t2 = (t0 + 5866);
    xsi_report(t2, 5U, 0);
    xsi_set_current_line(95, ng0);
    t2 = (t0 + 1512U);
    t3 = *((char **)t2);
    t2 = (t0 + 5871);
    t11 = 1;
    if (32U == 32U)
        goto LAB70;

LAB71:    t11 = 0;

LAB72:    if (t11 == 1)
        goto LAB67;

LAB68:    t10 = (unsigned char)0;

LAB69:    if (t10 == 0)
        goto LAB65;

LAB66:    xsi_set_current_line(97, ng0);
    t9 = (1 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB78:    *((char **)t1) = &&LAB79;
    goto LAB1;

LAB62:    goto LAB61;

LAB64:    goto LAB62;

LAB65:    t7 = (t0 + 5903);
    xsi_report(t7, 6U, (unsigned char)2);
    goto LAB66;

LAB67:    t7 = (t0 + 1672U);
    t8 = *((char **)t7);
    t13 = *((unsigned char *)t8);
    t14 = (t13 == (unsigned char)3);
    t10 = t14;
    goto LAB69;

LAB70:    t12 = 0;

LAB73:    if (t12 < 32U)
        goto LAB74;
    else
        goto LAB72;

LAB74:    t5 = (t3 + t12);
    t6 = (t2 + t12);
    if (*((unsigned char *)t5) != *((unsigned char *)t6))
        goto LAB71;

LAB75:    t12 = (t12 + 1);
    goto LAB73;

LAB76:    xsi_set_current_line(100, ng0);
    t2 = (t0 + 5909);
    t4 = (t0 + 3216);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(101, ng0);
    t2 = (t0 + 5941);
    t4 = (t0 + 3280);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(103, ng0);
    t2 = (t0 + 5973);
    t4 = (t0 + 3344);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 4U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(104, ng0);
    t9 = (10 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB82:    *((char **)t1) = &&LAB83;
    goto LAB1;

LAB77:    goto LAB76;

LAB79:    goto LAB77;

LAB80:    xsi_set_current_line(105, ng0);
    t2 = (t0 + 5977);
    xsi_report(t2, 5U, 0);
    xsi_set_current_line(106, ng0);
    t2 = (t0 + 1512U);
    t3 = *((char **)t2);
    t2 = (t0 + 5982);
    t11 = 1;
    if (32U == 32U)
        goto LAB89;

LAB90:    t11 = 0;

LAB91:    if (t11 == 1)
        goto LAB86;

LAB87:    t10 = (unsigned char)0;

LAB88:    if (t10 == 0)
        goto LAB84;

LAB85:    xsi_set_current_line(108, ng0);
    t9 = (1 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB97:    *((char **)t1) = &&LAB98;
    goto LAB1;

LAB81:    goto LAB80;

LAB83:    goto LAB81;

LAB84:    t7 = (t0 + 6014);
    xsi_report(t7, 6U, (unsigned char)2);
    goto LAB85;

LAB86:    t7 = (t0 + 1672U);
    t8 = *((char **)t7);
    t13 = *((unsigned char *)t8);
    t14 = (t13 == (unsigned char)2);
    t10 = t14;
    goto LAB88;

LAB89:    t12 = 0;

LAB92:    if (t12 < 32U)
        goto LAB93;
    else
        goto LAB91;

LAB93:    t5 = (t3 + t12);
    t6 = (t2 + t12);
    if (*((unsigned char *)t5) != *((unsigned char *)t6))
        goto LAB90;

LAB94:    t12 = (t12 + 1);
    goto LAB92;

LAB95:    xsi_set_current_line(111, ng0);
    t2 = (t0 + 6020);
    t4 = (t0 + 3216);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(112, ng0);
    t2 = (t0 + 6052);
    t4 = (t0 + 3280);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 32U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(114, ng0);
    t2 = (t0 + 6084);
    t4 = (t0 + 3344);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    t7 = (t6 + 56U);
    t8 = *((char **)t7);
    memcpy(t8, t2, 4U);
    xsi_driver_first_trans_fast(t4);
    xsi_set_current_line(115, ng0);
    t9 = (10 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB101:    *((char **)t1) = &&LAB102;
    goto LAB1;

LAB96:    goto LAB95;

LAB98:    goto LAB96;

LAB99:    xsi_set_current_line(116, ng0);
    t2 = (t0 + 6088);
    xsi_report(t2, 5U, 0);
    xsi_set_current_line(117, ng0);
    t2 = (t0 + 1512U);
    t3 = *((char **)t2);
    t2 = (t0 + 6093);
    t11 = 1;
    if (32U == 32U)
        goto LAB108;

LAB109:    t11 = 0;

LAB110:    if (t11 == 1)
        goto LAB105;

LAB106:    t10 = (unsigned char)0;

LAB107:    if (t10 == 0)
        goto LAB103;

LAB104:    xsi_set_current_line(119, ng0);
    t9 = (1 * 1000LL);
    t2 = (t0 + 2640);
    xsi_process_wait(t2, t9);

LAB116:    *((char **)t1) = &&LAB117;
    goto LAB1;

LAB100:    goto LAB99;

LAB102:    goto LAB100;

LAB103:    t7 = (t0 + 6125);
    xsi_report(t7, 6U, (unsigned char)2);
    goto LAB104;

LAB105:    t7 = (t0 + 1672U);
    t8 = *((char **)t7);
    t13 = *((unsigned char *)t8);
    t14 = (t13 == (unsigned char)2);
    t10 = t14;
    goto LAB107;

LAB108:    t12 = 0;

LAB111:    if (t12 < 32U)
        goto LAB112;
    else
        goto LAB110;

LAB112:    t5 = (t3 + t12);
    t6 = (t2 + t12);
    if (*((unsigned char *)t5) != *((unsigned char *)t6))
        goto LAB109;

LAB113:    t12 = (t12 + 1);
    goto LAB111;

LAB114:    xsi_set_current_line(121, ng0);
    t2 = (t0 + 6131);
    xsi_report(t2, 13U, 0);
    xsi_set_current_line(122, ng0);

LAB120:    *((char **)t1) = &&LAB121;
    goto LAB1;

LAB115:    goto LAB114;

LAB117:    goto LAB115;

LAB118:    goto LAB2;

LAB119:    goto LAB118;

LAB121:    goto LAB119;

}


extern void work_a_1599699025_2372691052_init()
{
	static char *pe[] = {(void *)work_a_1599699025_2372691052_p_0};
	xsi_register_didat("work_a_1599699025_2372691052", "isim/ALUTest_isim_beh.exe.sim/work/a_1599699025_2372691052.didat");
	xsi_register_executes(pe);
}
