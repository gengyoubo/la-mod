package github.com.gengyoubo.la.world.inventory;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public interface BIContainerListener {
    void slotChanged(BIAbstractContainerMenu var1, int var2, ItemStack var3);

    void dataChanged(BIAbstractContainerMenu var1, int var2, int var3);
}
