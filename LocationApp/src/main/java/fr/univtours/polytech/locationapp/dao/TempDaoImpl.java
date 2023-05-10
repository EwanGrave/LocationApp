package fr.univtours.polytech.locationapp.dao;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import fr.univtours.polytech.locationapp.model.temperature.WsTempResult;

@Stateless
public class TempDaoImpl implements TempDao {
	
	private static String URL = "https://api.openweathermap.org";
	private static String key = "8b571c471a04b8622841d0edad41d0c5";

	@Override
	public Double getTemp(int lon, int lat) {
	    Client client = ClientBuilder.newClient();
	    WebTarget target = client.target(URL);
	    
	    target = target.path("data/2.5/weather");
	    target = target.queryParam("appid", key);
	    target = target.queryParam("lat", lat);
	    target = target.queryParam("lon", lon);
	    
	    WsTempResult wsResult = target.request(MediaType.APPLICATION_JSON).get(WsTempResult.class);
	    return wsResult.getMain().getTemp();
	}

}
