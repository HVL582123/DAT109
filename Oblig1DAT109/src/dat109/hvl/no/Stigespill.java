package dat109.hvl.no;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stigespill {

	public static void main(String[] args) {
		int antSpillere = 0;

		Terning terning = new Terning();
		Boolean ferdig = false;
		
		Scanner scan = new Scanner(System.in);
		
		while (antSpillere <= 0 || antSpillere > 4) {
			System.out.println("Skriv inn antall spillere, det kan være 2-4 stk");
			antSpillere = scan.nextInt();
		}
			/**
			 * Legger til spillere i en liste
			 */
		List<Spiller> spillere = new ArrayList<Spiller>();
		for (int i = 1; i <= antSpillere; i++){
			Spiller spiller = new Spiller("Spiller " + i);
			spillere.add(spiller);
		}
		
		System.out.println(spillere.size());
		Brett brett = new Brett(spillere);
		brett.spill(spillere, terning);
		
	}

}
