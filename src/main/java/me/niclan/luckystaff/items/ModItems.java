package me.niclan.luckystaff.items;

import me.niclan.luckystaff.items.staff.StaffItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final StaffItem STAFF = register(new Identifier("luckystaff", "staff"), new StaffItem());
	private static <T extends Item> T register(Identifier id, T item) {
		return Registry.register(Registries.ITEM, id, item);
	}
	public static void init() {
	}
}