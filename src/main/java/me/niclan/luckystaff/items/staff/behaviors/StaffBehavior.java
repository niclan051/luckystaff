package me.niclan.luckystaff.items.staff.behaviors;

import me.niclan.luckystaff.items.staff.StaffController;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class StaffBehavior {
    protected Block block;
    public StaffBehavior(Block block) {
        StaffController.getBehaviors().put(block, this);
        this.block = block;
    }
    public void onTick(World world, PlayerEntity user) {}
    public void onEntityHit() {}
    
    public ActionResult onBlockHitServer(ItemStack stack, ServerPlayerEntity player, ServerWorld world, Hand hand, BlockPos pos, Direction direction) {
        return ActionResult.PASS;
    }
    public ActionResult onBlockHitClient(ItemStack stack, ClientPlayerEntity player, ClientWorld world, Hand hand, BlockPos pos, Direction direction) {
        return ActionResult.PASS;
    }
    public void onBlockBreak() {}
    public void onPickup() {}
    public void onDrop() {}
    public TypedActionResult<ItemStack> onUseServer(ItemStack stack, ServerWorld world, ServerPlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            NbtCompound nbt = stack.getOrCreateNbt();
            nbt.putString("Block", Registries.BLOCK.getId(Blocks.AIR).toString());
            block = Blocks.AIR;
            stack.setNbt(nbt);
            user.setStackInHand(hand, stack);
            return TypedActionResult.success(stack);
        }
        return TypedActionResult.pass(stack);
    }
    public TypedActionResult<ItemStack> onUseClient(ItemStack stack, ClientWorld world, ClientPlayerEntity user, Hand hand) {
        return TypedActionResult.pass(stack);
    }
}