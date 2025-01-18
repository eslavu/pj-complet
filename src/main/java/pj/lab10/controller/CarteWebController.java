package pj.lab10.controller;
import pj.lab10.entity.*;
import pj.lab10.repository.*;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarteWebController
{
	@Autowired
	CarteRepository repository;
	
	@GetMapping("/lista-carti")
	public String getLista_carti(Model model)
	{
		String str = "Lista cărților din repository:";
		model.addAttribute("rezultat", str);
		model.addAttribute("lista", repository.findAll());
		return "carti";
	}
	
	@PostMapping("/lista-carti")
    public String prelucrareCarte(@ModelAttribute Carte carte, @RequestParam String action, Model model)
    {
		String str = "Lista cărților din repository:";
		
		switch (action)
		{
			case "adauga":
				if (carte.getIsbn().isBlank() || carte.getTitlu().isBlank() || carte.getAutor().isBlank())
					str = "Adăugarea nu se realizează dacă nu completaţi toate caracteristicile!";
				else
				{
					repository.save(carte);
					str = "Adăugare realizată cu succes!";
				}
				break;
			case "modifica":
				if (carte.getIsbn().isBlank() || carte.getTitlu().isBlank() || carte.getAutor().isBlank())
					str = "Modificarea nu se realizează dacă nu completaţi toate caracteristicile!";
				else
				{
					var gasit = repository.findByIsbn(carte.getIsbn());
					if (gasit.size() == 0) str =  "Nu se găsește nicio carte cu ISBNul introdus!";
					else
					{
						repository.save(carte);
						str = "Cartea cu ISBN-ul " + carte.getIsbn() + " a fost modificată!";
					}
				}
				break;
			case "sterge":
				if (carte.getIsbn().isBlank())
					str = "Ștergerea nu se realizează dacă nu completaţi ISBNul!";
				else
				{
					Integer count = repository.deleteByIsbn(carte.getIsbn());
					if (count == 0) str = "Nu se găsește nicio carte cu ISBNul introdus!";
					else str = "Cartea cu ISBN-ul " + carte.getIsbn() + " a fost ștearsă!";
				}
				break;
			case "filtreaza":
				if (carte.getAutor().isBlank()) str = "Lista cărților din repository:";
				else str = "Cărţile următoare aparţin autorului " + carte.getAutor() + ":";
				break;
			default: break;
		}
		
		if (repository.findAll() != null)
		{
			if (action == "filtreaza") model.addAttribute("lista", repository.findByAutor(carte.getAutor()));
			else model.addAttribute("lista", repository.findAll());
		}
		else model.addAttribute("lista", new ArrayList<>());
		model.addAttribute("rezultat", str);
		return "carti";
    }
}