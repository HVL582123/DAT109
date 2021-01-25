package dat109.hvl.no;

import java.util.Random;

public class Terning {

	public Terning() {
		// TODO Auto-generated constructor stub
	}

		/**
		 * Returneres et tall mellom 1-6, som representerer 
		 * antall øyne på terningen som blir trilt
		 * */
	public static int trill() {
		return new Random().nextInt(6) + 1;

	}

}
