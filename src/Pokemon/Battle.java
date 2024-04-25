package Pokemon;

import java.util.Random;
import java.util.Scanner;

public class Battle {
	// 살려주세요
	Random rand = new Random();
	Scanner sc = new Scanner(System.in);
	private Pokemon playerPokemon;
	private Pokemon opponentPokemon;

	public Battle(Pokemon playerPokemon, Pokemon opponentPokemon) {
		this.playerPokemon = playerPokemon;
		this.opponentPokemon = opponentPokemon;
	}

	public String calculateDamage(Pokemon attacker, Pokemon defender, int input) {

		// 공격자의 스킬 타입 정보와 방어자의 타입 정보 가져오기
		String skillType = attacker.getSkillType(input);
		String defenderType = defender.getType();

		// 상성 정보에 따라 데미지 배수 가져오기(TypeChart)
		double damageMultiplier = TypeChart.getDamageMultiplier(skillType, defenderType);

		// 상성 관계에 따른 메세지 저장
		String result = null;
		if (damageMultiplier == 0.5) {
			result = "효과가 별로인 듯하다...";
		} else if (damageMultiplier == 2.0) {
			result = "효과가 굉장했다!";
		} else {
			result = "";
		}
		// rand.nextDouble() -> 0 ~ 1까지의 실수를 랜덤으로 가져옴
		// 85% ~ 100% 의 데미지를 랜덤으로 입히기 위함
		damageMultiplier *= 0.85 + 0.15 * rand.nextDouble();

		// 공격자의 공격력 수치에 damageMultiplier 곱하고 정수값으로 변환
		int damage = (int) (attacker.getAtk() * damageMultiplier);

		// 방어자에게 최종 데미지 적용
		defender.takeDamage(damage);
		// 메세지 반환
		return result;
	}

	public void startBattle(Pokemon attacker, Pokemon defender) {
		// 배틀 첫 진입 시 사용자의 체력이 0 이상인지 확인
		if (playerPokemon.getCurHP() > 0) {
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
			// 누구라도 체력이 0이하가 될때까지 반복 또는 도망갈때까지 반복
			while (playerPokemon.getCurHP() > 0 && opponentPokemon.getCurHP() > 0) {

				System.out.println(playerPokemon.getName() + "은 무엇을 할까?\n[1]싸운다 [2]도망간다");
				// 사용자 입력값
				int input = sc.nextInt();

				// 사용자가 싸운다를 선택한 경우
				if (input == 1) {

					System.out.println("[1]" + playerPokemon.getSkillName(1) + "\t[2]" + playerPokemon.getSkillName(2) + "\n[3]"
							+ playerPokemon.getSkillName(3));
					// 사용자가 사용할 스킬을 선택받아오기
					int pSkill = sc.nextInt();
					int eSkill = rand.nextInt(3) + 1;
					if (first == playerPokemon) {
						// 선공자가 플레이어일 경우 입력값에따라 스킬을 가져옴
						String message1 = calculateDamage(first, second, pSkill);
						System.out.println(first.getName() + "의 " + first.getSkillName(pSkill) + "!");
						System.out.println(message1);
						// 후공자 체력 확인 후 공격
						// 후공자가 적이면 랜덤으로 스킬을 가져옴
						if (second.getCurHP() > 0) {
							String message2 = calculateDamage(second, first, eSkill);
							System.out.println(second.getName() + "의 " + second.getSkillName(eSkill) + "!");
							System.out.println(message2);
						}

					} else {
						// 선공자가 적일 경우 랜덤으로 스킬을 가져옴
						String message1 = calculateDamage(first, second, eSkill);
						System.out.println(first.getName() + "의 " + first.getSkillName(eSkill) + "!");
						System.out.println(message1);
						// 후공자 체력 확인 후 공격
						// 후공자가 플레이어일 경우 입력값에따라 스킬을 가져옴
						if (second.getCurHP() > 0) {
							String message2 = calculateDamage(second, first, pSkill);
							System.out.println(second.getName() + "의 " + second.getSkillName(pSkill) + "!");
							System.out.println(message2);
						}
					}
				} else {
					break; // 도망갈 경우
				}
				// 전투 상태 출력
				System.out.println(playerPokemon.getName() + "의 체력: " + playerPokemon.getCurHP());
				System.out.println(opponentPokemon.getName() + "의 체력: " + opponentPokemon.getCurHP());
				System.out.println();
			}
			// 전투 종료 메시지 출력
			// 자신의 체력이 0이하라면
			if (playerPokemon.getCurHP() <= 0) {
				System.out.println("패배! " + playerPokemon.getName() + "이(가) 쓰러졌다...");

				// 적의 체력이 0 이하라면
			} else if (opponentPokemon.getCurHP() <= 0) {
				System.out.println("승리! " + playerPokemon.getName() + "이(가) 이겼다!");

				// 둘다 체력이 0 초과인 상태에서 도망가기로 전투를 끝냈을 경우
			} else {
				System.out.println("무사히 도망쳤다!");

			}
			// 배틀 첫 진입 시 사용자의 체력이 0 미만이라면 회복이 필요하다고 해줘야함
		} else {

		}
		sc.close();
		}

}