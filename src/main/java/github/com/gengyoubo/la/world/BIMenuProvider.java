package github.com.gengyoubo.la.world;

import github.com.gengyoubo.la.world.inventory.BIMenuConstructor;
import net.minecraft.network.chat.Component;

public interface BIMenuProvider extends BIMenuConstructor {
    Component getDisplayName();
}
