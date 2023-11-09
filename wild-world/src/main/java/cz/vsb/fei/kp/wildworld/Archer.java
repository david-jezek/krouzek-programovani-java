package cz.vsb.fei.kp.wildworld;

public class Archer extends Warrior{
	/*private String name;
	private int AP;
	private int HP;
	private int DEF;
	private int maxHP;*/
	private int arrows;
	
	public Archer(String obraz, String name, int maxHP, int AP, int DEF, int arrows) {
		super(obraz, name, maxHP, AP, DEF);
		this.arrows = arrows;
	}
	
	
	
	public int getArrows() {
		return arrows;
	}



	public void setArrows(int arrows) {
		this.arrows = arrows;
	}

	public void attack(Warrior defender) {
		if(HP > 0 && arrows > 0) {
			defender.hitBy(this);
			arrows--;
		} else if(HP > 0 && arrows <= 0) {
			System.out.println(String.format("%s cannot attack %s because he ran out of arrows!", this.name, defender.getName()));
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
}
