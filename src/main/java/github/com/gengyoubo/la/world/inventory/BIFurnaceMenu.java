package github.com.gengyoubo.la.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class BIFurnaceMenu extends BIAbstractFurnaceMenu {
    public BIFurnaceMenu(int p_39532_, Inventory p_39533_) {
        super(MenuType.FURNACE, RecipeType.SMELTING, RecipeBookType.FURNACE, p_39532_, p_39533_);
    }

    public BIFurnaceMenu(int p_39535_, Inventory p_39536_, Container p_39537_, ContainerData p_39538_) {
        super(MenuType.FURNACE, RecipeType.SMELTING, RecipeBookType.FURNACE, p_39535_, p_39536_, p_39537_, p_39538_);
    }
}
