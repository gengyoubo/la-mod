package github.com.gengyoubo.la.init;

import github.com.gengyoubo.la.event.FurnaceFuelBurnTimeEventExtension;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BurnTime {
    private static final int ITEMBURNTIME = 102400;
    private static final int BLOCKBURNTIME = 921600;

    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        ItemStack itemstack = event.getItemStack();
        if (itemstack.getItem() == Items.MagentaFlueItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (int) Math.pow(4, 1)); // 4^1
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.PinkFlueItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (int) Math.pow(4, 2)); // 4^2
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.PurpleFlueItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (int) Math.pow(4, 3)); // 4^3
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.VioletFlueItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (int) Math.pow(4, 4)); // 4^4
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.BlueFuelItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (int) Math.pow(4, 5)); // 4^5
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.CyanFlueItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (int) Math.pow(4, 6)); // 4^6
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.GreenFlueItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (int) Math.pow(4, 7)); // 4^7
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.LimeFlueItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (int) Math.pow(4, 8)); // 4^8
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.YellowFlueItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (int) Math.pow(4, 9)); // 4^9
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.MagentaFuelBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (int) Math.pow(4, 1)); // 4^1
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.PinkFlueBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (int) Math.pow(4, 2)); // 4^2
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.PurpleFlueBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (int) Math.pow(4, 3)); // 4^3
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.VioletFlueBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (int) Math.pow(4, 4)); // 4^4
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.BlueFuelBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (int) Math.pow(4, 5)); // 4^5
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.CyanFlueBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (int) Math.pow(4, 6)); // 4^6
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.GreenFlueBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (int) Math.pow(4, 7)); // 4^7
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.LimeFlueBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (int) Math.pow(4, 8)); // 4^8
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.YellowFlueBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (int) Math.pow(4, 9)); // 4^9
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
    }
    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEventExtension event){
        ItemStack itemstack = event.getItemStack();
        if (itemstack.getItem() == Items.OrangeFlueItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (long) Math.pow(4, 10));
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.WhiteFlueItem.get()) {
            event.setBurnTime(ITEMBURNTIME * (long) Math.pow(4, 11));
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
        if (itemstack.getItem() == Items.OrangeFlueBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (long) Math.pow(4, 11));
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }

        if (itemstack.getItem() == Items.WhiteFlueBlock.get()) {
            event.setBurnTime(BLOCKBURNTIME * (long) Math.pow(4, 11));
            System.out.println("Furnace Burn Time: " + event.getBurnTime());
        }
    }
}
