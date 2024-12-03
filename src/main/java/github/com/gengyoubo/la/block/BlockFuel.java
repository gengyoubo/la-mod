package github.com.gengyoubo.la.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class BlockFuel {
    public static class BlueFlueBlock extends Block {
        public BlueFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
    public static class CyanFlueBlock extends Block {
        public CyanFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
    public static class GreenFlueBlock extends Block {
        public GreenFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
    public static class LimeFlueBlock extends Block {
        public LimeFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
    public static class MagentaFlueBlock extends Block {
        public MagentaFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
    public static class OrangeFlueBlock extends Block {
        public OrangeFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
    public static class PinkFlueBlock extends Block {
        public PinkFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
    public static class PurpleFlueBlock extends Block {
        public PurpleFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
    public static class VioletFlueBlock extends Block {
        public VioletFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
    public static class WhiteFlueBlock extends Block {
        public WhiteFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
    public static class YellowFlueBlock extends Block {
        public YellowFlueBlock() {
            super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(1f, 10f).requiresCorrectToolForDrops());
        }
        @Override
        public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
            if (player.getInventory().getSelected().getItem() instanceof PickaxeItem) {
                // 获取工具所需的挖掘标签
                TagKey<Block> requiredTag = BlockTags.NEEDS_STONE_TOOL;
                // 检查当前方块是否属于该标签
                return state.is(requiredTag);
            }
            return false;
        }
    }
}
