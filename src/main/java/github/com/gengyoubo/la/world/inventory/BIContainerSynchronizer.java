package github.com.gengyoubo.la.world.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public interface BIContainerSynchronizer {
    void sendInitialData(BIAbstractContainerMenu var1, NonNullList<ItemStack> var2, ItemStack var3, int[] var4);

    void sendSlotChange(BIAbstractContainerMenu var1, int var2, ItemStack var3);

    void sendCarriedChange(BIAbstractContainerMenu var1, ItemStack var2);

    void sendDataChange(BIAbstractContainerMenu var1, int var2, int var3);
}
