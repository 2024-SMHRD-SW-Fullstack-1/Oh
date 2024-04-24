package Pokemon;

import java.util.List;

public class Battle {
	private Pokemon playerPokemon;
	private Pokemon opponentPokemon;
	private String skillType;

	public Battle(Pokemon playerPokemon, Pokemon opponentPokemon) {
		this.playerPokemon = playerPokemon;
		this.opponentPokemon = opponentPokemon;
	}

	public void calculateDamage(Pokemon attacker, Pokemon defender) {
		// 공격자와 방어자의 타입 정보 가져오기
		List<String> attackerType = attacker.getTypes();
		List<String> defenderType = defender.getTypes();

		// 상성 정보에 따라 데미지 배수 가져오기
		double damageMultiplier = TypeChart.getDamageMultiplier(skillType, defenderType);

		// 데미지 계산
		int damage = (int) (attacker.getAttackPower() * damageMultiplier);

		// 최종 데미지 적용
		defender.takeDamage(damage);
		System.out.println(attacker.getName() + "이(가) " + defender.getName() + "에게 " + damage + "의 피해를 입혔다!");
	}

	 public void startBattle() {
	        System.out.println("전투 시작!");

	        // 선공 후공을 결정하기 위해 스피드 비교
	        Pokemon firstAttacker;
	        Pokemon secondAttacker;
	        if (playerPokemon.getSpeed() >= opponentPokemon.getSpeed()) {
	            firstAttacker = playerPokemon;
	            secondAttacker = opponentPokemon;
	        } else {
	            firstAttacker = opponentPokemon;
	            secondAttacker = playerPokemon;
	        }

	        while (playerPokemon.getCurrentHP() > 0 && opponentPokemon.getCurrentHP() > 0) {
	            // 선제공격 기술 여부 확인
	            boolean isFirstMovePriority = firstAttacker.hasPriorityMove();

	            // 선공자 공격
	            if (isFirstMovePriority) {
	                int damage = firstAttacker.attack();
	                secondAttacker.takeDamage(damage);
	                System.out.println(firstAttacker.getName() + "이(가) " + secondAttacker.getName() + "에게 " + damage + "의 피해를 입혔다!");
	            }

	            // 후공자 체력 확인 후 공격
	            if (secondAttacker.getCurrentHP() > 0) {
	                int damage = secondAttacker.attack();
	                firstAttacker.takeDamage(damage);
	                System.out.println(secondAttacker.getName() + "이(가) " + firstAttacker.getName() + "에게 " + damage + "의 피해를 입혔다!");
	            }

	            // 선제공격 기술이 아닌 경우에만 선공자 공격
	            if (!isFirstMovePriority) {
	                int damage = firstAttacker.attack();
	                secondAttacker.takeDamage(damage);
	                System.out.println(firstAttacker.getName() + "이(가) " + secondAttacker.getName() + "에게 " + damage + "의 피해를 입혔다!");
	            }

	            // 전투 상태 출력
	            System.out.println(playerPokemon.getName() + "의 체력: " + playerPokemon.getCurrentHP());
	            System.out.println(opponentPokemon.getName() + "의 체력: " + opponentPokemon.getCurrentHP());
	            System.out.println();

	            // 선공자와 후공자 교체
	            Pokemon temp = firstAttacker;
	            firstAttacker = secondAttacker;
	            secondAttacker = temp;
	        }

	        // 전투 종료 메시지 출력
	        if (playerPokemon.getCurrentHP() <= 0) {
	            System.out.println("패배! " + playerPokemon.getName() + "이(가) 쓰러졌다...");
	        } else {
	            System.out.println("승리! " + playerPokemon.getName() + "이(가) 이겼다!");
	        }
	    }
}