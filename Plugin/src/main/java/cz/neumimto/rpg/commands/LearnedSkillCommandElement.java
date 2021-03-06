package cz.neumimto.rpg.commands;

import cz.neumimto.rpg.Arg;
import cz.neumimto.rpg.NtRpgPlugin;
import cz.neumimto.rpg.TextHelper;
import cz.neumimto.rpg.configuration.Localization;
import cz.neumimto.rpg.players.IActiveCharacter;
import cz.neumimto.rpg.skills.ISkill;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NeumimTo on 16.11.2017.
 */
public class LearnedSkillCommandElement extends CommandElement {

    public LearnedSkillCommandElement(@Nullable Text key) {
        super(key);
    }

    @Override
    protected Object parseValue(CommandSource source, CommandArgs args) throws ArgumentParseException {
        String skilllc = args.next().toLowerCase();
        ISkill skill = NtRpgPlugin.GlobalScope.skillService.getSkill(skilllc);
        if (skill == null) {
            throw args.createError(TextHelper.parse(Localization.UNKNOWN_SKILL, Arg.arg("skill", skilllc)));
        }
        IActiveCharacter character = NtRpgPlugin.GlobalScope.characterService.getCharacter((Player) source);
        if (!character.hasSkill(skill.getName())) {
            throw args.createError(TextHelper.parse(Localization.CHARACTER_DOES_NOT_HAVE_SKILL, Arg.arg("skill", skill.getName())));
        }
        return skill;
    }

    @Override
    public List<String> complete(CommandSource src, CommandArgs args, CommandContext context) {
        IActiveCharacter character = NtRpgPlugin.GlobalScope.characterService.getCharacter((Player) src);
        return new ArrayList<>(character.getSkills().keySet());
    }

    @Override
    public Text getUsage(CommandSource src) {
        return Text.of("<skill_name>");
    }

}
