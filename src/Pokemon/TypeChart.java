package Pokemon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeChart {
	private static final Map<String, Map<String, Double>> typeChart = new HashMap<>();

	static {
		Map<String, Double> grassChart = new HashMap<>();
		grassChart.put("grass", 0.5);
		grassChart.put("fire", 0.5);
		grassChart.put("ice", 1.0);
		grassChart.put("electric", 1.0);
		grassChart.put("rock", 2.0);
		typeChart.put("grass", grassChart);
		Map<String, Double> fireChart = new HashMap<>();
		fireChart.put("grass", 2.0);
		fireChart.put("fire", 0.5);
		fireChart.put("ice", 2.0);
		fireChart.put("electric", 1.0);
		fireChart.put("rock", 1.0);
		typeChart.put("fire", fireChart);
		Map<String, Double> iceChart = new HashMap<>();
		iceChart.put("grass", 2.0);
		iceChart.put("fire", 0.5);
		iceChart.put("ice", 0.5);
		iceChart.put("electric", 1.0);
		iceChart.put("rock", 1.0);
		typeChart.put("ice", iceChart);
		Map<String, Double> electricChart = new HashMap<>();
		electricChart.put("grass", 1.0);
		electricChart.put("fire", 1.0);
		electricChart.put("ice", 1.0);
		electricChart.put("electric", 1.0);
		electricChart.put("rock", 1.0);
		typeChart.put("electric", electricChart);
		Map<String, Double> rockChart = new HashMap<>();
		rockChart.put("grass", 1.0);
		rockChart.put("fire", 2.0);
		rockChart.put("ice", 2.0);
		rockChart.put("electric", 1.0);
		rockChart.put("rock", 1.0);
		typeChart.put("rock", rockChart);

	}

	public static double getDamageMultiplier(String skillType, List<String> defenderTypes) {
		double damageMultiplier = 1.0;

		// 모든 방어 포켓몬의 타입에 대해 반복합니다.
		for (String defenderType : defenderTypes) {
			Map<String, Double> attackerChart = typeChart.get(skillType);
			if (attackerChart != null) {
				Double multiplier = attackerChart.get(defenderType);
				if (multiplier != null) {
					damageMultiplier *= multiplier;
				}
			}
		}

		return damageMultiplier;
	}

}