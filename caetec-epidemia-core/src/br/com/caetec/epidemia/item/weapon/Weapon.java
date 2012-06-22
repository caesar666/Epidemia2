package br.com.caetec.epidemia.item.weapon;

public abstract class Weapon
{
	public static boolean IS_PRESSED = false;
	private int timeToNextShot = getDelay();

	public abstract int getDelay();

	public void update(long time)
	{
		if (timeToNextShot < 10000)
			timeToNextShot += time;
	}

	public boolean shouldShot()
	{
		boolean should = IS_PRESSED && timeToNextShot >= getDelay();
		if (should)
		{
			timeToNextShot = 0;
			return true;
		}

		return false;
	}

}
