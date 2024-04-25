package Pokemon;

public class Pokemon {
	private String name;
	private int level;
	private String type;
	private int atk;
	private int curHP;
	private int maxHP;
	private int speed;
	private String skill1;
	private String skill2;
	private String skill3;

	public static void main(String[] args) {
		// 포켓몬의 타입을 리스트로 생성합니다.

		// 포켓몬 객체 생성
		Pokemon pikachu = new Pokemon("피카츄", 5, "electric", 50, 100, 50);

		// 포켓몬 객체 생성
		Pokemon squirtle = new Pokemon("Squirtle", 10, "water", 30, 60, 51);
	}

	public Pokemon(String name, int level, String type, int attackPower, int maxHP, int speed) {
		this.name = name;
		this.level = level;
		this.type = type;
		this.atk = attackPower;
		this.maxHP = maxHP;
		this.curHP = maxHP; // 처음에는 최대 체력과 같습니다.
		this.speed = speed;
	}

	// 게터와 세터 메서드들
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getCurHP() {
		return curHP;
	}

	public void setCurHP(int curHP) {
		this.curHP = curHP;
	}

	// 피해를 입을 때 사용되는 메서드
	public void takeDamage(int damage) {
		int actualDamage = Math.max(1, damage);
		curHP -= actualDamage;
	}

	public int getHP() {
		return curHP;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// 포켓몬 정보 출력을 위한 toString 메서드
	@Override
	public String toString() {
		return "Pokemon{" + "name='" + name + '\'' + ", level=" + level + ", type=" + type + ", attackPower="
				+ atk + ", currentHP=" + curHP + ", maxHP=" + maxHP + ", currentHP=" + curHP
				+ ", speed=" + speed + '}';
	}

	public String getSkill(int input) {
		String result = null;
		if (input == 1) {
			result = skill1;
		} else if (input == 2) {
			result = skill2;
		} else if (input == 3) {
			result = skill3;
		}
		return result;
	}
}