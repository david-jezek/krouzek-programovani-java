package cz.vsb.fei.kp.mtj;

public class Archer {
	private String name;
	private int AP;
	private int HP;
	private int DEF;
	private int maxHP;
	
	public Archer(String name, int maxHP, int AP, int DEF) {
		this.name = name;
		this.AP = AP;
		this.maxHP = maxHP;
		this.DEF = DEF;
		HP = maxHP;
	}
	
	public void hitBy(Archer attacker) {
		int DMG;
		if(attacker.getAP() > DEF) {
			DMG = attacker.getAP() - DEF;
		} else {
			DMG = 0;
		}
		HP -= DMG;
		String MSG = String.format("%s has attacked %s for %d damage. He now has %d health.", attacker.getName(), name, DMG, HP);
		System.out.println(MSG);
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
	
	public void doATKBuff() {
		String MSG = String.format("%s drinks a potion of strength! Attack increased by 100!", name);
		System.out.println(MSG);
		AP += 100; 
	}
	
	public void doDEFBuff() {
		String MSG = String.format("%s has increased his defence stat by %d, now totalling %d.", name, DEF/10 + 40, DEF + DEF/10 + 40);
		System.out.println(MSG);
		DEF += DEF/10 + 40;
	}

	public void doHeal() {
		HP += AP/4;
		String MSG = String.format("%s patches himself up for %d HP. He now has %d health", name, AP/10, HP);
		System.out.println(MSG);
	}
}
