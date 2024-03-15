package me.niclan.luckystaff.items.staff.behaviors;

import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpawnFallingBlockStaffBehavior extends StaffBehavior {
    public SpawnFallingBlockStaffBehavior(FallingBlock block) {
        super(block);
    }
    @Override
    public TypedActionResult<ItemStack> onUseServer(ItemStack stack, ServerWorld world, ServerPlayerEntity user, Hand hand) {
        super.onUseServer(stack, world, user, hand);
        FallingBlockEntity fallingBlock = new FallingBlockEntity(EntityType.FALLING_BLOCK, world);
        fallingBlock.block = this.block.getDefaultState();
        ((FallingBlock)block).configureFallingBlockEntity(fallingBlock);
        
        Vec3d rotation = user.getRotationVector();
        fallingBlock.setVelocity(rotation);
        fallingBlock.setPos(user.getEyePos().getX() + rotation.getX() * 2, user.getEyePos().getY() + rotation.getY() * 2, user.getEyePos().getZ() + rotation.getZ() * 2);
        world.spawnEntity(fallingBlock);
        return TypedActionResult.success(stack);
    }
}
