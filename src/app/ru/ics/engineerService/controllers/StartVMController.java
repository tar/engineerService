package ru.ics.engineerService.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import ru.ics.engineerService.model.AbstractCloudManager;
import ru.ics.engineerService.model.XCPCloudManager;
import ru.ics.engineerService.util.Util;

/**
 * this controller process request like startVM.html?_vmName=&_vmType=
 * and the VM parameters for future
 * @author Lukashin
 *
 */
public class StartVMController implements Controller{
	private static final Logger logger = Logger.getLogger(StartVMController.class);
	private static final String PARAMETER_VM_NAME = "_vmName";
	private static final String PARAMETER_VM_TYPE = "_vmType";
	private static final String VIEW_NAME_SUCCESS = "success";
	private static final String VIEW_NAME_FAIL = "error";
/*
 * Processing HTTP request. 
 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		logger.info("StartVmController started");
		ModelAndView mav= new ModelAndView();
		String vmName=request.getParameter(PARAMETER_VM_NAME);
		String vmType=request.getParameter(PARAMETER_VM_TYPE);
		if (Util.isEmpty(vmName)||Util.isEmpty(vmType)) {
			logger.error("Empty properties recieved from request");
			mav.setViewName(VIEW_NAME_FAIL);
			return mav;
		}
		AbstractCloudManager cloudManager=new XCPCloudManager();
		String result=cloudManager.runVM(vmType, vmName);
		logger.info(result);
		//TODO process result
		mav.setViewName(VIEW_NAME_SUCCESS);
		return mav;
	}

}
