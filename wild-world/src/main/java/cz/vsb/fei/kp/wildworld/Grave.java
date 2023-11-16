package cz.vsb.fei.kp.wildworld;

public class Grave extends Sprite {
	private String text;
	
	public Grave(Warrior attacked) {
		super("/hrob.png");
		text = String.format("%s %s's grave",attacked.getType(), attacked.getName());
	}
	public void Zakopat(Warrior w2) {
		w2.
		w2.setSpeed(0);
		w2.setRotationSpeed(0);
	}
}
