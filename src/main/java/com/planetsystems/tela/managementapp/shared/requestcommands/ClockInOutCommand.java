package com.planetsystems.tela.managementapp.shared.requestcommands;

public interface ClockInOutCommand{
	String SAVE_CLOCK_IN = "SAVE_CLOCK_IN";
	String GET_ALL_CLOCK_INS = "GET_ALL_CLOCK_INS";
	String GET_CLOCK_IN_BY_ID = "GET_ALL_CLOCK_IN_ID";
	String GET_CLOCK_INS_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_CLOCK_INS_BY_SYSTEM_USER_PROFILE_SCHOOLS";
	
	
	String SAVE_CLOCK_OUT = "SAVE_CLOCK_OUT";
	String GET_ALL_CLOCK_OUTS = "GET_ALL_CLOCK_OUTS";
	String GET_ALL_CLOCK_OUT_BY_CLOCK_IN = "GET_ALL_CLOCK_OUT_BY_CLOCK_IN";
	String GET_CLOCK_OUT_BY_ID = "GET_CLOCK_OUT_BY_ID";
	String GET_CLOCK_OUTS_BY_SYSTEM_USER_PROFILE_SCHOOLS = "GET_CLOCK_OUTS_BY_SYSTEM_USER_PROFILE_SCHOOLS";

	
	
	
}
