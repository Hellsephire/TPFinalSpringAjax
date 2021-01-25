package comptoirs.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;

import comptoirs.dao.ProduitRepository;
import comptoirs.dto.UnitesParProduit;

@Service
@RequestMapping(path = "/api/stats")
public class StatisticsWebService {
	@Autowired
	private ProduitRepository dao;

	/**
	 * Unites vendues pour chaque produit d'une catégorie donnée. Pas d'utilisation
	 * de DTO, renvoie simplement une liste de valeurs
	 * 
	 * @param code le code de la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit en format JSON
	 */
	@GetMapping(path = "unitesVenduesPourCategorie", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody List<UnitesParProduit> unitesVenduesPourCategorieV3(
			@RequestParam(required = true) final Integer code) {
		return dao.produitsVendusPour(code);
	}

	/**
	 * Unites vendues pour chaque produit d'une catégorie donnée. Pas d'utilisation
	 * de DTO, renvoie simplement une liste de tableaux de valeurs
	 * 
	 * @param code le code de la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit en format JSON
	 */
	@GetMapping(path = "unitesVenduesPourCategorieV2", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Object> unitesVenduesPourCategorieV2(@RequestParam(required = true) final Integer code) {
		return dao.produitsVendusPourV2(code);
	}
}
