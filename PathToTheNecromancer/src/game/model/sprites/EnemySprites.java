package game.model.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class EnemySprites extends GameSprites 
{
	private int Attack;
	private int Defense;
	private int Dexterity;
	private int luck;
	private String[] banter; //Banter to be brought up in combat
	private String[] IntroBanter; //Banter to be brought up on the first attack scene
	private Texture texture;
	
	private int BantCount;
	private int IntroBanterCount;
	

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public int getAttack() {
		return Attack;
	}

	public void setAttack(int attack) {
		Attack = attack;
	}

	public int getDefense() {
		return Defense;
	}

	public void setDefense(int defense) {
		Defense = defense;
	}

	public int getDexterity() {
		return Dexterity;
	}

	public void setDexterity(int dexterity) {
		Dexterity = dexterity;
	}	
	
	public String getBanter()
	{
		return this.banter[(int)Math.ceil(Math.random() * (this.banter.length -1))]; //returns 0 to ban
	}
	
	public String getIntroBanter()
	{
		return this.IntroBanter[(int)Math.ceil(Math.random() * (this.banter.length -1))]; //returns 0 to ban
	}
}

