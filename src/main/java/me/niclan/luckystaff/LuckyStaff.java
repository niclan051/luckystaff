package me.niclan.luckystaff;

import me.niclan.luckystaff.items.ModItems;
import me.niclan.luckystaff.items.staff.StaffController;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

import java.util.concurrent.atomic.AtomicReference;

public class LuckyStaff implements ModInitializer {
    @Override
    public void onInitialize() {
        ModItems.init();
        StaffController.init();
        
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            AtomicReference<ActionResult> result = new AtomicReference<>(ActionResult.PASS);
            StaffController.getBehaviors().forEach((block, staffBehavior) -> {
                ItemStack stack = player.getMainHandStack();
                Block currentBlock = Registries.BLOCK.get(new Identifier(stack.getNbt() != null ? stack.getNbt().getString("Block") : "minecraft:air"));
                if (block != currentBlock) return;
                if (world.isClient()) {
                    result.set(staffBehavior.onBlockHitClient(stack, (ClientPlayerEntity) player, (ClientWorld) world, hand, pos, direction));
                }
                else {
                    result.set(staffBehavior.onBlockHitServer(stack, (ServerPlayerEntity) player, (ServerWorld) world, hand, pos, direction));
                }
            });
            return result.get();
        });
    }
}