package code.character;

import code.level.Difficulty;

public class Player_Stats {
	
	Difficulty Difficulty;
	
	boolean Alive = true;
	
	long LastUpdate = System.currentTimeMillis();
	
	long MovSpeedMP;
	long ShtSpeedMP;
	long BulletCountMP;
	long BulletTTLMP;
	long BulletMovSpeedMP;
	int ItemSpawnCooldownMP;
	int ItemSpawnChanceMP;
	long PassthroughTTL;
	
	public long CurrentMovSpeed;
	public long CurrentShtSpeed;
	public long CurrentBulletCount;
	public long CurrentBulletTTL;
	public long CurrentBulletMovSpeed;
	public int CurrentItemSpawnCooldown;
	public int CurrentItemSpawnChance;
	
	public boolean BulletPassthrough;
	public long TSLShoot = 0;
	
	public Player_Stats(Difficulty d) {
		Difficulty = d;
		
		CurrentMovSpeed = Difficulty.BaseMovSpeed;
		CurrentShtSpeed = Difficulty.BaseShtSpeed;
		CurrentBulletCount = Difficulty.BaseBulletCount;
		CurrentBulletTTL = Difficulty.BaseBulletTTL;
		CurrentBulletMovSpeed = Difficulty.BaseBulletMovSpeed;
		CurrentItemSpawnCooldown = Difficulty.BaseItemSpawnCooldown;
		CurrentItemSpawnChance = Difficulty.BaseItemSpawnChance;
	}
	
	public void Update() {
		PassthroughTTL = LastUpdate - System.currentTimeMillis();
		if(PassthroughTTL < 0) {
			BulletPassthrough = false;
			PassthroughTTL = 0;
		}
		LastUpdate = System.currentTimeMillis();
	}
	
	public void UpdateMovSpeedMultiplier(double value, boolean add) {
		if(add) {
			MovSpeedMP += value;
		}else {
			MovSpeedMP -= value;
		}
		CurrentMovSpeed = Difficulty.BaseMovSpeed * (MovSpeedMP * Difficulty.BaseMovSpeedMP);
	}
	
	public void UpdateShtSpeedMultiplier(double value, boolean add) {
		if(add) {
			ShtSpeedMP += value;
		}else {
			ShtSpeedMP -= value;
		}
		CurrentShtSpeed = Difficulty.BaseShtSpeed * (ShtSpeedMP * Difficulty.BaseShtSpeedMP);
	}
	
	public void UpdateBulletCountMultiplier(double value, boolean add) {
		if(add) {
			BulletCountMP += value;
		}else {
			BulletCountMP -= value;
		}
		CurrentBulletCount = Difficulty.BaseBulletCount * (BulletCountMP * Difficulty.BaseBulletCountMP);
	}
	
	public void UpdateBulletTTLMultiplier(double value, boolean add) {
		if(add) {
			BulletTTLMP += value;
		}else {
			BulletTTLMP -= value;
		}
		CurrentBulletTTL = Difficulty.BaseBulletTTL * (BulletTTLMP * Difficulty.BaseBulletTTLMP);
	}
	
	public void UpdateBulletMovSpeedMultiplier(double value, boolean add) {
		if(add) {
			BulletMovSpeedMP += value;
		}else {
			BulletMovSpeedMP -= value;
		}
		CurrentBulletMovSpeed = Difficulty.BaseBulletMovSpeed * (BulletMovSpeedMP * Difficulty.BaseBulletMovSpeedMP);
	}

	public void UpdateItemSpawnCooldownMultiplier(int value, boolean add) {
		if(add) {
			ItemSpawnCooldownMP += value;
		}else {
			ItemSpawnCooldownMP -= value;
		}
		CurrentItemSpawnCooldown = Difficulty.BaseItemSpawnCooldown * (ItemSpawnCooldownMP * Difficulty.BaseItemSpawnCooldownMP);
	}
	
	public void UpdateItemSpawnChanceMultiplier(int value, boolean add) {
		if(add) {
			ItemSpawnChanceMP += value;
		}else {
			ItemSpawnChanceMP -= value;
		}
		CurrentItemSpawnChance = Difficulty.BaseItemSpawnChance * (ItemSpawnChanceMP * Difficulty.BaseItemSpawnChanceMP);
	}
	
	public void UpdateBulletPassthrough(long TTL) {
		BulletPassthrough = true;
		if(TTL > PassthroughTTL) {
			PassthroughTTL = TTL;
		}
	}

	public void setAlive(boolean b) {
		Alive = b;
	}
	
	public boolean IsAlive() {
		return Alive;
	}

}
