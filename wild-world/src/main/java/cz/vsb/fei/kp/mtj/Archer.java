package cz.vsb.fei.kp.mtj;

public class Archer extends Warrior{
	/*private String name;
	private int AP;
	private int HP;
	private int DEF;
	private int maxHP;*/
	private int arrows;
	
	public Archer(String name, int maxHP, int AP, int DEF, int arrows) {
		super(name, maxHP, AP, DEF);
		this.arrows = arrows;
	}
	
	
	
	public int getArrows() {
		return arrows;
	}



	public void setArrows(int arrows) {
		this.arrows = arrows;
	}

	public void attack(Archer defender) {
		if(HP > 0 && arrows > 0) {
			defender.hitBy(this);
			arrows--;
		}
	}

	private void hitBy(Archer attacker) {
		int DMG = attacker.getAP() - DEF;
		
		if(DMG <= 0) {
			System.out.println(String.format("%s tried to hit %s but failed because he could not get through his armour!", attacker.getName(), name));
			return;
		}
		
		HP -= DMG;
		
		System.out.println(String.format("%s has attacked %s for %d damage. He now has %d health.", attacker.getName(), name, DMG, HP));
	}
}
