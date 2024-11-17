package github.com.gengyoubo.la.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;
import java.util.List;

public class BlockFuel {
    public static class BlueFlueBlock extends Block {
        public BlueFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
    public static class CyanFlueBlock extends Block {
        public CyanFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
    public static class GreenFlueBlock extends Block {
        public GreenFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
    public static class LimeFlueBlock extends Block {
        public LimeFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
    public static class MagentaFlueBlock extends Block {
        public MagentaFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
    public static class OrangeFlueBlock extends Block {
        public OrangeFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
    public static class PinkFlueBlock extends Block {
        public PinkFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
    public static class PurpleFlueBlock extends Block {
        public PurpleFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
    public static class VioletFlueBlock extends Block {
        public VioletFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
    public static class WhiteFlueBlock extends Block {
        public WhiteFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
    public static class YellowFlueBlock extends Block {
        public YellowFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
            return 15;
        }

        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem tieredItem)
                return tieredItem.getTier().getLevel() >= 1;
            return false;
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }
    }
}
