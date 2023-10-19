package cz.minerik;

public class Monsters {
	
	private String name;
	private int healt;
	private int strenght;
	private int deffence;
	
	public Monsters(String name, int healt, int strenght) {
		this.name = name;
		this.strenght = strenght;
	}
	
	public String getName() {
		return name;
	}
	
	public Boolean isAlive() {
		if(healt <= 0) {
			return false;
		}
		return true;
	}
	
	public void doAttack(String toEnemy) {
		System.out.println(String.format("Monster %s do attack with strenght %d to the %s.", this.name, this.strenght, toEnemy));
	}
	public void reciveAttack(String fromEnemy) {
		System.out.println(String.format("Monster %s got attack with strenght %d from %s.", this.name, this.strenght, fromEnemy));
	}
}
