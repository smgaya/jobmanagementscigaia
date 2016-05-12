<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="com.test.JobManager" %>
<%@ page import="com.test.Job" %>
<portlet:defineObjects />

<h3>Add a new Job</h3>
<portlet:actionURL name="processInput" var="addJobURL"></portlet:actionURL>
<aui:form action="<%=addJobURL%>" method="POST">
	<aui:fieldset label="Job Details">
		<aui:input label="Title" name="title" size="80">
		  <aui:validator  name="required"/>
		</aui:input>
		<aui:input label="Description" name="description" type="textarea" col="80" row="10">
          <aui:validator  name="required"/>		
		</aui:input>
		<aui:select label="type" name="type">
			<aui:option label="Regular" value="regular"></aui:option>
			<aui:option label="Workflow" value="workflow"></aui:option>
			<aui:option label="Parametric" value="parametric"></aui:option>
		</aui:select>
		<aui:field-wrapper label="Scheduled At">
			<liferay-ui:input-date  dayValue="3" monthValue="5" yearValue="2016"  yearRangeEnd="2020" yearRangeStart="2016"  dayParam="scheduledAtDay" monthParam="scheduledAtMonth" yearParam="scheduledAtYear">
			  
			</liferay-ui:input-date>	
		
		</aui:field-wrapper>
		<aui:input label="Execution" name="executable" size="80">
           <aui:validator  name="required"/>		
		</aui:input>
		<aui:button type="Submit" value="add"></aui:button>
	</aui:fieldset>

</aui:form>

<%
ArrayList<Job> joblist = (ArrayList<Job>)portletSession.getAttribute("job-list");

if (joblist == null || joblist.isEmpty()){
 joblist = new ArrayList<Job>();
 
}
%>


 <liferay-ui:search-container>
	<liferay-ui:search-container-results
		results="<%=joblist %>"
		total="<%=joblist.size()%>"
	/>

	<liferay-ui:search-container-row
		className="com.test.Job"
		modelVar="aJob"
	>
	
		<liferay-ui:search-container-column-text property="description" />

		<liferay-ui:search-container-column-text property="executable" />

		<liferay-ui:search-container-column-text property="scheduledAt" />

		<liferay-ui:search-container-column-text property="title" />

		<liferay-ui:search-container-column-text property="type" />

		
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

