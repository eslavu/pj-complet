package pj.lab9;

import java.util.*;
import java.time.*;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.beans.factory.annotation.*;
import org.slf4j.*;

@SpringBootApplication
public class Lab9Application implements CommandLineRunner
{
	@Autowired
	MasinaJpaRepository repository;
	
	// 0 = print / 1 = log
	private final Integer mod = 1;
	
	private Scanner scanner = new Scanner(System.in);
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private void meniu()
	{
		System.out.println("~~ jpa");
			
		System.out.println("1) adaugare masina in baza de date");
		System.out.println("2) stergere masina dupa numarul de inmatriculare");
		System.out.println("3) cautare masina dupa numarul de inmatriculare");
		System.out.println("4) extragere lista cu toate masinile din baza de date");
		System.out.println("5) determinare numar de masini de o anumita marca");
		System.out.println("6) determinare numar de masini care au sub 100000km");
		System.out.println("7) extragere lista cu masinile mai noi de 5 ani");
		System.out.println("0) iesire");
		System.out.println();
	}
	
	private void optiune1()
	{
		System.out.println("~~ adaugare masina:");
    	
    	System.out.print("numar de inmatriculare? = ");
    	scanner.nextLine();
    	String nr_inmatriculare = scanner.nextLine().toUpperCase();
    	
    	System.out.print("marca masina? = ");
    	String marca = scanner.nextLine();
    	
    	System.out.print("anul fabricatiei? = ");
    	Integer an_fabricatie = scanner.nextInt();
    	
    	System.out.print("culoare masina? = ");
    	scanner.nextLine();
    	String culoare = scanner.nextLine();
    	
    	System.out.print("numarul de kilometri? = ");
    	Integer nr_km = scanner.nextInt();
    	
    	repository.insert(new Masina(nr_inmatriculare, marca, an_fabricatie, culoare, nr_km));
    	
    	System.out.println();
    	if (mod == 0) System.out.println("~~ masina adaugata cu succes!");
    	else logger.info("~~ masina adaugata cu succes!");
    	System.out.println();
	}
	private void optiune2()
	{
		System.out.print("numar de inmatriculare de sters? = ");
    	scanner.nextLine();
    	String nr_inmatriculare = scanner.nextLine().toUpperCase();
    	
    	Integer count = 0;
    	count = repository.deleteByNr_inmatriculare(nr_inmatriculare);
    	
    	if (mod == 0)
    	{
	    	if (count == 0) System.out.println("~~ nu exista masina cu numarul " + nr_inmatriculare + "!");
			else System.out.println("~~ masina stearsa cu succes!");
    	}
    	else
    	{
    		if (count == 0) logger.info("~~ nu exista masina cu numarul {}!", nr_inmatriculare);
    		else logger.info("~~ masina stearsa cu succes!");
    	}
		System.out.println();
	}
	private void optiune3()
	{
		System.out.print("numar de inmatriculare de cautat? = ");
    	scanner.nextLine();
    	String nr_inmatriculare = scanner.nextLine().toUpperCase();
    	
    	List<Masina> find = new ArrayList<>();
    	find = repository.findByNr_inmatriculare(nr_inmatriculare);
    	
    	if (mod == 0)
    	{
	    	if (find.size() == 0) System.out.println("~~ nu exista masina cu numarul " + nr_inmatriculare + "!");
			else System.out.println(find.get(0));
    	}
    	else
    	{
    		if (find.size() == 0) logger.info("~~ nu exista masina cu numarul {}!", nr_inmatriculare);
			else logger.info("{}", find.get(0));
    	}
		System.out.println();
	}
	private void optiune4()
	{
		if (mod == 0)
		{
			System.out.println("~~ lista cu toate masinile:");
			repository.findAll().forEach(System.out::println);
		}
		else
		{
			logger.info("~~ lista cu toate masinile:");
			for (var masina : repository.findAll()) logger.info("{}", masina);
		}
		
		System.out.println();
	}
	private void optiune5()
	{
		System.out.print("marca de cautat? = ");
		scanner.nextLine();
		String marca = scanner.nextLine();
		
		Integer count = 0;
		count = repository.countByMarca(marca);
		
		if (mod == 0)
		{
			if (count == 0) System.out.println("~~ nu exista masini cu marca " + marca + "!");
			else System.out.println("~~ exista " + count + " masini cu marca " + marca + "!");
		}
		else
		{
			if (count == 0) logger.info("~~ nu exista masini cu marca {}!", marca);
			else logger.info("~~ exista {} masini cu marca {}!", count, marca);
		}
		System.out.println();
	}
	private void optiune6()
	{
		Integer count = 0;
		count = repository.countByNr_km(-2, 100000);
		
		if (mod == 0)
		{
			if (count == 0) System.out.println("~~ nu exista masini care au sub 100000km!");
			else System.out.println("~~ " + count + " masini au sub 100000km!");
		}
		else
		{
			if (count == 0) logger.info("~~ nu exista masini care au sub 100000km!");
			else logger.info("~~ {} masini au sub 100000km!", count);
		}
		System.out.println();
	}
	private void optiune7()
	{
		Integer an = LocalDate.now().getYear() - 5;
		
		if (mod == 0)
		{
			System.out.println("~~ lista cu masinile mai noi de 5 ani:");
			repository.findByAn_fabricatie(1, an).forEach(System.out::println);
		}
		else
		{
			logger.info("~~ lista cu masinile mai noi de 5 ani:");
			for (var masina : repository.findByAn_fabricatie(1, an)) logger.info("{}", masina);
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{ SpringApplication.run(Lab9Application.class, args); }
	
	@Override
	public void run(String... args) throws Exception
	{
		System.out.println();
		
		Boolean running = true;
        Integer optiune;
        while (running)
        {
            meniu();
            System.out.print("~~ optiunea ta: ");
            optiune = scanner.nextInt();
            System.out.println();

            switch (optiune)
            {
	            case 0:
	            	running = false;
	            	break;
	            case 1:
	            	optiune1();
	            	break;
	            case 2:
	            	optiune2();
	            	break;
	            case 3:
	            	optiune3();
	            	break;
	            case 4:
	            	optiune4();
	            	break;
	            case 5:
	            	optiune5();
	            	break;
	            case 6:
	            	optiune6();
	            	break;
	            case 7:
	            	optiune7();
	            	break;
            	default:
            		System.out.println("~~ optiune invalida!");
            		break;
            }
            
            if (running)
            {
                try
                {
                    System.out.print("(apasati ENTER pentru a continua)");
                    System.in.read();
                    System.out.println();
                }
                catch (Exception exception) {}
            }
        }
        
		scanner.close();
	}
}
