package cz.vsb.fei.kp.wildworld;

public class Grave extends Sprite {

	private String text;

	public Grave(Warrior warrior) {
		super("rip.png");
		text = String.format("RIP %s", warrior.getName());
	}
}
