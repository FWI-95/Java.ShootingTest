package code.level;

public class Difficulty {
	Difficulty CurrentDifficulty;

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
				CurrentDifficulty = new Difficulty_Easy();
				break;
			case MEDIUM:
				CurrentDifficulty = new Difficulty_Medium();
				break;
			case HARD:
				CurrentDifficulty = new Difficulty_Hard();
				break;
			case NIGHTMARE:
				CurrentDifficulty = new Difficulty_Nightmare();
				break;
		}
	}

	public Difficulty(){}

	public long GetBaseMovSpeed(){return CurrentDifficulty.BaseMovSpeed;};
	public long GetBaseShtSpeed(){return CurrentDifficulty.BaseShtSpeed;};
	public long GetBaseBulletCount(){return CurrentDifficulty.BaseBulletCount;};
	public long GetBaseBulletTTL(){return CurrentDifficulty.BaseBulletTTL;};
	public long GetBaseBulletMovSpeed(){return CurrentDifficulty.BaseBulletMovSpeed;};
	public int GetBaseItemSpawnCooldown(){return CurrentDifficulty.BaseItemSpawnCooldown;};
	public int GetBaseItemSpawnChance(){return CurrentDifficulty.BaseItemSpawnChance;};
	
	public long GetBaseMovSpeedMP(){return CurrentDifficulty.BaseMovSpeedMP;};
	public long GetBaseShtSpeedMP(){return CurrentDifficulty.BaseShtSpeedMP;};
	public long GetBaseBulletCountMP(){return CurrentDifficulty.BaseBulletCountMP;};
	public long GetBaseBulletTTLMP(){return CurrentDifficulty.BaseBulletTTLMP;};
	public long GetBaseBulletMovSpeedMP(){return CurrentDifficulty.BaseBulletMovSpeedMP;};
	public int GetBaseItemSpawnCooldownMP(){return CurrentDifficulty.BaseItemSpawnCooldownMP;};
	public int GetBaseItemSpawnChanceMP(){return CurrentDifficulty.BaseItemSpawnChanceMP;};
	public long GetBasePassthroughTTL(){return CurrentDifficulty.BasePassthroughTTL;};

}
