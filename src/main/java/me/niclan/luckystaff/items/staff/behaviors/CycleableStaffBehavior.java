package me.niclan.luckystaff.items.staff.behaviors;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Range;

public class CycleableStaffBehavior extends StaffBehavior {
    protected int currentIndex = 0;
    protected int maxIndex;
    public CycleableStaffBehavior(Block block, @Range(from = 1, to = Integer.MAX_VALUE) int maxIndex) {
        super(block);
        this.maxIndex = maxIndex;
    }
    
    public void cycleForward() {
        currentIndex = currentIndex + 1;
        while (currentIndex > maxIndex) {
            currentIndex -= (maxIndex + 1);
        }
    }
    public void cycleBackward() {
        currentIndex = currentIndex - 1;
        while (currentIndex < 0) {
            currentIndex += (maxIndex + 1);
        }
    }
}
