package github.com.gengyoubo.la.tabs;

import github.com.gengyoubo.la.init.LaItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class lateb {

    public class LAMAITAB {
        private static final CreativeModeTab LA_TAB = new CreativeModeTab("la_tab") {
            @Override
            public @NotNull ItemStack makeIcon() {
                return new ItemStack(LaItem.BI_FURNACE_ITEM.get());
            }
        };

        public static CreativeModeTab getInstance() {
            return LA_TAB;
        }
    }

}
