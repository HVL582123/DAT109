package dat109.hvl.no;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stigespill {

	public static void main(String[] args) {
		Terning terning = new Terning();
		int antSpillere = 1;
		
		Scanner scan = new Scanner(System.in);
		
		while (antSpillere <= 1 || antSpillere > 4) {
			System.out.println("Skriv inn antall spillere, det kan være 2-4 stk");
			antSpillere = scan.nextInt();
		}
		
		List<Spiller> spillere = new ArrayList<Spiller>();
		for (int i = 1; i <= antSpillere; i++){
			Spiller spiller = new Spiller("Spiller " + i);
			spillere.add(spiller);
		}
		
		Brett brett = new Brett(spillere);
		brett.spill(spillere, terning);
		
	}

}
