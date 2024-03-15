package me.niclan.luckystaff.items.staff;

import me.niclan.luckystaff.items.staff.behaviors.impl.AnvilStaffBehavior;
import me.niclan.luckystaff.items.staff.behaviors.StaffBehavior;
import me.niclan.luckystaff.items.staff.behaviors.impl.DiamondBlockStaffBehavior;
import me.niclan.luckystaff.items.staff.behaviors.impl.TntStaffBehavior;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public final class StaffController {
    private static final Map<Block, StaffBehavior> BEHAVIORS = new HashMap<>();
    private static final TntStaffBehavior TNT = new TntStaffBehavior();
    private static final AnvilStaffBehavior ANVIL = new AnvilStaffBehavior();
    private static final StaffBehavior AIR = new StaffBehavior(Blocks.AIR);
    private static final DiamondBlockStaffBehavior DIAMOND_BLOCK = new DiamondBlockStaffBehavior();
    
    public static Map<Block, StaffBehavior> getBehaviors() {
        return BEHAVIORS;
    }
    
    public static void init() {}
}