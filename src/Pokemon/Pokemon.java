package Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pokemon {
	private String name;
	private int level;
	private List<String> types;
	private int attackPower;
	private int currentHP;
	private int maxHP;
	private int speed;

	public static void main(String[] args) {
		// 포켓몬의 타입을 리스트로 생성합니다.
		List<String> pikachuTypes = new ArrayList<>();
		pikachuTypes.add("electric");

		// 포켓몬 객체 생성
		Pokemon pikachu = new Pokemon("피카츄", 5, pikachuTypes, 50, 100, 50);

		List<String> squirtleTypes = new ArrayList<>();
		squirtleTypes.add("water");

		// 포켓몬 객체 생성
		Pokemon squirtle = new Pokemon("Squirtle", 10, squirtleTypes, 30, 60, 51);
	}

	public Pokemon(String name, int level, List<String> types, int attackPower, int maxHP, int speed) {
		this.name = name;
		this.level = level;
		this.types = types;
		this.attackPower = attackPower;
		this.maxHP = maxHP;
		this.currentHP = maxHP; // 처음에는 최대 체력과 같습니다.
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

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public int attack() {
		// 공격력을 랜덤하게 조정하여 공격
		Random rand = new Random();
		double damageMultiplier = 0.8 + (1.2 - 0.8) * rand.nextDouble();
		return (int) (attackPower * damageMultiplier);
	}

	// 피해를 입을 때 사용되는 메서드
	public void takeDamage(int damage) {
		int actualDamage = Math.max(0, damage);
		currentHP -= actualDamage;
	}

	public int getHP() {
		return currentHP;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	private boolean hasPriorityMove;

	public Pokemon(String name, int level, int maxHP, int attackPower, int defensePower, int speed,
			boolean hasPriorityMove) {
		// 기존 생성자 내용
		this.hasPriorityMove = hasPriorityMove;
	}

	public boolean hasPriorityMove() {
		return hasPriorityMove;
	}

	// 포켓몬 정보 출력을 위한 toString 메서드
	@Override
	public String toString() {
		return "Pokemon{" + "name='" + name + '\'' + ", level=" + level + ", types=" + types + ", attackPower="
				+ attackPower + ", currentHP=" + currentHP + ", maxHP=" + maxHP + ", currentHP=" + currentHP
				+ ", speed=" + speed + '}';
	}
}