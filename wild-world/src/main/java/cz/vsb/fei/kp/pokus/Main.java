package cz.vsb.fei.kp.pokus;

import java.util.ArrayList;
import java.util.Random;

import cz.vsb.fei.kp.wildworld.Warrior;

public class Main {

	public static void main(String[] args) {
        Random randomGenerator = new Random();
		ArrayList<Warrior> warriors = new ArrayList<>();
		warriors.add(new Warrior("Princ Krasoň", 1000, 300, 450));
		warriors.add(new Warrior("Alex"));
		warriors.add(new Warrior("John Wick"));
		warriors.add(new Warrior("Shrek"));
		warriors.add(new Warrior("Hello Kitty"));
		warriors.add(new Warrior("Prasatko Pepa"));
		warriors.add(new Warrior("Bořek stavitel"));
		warriors.add(new Warrior("Mario"));
		warriors.add(new Warrior("Pat"));
		warriors.add(new Warrior("Mat"));
		for (int i = 0; i < 20; i++) {
			int index1 = randomGenerator.nextInt(warriors.size());
			Warrior w1 = warriors.get(index1);
			Warrior w2;
			do {
				int index2 = randomGenerator.nextInt(warriors.size());
				w2 = warriors.get(index2);
			} while (w1.equals(w2));

			w1.attackedBy(w2);
		}

	}
}
