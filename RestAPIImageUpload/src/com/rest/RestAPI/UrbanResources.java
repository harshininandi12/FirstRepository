package com.rest.RestAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.rest.dto.Customer;
import com.rest.dto.Employee;
import com.rest.dto.Professionals;
import com.rest.dto.Services;
import com.rest.dto.SubServices;
import com.ts.dao.CustomerDAO;
import com.ts.dao.EmployeeDAO;
import com.ts.dao.ProfessionalsDAO;
import com.ts.dao.ServicesDAO;
import com.ts.dao.SubServicesDAO;

@Path("urbanresources")
public class UrbanResources {
	@Path("getuser/{userName}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getuser(@PathParam("userName") String userName,@PathParam("password") String password) {
	        System.out.println("Recieved path params: "+userName+" "+password); 
	        CustomerDAO customerDAO = new CustomerDAO();
	        Customer customer = customerDAO.getuser(userName, password);
	        return customer;
	}
	
	@Path("getProfUser/{userName}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Professionals getProfUser(@PathParam("userName") String userName,@PathParam("password") String password) {
	        System.out.println("Recieved path params: "+userName+" "+password); 
	        ProfessionalsDAO professionalDAO = new ProfessionalsDAO();
	        Professionals professional = professionalDAO.getProfUser(userName, password);
	        return professional;
	}
	 
	@Path("registerC")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerC(Customer customer) {
    	System.out.println("Inside REST...");
    	CustomerDAO customerDAO = new CustomerDAO();
    	customerDAO.register(customer); //send to database code 
    	System.out.println(customer.getCustomerId()+" "+customer.getCustomerName()+" "+customer.getCustomerNumber()+""+customer.getCustomerAddress()+customer.getPassword());
    }
	
	@Path("registerP")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerP(Professionals professional) {
    	System.out.println("Inside REST...");
    	ProfessionalsDAO professionalDAO = new ProfessionalsDAO();
    	professionalDAO.register(professional); //send to database code 
    	System.out.println(professional.getProfId()+" "+professional.getProfName()+" "+professional.getProfNumber()+""+professional.getQualification()+professional.getAadharNumber()+professional.getPassword());
    }
	
	@Path("getAllServices")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Services> getAllServices() {
    	ServicesDAO servicesDAO = new ServicesDAO();
    	List <Services> serviceList = ServicesDAO.getAllServices();
        return serviceList;
    }
	@Path("getAllSubServices")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubServices> getAllSubServices() {
    	SubServicesDAO subservicesDAO = new SubServicesDAO();
    	List <SubServices> subserviceList = SubServicesDAO.getAllSubServices();
        return subserviceList;
    }
	
	@Path("checkRegistration")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public int checkRegistration(String email) {
		CustomerDAO customerDAO = new CustomerDAO();
		return customerDAO.checkRegistration(email);
	}
	
	@POST
	@Path("/uploadImage")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void uploadImage(@FormDataParam("Image") InputStream fileInputStream, @FormDataParam("Image") FormDataContentDisposition 
    formDataContentDisposition, @FormDataParam("ImageCaption") String caption, @FormDataParam("ImageDescription") String description) throws IOException {
		int read = 0;
	    byte[] bytes = new byte[1024];
	    System.out.println("METHOD CALLED...."+caption);
	   
	    FileOutputStream out = new FileOutputStream(new File("/home/hp/Downloads/HandyFinal/RestAPIImageUpload/WebContent/image", formDataContentDisposition.getFileName()));
	    while ((read = fileInputStream.read(bytes)) != -1) {
	    	out.write(bytes, 0, read);
	    }
	    out.flush();
	    out.close();
	    System.out.println("METHOD CALLED...."+description);
	    Services services = new Services();
		services.setServiceName(caption);
		services.setServiceDesc(description);
		services.setImagePath(formDataContentDisposition.getFileName());
		ServicesDAO servicesDAO = new ServicesDAO();
		servicesDAO.register(services);
		//return Response.ok("Data Submitted Successfully.").build();	    
	}
	 
	/*@Path("getEmpByUserPass/{loginId}/{password}")
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   public Customer getCustomerByUserPass(@PathParam("loginId") String loginId,@PathParam("password") String password) {
	        System.out.println("Recieved path params: "+loginId+" "+password); 
	    	CustomerDAO customerDAO = new CustomerDAO();
	        Customer customer = customerDAO.getCustomerByUserPass(loginId, password);
	        return customer;
	   }*/
}
