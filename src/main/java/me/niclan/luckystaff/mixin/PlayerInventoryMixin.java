package me.niclan.luckystaff.mixin;

import me.niclan.luckystaff.items.staff.StaffController;
import me.niclan.luckystaff.items.staff.behaviors.CycleableStaffBehavior;
import me.niclan.luckystaff.items.staff.behaviors.StaffBehavior;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.Nameable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements Inventory, Nameable {
    @Shadow public abstract ItemStack getMainHandStack();
    
    @Inject(method = "scrollInHotbar", at = @At("HEAD"), cancellable = true)
    private void cycleBehavior(double scrollAmount, CallbackInfo ci) {
        if (MinecraftClient.getInstance().options.sneakKey.isPressed()) {
            StaffController.getBehaviors().forEach((block, staffBehavior) -> {
                ItemStack stack = this.getMainHandStack();
                Block currentBlock = Registries.BLOCK.get(new Identifier(stack.getNbt() != null ? stack.getNbt().getString("Block") : "minecraft:air"));
                if (block != currentBlock) return;
                if (staffBehavior instanceof CycleableStaffBehavior cycleableBehavior) {
                    int scrollDirection = (int) Math.signum(scrollAmount);
                    if (scrollDirection == 1) {
                        cycleableBehavior.cycleForward();
                    }
                    else if (scrollDirection == -1) {
                        cycleableBehavior.cycleBackward();
                    }
                    ci.cancel();
                }
            });
        }
    }
}
