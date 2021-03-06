package cz.neumimto.skills.active;

import com.flowpowered.math.imaginary.Quaterniond;
import com.flowpowered.math.vector.Vector3d;
import cz.neumimto.SkillLocalization;
import cz.neumimto.core.ioc.Inject;
import cz.neumimto.rpg.ResourceLoader;
import cz.neumimto.rpg.damage.SkillDamageSourceBuilder;
import cz.neumimto.rpg.effects.EffectService;
import cz.neumimto.rpg.effects.common.negative.SlowPotion;
import cz.neumimto.rpg.entities.EntityService;
import cz.neumimto.rpg.players.IActiveCharacter;
import cz.neumimto.rpg.skills.*;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.projectile.Snowball;
import org.spongepowered.api.world.World;

import static com.flowpowered.math.TrigMath.cos;
import static com.flowpowered.math.TrigMath.sin;

/**
 * Created by NeumimTo on 11.8.17.
 */
@ResourceLoader.Skill
public class IceBolt extends ActiveSkill {

	@Inject
	private EffectService effectService;

	@Inject
	private EntityService entityService;

	public IceBolt() {
		setName("Icebolt");
		setLore(SkillLocalization.SKILL_ICEBOLT_LORE);
		setDamageType(NDamageType.ICE);
		setDescription(SkillLocalization.SKILL_ICEBOLT_DESC);
		SkillSettings skillSettings = new SkillSettings();
		skillSettings.addNode(SkillNodes.DAMAGE, 10, 10);
		skillSettings.addNode(SkillNodes.VELOCITY, 0.5f, .5f);
		skillSettings.addNode(SkillNodes.DURATION, 750, 15);
		skillSettings.addNode(SkillNodes.AMPLIFIER, 1, 0f);
		settings = skillSettings;
		addSkillType(SkillType.SUMMON);
		addSkillType(SkillType.PROJECTILE);
		addSkillType(SkillType.ELEMENTAL);
		addSkillType(SkillType.ICE);
	}

	@Override
	public SkillResult cast(IActiveCharacter character, ExtendedSkillInfo info, SkillModifier skillModifier) {
		Player p = character.getPlayer();
		World world = p.getWorld();
		Entity optional = world.createEntity(EntityTypes.SNOWBALL, p.getLocation().getPosition().add(cos((p.getRotation().getX() - 90) % 360) * 0.2, 1.8, sin((p.getRotation().getX() - 90) % 360) * 0.2));

		Vector3d rotation = p.getRotation();
		Vector3d direction = Quaterniond.fromAxesAnglesDeg(rotation.getX(), -rotation.getY(), rotation.getZ()).getDirection();
		Snowball sb = (Snowball) optional;
		sb.offer(Keys.VELOCITY, direction.mul(settings.getLevelNodeValue(SkillNodes.VELOCITY, info.getTotalLevel())));
		sb.setShooter(p);
		world.spawnEntity(sb);
		ProjectileProperties projectileProperties = new ProjectileProperties(sb, character);
		projectileProperties.setDamage(settings.getLevelNodeValue(SkillNodes.DAMAGE, info.getTotalLevel()));
		SkillDamageSourceBuilder build = new SkillDamageSourceBuilder();
		build.fromSkill(this);
		build.setCaster(character);
		build.type(getDamageType());

		projectileProperties.onHit((event, caster, target) -> {
			long slowduration = getLongNodeValue(info, SkillNodes.DURATION);
			int slowamplf = getIntNodeValue(info, SkillNodes.AMPLIFIER);
			target.getEntity().damage(projectileProperties.getDamage(), build.build());
			effectService.addEffect(new SlowPotion(target, slowduration, slowamplf), target, this);
		});
		return SkillResult.OK;
	}
}
