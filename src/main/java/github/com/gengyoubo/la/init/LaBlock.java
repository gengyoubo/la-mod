package github.com.gengyoubo.la.init;

import github.com.gengyoubo.la.block.BIFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LaBlock {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, "la");
    // 注册 BIFurnaceBlock
    public static final RegistryObject<Block> BI_FURNACE_BLOCK = REGISTRY.register("bi_furnace",
            () -> new BIFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F).lightLevel(state -> state.getValue(BIFurnaceBlock.LIT) ? 13 : 0)));
}
