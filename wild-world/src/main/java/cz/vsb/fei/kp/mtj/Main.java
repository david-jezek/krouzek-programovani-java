package cz.vsb.fei.kp.mtj;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	static Random r = new Random();
	
	public static void main(String[] args) {
		ArrayList<Warrior> warriors = new ArrayList<Warrior>();
		
		warriors.add(randWarrior("Princ Krasoň")); 
		warriors.add(randWarrior("Alex Jones"));
		warriors.add(randWarrior("FIZIStyle"));
		warriors.add(randWarrior("Tary"));
		warriors.add(randWarrior("Amogus"));
		warriors.add(randWarrior("Melon Usk"));
		warriors.add(randWarrior("Tomio Okamura"));
		warriors.add(randWarrior("Petr Pavel"));
		warriors.add(randWarrior("Bill Gates"));
		warriors.add(new Warrior("Linus Trollvalds", 9999, 300, 0));
		warriors.add(randWarrior("Kung Fu Panda"));
		warriors.add(randWarrior("Pepa Troska"));
		
		for(;warriors.size() > 2;) {
			warriors.remove(r.nextInt(warriors.size()));
		}
		
		String MSG = String.format("%s [HP: %d ATK: %d DEF %d] VS. %s [HP: %d ATK: %d DEF %d], FIGHT TO THE DEATH!", warriors.get(0).getName(), warriors.get(0).getHP(), warriors.get(0).getAP(), warriors.get(0).getDEF(), warriors.get(1).getName(), warriors.get(1).getHP(), warriors.get(1).getAP(), warriors.get(1).getDEF());
		System.out.println(MSG);
		Warrior w1 = warriors.get(0);
		Warrior w2 = warriors.get(1);
		
		for (;true;) {
			int chance1 = r.nextInt(100);
			int chance2 = r.nextInt(100);
			
			if(w1.getAP() < w2.getDEF()) {
				w1.doATKBuff();
			} else if(chance1 < 25 && w1.getHP() < w1.getMaxHP()/2) {
				w1.doHeal();
			} /*else if(chance1 < 35) {
				w1.doDEFBuff();
			}*/ else {
				w2.hitBy(w1);
				if(w2.isDead()) {
					System.out.println(String.format("The fearful %s has been killed in battle by %s with %d HP left.", w2.getName(), w1.getName(), w1.getHP()));
					return;
				}
			}
			
			if(w2.getAP() < w2.getDEF()) {
				w2.doATKBuff();
			} else if(chance2 < 25 && w2.getHP() < w2.getMaxHP()/2) {
				w2.doHeal();
			} else if(chance2 < 35) {
				w2.doDEFBuff();
			} else {
				w1.hitBy(w2);
				if(w1.isDead()) {
					System.out.println(String.format("The tough %s has been killed in battle by %s with %d HP left.", w1.getName(), w2.getName(), w2.getHP()));
					return;
				}
			}
		}
	}
	
	private static Warrior randWarrior(String name) {
		Warrior warrior = new Warrior(name, 1000, r.nextInt(200), r.nextInt(150));
		return warrior;
	}
}

