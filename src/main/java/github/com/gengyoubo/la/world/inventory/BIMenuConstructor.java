package github.com.gengyoubo.la.world.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

@FunctionalInterface
public interface BIMenuConstructor {
    @Nullable
    BIAbstractContainerMenu BIcreateMenu(int value1, Inventory value2, Player value3);
}
