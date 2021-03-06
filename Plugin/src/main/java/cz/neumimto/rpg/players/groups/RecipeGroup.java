/*    
 *     Copyright (c) 2015, NeumimTo https://github.com/NeumimTo
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *     
 */

package cz.neumimto.rpg.players.groups;

import org.spongepowered.api.item.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by NeumimTo on 14.2.2015.
 */
public class RecipeGroup {
	private String name;
	private Set<ItemStack> set = new HashSet<>();

	public RecipeGroup(String name) {
		this.name = name;
	}

	public Set<ItemStack> getSet() {
		return set;
	}

	public String getName() {
		return name;
	}

	public boolean canCraft(ItemStack i) {
		return set.contains(i);
	}
}
