package com.rohit.location.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.rohit.location.entities.Location;
import com.rohit.location.repos.LocationRepository;
import com.rohit.location.service.LocationService;
import com.rohit.location.util.EmailUtil;
import com.rohit.location.util.ReportUtil;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private ReportUtil reportUtil;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private ServletContext context;

	private static final Logger log = LogManager.getLogger(LocationController.class);

	@RequestMapping("/showCreate")
	public String showCreate() {
		log.debug("show create URI called");
		return "createLocation";
	}

	@RequestMapping("/saveLocation")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		locationService.saveLocation(location);
		modelMap.addAttribute("message", "location saved successfully with id: " + location.getId());

		// Code for sending mail
		/*
		 * emailUtil.sendEmail("kumbharrohit13@gmail.com", "Location saved",
		 * "Location saved successfully and about to return a response");
		 */
		return "createLocation";
	}

	@RequestMapping("/showlocation")
	public String showLocation(ModelMap modelMap) {
		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "showLocations";
	}

	@RequestMapping("deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {

//	 	Step 1 (This will use database call)
//		Location loc = locationService.getLocationById(id).get();

		System.out.println("id= " + id);
		Location location = new Location();
		location.setId(id);
		locationService.deleteLocation(location);
		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "showLocations";
	}

	@RequestMapping("updateLocation")
	public String updateLocation(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = locationService.getLocationById(id).get();
		modelMap.addAttribute("location", location);
		return "updateLocation";
	}

	@RequestMapping("/saveUpdatedLocation")
	public String saveUpdatedLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		locationService.updateLocation(location);
		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "showLocations";
	}

	@RequestMapping("/showReport")
	public String showReport() {
		List<Object[]> data = locationRepository.findTypeAndTypeCount();
		String path = context.getRealPath("/");
		reportUtil.generatePieChart(path, data);
		return "report";
	}
}
