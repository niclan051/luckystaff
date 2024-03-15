package me.niclan.luckystaff.items.staff.behaviors.impl;

import me.niclan.luckystaff.items.staff.behaviors.SpawnFallingBlockStaffBehavior;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;

public class AnvilStaffBehavior extends SpawnFallingBlockStaffBehavior {
    public AnvilStaffBehavior() {
        super((FallingBlock) Blocks.ANVIL);
    }
}
