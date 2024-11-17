package github.com.gengyoubo.la.init;


import cool.furry.mc.forge.projectexpansion.Main;
import github.com.gengyoubo.la.block.BlockFuel;
import github.com.gengyoubo.la.item.ItemFuel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class Items {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS,"projectexpansion");
    public static final RegistryObject<Item> BlueFuelItem = REGISTRY.register("blue_fuel", ItemFuel.BlueFlue::new);
    public static final RegistryObject<Item> CyanFlueItem = REGISTRY.register("cyan_fuel", ItemFuel.CyanFlue::new);
    public static final RegistryObject<Item> GreenFlueItem = REGISTRY.register("green_fuel", ItemFuel.GreenFlue::new);
    public static final RegistryObject<Item> LimeFlueItem = REGISTRY.register("lime_fuel", ItemFuel.LimeFlue::new);
    public static final RegistryObject<Item> MagentaFlueItem = REGISTRY.register("magenta_fuel", ItemFuel.MagentaFlue::new);
    public static final RegistryObject<Item> OrangeFlueItem = REGISTRY.register("orange_fuel", ItemFuel.OrangeFlue::new);
    public static final RegistryObject<Item> PinkFlueItem = REGISTRY.register("pink_fuel", ItemFuel.PinkFlue::new);
    public static final RegistryObject<Item> PurpleFlueItem = REGISTRY.register("purple_fuel", ItemFuel.PurpleFlue::new);
    public static final RegistryObject<Item> VioletFlueItem = REGISTRY.register("violet_fuel", ItemFuel.VioletFlue::new);
    public static final RegistryObject<Item> WhiteFlueItem = REGISTRY.register("white_fuel", ItemFuel.WhiteFlue::new);
    public static final RegistryObject<Item> YellowFlueItem = REGISTRY.register("yellow_fuel", ItemFuel.YellowFlue::new);
    public static final RegistryObject<Item> BlueFuelBlock = block(Blocks.BlueFuelBlock,Main.tab);
    public static final RegistryObject<Item> CyanFlueBlock = block(Blocks.CyanFlueBlock,Main.tab);
    public static final RegistryObject<Item> GreenFlueBlock = block(Blocks.GreenFlueBlock,Main.tab);
    public static final RegistryObject<Item> LimeFlueBlock = block(Blocks.LimeFlueBlock,Main.tab);
    public static final RegistryObject<Item> MagentaFuelBlock = block(Blocks.MagentaFlueBlock,Main.tab);
    public static final RegistryObject<Item> OrangeFlueBlock = block(Blocks.OrangeFlueBlock,Main.tab);
    public static final RegistryObject<Item> PinkFlueBlock = block(Blocks.PinkFlueBlock,Main.tab);
    public static final RegistryObject<Item> PurpleFlueBlock = block(Blocks.PurpleFlueBlock,Main.tab);
    public static final RegistryObject<Item> VioletFlueBlock = block(Blocks.VioletFlueBlock,Main.tab);
    public static final RegistryObject<Item> WhiteFlueBlock = block(Blocks.WhiteFlueBlock,Main.tab);
    public static final RegistryObject<Item> YellowFlueBlock = block(Blocks.YellowFlueBlock,Main.tab);
    private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
