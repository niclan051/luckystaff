package me.niclan.luckystaff.items.staff;

import me.niclan.luckystaff.items.staff.behaviors.StaffBehavior;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class StaffItem extends Item {
    public StaffItem() {
        super(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC));
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() != null && context.getPlayer().isSneaking()) {
            Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
            context.getStack().getOrCreateNbt().putString("Block", Registries.BLOCK.getId(block).toString());
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        Block block = Registries.BLOCK.get(new Identifier(stack.getNbt() != null ? stack.getNbt().getString("Block") : "minecraft:air"));
        StaffBehavior behavior = StaffController.getBehaviors().get(block);
        if (behavior != null) {
            if (world.isClient()) {
                return behavior.onUseClient(stack, (ClientWorld) world, (ClientPlayerEntity) user, hand);
            }
            else {
                return behavior.onUseServer(stack, (ServerWorld) world, (ServerPlayerEntity) user, hand);
            }
        }
        return TypedActionResult.fail(stack);
    }
    
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            Block block = Registries.BLOCK.get(new Identifier(stack.getNbt() != null ? stack.getNbt().getString("Block") : "minecraft:air"));
            StaffBehavior behavior = StaffController.getBehaviors().get(block);
            if (behavior != null) {
                behavior.onTick(world, player);
            }
        }
    }
}