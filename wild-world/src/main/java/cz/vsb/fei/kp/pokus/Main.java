package cz.vsb.fei.kp.pokus;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
        Random randomGenerator = new Random();
		ArrayList<Warrior> warriors = new ArrayList<>();
		warriors.add(new Knight("Princ Krasoň", 1000, 300, 450));
		warriors.add(new Knight("Alex"));
		warriors.add(new Knight("John Wick"));
		warriors.add(new Knight("Shrek"));
		warriors.add(new Archer("Hello Kitty"));
		warriors.add(new Knight("Prasatko Pepa"));
		warriors.add(new Archer("Bořek stavitel"));
		warriors.add(new Knight("Mario"));
		warriors.add(new Knight("Pat"));
		warriors.add(new Knight("Mat"));
		for (int i = 0; i < 20; i++) {
			int index1 = randomGenerator.nextInt(warriors.size());
			Warrior w1 = warriors.get(index1);
			if(w1 instanceof Archer a) {
				a.getArrowsCount();
			}
			Warrior w2;
			do {
				int index2 = randomGenerator.nextInt(warriors.size());
				w2 = warriors.get(index2);
			} while (w1.equals(w2));

			w2.attack(w1);
		}

	}
}
