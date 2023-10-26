package cz.vsb.fei.kp.mtj;

public class Warrior {
	protected String name;
	protected int AP;
	protected int HP;
	protected int DEF;
	protected int maxHP;
	
	public Warrior(String name, int maxHP, int AP, int DEF) {
		this.name = name;
		this.AP = AP;
		this.maxHP = maxHP;
		this.DEF = DEF;
		HP = maxHP;
	}
	
	
	
	public void attack(Warrior defender) {
		if(HP > 0) {
			defender.hitBy(this);
		}
	}

	protected void hitBy(Warrior attacker) {
		int DMG = attacker.getAP() - DEF;
		
		if(DMG <= 0) {
			System.out.println(String.format("%s tried to hit %s but failed because he could not get through his armour!", attacker.getName(), name));
			return;
		}
		
		HP -= DMG;
		
		System.out.println(String.format("%s has attacked %s for %d damage. He now has %d health.", attacker.getName(), name, DMG, HP));
	}
	
	public int getMaxHP() {
		return maxHP;
	}
	
	public boolean isDead() {
		if(HP <= 0) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDEF() {
		return DEF;
	}

	public int getAP() {
		return AP;
	}

	public int getHP() {
		return HP;
	}

	public void doHeal() {
		HP += AP/4;
		String MSG = String.format("%s patches himself up for %d HP. He now has %d health", name, AP/10, HP);
		System.out.println(MSG);
	}
	
	public void doATKBuff() {
		String MSG = String.format("%s drinks a potion of strength! Attack increased by 100!", name);
		System.out.println(MSG);
		AP += 100; 
	}
	
}
