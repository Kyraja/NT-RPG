package cz.neumimto.rpg.effects.common.stacking;

import cz.neumimto.rpg.effects.EffectStackingStrategy;

/**
 * Created by NeumimTo on 2.4.2017.
 */
public class IntegerEffectStackingStrategy implements EffectStackingStrategy<Integer> {

	@Override
	public Integer mergeValues(Integer current, Integer toAdd) {
		return current + toAdd;
	}

	@Override
	public Integer getDefaultValue() {
		return 0;
	}
}
