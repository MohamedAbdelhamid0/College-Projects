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
static const char *ng0 = "C:/Users/Windows/Desktop/CO Final Project/Project/ALU.vhd";
extern char *IEEE_P_3620187407;
extern char *IEEE_P_2592010699;

char *ieee_p_2592010699_sub_1735675855_503743352(char *, char *, char *, char *, char *, char *);
char *ieee_p_2592010699_sub_1837678034_503743352(char *, char *, char *, char *);
char *ieee_p_2592010699_sub_795620321_503743352(char *, char *, char *, char *, char *, char *);
char *ieee_p_3620187407_sub_767668596_3965413181(char *, char *, char *, char *, char *, char *);
char *ieee_p_3620187407_sub_767740470_3965413181(char *, char *, char *, char *, char *, char *);


static void work_a_0832606739_3212880686_p_0(char *t0)
{
    char t1[16];
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    unsigned int t8;
    unsigned int t9;
    unsigned char t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    char *t16;

LAB0:    xsi_set_current_line(48, ng0);

LAB3:    t2 = (t0 + 1032U);
    t3 = *((char **)t2);
    t2 = (t0 + 7256U);
    t4 = (t0 + 1192U);
    t5 = *((char **)t4);
    t4 = (t0 + 7272U);
    t6 = ieee_p_3620187407_sub_767740470_3965413181(IEEE_P_3620187407, t1, t3, t2, t5, t4);
    t7 = (t1 + 12U);
    t8 = *((unsigned int *)t7);
    t9 = (1U * t8);
    t10 = (32U != t9);
    if (t10 == 1)
        goto LAB5;

LAB6:    t11 = (t0 + 4768);
    t12 = (t11 + 56U);
    t13 = *((char **)t12);
    t14 = (t13 + 56U);
    t15 = *((char **)t14);
    memcpy(t15, t6, 32U);
    xsi_driver_first_trans_fast(t11);

LAB2:    t16 = (t0 + 4624);
    *((int *)t16) = 1;

LAB1:    return;
LAB4:    goto LAB2;

LAB5:    xsi_size_not_matching(32U, t9, 0);
    goto LAB6;

}

static void work_a_0832606739_3212880686_p_1(char *t0)
{
    char t1[16];
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    unsigned int t8;
    unsigned int t9;
    unsigned char t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    char *t16;

LAB0:    xsi_set_current_line(49, ng0);

LAB3:    t2 = (t0 + 1032U);
    t3 = *((char **)t2);
    t2 = (t0 + 7256U);
    t4 = (t0 + 1192U);
    t5 = *((char **)t4);
    t4 = (t0 + 7272U);
    t6 = ieee_p_3620187407_sub_767668596_3965413181(IEEE_P_3620187407, t1, t3, t2, t5, t4);
    t7 = (t1 + 12U);
    t8 = *((unsigned int *)t7);
    t9 = (1U * t8);
    t10 = (32U != t9);
    if (t10 == 1)
        goto LAB5;

LAB6:    t11 = (t0 + 4832);
    t12 = (t11 + 56U);
    t13 = *((char **)t12);
    t14 = (t13 + 56U);
    t15 = *((char **)t14);
    memcpy(t15, t6, 32U);
    xsi_driver_first_trans_fast(t11);

LAB2:    t16 = (t0 + 4640);
    *((int *)t16) = 1;

LAB1:    return;
LAB4:    goto LAB2;

LAB5:    xsi_size_not_matching(32U, t9, 0);
    goto LAB6;

}

static void work_a_0832606739_3212880686_p_2(char *t0)
{
    char t6[16];
    char t12[16];
    char t17[16];
    char t35[16];
    char t41[16];
    char t46[16];
    char t64[16];
    char t70[16];
    char t84[16];
    char t90[16];
    char t104[16];
    char t110[16];
    char t123[16];
    char t125[16];
    char t140[16];
    char t146[16];
    char t151[16];
    char t152[16];
    char *t1;
    char *t2;
    unsigned int t3;
    unsigned int t4;
    unsigned int t5;
    char *t7;
    char *t8;
    int t9;
    unsigned int t10;
    char *t13;
    char *t14;
    int t15;
    unsigned char t16;
    char *t18;
    char *t19;
    char *t20;
    char *t21;
    char *t22;
    unsigned int t23;
    unsigned char t24;
    char *t25;
    char *t26;
    char *t27;
    char *t28;
    char *t29;
    char *t30;
    char *t31;
    unsigned int t32;
    unsigned int t33;
    unsigned int t34;
    char *t36;
    char *t37;
    int t38;
    unsigned int t39;
    char *t42;
    char *t43;
    int t44;
    unsigned char t45;
    char *t47;
    char *t48;
    char *t49;
    char *t50;
    char *t51;
    unsigned int t52;
    unsigned char t53;
    char *t54;
    char *t55;
    char *t56;
    char *t57;
    char *t58;
    char *t59;
    char *t60;
    unsigned int t61;
    unsigned int t62;
    unsigned int t63;
    char *t65;
    char *t66;
    int t67;
    unsigned int t68;
    char *t71;
    char *t72;
    int t73;
    unsigned char t74;
    char *t75;
    char *t76;
    char *t77;
    char *t78;
    char *t79;
    char *t80;
    char *t81;
    unsigned int t82;
    unsigned int t83;
    char *t85;
    char *t86;
    int t87;
    unsigned int t88;
    char *t91;
    char *t92;
    int t93;
    unsigned char t94;
    char *t95;
    char *t96;
    char *t97;
    char *t98;
    char *t99;
    char *t100;
    char *t101;
    unsigned int t102;
    unsigned int t103;
    char *t105;
    char *t106;
    int t107;
    unsigned int t108;
    char *t111;
    char *t112;
    int t113;
    unsigned char t114;
    char *t116;
    char *t117;
    int t118;
    unsigned int t119;
    unsigned int t120;
    unsigned char t121;
    char *t122;
    char *t124;
    char *t126;
    char *t127;
    int t128;
    unsigned int t129;
    unsigned char t130;
    char *t131;
    char *t132;
    char *t133;
    char *t134;
    char *t135;
    char *t136;
    unsigned int t137;
    unsigned int t138;
    unsigned int t139;
    char *t141;
    char *t142;
    int t143;
    unsigned int t144;
    char *t147;
    char *t148;
    int t149;
    unsigned char t150;
    char *t153;
    char *t154;
    char *t155;
    char *t156;
    char *t157;
    char *t158;
    unsigned int t159;
    unsigned char t160;
    char *t161;
    char *t162;
    char *t163;
    char *t164;
    char *t165;
    char *t166;
    char *t168;
    char *t169;
    char *t170;
    char *t171;
    char *t172;
    char *t173;

LAB0:    xsi_set_current_line(50, ng0);
    t1 = (t0 + 1352U);
    t2 = *((char **)t1);
    t3 = (3 - 3);
    t4 = (t3 * 1U);
    t5 = (0 + t4);
    t1 = (t2 + t5);
    t7 = (t6 + 0U);
    t8 = (t7 + 0U);
    *((int *)t8) = 3;
    t8 = (t7 + 4U);
    *((int *)t8) = 0;
    t8 = (t7 + 8U);
    *((int *)t8) = -1;
    t9 = (0 - 3);
    t10 = (t9 * -1);
    t10 = (t10 + 1);
    t8 = (t7 + 12U);
    *((unsigned int *)t8) = t10;
    t8 = (t0 + 7508);
    t13 = (t12 + 0U);
    t14 = (t13 + 0U);
    *((int *)t14) = 0;
    t14 = (t13 + 4U);
    *((int *)t14) = 3;
    t14 = (t13 + 8U);
    *((int *)t14) = 1;
    t15 = (3 - 0);
    t10 = (t15 * 1);
    t10 = (t10 + 1);
    t14 = (t13 + 12U);
    *((unsigned int *)t14) = t10;
    t16 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t1, t6, t8, t12);
    if (t16 != 0)
        goto LAB3;

LAB4:    t30 = (t0 + 1352U);
    t31 = *((char **)t30);
    t32 = (3 - 3);
    t33 = (t32 * 1U);
    t34 = (0 + t33);
    t30 = (t31 + t34);
    t36 = (t35 + 0U);
    t37 = (t36 + 0U);
    *((int *)t37) = 3;
    t37 = (t36 + 4U);
    *((int *)t37) = 0;
    t37 = (t36 + 8U);
    *((int *)t37) = -1;
    t38 = (0 - 3);
    t39 = (t38 * -1);
    t39 = (t39 + 1);
    t37 = (t36 + 12U);
    *((unsigned int *)t37) = t39;
    t37 = (t0 + 7512);
    t42 = (t41 + 0U);
    t43 = (t42 + 0U);
    *((int *)t43) = 0;
    t43 = (t42 + 4U);
    *((int *)t43) = 3;
    t43 = (t42 + 8U);
    *((int *)t43) = 1;
    t44 = (3 - 0);
    t39 = (t44 * 1);
    t39 = (t39 + 1);
    t43 = (t42 + 12U);
    *((unsigned int *)t43) = t39;
    t45 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t30, t35, t37, t41);
    if (t45 != 0)
        goto LAB7;

LAB8:    t59 = (t0 + 1352U);
    t60 = *((char **)t59);
    t61 = (3 - 3);
    t62 = (t61 * 1U);
    t63 = (0 + t62);
    t59 = (t60 + t63);
    t65 = (t64 + 0U);
    t66 = (t65 + 0U);
    *((int *)t66) = 3;
    t66 = (t65 + 4U);
    *((int *)t66) = 0;
    t66 = (t65 + 8U);
    *((int *)t66) = -1;
    t67 = (0 - 3);
    t68 = (t67 * -1);
    t68 = (t68 + 1);
    t66 = (t65 + 12U);
    *((unsigned int *)t66) = t68;
    t66 = (t0 + 7516);
    t71 = (t70 + 0U);
    t72 = (t71 + 0U);
    *((int *)t72) = 0;
    t72 = (t71 + 4U);
    *((int *)t72) = 3;
    t72 = (t71 + 8U);
    *((int *)t72) = 1;
    t73 = (3 - 0);
    t68 = (t73 * 1);
    t68 = (t68 + 1);
    t72 = (t71 + 12U);
    *((unsigned int *)t72) = t68;
    t74 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t59, t64, t66, t70);
    if (t74 != 0)
        goto LAB11;

LAB12:    t80 = (t0 + 1352U);
    t81 = *((char **)t80);
    t68 = (3 - 3);
    t82 = (t68 * 1U);
    t83 = (0 + t82);
    t80 = (t81 + t83);
    t85 = (t84 + 0U);
    t86 = (t85 + 0U);
    *((int *)t86) = 3;
    t86 = (t85 + 4U);
    *((int *)t86) = 0;
    t86 = (t85 + 8U);
    *((int *)t86) = -1;
    t87 = (0 - 3);
    t88 = (t87 * -1);
    t88 = (t88 + 1);
    t86 = (t85 + 12U);
    *((unsigned int *)t86) = t88;
    t86 = (t0 + 7520);
    t91 = (t90 + 0U);
    t92 = (t91 + 0U);
    *((int *)t92) = 0;
    t92 = (t91 + 4U);
    *((int *)t92) = 3;
    t92 = (t91 + 8U);
    *((int *)t92) = 1;
    t93 = (3 - 0);
    t88 = (t93 * 1);
    t88 = (t88 + 1);
    t92 = (t91 + 12U);
    *((unsigned int *)t92) = t88;
    t94 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t80, t84, t86, t90);
    if (t94 != 0)
        goto LAB13;

LAB14:    t100 = (t0 + 1352U);
    t101 = *((char **)t100);
    t88 = (3 - 3);
    t102 = (t88 * 1U);
    t103 = (0 + t102);
    t100 = (t101 + t103);
    t105 = (t104 + 0U);
    t106 = (t105 + 0U);
    *((int *)t106) = 3;
    t106 = (t105 + 4U);
    *((int *)t106) = 0;
    t106 = (t105 + 8U);
    *((int *)t106) = -1;
    t107 = (0 - 3);
    t108 = (t107 * -1);
    t108 = (t108 + 1);
    t106 = (t105 + 12U);
    *((unsigned int *)t106) = t108;
    t106 = (t0 + 7524);
    t111 = (t110 + 0U);
    t112 = (t111 + 0U);
    *((int *)t112) = 0;
    t112 = (t111 + 4U);
    *((int *)t112) = 3;
    t112 = (t111 + 8U);
    *((int *)t112) = 1;
    t113 = (3 - 0);
    t108 = (t113 * 1);
    t108 = (t108 + 1);
    t112 = (t111 + 12U);
    *((unsigned int *)t112) = t108;
    t114 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t100, t104, t106, t110);
    if (t114 != 0)
        goto LAB15;

LAB16:    t135 = (t0 + 1352U);
    t136 = *((char **)t135);
    t137 = (3 - 3);
    t138 = (t137 * 1U);
    t139 = (0 + t138);
    t135 = (t136 + t139);
    t141 = (t140 + 0U);
    t142 = (t141 + 0U);
    *((int *)t142) = 3;
    t142 = (t141 + 4U);
    *((int *)t142) = 0;
    t142 = (t141 + 8U);
    *((int *)t142) = -1;
    t143 = (0 - 3);
    t144 = (t143 * -1);
    t144 = (t144 + 1);
    t142 = (t141 + 12U);
    *((unsigned int *)t142) = t144;
    t142 = (t0 + 7559);
    t147 = (t146 + 0U);
    t148 = (t147 + 0U);
    *((int *)t148) = 0;
    t148 = (t147 + 4U);
    *((int *)t148) = 3;
    t148 = (t147 + 8U);
    *((int *)t148) = 1;
    t149 = (3 - 0);
    t144 = (t149 * 1);
    t144 = (t144 + 1);
    t148 = (t147 + 12U);
    *((unsigned int *)t148) = t144;
    t150 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t135, t140, t142, t146);
    if (t150 != 0)
        goto LAB19;

LAB20:
LAB23:    t166 = (t0 + 7563);
    t168 = (t0 + 4896);
    t169 = (t168 + 56U);
    t170 = *((char **)t169);
    t171 = (t170 + 56U);
    t172 = *((char **)t171);
    memcpy(t172, t166, 32U);
    xsi_driver_first_trans_fast(t168);

LAB2:    t173 = (t0 + 4656);
    *((int *)t173) = 1;

LAB1:    return;
LAB3:    t14 = (t0 + 1032U);
    t18 = *((char **)t14);
    t14 = (t0 + 7256U);
    t19 = (t0 + 1192U);
    t20 = *((char **)t19);
    t19 = (t0 + 7272U);
    t21 = ieee_p_2592010699_sub_795620321_503743352(IEEE_P_2592010699, t17, t18, t14, t20, t19);
    t22 = (t17 + 12U);
    t10 = *((unsigned int *)t22);
    t23 = (1U * t10);
    t24 = (32U != t23);
    if (t24 == 1)
        goto LAB5;

LAB6:    t25 = (t0 + 4896);
    t26 = (t25 + 56U);
    t27 = *((char **)t26);
    t28 = (t27 + 56U);
    t29 = *((char **)t28);
    memcpy(t29, t21, 32U);
    xsi_driver_first_trans_fast(t25);
    goto LAB2;

LAB5:    xsi_size_not_matching(32U, t23, 0);
    goto LAB6;

LAB7:    t43 = (t0 + 1032U);
    t47 = *((char **)t43);
    t43 = (t0 + 7256U);
    t48 = (t0 + 1192U);
    t49 = *((char **)t48);
    t48 = (t0 + 7272U);
    t50 = ieee_p_2592010699_sub_1735675855_503743352(IEEE_P_2592010699, t46, t47, t43, t49, t48);
    t51 = (t46 + 12U);
    t39 = *((unsigned int *)t51);
    t52 = (1U * t39);
    t53 = (32U != t52);
    if (t53 == 1)
        goto LAB9;

LAB10:    t54 = (t0 + 4896);
    t55 = (t54 + 56U);
    t56 = *((char **)t55);
    t57 = (t56 + 56U);
    t58 = *((char **)t57);
    memcpy(t58, t50, 32U);
    xsi_driver_first_trans_fast(t54);
    goto LAB2;

LAB9:    xsi_size_not_matching(32U, t52, 0);
    goto LAB10;

LAB11:    t72 = (t0 + 1992U);
    t75 = *((char **)t72);
    t72 = (t0 + 4896);
    t76 = (t72 + 56U);
    t77 = *((char **)t76);
    t78 = (t77 + 56U);
    t79 = *((char **)t78);
    memcpy(t79, t75, 32U);
    xsi_driver_first_trans_fast(t72);
    goto LAB2;

LAB13:    t92 = (t0 + 1832U);
    t95 = *((char **)t92);
    t92 = (t0 + 4896);
    t96 = (t92 + 56U);
    t97 = *((char **)t96);
    t98 = (t97 + 56U);
    t99 = *((char **)t98);
    memcpy(t99, t95, 32U);
    xsi_driver_first_trans_fast(t92);
    goto LAB2;

LAB15:    t112 = (t0 + 7528);
    t116 = (t0 + 1832U);
    t117 = *((char **)t116);
    t118 = (31 - 31);
    t108 = (t118 * -1);
    t119 = (1U * t108);
    t120 = (0 + t119);
    t116 = (t117 + t120);
    t121 = *((unsigned char *)t116);
    t124 = ((IEEE_P_2592010699) + 4024);
    t126 = (t125 + 0U);
    t127 = (t126 + 0U);
    *((int *)t127) = 0;
    t127 = (t126 + 4U);
    *((int *)t127) = 30;
    t127 = (t126 + 8U);
    *((int *)t127) = 1;
    t128 = (30 - 0);
    t129 = (t128 * 1);
    t129 = (t129 + 1);
    t127 = (t126 + 12U);
    *((unsigned int *)t127) = t129;
    t122 = xsi_base_array_concat(t122, t123, t124, (char)97, t112, t125, (char)99, t121, (char)101);
    t129 = (31U + 1U);
    t130 = (32U != t129);
    if (t130 == 1)
        goto LAB17;

LAB18:    t127 = (t0 + 4896);
    t131 = (t127 + 56U);
    t132 = *((char **)t131);
    t133 = (t132 + 56U);
    t134 = *((char **)t133);
    memcpy(t134, t122, 32U);
    xsi_driver_first_trans_fast(t127);
    goto LAB2;

LAB17:    xsi_size_not_matching(32U, t129, 0);
    goto LAB18;

LAB19:    t148 = (t0 + 1032U);
    t153 = *((char **)t148);
    t148 = (t0 + 7256U);
    t154 = (t0 + 1192U);
    t155 = *((char **)t154);
    t154 = (t0 + 7272U);
    t156 = ieee_p_2592010699_sub_1735675855_503743352(IEEE_P_2592010699, t152, t153, t148, t155, t154);
    t157 = ieee_p_2592010699_sub_1837678034_503743352(IEEE_P_2592010699, t151, t156, t152);
    t158 = (t151 + 12U);
    t144 = *((unsigned int *)t158);
    t159 = (1U * t144);
    t160 = (32U != t159);
    if (t160 == 1)
        goto LAB21;

LAB22:    t161 = (t0 + 4896);
    t162 = (t161 + 56U);
    t163 = *((char **)t162);
    t164 = (t163 + 56U);
    t165 = *((char **)t164);
    memcpy(t165, t157, 32U);
    xsi_driver_first_trans_fast(t161);
    goto LAB2;

LAB21:    xsi_size_not_matching(32U, t159, 0);
    goto LAB22;

LAB24:    goto LAB2;

}

static void work_a_0832606739_3212880686_p_3(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;

LAB0:    xsi_set_current_line(59, ng0);

LAB3:    t1 = (t0 + 2152U);
    t2 = *((char **)t1);
    t1 = (t0 + 4960);
    t3 = (t1 + 56U);
    t4 = *((char **)t3);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    memcpy(t6, t2, 32U);
    xsi_driver_first_trans_fast_port(t1);

LAB2:    t7 = (t0 + 4672);
    *((int *)t7) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}

static void work_a_0832606739_3212880686_p_4(char *t0)
{
    char t5[16];
    char *t1;
    char *t2;
    char *t3;
    char *t6;
    char *t7;
    int t8;
    unsigned int t9;
    unsigned char t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    char *t16;
    char *t17;
    char *t18;
    char *t19;
    char *t20;

LAB0:    xsi_set_current_line(60, ng0);
    t1 = (t0 + 2152U);
    t2 = *((char **)t1);
    t1 = (t0 + 7320U);
    t3 = (t0 + 7595);
    t6 = (t5 + 0U);
    t7 = (t6 + 0U);
    *((int *)t7) = 0;
    t7 = (t6 + 4U);
    *((int *)t7) = 31;
    t7 = (t6 + 8U);
    *((int *)t7) = 1;
    t8 = (31 - 0);
    t9 = (t8 * 1);
    t9 = (t9 + 1);
    t7 = (t6 + 12U);
    *((unsigned int *)t7) = t9;
    t10 = ieee_std_logic_unsigned_equal_stdv_stdv(IEEE_P_3620187407, t2, t1, t3, t5);
    if (t10 != 0)
        goto LAB3;

LAB4:
LAB5:    t15 = (t0 + 5024);
    t16 = (t15 + 56U);
    t17 = *((char **)t16);
    t18 = (t17 + 56U);
    t19 = *((char **)t18);
    *((unsigned char *)t19) = (unsigned char)2;
    xsi_driver_first_trans_fast_port(t15);

LAB2:    t20 = (t0 + 4688);
    *((int *)t20) = 1;

LAB1:    return;
LAB3:    t7 = (t0 + 5024);
    t11 = (t7 + 56U);
    t12 = *((char **)t11);
    t13 = (t12 + 56U);
    t14 = *((char **)t13);
    *((unsigned char *)t14) = (unsigned char)3;
    xsi_driver_first_trans_fast_port(t7);
    goto LAB2;

LAB6:    goto LAB2;

}


extern void work_a_0832606739_3212880686_init()
{
	static char *pe[] = {(void *)work_a_0832606739_3212880686_p_0,(void *)work_a_0832606739_3212880686_p_1,(void *)work_a_0832606739_3212880686_p_2,(void *)work_a_0832606739_3212880686_p_3,(void *)work_a_0832606739_3212880686_p_4};
	xsi_register_didat("work_a_0832606739_3212880686", "isim/ALUTest_isim_beh.exe.sim/work/a_0832606739_3212880686.didat");
	xsi_register_executes(pe);
}
