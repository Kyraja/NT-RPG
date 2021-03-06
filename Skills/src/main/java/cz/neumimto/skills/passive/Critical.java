package cz.neumimto.skills.passive;

import cz.neumimto.SkillLocalization;
import cz.neumimto.core.ioc.Inject;
import cz.neumimto.effects.positive.CriticalEffect;
import cz.neumimto.model.CriticalEffectModel;
import cz.neumimto.rpg.ResourceLoader;
import cz.neumimto.rpg.effects.EffectService;
import cz.neumimto.rpg.effects.IEffectContainer;
import cz.neumimto.rpg.players.IActiveCharacter;
import cz.neumimto.rpg.skills.*;

/**
 * Created by ja on 6.7.2017.
 */
@ResourceLoader.Skill
public class Critical extends PassiveSkill {

	@Inject
	private EffectService effectService;

	public Critical() {
		super(CriticalEffect.name);
		setName("Critical");
		setLore(SkillLocalization.SKILL_CRITICAL_LORE);
		setDescription(SkillLocalization.SKILL_CRITICAL_DESC);
		SkillSettings skillSettings = new SkillSettings();
		skillSettings.addNode(SkillNodes.CHANCE, 10, 20);
		skillSettings.addNode(SkillNodes.MULTIPLIER, 10, 20);
		super.settings = skillSettings;
		setDamageType(NDamageType.MEELE_CRITICAL);
		addSkillType(SkillType.PHYSICAL);
	}

	@Override
	public void applyEffect(ExtendedSkillInfo info, IActiveCharacter character) {
		CriticalEffectModel model = getModel(info);
		CriticalEffect dodgeEffect = new CriticalEffect(character, -1, model);
		effectService.addEffect(dodgeEffect, character, this);
	}

	@Override
	public void skillUpgrade(IActiveCharacter character, int level) {
		ExtendedSkillInfo info = character.getSkill(getName());
		IEffectContainer<CriticalEffectModel, CriticalEffect> effect = character.getEffect(CriticalEffect.name);
		effect.updateValue(getModel(info), this);
		effect.updateStackedValue();
	}

	private CriticalEffectModel getModel(ExtendedSkillInfo info) {
		int chance = getIntNodeValue(info, SkillNodes.CHANCE);
		float mult = getFloatNodeValue(info, SkillNodes.MULTIPLIER);
		return new CriticalEffectModel(chance, mult);
	}
}
