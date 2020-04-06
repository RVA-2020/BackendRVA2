package rva.ctrls;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

	/*
	 * @RequestMapping - predstavlja anotaciju koja se može koristiti i na nivou klase i na
	 * nivou metode. Služi za mapiranje web zahteva na određene klase ili metode. U zagradi
	 * se navodi deo URI-ja koji predstavlja putanju. U slučaju metode helloWorld(), 
	 * @RequestMapping("/") označava da će ova metoda biti pozvana kada se u browseru unese
	 * adresa localhost:8083 
	 */
	
	@RequestMapping("/")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@RequestMapping("zbir")
	public String zbir() {
		
		long x = Math.round(Math.random() * 10);
		long y = Math.round(Math.random() * 10);
		
		return x+" + "+y+ " = "+(x+y);
	}
	
}
