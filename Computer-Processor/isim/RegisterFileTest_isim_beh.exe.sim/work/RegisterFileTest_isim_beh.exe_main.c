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

#include "xsi.h"

struct XSI_INFO xsi_info;

char *IEEE_P_3620187407;
char *IEEE_P_3499444699;
char *WORK_P_1204484316;
char *WORK_P_2324653619;
char *IEEE_P_1242562249;
char *STD_STANDARD;
char *IEEE_P_2592010699;
char *WORK_P_0185705960;


int main(int argc, char **argv)
{
    xsi_init_design(argc, argv);
    xsi_register_info(&xsi_info);

    xsi_register_min_prec_unit(-12);
    ieee_p_2592010699_init();
    ieee_p_3499444699_init();
    ieee_p_3620187407_init();
    ieee_p_1242562249_init();
    work_p_0185705960_init();
    work_p_2324653619_init();
    work_p_1204484316_init();
    work_a_2263464102_3212880686_init();
    work_a_3714479754_3212880686_init();
    work_a_1025691937_3212880686_init();
    work_a_2166523021_3212880686_init();
    work_a_2230306503_2372691052_init();


    xsi_register_tops("work_a_2230306503_2372691052");

    IEEE_P_3620187407 = xsi_get_engine_memory("ieee_p_3620187407");
    IEEE_P_3499444699 = xsi_get_engine_memory("ieee_p_3499444699");
    WORK_P_1204484316 = xsi_get_engine_memory("work_p_1204484316");
    WORK_P_2324653619 = xsi_get_engine_memory("work_p_2324653619");
    IEEE_P_1242562249 = xsi_get_engine_memory("ieee_p_1242562249");
    STD_STANDARD = xsi_get_engine_memory("std_standard");
    IEEE_P_2592010699 = xsi_get_engine_memory("ieee_p_2592010699");
    xsi_register_ieee_std_logic_1164(IEEE_P_2592010699);
    WORK_P_0185705960 = xsi_get_engine_memory("work_p_0185705960");

    return xsi_run_simulation(argc, argv);

}
