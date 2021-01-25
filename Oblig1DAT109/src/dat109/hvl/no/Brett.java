package dat109.hvl.no;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Brett {
	private final int STORRELSE = 100;
	private final int ANTALL_RUNDER = 20;
	private int runderSpilt = 0;

	private static Map<Integer, Integer> slange = new HashMap<>();
	private static Map<Integer, Integer> stige = new HashMap<>();

	Map<Spiller, Integer> spillerposisjon;

	/**
	 * Lager nytt brett med gitt liste av spillere. Plasserer dem så på rute 1
	 * (Start)
	 */
	public Brett(List<Spiller> spillere) {

		this.spillerposisjon = new HashMap<Spiller, Integer>();

		// Setter alle spillerene på rute 1
		for (Spiller s : spillere) {
			this.spillerposisjon.put(s, 1);
		}

		leggTilSlanger();
		leggTilStiger();
	}

	/**
	 * 
	 * Flytter spiller posisjon + oyne fram på brettet
	 */
	public Boolean flyttSpiller(Spiller spiller, int oyne) {
		boolean ferdig = false;
		System.out.println(spiller.getNavn() + " triller..." + oyne);
		// Oppdaterer spillerens posisjon med det den fikk på terningkastet
		int posisjon = spillerposisjon.get(spiller);
		posisjon += oyne;

		if (posisjon > STORRELSE) { // Spilleren fikk over 100, blir stående på sin posisjon
			posisjon -= oyne;
			spillerposisjon.put(spiller, posisjon);
			System.out.println(spiller.getNavn() + " står på posisjon " + posisjon);
		} else {
			if (posisjon == STORRELSE) { // Spilleren vinner
				spillerposisjon.put(spiller, posisjon);
				ferdig = true;
			}
			if (null != slange.get(posisjon)) { // Spilleren treffer slange
				System.out.println(
						spiller.getNavn() + " traff på en slange... Blir flyttet til posisjon " + slange.get(posisjon));
				posisjon = slange.get(posisjon);
				spillerposisjon.put(spiller, posisjon);
			}
			if (null != stige.get(posisjon)) { // Spilleren treffer stige
				if (stige.get(posisjon) == 100) {
					System.out.println(spiller.getNavn() + " traff på vinnerstigen!!");
					ferdig = true;
				} else {
					System.out.println(
							spiller.getNavn() + " traff på en stige! Blir flyttet til posisjon " + stige.get(posisjon));
					posisjon = stige.get(posisjon);
					spillerposisjon.put(spiller, posisjon);
				}
			} else {
				spillerposisjon.put(spiller, posisjon);
				System.out.println(spiller.getNavn() + " står på posisjon " + posisjon);
			}
		}

		return ferdig;
	}

	/**
	 * Legger til stiger i spillet vårt. Ikke random
	 */
	private void leggTilStiger() {
		stige.put(2, 38);
//		stige.put(4, 14);
		stige.put(8, 31);
//		stige.put(28, 84);
		stige.put(21, 42);
//		stige.put(36, 44);
		stige.put(51, 67);
//		stige.put(71, 91);
		stige.put(80, 99);

	}

	/**
	 * Legger til slanger i spillet vårt. Ikke random
	 */
	private void leggTilSlanger() {

		slange.put(98, 78);
//		slange.put(95, 75);
//		slange.put(92, 73);
		slange.put(87, 24);
//		slange.put(64, 60);
//		slange.put(62, 18);
		slange.put(56, 53);
//		slange.put(47, 26);
//		slange.put(49, 11);
		slange.put(16, 5);
	}

	/**
	 * Sjekker om vi har nådd det maksimale antall runder for brettet.
	 * */
	public boolean maksRunder() {
		boolean erFerdig = false;
		runderSpilt++;
		if (runderSpilt == ANTALL_RUNDER) {
			erFerdig = true;
			System.out.println("Det er spilt " + runderSpilt + " runder!");
			System.out.println("Maks antall runder nådd!");
		} else {
			System.out.println("Det er spilt " + runderSpilt + " runder!" + "\n");
		}

		return erFerdig;
	}
	/**
	 * Starter spillet med gitt liste av spillere, og med ternings-objekt. 
	 * Det vil bli sjekket etter siste spillers kast om det er flere runder igjen å spille.
	 * */
	public void spill(List<Spiller> spillere, Terning terning) {
		int spillersTur = 0;
		boolean ferdig = false;
		int spillerantall = spillere.size();

		while (!ferdig) {
			Spiller denne = spillere.get(spillersTur);
			int oyne = Terning.trill();

			ferdig = flyttSpiller(denne, oyne);
			if (ferdig) {
				System.out.println(denne.getNavn() + " vant spillet!");
			}

			spillersTur++;
			if (spillersTur == spillerantall) {
				spillersTur = 0;
				if (maksRunder()) {
					ferdig = true;
				}
			}
		}

	}
}
