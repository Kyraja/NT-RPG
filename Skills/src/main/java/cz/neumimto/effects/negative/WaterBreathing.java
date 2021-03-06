package cz.neumimto.effects.negative;

import cz.neumimto.rpg.ClassGenerator;
import cz.neumimto.rpg.effects.EffectBase;
import cz.neumimto.rpg.effects.IEffectConsumer;
import org.spongepowered.api.effect.potion.PotionEffect;

/**
 * Created by NeumimTo on 3.6.2017.
 */
@ClassGenerator.Generate(id = "name")
public class WaterBreathing extends EffectBase<Object> {

	public static final String name = "Water Breathing";

	public WaterBreathing(IEffectConsumer consumer, long duration, String nll) {
		super(name, consumer);
		setDuration(duration);
		setPeriod(2000L);
		super.getPotions().add(PotionEffect.builder().amplifier(1).duration(2500).build());
	}

	@Override
	public void onTick() {
		super.onApply();
	}

	@Override
	public void tickCountIncrement() {
	}


}
