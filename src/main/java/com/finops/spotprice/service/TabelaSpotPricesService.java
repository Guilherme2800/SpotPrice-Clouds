package com.finops.spotprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.finops.spotprice.persistence.entity.SpotPrices;
import com.finops.spotprice.persistence.repository.SpotRepository;

@Service
public class TabelaSpotPricesService {

	@Autowired
	SpotRepository spotRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/listarSpot")
	public ModelAndView listarIndividuos(String cloud, String region, String instanceType) {
		ModelAndView modelView = new ModelAndView("paginaPrincipal/tabelaSpot");

		if (cloud.length() != 0 && region.length() != 0 && instanceType.length() != 0) {
			Iterable<SpotPrices> spotIt = spotRepository.findBycloudNameAndRegionAndInstanceType(cloud, region,instanceType);
			modelView.addObject("spots", spotIt);
			return modelView;
		}
		
		if (cloud.length() != 0 && region.length() != 0) {
			Iterable<SpotPrices> spotIt = spotRepository.findBycloudNameAndRegion(cloud, region);
			modelView.addObject("spots", spotIt);
			return modelView;
		}
		
		if (cloud.length() != 0 && instanceType.length() != 0) {
			Iterable<SpotPrices> spotIt = spotRepository.findBycloudNameAndInstanceType(cloud, instanceType);
			modelView.addObject("spots", spotIt);
			return modelView;
		}
		
		if (region.length() != 0) {
			Iterable<SpotPrices> spotIt = spotRepository.findByregion(region);
			modelView.addObject("spots", spotIt);
			return modelView;
		}
		
		if (instanceType.length() != 0) {
			Iterable<SpotPrices> spotIt = spotRepository.findByinstanceType(instanceType);
			modelView.addObject("spots", spotIt);
			return modelView;
		}
		
		if (cloud.length() != 0) {
			Iterable<SpotPrices> spotIt = spotRepository.findBycloudName(cloud);
			modelView.addObject("spots", spotIt);
			return modelView;
		}
		
		

		return modelView;
	}

}