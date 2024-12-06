package github.com.gengyoubo.la.init;

import github.com.gengyoubo.la.block.entity.BIFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, "la");
    public static final RegistryObject<BlockEntityType<BIFurnaceBlockEntity>> BI_FURNACE = REGISTRY.register("bi_furnace",
            () -> BlockEntityType.Builder.of(BIFurnaceBlockEntity::new, LaBlock.BI_FURNACE_BLOCK.get()).build(null));
}
