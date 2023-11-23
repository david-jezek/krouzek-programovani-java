package cz.vsb.fei.kp.wildworld;

import java.util.Random;


public class Swordsman extends Warrior{
	/*private String name;
	private int AP;
	private int HP;
	private int DEF;
	private int maxHP;*/
	
	private Random r = new Random();
	
	public Swordsman(String obraz, String name, int maxHP, int AP, int DEF) {
		super(obraz, name, maxHP, AP, DEF);
	}
	
	/*public void attack(Warrior defender) {
		if(HP > 0) {
			defender.hitBy(this);
			
		}
	}*/

	protected void hitBy(Warrior attacker) {
		int DMG = attacker.getAP() - DEF;
		if(DMG <= 0) {
			System.out.println(String.format("%s tried to hit %s but failed because he could not get through his armour!", attacker.getName(), name));
			return;
		} else if (r.nextInt(100) < 25) {
			System.out.println(String.format("%s tried to hit %s but failed as %s blocked the hit with his shield!", attacker.getName(), name, name));
		}
		
		HP -= DMG;
		
		System.out.println(String.format("%s has attacked %s for %d damage. He now has %d health.", attacker.getName(), name, DMG, HP));
	}
	
	public void doDEFBuff() {
		String MSG = String.format("%s has increased his defence stat by %d, now totalling %d.", name, DEF/10 + 40, DEF + DEF/10 + 40);
		System.out.println(MSG);
		DEF += DEF/10 + 40;
	}
}
