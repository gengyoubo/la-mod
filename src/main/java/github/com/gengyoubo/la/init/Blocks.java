package github.com.gengyoubo.la.init;

import github.com.gengyoubo.la.block.BlockFuel;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Blocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, "projectexpansion");
    public static final RegistryObject<Block> BlueFuelBlock = REGISTRY.register("blue_fuel_block", BlockFuel.BlueFlueBlock::new);
    public static final RegistryObject<Block> CyanFlueBlock = REGISTRY.register("cyan_fuel_block", BlockFuel.CyanFlueBlock::new);
    public static final RegistryObject<Block> GreenFlueBlock = REGISTRY.register("green_fuel_block", BlockFuel.GreenFlueBlock::new);
    public static final RegistryObject<Block> LimeFlueBlock = REGISTRY.register("lime_fuel_block", BlockFuel.LimeFlueBlock::new);
    public static final RegistryObject<Block> MagentaFlueBlock = REGISTRY.register("magenta_fuel_block", BlockFuel.MagentaFlueBlock::new);
    public static final RegistryObject<Block> OrangeFlueBlock = REGISTRY.register("orange_fuel_block", BlockFuel.OrangeFlueBlock::new);
    public static final RegistryObject<Block> PinkFlueBlock = REGISTRY.register("pink_fuel_block", BlockFuel.PinkFlueBlock::new);
    public static final RegistryObject<Block> PurpleFlueBlock = REGISTRY.register("purple_fuel_block", BlockFuel.PurpleFlueBlock::new);
    public static final RegistryObject<Block> VioletFlueBlock = REGISTRY.register("violet_fuel_block", BlockFuel.VioletFlueBlock::new);
    public static final RegistryObject<Block> WhiteFlueBlock = REGISTRY.register("white_fuel_block", BlockFuel.WhiteFlueBlock::new);
    public static final RegistryObject<Block> YellowFlueBlock = REGISTRY.register("yellow_fuel_block", BlockFuel.YellowFlueBlock::new);
}
