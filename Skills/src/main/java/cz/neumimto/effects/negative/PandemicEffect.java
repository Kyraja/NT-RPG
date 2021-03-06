package cz.neumimto.effects.negative;

import cz.neumimto.rpg.IEntity;
import cz.neumimto.rpg.NtRpgPlugin;
import cz.neumimto.rpg.damage.SkillDamageSource;
import cz.neumimto.rpg.effects.EffectBase;
import cz.neumimto.rpg.utils.Utils;
import org.spongepowered.api.entity.living.Living;

/**
 * Created by NeumimTo on 6.8.2017.
 */
public class PandemicEffect extends EffectBase {

	public static final String name = "Pandemic";

	private IEntity caster;
	private float damage;
	private SkillDamageSource damageSource;

	public PandemicEffect(IEntity caster, IEntity iEntity, float damage, long duration, long period) {
		super(name, iEntity);
		this.caster = caster;
		this.damage = damage;
		setDuration(duration);
		setPeriod(period);

	}

	@Override
	public void onTick() {
		Living entity = getConsumer().getEntity();
		if (Utils.canDamage(caster, entity)) {
			entity.damage(damage, damageSource);
			//todo some particles
			NtRpgPlugin.GlobalScope.entityService.healEntity(caster, damage, damageSource.getSkill());
		}
	}

	public void setDamageSource(SkillDamageSource damageSource) {
		this.damageSource = damageSource;
	}

	public SkillDamageSource getDamageSource() {
		return damageSource;
	}
}
