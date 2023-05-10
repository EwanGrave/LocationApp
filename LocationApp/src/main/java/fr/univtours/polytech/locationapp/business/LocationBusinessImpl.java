package fr.univtours.polytech.locationapp.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.univtours.polytech.locationapp.dao.LocationDao;
import fr.univtours.polytech.locationapp.dao.TempDao;
import fr.univtours.polytech.locationapp.model.LocationBean;
import fr.univtours.polytech.locationapp.model.address.Feature;

@Stateless
public class LocationBusinessImpl implements LocationBusinessLocal, LocationBusinessRemote {

	@Inject	private LocationDao locationDao;
	@Inject private TempDao tempDao;
	@Inject private AddressBusinessLocal adressBusiness;

	@Override
	public void addLocation(LocationBean bean) {
		locationDao.createLocation(bean);
	}

	@Override
	public List<LocationBean> getLocations() {
		return locationDao.getLocations();
	}

	@Override
	public LocationBean getLocation(Integer id) {
		return locationDao.getLocation(id);
	}

	@Override
	public void updateLocation(LocationBean locationBean) {
		locationDao.updateLocation(locationBean);
	}

	@Override
	public void deleteLocation(Integer id) {
		LocationBean locationBean = getLocation(id);
		locationDao.deleteLocation(locationBean);
	}
	
	@Override
	public Double getTemp(LocationBean bean) {
		Feature address = adressBusiness.searchAdresses(bean.getAddress()).get(0);
		List<Double> coordinates = address.getGeometry().getCoordinates();
		return tempDao.getTemp(coordinates.get(0), coordinates.get(1));
	}
	

}
