package github.com.gengyoubo.la.block.entity;

import github.com.gengyoubo.la.world.inventory.BIFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


public class BIFurnaceBlockEntity extends BIAbstractFurnaceBlockEntity {
    public BIFurnaceBlockEntity(BlockPos p_155545_, BlockState p_155546_) {
        super(BlockEntityType.FURNACE, p_155545_, p_155546_, RecipeType.SMELTING);
    }

    protected Component getDefaultName() {
        return new TranslatableComponent("container.furnace");
    }

    protected BIFurnaceMenu BIcreateMenu(int p_59293_, Inventory p_59294_) {
        return new BIFurnaceMenu(p_59293_,p_59294_,this,this.dataAccess);
    }
}
