package fr.univtours.polytech.locationapp.model.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.univtours.polytech.locationapp.business.LocationBusinessLocal;
import fr.univtours.polytech.locationapp.model.LocationBean;

@Path("api")
@Stateless
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class WebService {
	
	@EJB
	private LocationBusinessLocal business;

	@GET
	@Path("locations/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public LocationBean getLocation(@PathParam("id") int id) {
	    return this.business.getLocation(id);
	}
	
	@GET
	@Path("locations")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<LocationBean> getLocations()
	{
		return business.getLocations();
	}
	
	// Méthode appelée lorsqu'on ajoute toutes les informations dans le corps de la requête.
	@POST
	@Path("create")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createLocation(LocationBean locationBean, @HeaderParam(HttpHeaders.AUTHORIZATION) String auth)
	{
		if ("".equals(auth))
			return Response.status(Status.UNAUTHORIZED).build();
		else if (!"42".equals(auth))
			return Response.status(Status.FORBIDDEN).build();
		else
		{
			business.addLocation(locationBean);
			return Response.ok().build();
		}
	}

	// Méthode appelée lorsqu'on soumet un formulaire HTML.
	@POST
	@Path("create")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createLocation(@FormParam("address") String address,
	        @FormParam("city") String city,
	        @FormParam("nightPrice") Double price,
	        @FormParam("zipCode") Integer zipCode,
	        @HeaderParam(HttpHeaders.AUTHORIZATION) String auth)
	{
		if ("".equals(auth))
			return Response.status(Status.UNAUTHORIZED).build();
		else if (!"42".equals(auth))
			return Response.status(Status.FORBIDDEN).build();
		else
		{
			LocationBean location = new LocationBean();
			location.setAddress(address);
			location.setCity(city);
			location.setNightPrice(price);
			location.setZipCode(String.valueOf(zipCode));

			business.addLocation(location);
			
			return Response.ok().build();
		}
	}
	
	@DELETE
	@Path("location/{id}")
	public Response deleteLocation(@PathParam("id") Integer id, @HeaderParam(HttpHeaders.AUTHORIZATION) String auth)
	{
		if ("".equals(auth))
			return Response.status(Status.UNAUTHORIZED).build();
		else if (!"42".equals(auth))
			return Response.status(Status.FORBIDDEN).build();
		else
		{
			LocationBean loc = business.getLocation(id);
			if (loc == null)
				return Response.status(Status.NOT_FOUND).build();
			else
			{
				business.deleteLocation(id);
				return Response.noContent().build();
			}
		}
	}
	
	@POST
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateLocation(LocationBean newLocation, @HeaderParam(HttpHeaders.AUTHORIZATION) String auth)
	{
		if ("".equals(auth))
			return Response.status(Status.UNAUTHORIZED).build();
		else if (!"42".equals(auth))
			return Response.status(Status.FORBIDDEN).build();
		else
		{
			

			return Response.ok().build();
		}
		
	}
}
