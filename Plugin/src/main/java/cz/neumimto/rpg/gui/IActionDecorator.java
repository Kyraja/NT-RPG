package cz.neumimto.rpg.gui;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.function.BiConsumer;

/**
 * Created by NeumimTo on 9.7.2017.
 */
public interface IActionDecorator {

	void strikeLightning(Location<World> location);

	void createTrajectory(Entity entity, int interval, int maxticks, BiConsumer<Task, Entity> e);



}
