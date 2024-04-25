package Pokemon;

public class test {

	public static void main(String[] args) {
		Pokemon pikachu = new Pokemon("피카츄", 5, "electric", 50, 300, 52);
		pikachu.setSkill1("fire", "불대문자");
		pikachu.setSkill2("grass", "리프스톰");
		pikachu.setSkill3("rock", "스톤샤워");
		Pokemon squirtle = new Pokemon("꼬부기", 10, "fire", 30, 300, 51);
		squirtle.setSkill1("fire", "불꽃세례");
		squirtle.setSkill2("grass", "우드해머");
		squirtle.setSkill3("electric", "10만볼트");

		Battle bt = new Battle(pikachu, squirtle);
		bt.startBattle(pikachu, squirtle);

	}

}
