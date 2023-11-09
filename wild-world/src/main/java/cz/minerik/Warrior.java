package cz.minerik;

import java.util.Random;

public class Warrior extends Entity {
	
	public Warrior(String name) {
		this("/giphy.gif", name, random.nextInt(1000), random.nextInt(750), random.nextInt(500));
	}
	
	public Warrior(String image, String name, int maxHealth, int strenght, int deffence) {
		super("/giphy.gif", name, maxHealth, strenght, deffence);
	}
	
}
