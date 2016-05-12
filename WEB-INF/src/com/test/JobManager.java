package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class JobManager
 */
public class JobManager extends MVCPortlet {
	Log log = LogFactoryUtil.getLog(MVCPortlet.class) ;
	private int id=0;
	
	
  
	@Override
	public void doEdit(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		// TODO Auto-generated method stub
		super.doEdit(renderRequest, renderResponse);
	}

	public void processInput(ActionRequest request, ActionResponse response)
	
		throws IOException, PortletException{
			
			String title = ParamUtil.getString(request, "title");
			String description = ParamUtil.getString(request, "description");
			String type = ParamUtil.getString(request, "type");
			String scheduledAtYear = ParamUtil.getString(request, "scheduledAtYear");
			String scheduledAtMonth = ParamUtil.getString(request, "scheduledAtMonth");
			String scheduledAtDay = ParamUtil.getString(request, "scheduledAtDay");
			String executable = ParamUtil.getString(request, "excutable");
			
			log.warn("title:"+ title);
			log.warn("description:"+ description);
			log.warn("type:" + type);
			log.warn("scheduleAt:" + scheduledAtYear + "-"+ scheduledAtMonth + "-" + scheduledAtDay);
			log.warn("executable:" + executable);
			 
            Calendar scheduledDate =CalendarFactoryUtil.getCalendar();
			
			scheduledDate.set(Calendar.MONTH,Integer.parseInt(scheduledAtMonth));
			scheduledDate.set(Calendar.DATE, Integer.parseInt(scheduledAtDay));
			scheduledDate.set(Calendar.YEAR, Integer.parseInt(scheduledAtYear));
			// System.out.println(scheduledDate);
			
			Job newJob = new Job(title, description,type, scheduledDate, executable, id);
					
			//store into portlet session
					
		    PortletSession session = request.getPortletSession(); 
		    
		
			
			ArrayList<Job> joblist =  (ArrayList<Job>) session.getAttribute("job-list");
			
			if (joblist == null || joblist.isEmpty()){ 
				 
			   joblist = new ArrayList<Job>();
			
			 }
			 joblist.add(newJob);
			 log.warn("joblist:");
			 log.warn(joblist);
			 session.setAttribute("job-list", joblist);
		
	 }
	
	public void deleteJob(ActionRequest request, ActionResponse response)
	throws IOException,   PortletException {
		
		String jobid = ParamUtil.getString(request, "jobid");
		log.warn("jobid:" +jobid);
	}

}
