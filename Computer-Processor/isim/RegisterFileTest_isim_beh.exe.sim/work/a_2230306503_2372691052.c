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
static const char *ng0 = "C:/Users/Windows/Downloads/RegisterTest.vhd";
extern char *IEEE_P_3620187407;



static void work_a_2230306503_2372691052_p_0(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    int64 t7;
    int64 t8;

LAB0:    t1 = (t0 + 3592U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(61, ng0);
    t2 = (t0 + 4224);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    *((unsigned char *)t6) = (unsigned char)2;
    xsi_driver_first_trans_fast(t2);
    xsi_set_current_line(62, ng0);
    t2 = (t0 + 2608U);
    t3 = *((char **)t2);
    t7 = *((int64 *)t3);
    t8 = (t7 / 2);
    t2 = (t0 + 3400);
    xsi_process_wait(t2, t8);

LAB6:    *((char **)t1) = &&LAB7;

LAB1:    return;
LAB4:    xsi_set_current_line(63, ng0);
    t2 = (t0 + 4224);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    *((unsigned char *)t6) = (unsigned char)3;
    xsi_driver_first_trans_fast(t2);
    xsi_set_current_line(64, ng0);
    t2 = (t0 + 2608U);
    t3 = *((char **)t2);
    t7 = *((int64 *)t3);
    t8 = (t7 / 2);
    t2 = (t0 + 3400);
    xsi_process_wait(t2, t8);

LAB10:    *((char **)t1) = &&LAB11;
    goto LAB1;

LAB5:    goto LAB4;

LAB7:    goto LAB5;

LAB8:    goto LAB2;

LAB9:    goto LAB8;

LAB11:    goto LAB9;

}

static void work_a_2230306503_2372691052_p_1(char *t0)
{
    char t12[16];
    char *t1;
    char *t2;
    char *t3;
    int64 t4;
    int64 t5;
    int64 t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;
    char *t11;
    int t13;
    unsigned int t14;
    unsigned char t15;

LAB0:    t1 = (t0 + 3840U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(71, ng0);
    t2 = (t0 + 2608U);
    t3 = *((char **)t2);
    t4 = *((int64 *)t3);
    t5 = (3 * 1LL);
    t6 = (t4 - t5);
    t2 = (t0 + 3648);
    xsi_process_wait(t2, t6);

LAB6:    *((char **)t1) = &&LAB7;

LAB1:    return;
LAB4:    xsi_set_current_line(74, ng0);
    t2 = (t0 + 7156);
    t7 = (t0 + 4288);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    memcpy(t11, t2, 5U);
    xsi_driver_first_trans_fast(t7);
    xsi_set_current_line(75, ng0);
    t2 = (t0 + 7161);
    t7 = (t0 + 4352);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    memcpy(t11, t2, 32U);
    xsi_driver_first_trans_fast(t7);
    xsi_set_current_line(76, ng0);
    t2 = (t0 + 4416);
    t3 = (t2 + 56U);
    t7 = *((char **)t3);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    *((unsigned char *)t9) = (unsigned char)3;
    xsi_driver_first_trans_fast(t2);
    xsi_set_current_line(77, ng0);
    t2 = (t0 + 2608U);
    t3 = *((char **)t2);
    t4 = *((int64 *)t3);
    t5 = (t4 * 1);
    t2 = (t0 + 3648);
    xsi_process_wait(t2, t5);

LAB10:    *((char **)t1) = &&LAB11;
    goto LAB1;

LAB5:    goto LAB4;

LAB7:    goto LAB5;

LAB8:    xsi_set_current_line(80, ng0);
    t2 = (t0 + 7193);
    t7 = (t0 + 4288);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    memcpy(t11, t2, 5U);
    xsi_driver_first_trans_fast(t7);
    xsi_set_current_line(81, ng0);
    t2 = (t0 + 7198);
    t7 = (t0 + 4352);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    memcpy(t11, t2, 32U);
    xsi_driver_first_trans_fast(t7);
    xsi_set_current_line(82, ng0);
    t2 = (t0 + 4416);
    t3 = (t2 + 56U);
    t7 = *((char **)t3);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    *((unsigned char *)t9) = (unsigned char)3;
    xsi_driver_first_trans_fast(t2);
    xsi_set_current_line(83, ng0);
    t2 = (t0 + 2608U);
    t3 = *((char **)t2);
    t4 = *((int64 *)t3);
    t5 = (t4 * 1);
    t2 = (t0 + 3648);
    xsi_process_wait(t2, t5);

LAB14:    *((char **)t1) = &&LAB15;
    goto LAB1;

LAB9:    goto LAB8;

LAB11:    goto LAB9;

LAB12:    xsi_set_current_line(86, ng0);
    t2 = (t0 + 7230);
    t7 = (t0 + 4480);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    memcpy(t11, t2, 5U);
    xsi_driver_first_trans_fast(t7);
    xsi_set_current_line(87, ng0);
    t2 = (t0 + 7235);
    t7 = (t0 + 4544);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    memcpy(t11, t2, 5U);
    xsi_driver_first_trans_fast(t7);
    xsi_set_current_line(88, ng0);
    t2 = (t0 + 4416);
    t3 = (t2 + 56U);
    t7 = *((char **)t3);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    *((unsigned char *)t9) = (unsigned char)2;
    xsi_driver_first_trans_fast(t2);
    xsi_set_current_line(89, ng0);
    t2 = (t0 + 2608U);
    t3 = *((char **)t2);
    t4 = *((int64 *)t3);
    t5 = (t4 * 2);
    t2 = (t0 + 3648);
    xsi_process_wait(t2, t5);

LAB18:    *((char **)t1) = &&LAB19;
    goto LAB1;

LAB13:    goto LAB12;

LAB15:    goto LAB13;

LAB16:    xsi_set_current_line(91, ng0);
    t2 = (t0 + 7240);
    xsi_report(t2, 5U, 0);
    xsi_set_current_line(92, ng0);
    t2 = (t0 + 2152U);
    t3 = *((char **)t2);
    t2 = (t0 + 6988U);
    t7 = (t0 + 7245);
    t9 = (t12 + 0U);
    t10 = (t9 + 0U);
    *((int *)t10) = 0;
    t10 = (t9 + 4U);
    *((int *)t10) = 31;
    t10 = (t9 + 8U);
    *((int *)t10) = 1;
    t13 = (31 - 0);
    t14 = (t13 * 1);
    t14 = (t14 + 1);
    t10 = (t9 + 12U);
    *((unsigned int *)t10) = t14;
    t15 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t3, t2, t7, t12);
    if (t15 == 0)
        goto LAB20;

LAB21:    xsi_set_current_line(93, ng0);
    t2 = (t0 + 7283);
    xsi_report(t2, 5U, 0);
    xsi_set_current_line(94, ng0);
    t2 = (t0 + 2312U);
    t3 = *((char **)t2);
    t2 = (t0 + 7004U);
    t7 = (t0 + 7288);
    t9 = (t12 + 0U);
    t10 = (t9 + 0U);
    *((int *)t10) = 0;
    t10 = (t9 + 4U);
    *((int *)t10) = 31;
    t10 = (t9 + 8U);
    *((int *)t10) = 1;
    t13 = (31 - 0);
    t14 = (t13 * 1);
    t14 = (t14 + 1);
    t10 = (t9 + 12U);
    *((unsigned int *)t10) = t14;
    t15 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t3, t2, t7, t12);
    if (t15 == 0)
        goto LAB22;

LAB23:    xsi_set_current_line(96, ng0);
    t2 = (t0 + 2608U);
    t3 = *((char **)t2);
    t4 = *((int64 *)t3);
    t5 = (t4 * 1);
    t2 = (t0 + 3648);
    xsi_process_wait(t2, t5);

LAB26:    *((char **)t1) = &&LAB27;
    goto LAB1;

LAB17:    goto LAB16;

LAB19:    goto LAB17;

LAB20:    t10 = (t0 + 7277);
    xsi_report(t10, 6U, (unsigned char)2);
    goto LAB21;

LAB22:    t10 = (t0 + 7320);
    xsi_report(t10, 6U, (unsigned char)2);
    goto LAB23;

LAB24:    xsi_set_current_line(99, ng0);
    t2 = (t0 + 7326);
    t7 = (t0 + 4480);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    memcpy(t11, t2, 5U);
    xsi_driver_first_trans_fast(t7);
    xsi_set_current_line(100, ng0);
    t2 = (t0 + 7331);
    t7 = (t0 + 4544);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    memcpy(t11, t2, 5U);
    xsi_driver_first_trans_fast(t7);
    xsi_set_current_line(101, ng0);
    t2 = (t0 + 7336);
    t7 = (t0 + 4288);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    memcpy(t11, t2, 5U);
    xsi_driver_first_trans_fast(t7);
    xsi_set_current_line(102, ng0);
    t2 = (t0 + 7341);
    t7 = (t0 + 4352);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    t10 = (t9 + 56U);
    t11 = *((char **)t10);
    memcpy(t11, t2, 32U);
    xsi_driver_first_trans_fast(t7);
    xsi_set_current_line(103, ng0);
    t2 = (t0 + 4416);
    t3 = (t2 + 56U);
    t7 = *((char **)t3);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    *((unsigned char *)t9) = (unsigned char)3;
    xsi_driver_first_trans_fast(t2);
    xsi_set_current_line(105, ng0);
    t2 = (t0 + 2608U);
    t3 = *((char **)t2);
    t4 = *((int64 *)t3);
    t5 = (t4 * 2);
    t2 = (t0 + 3648);
    xsi_process_wait(t2, t5);

LAB30:    *((char **)t1) = &&LAB31;
    goto LAB1;

LAB25:    goto LAB24;

LAB27:    goto LAB25;

LAB28:    xsi_set_current_line(107, ng0);
    t2 = (t0 + 7373);
    xsi_report(t2, 5U, 0);
    xsi_set_current_line(108, ng0);
    t2 = (t0 + 2152U);
    t3 = *((char **)t2);
    t2 = (t0 + 6988U);
    t7 = (t0 + 7378);
    t9 = (t12 + 0U);
    t10 = (t9 + 0U);
    *((int *)t10) = 0;
    t10 = (t9 + 4U);
    *((int *)t10) = 31;
    t10 = (t9 + 8U);
    *((int *)t10) = 1;
    t13 = (31 - 0);
    t14 = (t13 * 1);
    t14 = (t14 + 1);
    t10 = (t9 + 12U);
    *((unsigned int *)t10) = t14;
    t15 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t3, t2, t7, t12);
    if (t15 == 0)
        goto LAB32;

LAB33:    xsi_set_current_line(109, ng0);
    t2 = (t0 + 7416);
    xsi_report(t2, 5U, 0);
    xsi_set_current_line(110, ng0);
    t2 = (t0 + 2312U);
    t3 = *((char **)t2);
    t2 = (t0 + 7004U);
    t7 = (t0 + 7421);
    t9 = (t12 + 0U);
    t10 = (t9 + 0U);
    *((int *)t10) = 0;
    t10 = (t9 + 4U);
    *((int *)t10) = 31;
    t10 = (t9 + 8U);
    *((int *)t10) = 1;
    t13 = (31 - 0);
    t14 = (t13 * 1);
    t14 = (t14 + 1);
    t10 = (t9 + 12U);
    *((unsigned int *)t10) = t14;
    t15 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t3, t2, t7, t12);
    if (t15 == 0)
        goto LAB34;

LAB35:    xsi_set_current_line(112, ng0);
    t2 = (t0 + 7459);
    xsi_report(t2, 13U, 0);
    xsi_set_current_line(113, ng0);

LAB38:    *((char **)t1) = &&LAB39;
    goto LAB1;

LAB29:    goto LAB28;

LAB31:    goto LAB29;

LAB32:    t10 = (t0 + 7410);
    xsi_report(t10, 6U, (unsigned char)2);
    goto LAB33;

LAB34:    t10 = (t0 + 7453);
    xsi_report(t10, 6U, (unsigned char)2);
    goto LAB35;

LAB36:    goto LAB2;

LAB37:    goto LAB36;

LAB39:    goto LAB37;

}


extern void work_a_2230306503_2372691052_init()
{
	static char *pe[] = {(void *)work_a_2230306503_2372691052_p_0,(void *)work_a_2230306503_2372691052_p_1};
	xsi_register_didat("work_a_2230306503_2372691052", "isim/RegisterFileTest_isim_beh.exe.sim/work/a_2230306503_2372691052.didat");
	xsi_register_executes(pe);
}
