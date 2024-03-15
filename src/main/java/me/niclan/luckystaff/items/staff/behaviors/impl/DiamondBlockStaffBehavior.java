package me.niclan.luckystaff.items.staff.behaviors.impl;

import me.niclan.luckystaff.items.staff.behaviors.BlockDestroyStaffBehavior;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class DiamondBlockStaffBehavior extends BlockDestroyStaffBehavior {
    public DiamondBlockStaffBehavior() {
        super(Blocks.DIAMOND_BLOCK, 10);
    }
}
