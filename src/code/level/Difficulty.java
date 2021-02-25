package code.level;

public class Difficulty {
	public long BaseMovSpeed;
	public long BaseShtSpeed;
	public long BaseBulletCount;
	public long BaseBulletTTL;
	public long BaseBulletMovSpeed;
	public int BaseItemSpawnCooldown;
	public int BaseItemSpawnChance;
	
	public long BaseMovSpeedMP;
	public long BaseShtSpeedMP;
	public long BaseBulletCountMP;
	public long BaseBulletTTLMP;
	public long BaseBulletMovSpeedMP;
	public int BaseItemSpawnCooldownMP;
	public int BaseItemSpawnChanceMP;
	public long BasePassthroughTTL;

	public Difficulty(Difficulties curdiff) {
		switch(curdiff){
			case EASY:
				this = new Difficulty_Easy();
				break;
			case MEDIUM:

				break;
			case HARD:
				break;
			case NIGHTMARE:
				break;
		}
	}
}
