package me.niclan.luckystaff.items.staff.behaviors;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class BlockDestroyStaffBehavior extends StaffBehavior {
    private final int range;
    public BlockDestroyStaffBehavior(Block block, int range) {
        super(block);
        this.range = range;
    }
    
    @Override
    public ActionResult onBlockHitServer(ItemStack stack, ServerPlayerEntity player, ServerWorld world, Hand hand, BlockPos pos, Direction direction) {
        Vec3d playerRotation = player.getRotationVector();
        // yeah idk how to conecast
        for (int i = 0; i < range; i++) {
            Vec3d currentPosition = playerRotation.multiply(i).add(player.getEyePos());
            for (int j = -i / 2 - 1; j <= i / 2 + 1; j++) {
                for (int k = -i / 2 - 1; k <= i / 2 + 1; k++) {
                    world.breakBlock(BlockPos.ofFloored(currentPosition).add(new Vec3i(j, k, 0)), true);
                }
            }
            world.breakBlock(pos, true, player);
        }
        return super.onBlockHitServer(stack, player, world, hand, pos, direction);
    }
}
