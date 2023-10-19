package cz.minerik;

import java.util.ArrayList;
import java.util.Random;

public class HelloWorld {

	public static void main(String[] args) {
		Random random = new Random();
		ArrayList<Entity> warriors = new ArrayList<>();
		warriors.add(new Warrior("Bořek Stavitel"));
		warriors.add(new Warrior("Mat"));
		warriors.add(new Warrior("Pat"));
		warriors.add(new Warrior("John Wick"));
		warriors.add(new Warrior("Prasátko Pepa"));
		warriors.add(new Warrior("Mario"));
		warriors.add(new Archer("Princ Krasoň"));
		warriors.add(new Archer("Alex"));
		warriors.add(new Archer("Shrek"));
		warriors.add(new Archer("Hello Kitty"));
		for(int i = 0; i<50; i++) {
			int a = random.nextInt(warriors.size());
			int b = random.nextInt(warriors.size());
			Entity w1 = warriors.get(a);
			Entity w2 = warriors.get(b);
			if(w1.isAlive()) {
				if(w2.isAlive()) {
					w1.attack(w2);
				}
			}
		}
		for(Entity warrior : warriors) {
			warrior.printStatus();
		}
	}
}
