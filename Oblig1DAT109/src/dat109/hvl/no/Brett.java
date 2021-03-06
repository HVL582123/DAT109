package dat109.hvl.no;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Brett {
	private final int STORRELSE = 100;
	private final int ANTALL_RUNDER = 10;
	private int runderSpilt = 0;

	private static Map<Integer, Integer> slange = new HashMap<>();
	private static Map<Integer, Integer> stige = new HashMap<>();

	Map<Spiller, Integer> spillerposisjon;

	/**
	 * Lager nytt brett med gitt liste av spillere. Plasserer dem s� p� rute 1 (Start)
	 * Legger til stiger og slanger. Disse er forh�ndsdefinert, ikke random. 
	 */
	public Brett(List<Spiller> spillere) {

		this.spillerposisjon = new HashMap<Spiller, Integer>();

		for (Spiller spiller : spillere) {
			this.spillerposisjon.put(spiller, 1);
		}

		leggTilSlanger();
		leggTilStiger();
	}

	/**
	 * 
	 * Flytter spiller posisjon + oyne fram p� brettet
	 */
	public Boolean flyttSpiller(Spiller spiller, int oyne) {
		boolean ferdig = false;
		System.out.println(spiller.getNavn() + " triller..." + oyne);
		// Oppdaterer spillerens posisjon med det den fikk p� terningkastet
		int posisjon = spillerposisjon.get(spiller);
		posisjon += oyne;

		if (posisjon > STORRELSE) { // Spilleren fikk over 100, blir st�ende p� sin posisjon
			posisjon -= oyne;
			spillerposisjon.put(spiller, posisjon);
			System.out.println(spiller.getNavn() + " st�r p� posisjon " + posisjon);
		} else {
			if (posisjon == STORRELSE) { // Spilleren vinner
				spillerposisjon.put(spiller, posisjon);
				System.out.println(spiller.getNavn() + "vant!!");
				ferdig = true;
			}
			if (null != slange.get(posisjon)) { // Spilleren treffer slange
				System.out.println(
						spiller.getNavn() + " traff p� en slange... Blir flyttet til posisjon " + slange.get(posisjon));
				posisjon = slange.get(posisjon);
				spillerposisjon.put(spiller, posisjon);
			}
			if (null != stige.get(posisjon)) { // Spilleren treffer stige
				if (stige.get(posisjon) == 100) {
					System.out.println(spiller.getNavn() + " traff p� vinnerstigen!!");
					ferdig = true;
				} else {
					System.out.println(
							spiller.getNavn() + " traff p� en stige! Blir flyttet til posisjon " + stige.get(posisjon));
					posisjon = stige.get(posisjon);
					spillerposisjon.put(spiller, posisjon);
				}
			} else {
				spillerposisjon.put(spiller, posisjon);
				System.out.println(spiller.getNavn() + " st�r p� posisjon " + posisjon);
			}
		}

		return ferdig;
	}

	private void leggTilStiger() {
		stige.put(2, 38);
		stige.put(4, 14);
		stige.put(8, 31);
		stige.put(28, 84);
		stige.put(21, 42);
		stige.put(36, 44);
		stige.put(51, 67);
		stige.put(71, 91);
		stige.put(80, 100);

	}

	private void leggTilSlanger() {

		slange.put(98, 78);
		slange.put(95, 75);
		slange.put(92, 73);
		slange.put(87, 24);
		slange.put(64, 60);
		slange.put(62, 18);
		slange.put(56, 53);
		slange.put(47, 26);
		slange.put(49, 11);
		slange.put(16, 5);
	}

	/**
	 * Sjekker om vi har n�dd det maksimale antall runder for brettet.
	 */
	public boolean maksRunder() {
		boolean erFerdig = false;

		if (this.runderSpilt == ANTALL_RUNDER) {
			erFerdig = true;
			System.out.println("Det er spilt " + runderSpilt + " runder!");
			System.out.println("Maks antall runder n�dd!");
		} else {
			System.out.println("Det er spilt " + runderSpilt + " runder!" + "\n");
		}

		return erFerdig;
	}

	/**
	 * Starter spillet med gitt liste av spillere, og med ternings-objekt. Det vil
	 * bli sjekket etter siste spillers kast om det er flere runder igjen � spille.
	 */
	public void spill(List<Spiller> spillere, Terning terning) {
		boolean spillFerdig = false;
		int spillerantall = spillere.size();
		int spillersTur = 0;

		while (!spillFerdig) {
			Spiller denne = spillere.get(spillersTur);

			int oyne = Terning.trill();
			spillFerdig = flyttSpiller(denne, oyne);

			spillersTur++;
			this.runderSpilt++;

			if (spillersTur == spillerantall) {
				spillersTur = 0;
				if (maksRunder()) {
					spillFerdig = true;
				}
			}
		}

	}
}
