package com.restprovider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eintern.orm.dao.HibernateGenericDAO;
import com.eintern.orm.dao.RequestDAO;
import com.eintern.orm.entity.Request;

@Controller

public class ApprovedVehicleResource {
	
	/*public static void main(String[] args) {
		ApprovedVehicleResource r=new ApprovedVehicleResource();
		r.retieveVehicle();
	}*/
	
	@Inject
	private RequestDAO reqDaoImpl;
	
	@Inject
	private HibernateGenericDAO genDaoImpl;

	
	//@GET
	@RequestMapping(value="/webservice",  method = RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody String retrieveVehicle() {
		
		//List list = reqDaoImpl.retrieveRequestsByStatus(Request.class, "Approved");
		List list =genDaoImpl.retrieveAll(Request.class);
		// write vehicle information as JSON Objects
		//store the json objects in an arraylist
		//printout the arraylist's objects as appended strings
		StringBuilder builder= new StringBuilder();
		
		
		
		/*{"employees":[
	    {"firstName":"John", "lastName":"Doe"},
	    {"firstName":"Anna", "lastName":"Smith"},
	    {"firstName":"Peter", "lastName":"Jones"}
	]}*/
		
		/*List<String> list= new ArrayList<String>();
		list.add(builder.toString());
		list.add("hi");*/
	
		/*builder.append("{\"requests\":[");*/
		builder.append("[");
		Iterator iter=list.iterator();

		while(iter.hasNext()){
			
			builder.append(iter.next().toString());
			
			if(iter.hasNext()){
				builder.append(",");
			}
		}
		/*builder.append("]}");*/
		builder.append("]");
		
		return builder.toString();

		/*return new String() {
			public void write(OutputStream outputStream) {

				PrintWriter out = new PrintWriter(outputStream);
				JSONObject json= new JSONObject("fdsf");
				JSONArray jarray= new JSONArray();
				
				StringJSON 
				
			}
		};*/

	}

}
