package me.niclan.luckystaff.items.staff.behaviors.impl;

import me.niclan.luckystaff.items.staff.behaviors.StaffBehavior;
import net.minecraft.block.Blocks;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;

public class TntStaffBehavior extends StaffBehavior {
    public TntStaffBehavior() {
        super(Blocks.TNT);
    }
    
    @Override
    public TypedActionResult<ItemStack> onUseServer(ItemStack stack, ServerWorld world, ServerPlayerEntity user, Hand hand) {
        super.onUseServer(stack, world, user, hand);
        TntEntity tnt = new TntEntity(world, user.getX(), user.getEyeY(), user.getZ(), user);
        
        Vec3d rotation = user.getRotationVector();
        tnt.setVelocity(rotation);
        
        world.spawnEntity(tnt);
        return TypedActionResult.success(stack);
    }
}
