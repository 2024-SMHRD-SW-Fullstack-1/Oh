package Pokemon;

import java.util.Random;

public class Battle {
	// 살려주세요
	Random rand = new Random();
	private Pokemon playerPokemon;
	private Pokemon opponentPokemon;

	public Battle(Pokemon playerPokemon, Pokemon opponentPokemon) {
		this.playerPokemon = playerPokemon;
		this.opponentPokemon = opponentPokemon;
	}

	public void calculateDamage(Pokemon attacker, Pokemon defender, int input) {
		// 공격자와 방어자의 타입 정보 가져오기
		String skillType = attacker.getSkill(input);
		String defenderType = defender.getType();

		// 상성 정보에 따라 데미지 배수 가져오기
		double damageMultiplier = TypeChart.getDamageMultiplier(skillType, defenderType);
		damageMultiplier *= 0.85 + (0.15) * rand.nextDouble();
		// 데미지 계산
		int damage = (int) (attacker.getAtk() * damageMultiplier);

		// 최종 데미지 적용
		defender.takeDamage(damage);
	}

	public void startBattle(Pokemon attacker, Pokemon defender) {
		System.out.println("전투 시작!");

		// 선공 후공을 결정하기 위해 스피드 비교
		Pokemon first;
		Pokemon second;
		if (playerPokemon.getSpeed() >= opponentPokemon.getSpeed()) {
			first = playerPokemon;
			second = opponentPokemon;
		} else {
			first = opponentPokemon;
			second = playerPokemon;
		}
		while (playerPokemon.getCurHP() > 0 && opponentPokemon.getCurHP() > 0) {
			int inputU = sc.nextInt();
			calculateDamage(first, second, inputU);
			// 후공자 체력 확인 후 공격
			if (second.getCurHP() > 0) {
				calculateDamage(second, first, rand.nextInt(3) + 1);
			}
			// 전투 상태 출력
			System.out.println(playerPokemon.getName() + "의 체력: " + playerPokemon.getCurHP());
			System.out.println(opponentPokemon.getName() + "의 체력: " + opponentPokemon.getCurHP());
			System.out.println();
		}
		// 전투 종료 메시지 출력
		if (playerPokemon.getCurHP() <= 0) {
			System.out.println("패배! " + playerPokemon.getName() + "이(가) 쓰러졌다...");
		} else {
			System.out.println("승리! " + playerPokemon.getName() + "이(가) 이겼다!");
		}

	}

}