package comptoirs.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import comptoirs.dao.CategorieRepository;
import comptoirs.entity.Categorie;
import comptoirs.exceptions.DuplicateException;

@Controller // Cette classe est un contrôleur
@RequestMapping(path = "/comptoirs/simple") // chemin d'accès
public class SimpleController {

	private final CategorieRepository dao;

	public SimpleController(CategorieRepository dao) {
		this.dao = dao;
	}

	/**
     * Un contrôleur qui génère du HTML "à la main"
     * @return un fragment de HTML qui montre le nombre de catégories dans la base
     */
    @GetMapping(path = "combien", 
		produces = MediaType.TEXT_HTML_VALUE) // pas de vue , génère directement du HTML
	public @ResponseBody String combienDeCategories() {
		return "<h1>Il y a " + dao.count() + " catégories dans la base</h1>";
	}	
	
    /**
     * Un contrôleur qui renvoie une liste d'entités
     * @return la liste des catégories
     */
    @GetMapping(path = "list")
	public @ResponseBody List<Categorie> getAll() {
		// This returns a JSON or XML with the categories
		return dao.findAll();
	}	
	
    /**
     * Ajoute une nouvelle catégorie par POST ou GET
     * @param libelle le libellé de la nouvelle catégorie
     * @param description la description de la nouvelle catégorie
     * @return la catégorie nouvellement créée, avec sa clé auto-générée
     * @throws DuplicateException si le libellé existe déjà
     */
    @RequestMapping(path = "ajouter", method = {RequestMethod.GET, RequestMethod.POST}) 
	public @ResponseBody Categorie addNew( 
			@RequestParam(required = true) final String libelle,
			@RequestParam(defaultValue = "Description non fournie") final String description
	) throws DuplicateException {
		final Categorie result = new Categorie();
		result.setLibelle(libelle);
		result.setDescription(description);
		try {
			dao.save(result);
		} catch (final DataIntegrityViolationException e) {
			throw new DuplicateException("Le libellé '" + libelle + "' est déjà utilisé");
		}
		return result;
	}

    /**
     *
     * @param timeout
     * @return
     */
    @GetMapping(path = "wait", produces = MediaType.TEXT_PLAIN_VALUE) // pas de vue , génère directement du texte)
	public @ResponseBody String waitFor(@RequestParam(defaultValue = "10") final int timeout) {
		try {
			Thread.sleep(1000 * timeout);
		} catch (final InterruptedException e) {}
		return "Après un délai de " + timeout + " seconds";
	}

}
