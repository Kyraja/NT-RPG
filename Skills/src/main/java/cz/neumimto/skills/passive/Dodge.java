package cz.neumimto.skills.passive;

import cz.neumimto.SkillLocalization;
import cz.neumimto.core.ioc.Inject;
import cz.neumimto.effects.positive.DodgeEffect;
import cz.neumimto.rpg.ResourceLoader;
import cz.neumimto.rpg.effects.EffectService;
import cz.neumimto.rpg.effects.IEffectContainer;
import cz.neumimto.rpg.players.IActiveCharacter;
import cz.neumimto.rpg.skills.*;

/**
 * Created by NeumimTo on 6.7.2017.
 */
@ResourceLoader.Skill
public class Dodge extends PassiveSkill {

	@Inject
	private EffectService effectService;

	public Dodge() {
		super(DodgeEffect.name);
		setName("Dodge");
		setLore(SkillLocalization.SKILL_DODGE_LORE);
		setDescription(SkillLocalization.SKILL_DODGE_DESC);
		SkillSettings skillSettings = new SkillSettings();
		skillSettings.addNode(SkillNodes.CHANCE, 10, 20);
		super.settings = skillSettings;
		addSkillType(SkillType.PHYSICAL);
	}

	@Override
	public void applyEffect(ExtendedSkillInfo info, IActiveCharacter character) {
		float chance = getFloatNodeValue(info, SkillNodes.CHANCE);
		DodgeEffect dodgeEffect = new DodgeEffect(character, -1, chance);
		effectService.addEffect(dodgeEffect, character, this);
	}

	@Override
	public void skillUpgrade(IActiveCharacter character, int level) {
		ExtendedSkillInfo info = character.getSkill(getName());
		float chance = getFloatNodeValue(info, SkillNodes.CHANCE);
		IEffectContainer<Float, DodgeEffect> effect = character.getEffect(DodgeEffect.name);
		effect.updateValue(chance, this);
	}
}
