package com.finops.spotprice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.finops.spotprice.model.SpotPrices;
import com.finops.spotprice.model.Usuario;
import com.finops.spotprice.repository.SpotRepository;
import com.finops.spotprice.repository.UsuarioRepository;

@Controller
public class TabelaSpotController {

	@Autowired
	SpotRepository spotRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/desconectar")
	public String desconectar() {
		return "login/loginUsuario";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listarSpot")
	public ModelAndView listarIndividuos(String cloud, String region, String instanceType) {
		ModelAndView modelView = new ModelAndView("paginaPrincipal/tabelaSpot");
		
		System.out.println(cloud);

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
		
		if (cloud.length() != 0) {
			Iterable<SpotPrices> spotIt = spotRepository.findBycloudName(cloud);
			modelView.addObject("spots", spotIt);
			return modelView;
		}

		return modelView;
	}

	@RequestMapping (method = RequestMethod.GET, value = "/tabelaSpot")
	public ModelAndView getTabelaSpot(){
		ModelAndView modelView = new ModelAndView("paginaPrincipal/tabelaSpot");
		List<String> nomesCloud = spotRepository.findBycloudNameSelect();
		modelView.addObject("cloudNames", nomesCloud);
		
		return modelView;
	}
	
}
