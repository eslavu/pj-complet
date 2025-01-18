package pj.lab11;

import java.util.*;
import java.time.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class EvenimentController
{
	@Autowired
	EvenimentRepository repository;
	
	@GetMapping("/jpa/evenimente")
	public List<Eveniment> findAll()
	{ return repository.findAll(); }
	
	@PostMapping("jpa/evenimente")
	public void save(@RequestBody Eveniment eveniment)
	{ repository.save(eveniment); }
	
	@PutMapping("jpa/evenimente")
	public void modify(@RequestBody Eveniment eveniment)
	{ repository.save(eveniment); }
	
	@GetMapping("jpa/evenimente/locatie/{locatie}")
	public List<Eveniment> findByLocatie(@PathVariable String locatie) throws EvenimentNotFoundException
	{
		var evenimente = repository.findByLocatie(locatie);
		if (!evenimente.isEmpty()) return evenimente;
		else throw new EvenimentNotFoundException("Nu exista evenimente in locatia " + locatie + "!");
	}
	
	@GetMapping("jpa/evenimente/data/{data}")
	public List<Eveniment> findByData(@PathVariable LocalDate data) throws EvenimentNotFoundException
	{
		var evenimente = repository.findByData(data);
		if (!evenimente.isEmpty()) return evenimente;
		else throw new EvenimentNotFoundException("Nu exista evenimente in data de " + data + "!");
	}
	
	@DeleteMapping("jpa/evenimente/id/{id}")
	public void deleteById(@PathVariable Long id)
	{ repository.deleteById(id); }
}