package github.com.gengyoubo.la.block.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BIAbstractFurnaceBlockEntity extends BIBaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {
    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_FUEL = 1;
    protected static final int SLOT_RESULT = 2;
    public static final int DATA_LIT_TIME = 0;
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1};
    public static final int DATA_LIT_DURATION = 1;
    public static final int DATA_COOKING_PROGRESS = 2;
    public static final int DATA_COOKING_TOTAL_TIME = 3;
    public static final int NUM_DATA_VALUES = 4;
    public static final int BURN_TIME_STANDARD = 200;
    public static final int BURN_COOL_SPEED = 2;
    protected NonNullList<ItemStack> items;
    int litTime;
    int litDuration;
    int cookingProgress;
    int cookingTotalTime;
    protected final ContainerData dataAccess;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed;
    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    LazyOptional<? extends IItemHandler>[] handlers;

    protected BIAbstractFurnaceBlockEntity(BlockEntityType<?> p_154991_, BlockPos p_154992_, BlockState p_154993_, RecipeType<? extends AbstractCookingRecipe> p_154994_) {
        super(p_154991_, p_154992_, p_154993_);
        this.items = NonNullList.withSize(3, ItemStack.EMPTY);
        this.dataAccess = new ContainerData() {
            public int get(int p_58431_) {
                switch (p_58431_) {
                    case 0 -> {
                        return BIAbstractFurnaceBlockEntity.this.litTime;
                    }
                    case 1 -> {
                        return BIAbstractFurnaceBlockEntity.this.litDuration;
                    }
                    case 2 -> {
                        return BIAbstractFurnaceBlockEntity.this.cookingProgress;
                    }
                    case 3 -> {
                        return BIAbstractFurnaceBlockEntity.this.cookingTotalTime;
                    }
                    default -> {
                        return 0;
                    }
                }
            }

            public void set(int p_58433_, int p_58434_) {
                switch (p_58433_) {
                    case 0 -> BIAbstractFurnaceBlockEntity.this.litTime = p_58434_;
                    case 1 -> BIAbstractFurnaceBlockEntity.this.litDuration = p_58434_;
                    case 2 -> BIAbstractFurnaceBlockEntity.this.cookingProgress = p_58434_;
                    case 3 -> BIAbstractFurnaceBlockEntity.this.cookingTotalTime = p_58434_;
                }

            }

            public int getCount() {
                return 4;
            }
        };
        this.recipesUsed = new Object2IntOpenHashMap();
        this.handlers = SidedInvWrapper.create(this, new Direction[]{Direction.UP, Direction.DOWN, Direction.NORTH});
        this.recipeType = p_154994_;
    }

    /** @deprecated */
    @Deprecated
    public static Map<Item, Integer> getFuel() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        add(map, (ItemLike) Items.LAVA_BUCKET, 20000);
        add(map, (ItemLike) Blocks.COAL_BLOCK, 16000);
        add(map, (ItemLike)Items.BLAZE_ROD, 2400);
        add(map, (ItemLike)Items.COAL, 1600);
        add(map, (ItemLike)Items.CHARCOAL, 1600);
        add(map, (TagKey) ItemTags.LOGS, 300);
        add(map, (TagKey)ItemTags.PLANKS, 300);
        add(map, (TagKey)ItemTags.WOODEN_STAIRS, 300);
        add(map, (TagKey)ItemTags.WOODEN_SLABS, 150);
        add(map, (TagKey)ItemTags.WOODEN_TRAPDOORS, 300);
        add(map, (TagKey)ItemTags.WOODEN_PRESSURE_PLATES, 300);
        add(map, (ItemLike)Blocks.OAK_FENCE, 300);
        add(map, (ItemLike)Blocks.BIRCH_FENCE, 300);
        add(map, (ItemLike)Blocks.SPRUCE_FENCE, 300);
        add(map, (ItemLike)Blocks.JUNGLE_FENCE, 300);
        add(map, (ItemLike)Blocks.DARK_OAK_FENCE, 300);
        add(map, (ItemLike)Blocks.ACACIA_FENCE, 300);
        add(map, (ItemLike)Blocks.OAK_FENCE_GATE, 300);
        add(map, (ItemLike)Blocks.BIRCH_FENCE_GATE, 300);
        add(map, (ItemLike)Blocks.SPRUCE_FENCE_GATE, 300);
        add(map, (ItemLike)Blocks.JUNGLE_FENCE_GATE, 300);
        add(map, (ItemLike)Blocks.DARK_OAK_FENCE_GATE, 300);
        add(map, (ItemLike)Blocks.ACACIA_FENCE_GATE, 300);
        add(map, (ItemLike)Blocks.NOTE_BLOCK, 300);
        add(map, (ItemLike)Blocks.BOOKSHELF, 300);
        add(map, (ItemLike)Blocks.LECTERN, 300);
        add(map, (ItemLike)Blocks.JUKEBOX, 300);
        add(map, (ItemLike)Blocks.CHEST, 300);
        add(map, (ItemLike)Blocks.TRAPPED_CHEST, 300);
        add(map, (ItemLike)Blocks.CRAFTING_TABLE, 300);
        add(map, (ItemLike)Blocks.DAYLIGHT_DETECTOR, 300);
        add(map, (TagKey)ItemTags.BANNERS, 300);
        add(map, (ItemLike)Items.BOW, 300);
        add(map, (ItemLike)Items.FISHING_ROD, 300);
        add(map, (ItemLike)Blocks.LADDER, 300);
        add(map, (TagKey)ItemTags.SIGNS, 200);
        add(map, (ItemLike)Items.WOODEN_SHOVEL, 200);
        add(map, (ItemLike)Items.WOODEN_SWORD, 200);
        add(map, (ItemLike)Items.WOODEN_HOE, 200);
        add(map, (ItemLike)Items.WOODEN_AXE, 200);
        add(map, (ItemLike)Items.WOODEN_PICKAXE, 200);
        add(map, (TagKey)ItemTags.WOODEN_DOORS, 200);
        add(map, (TagKey)ItemTags.BOATS, 1200);
        add(map, (TagKey)ItemTags.WOOL, 100);
        add(map, (TagKey)ItemTags.WOODEN_BUTTONS, 100);
        add(map, (ItemLike)Items.STICK, 100);
        add(map, (TagKey)ItemTags.SAPLINGS, 100);
        add(map, (ItemLike)Items.BOWL, 100);
        add(map, (TagKey)ItemTags.CARPETS, 67);
        add(map, (ItemLike)Blocks.DRIED_KELP_BLOCK, 4001);
        add(map, (ItemLike)Items.CROSSBOW, 300);
        add(map, (ItemLike)Blocks.BAMBOO, 50);
        add(map, (ItemLike)Blocks.DEAD_BUSH, 100);
        add(map, (ItemLike)Blocks.SCAFFOLDING, 400);
        add(map, (ItemLike)Blocks.LOOM, 300);
        add(map, (ItemLike)Blocks.BARREL, 300);
        add(map, (ItemLike)Blocks.CARTOGRAPHY_TABLE, 300);
        add(map, (ItemLike)Blocks.FLETCHING_TABLE, 300);
        add(map, (ItemLike)Blocks.SMITHING_TABLE, 300);
        add(map, (ItemLike)Blocks.COMPOSTER, 300);
        add(map, (ItemLike)Blocks.AZALEA, 100);
        add(map, (ItemLike)Blocks.FLOWERING_AZALEA, 100);
        return map;
    }

    private static boolean isNeverAFurnaceFuel(Item p_58398_) {
        return p_58398_.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);
    }

    private static void add(Map<Item, Integer> p_204303_, TagKey<Item> p_204304_, int p_204305_) {
        Iterator var3 = Registry.ITEM.getTagOrEmpty(p_204304_).iterator();

        while(var3.hasNext()) {
            Holder<Item> holder = (Holder)var3.next();
            if (!isNeverAFurnaceFuel((Item)holder.value())) {
                p_204303_.put((Item)holder.value(), p_204305_);
            }
        }

    }

    private static void add(Map<Item, Integer> p_58375_, ItemLike p_58376_, int p_58377_) {
        Item item = p_58376_.asItem();
        if (isNeverAFurnaceFuel(item)) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw (IllegalStateException) Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack)null).getString() + " a furnace fuel. That will not work!"));
            }
        } else {
            p_58375_.put(item, p_58377_);
        }

    }

    private boolean isLit() {
        return this.litTime > 0;
    }

    public void load(CompoundTag p_155025_) {
        super.load(p_155025_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(p_155025_, this.items);
        this.litTime = p_155025_.getInt("BurnTime");
        this.cookingProgress = p_155025_.getInt("CookTime");
        this.cookingTotalTime = p_155025_.getInt("CookTimeTotal");
        this.litDuration = this.getBurnDuration((ItemStack)this.items.get(1));
        CompoundTag compoundtag = p_155025_.getCompound("RecipesUsed");
        Iterator var3 = compoundtag.getAllKeys().iterator();

        while(var3.hasNext()) {
            String s = (String)var3.next();
            this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
        }

    }

    protected void saveAdditional(CompoundTag p_187452_) {
        super.saveAdditional(p_187452_);
        p_187452_.putInt("BurnTime", this.litTime);
        p_187452_.putInt("CookTime", this.cookingProgress);
        p_187452_.putInt("CookTimeTotal", this.cookingTotalTime);
        ContainerHelper.saveAllItems(p_187452_, this.items);
        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((p_187449_, p_187450_) -> {
            compoundtag.putInt(p_187449_.toString(), p_187450_);
        });
        p_187452_.put("RecipesUsed", compoundtag);
    }

    public static void serverTick(Level p_155014_, BlockPos p_155015_, BlockState p_155016_, BIAbstractFurnaceBlockEntity p_155017_) {
        boolean flag = p_155017_.isLit();
        boolean flag1 = false;
        if (p_155017_.isLit()) {
            --p_155017_.litTime;
        }

        ItemStack itemstack = (ItemStack)p_155017_.items.get(1);
        if (!p_155017_.isLit() && (itemstack.isEmpty() || ((ItemStack)p_155017_.items.get(0)).isEmpty())) {
            if (!p_155017_.isLit() && p_155017_.cookingProgress > 0) {
                p_155017_.cookingProgress = Mth.clamp(p_155017_.cookingProgress - 2, 0, p_155017_.cookingTotalTime);
            }
        } else {
            Recipe<?> recipe = (Recipe)p_155014_.getRecipeManager().getRecipeFor(p_155017_.recipeType, p_155017_, p_155014_).orElse(null);
            int i = p_155017_.getMaxStackSize();
            if (!p_155017_.isLit() && p_155017_.canBurn((Recipe<BIAbstractFurnaceBlockEntity>) recipe, p_155017_.items, i)) {
                p_155017_.litTime = p_155017_.getBurnDuration(itemstack);
                p_155017_.litDuration = p_155017_.litTime;
                if (p_155017_.isLit()) {
                    flag1 = true;
                    if (itemstack.hasContainerItem()) {
                        p_155017_.items.set(1, itemstack.getContainerItem());
                    } else if (!itemstack.isEmpty()) {
                        Item item = itemstack.getItem();
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            p_155017_.items.set(1, itemstack.getContainerItem());
                        }
                    }
                }
            }

            if (p_155017_.isLit() && p_155017_.canBurn((Recipe<BIAbstractFurnaceBlockEntity>) recipe, p_155017_.items, i)) {
                ++p_155017_.cookingProgress;
                if (p_155017_.cookingProgress == p_155017_.cookingTotalTime) {
                    p_155017_.cookingProgress = 0;
                    p_155017_.cookingTotalTime = getTotalCookTime(p_155014_, p_155017_.recipeType, p_155017_);
                    if (p_155017_.burn((Recipe<BIAbstractFurnaceBlockEntity>) recipe, p_155017_.items, i)) {
                        p_155017_.setRecipeUsed(recipe);
                    }

                    flag1 = true;
                }
            } else {
                p_155017_.cookingProgress = 0;
            }
        }

        if (flag != p_155017_.isLit()) {
            flag1 = true;
            p_155016_ = (BlockState)p_155016_.setValue(AbstractFurnaceBlock.LIT, p_155017_.isLit());
            p_155014_.setBlock(p_155015_, p_155016_, 3);
        }

        if (flag1) {
            setChanged(p_155014_, p_155015_, p_155016_);
        }

    }

    private boolean canBurn(@Nullable Recipe<BIAbstractFurnaceBlockEntity> p_155006_, NonNullList<ItemStack> p_155007_, int p_155008_) {
        if (!((ItemStack)p_155007_.get(0)).isEmpty() && p_155006_ != null) {
            ItemStack itemstack = p_155006_.assemble(this);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = (ItemStack)p_155007_.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.sameItem(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= p_155008_ && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) {
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private boolean burn(@Nullable Recipe<BIAbstractFurnaceBlockEntity> p_155027_, NonNullList<ItemStack> p_155028_, int p_155029_) {
        if (p_155027_ != null && this.canBurn(p_155027_, p_155028_, p_155029_)) {
            ItemStack itemstack = (ItemStack)p_155028_.get(0);
            ItemStack itemstack1 = p_155027_.assemble(this);
            ItemStack itemstack2 = (ItemStack)p_155028_.get(2);
            if (itemstack2.isEmpty()) {
                p_155028_.set(2, itemstack1.copy());
            } else if (itemstack2.is(itemstack1.getItem())) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !((ItemStack)p_155028_.get(1)).isEmpty() && ((ItemStack)p_155028_.get(1)).is(Items.BUCKET)) {
                p_155028_.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    protected int getBurnDuration(ItemStack p_58343_) {
        if (p_58343_.isEmpty()) {
            return 0;
        } else {
            Item item = p_58343_.getItem();
            return ForgeHooks.getBurnTime(p_58343_, this.recipeType);
        }
    }

    private static int getTotalCookTime(Level p_155010_, RecipeType<? extends AbstractCookingRecipe> p_155011_, Container p_155012_) {
        return (Integer)p_155010_.getRecipeManager().getRecipeFor(p_155011_, p_155012_, p_155010_).map(AbstractCookingRecipe::getCookingTime).orElse(200);
    }

    public static boolean isFuel(ItemStack p_58400_) {
        return ForgeHooks.getBurnTime(p_58400_, (RecipeType)null) > 0;
    }

    public int[] getSlotsForFace(Direction p_58363_) {
        if (p_58363_ == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return p_58363_ == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    public boolean canPlaceItemThroughFace(int p_58336_, ItemStack p_58337_, @Nullable Direction p_58338_) {
        return this.canPlaceItem(p_58336_, p_58337_);
    }

    public boolean canTakeItemThroughFace(int p_58392_, ItemStack p_58393_, Direction p_58394_) {
        if (p_58394_ == Direction.DOWN && p_58392_ == 1) {
            return p_58393_.is(Items.WATER_BUCKET) || p_58393_.is(Items.BUCKET);
        } else {
            return true;
        }
    }

    public int getContainerSize() {
        return this.items.size();
    }

    public boolean isEmpty() {
        Iterator var1 = this.items.iterator();

        ItemStack itemstack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemstack = (ItemStack)var1.next();
        } while(itemstack.isEmpty());

        return false;
    }

    public ItemStack getItem(int p_58328_) {
        return (ItemStack)this.items.get(p_58328_);
    }

    public ItemStack removeItem(int p_58330_, int p_58331_) {
        return ContainerHelper.removeItem(this.items, p_58330_, p_58331_);
    }

    public ItemStack removeItemNoUpdate(int p_58387_) {
        return ContainerHelper.takeItem(this.items, p_58387_);
    }

    public void setItem(int p_58333_, ItemStack p_58334_) {
        ItemStack itemstack = (ItemStack)this.items.get(p_58333_);
        boolean flag = !p_58334_.isEmpty() && p_58334_.sameItem(itemstack) && ItemStack.tagMatches(p_58334_, itemstack);
        this.items.set(p_58333_, p_58334_);
        if (p_58334_.getCount() > this.getMaxStackSize()) {
            p_58334_.setCount(this.getMaxStackSize());
        }

        if (p_58333_ == 0 && !flag) {
            this.cookingTotalTime = getTotalCookTime(this.level, this.recipeType, this);
            this.cookingProgress = 0;
            this.setChanged();
        }

    }

    public boolean stillValid(Player p_58340_) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return p_58340_.distanceToSqr((double)this.worldPosition.getX() + 0.5, (double)this.worldPosition.getY() + 0.5, (double)this.worldPosition.getZ() + 0.5) <= 64.0;
        }
    }

    public boolean canPlaceItem(int p_58389_, ItemStack p_58390_) {
        if (p_58389_ == 2) {
            return false;
        } else if (p_58389_ != 1) {
            return true;
        } else {
            ItemStack itemstack = (ItemStack)this.items.get(1);
            return ForgeHooks.getBurnTime(p_58390_, this.recipeType) > 0 || p_58390_.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
        }
    }

    public void clearContent() {
        this.items.clear();
    }

    public void setRecipeUsed(@Nullable Recipe<?> p_58345_) {
        if (p_58345_ != null) {
            ResourceLocation resourcelocation = p_58345_.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }

    }

    @Nullable
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    public void awardUsedRecipes(Player p_58396_) {
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer p_155004_) {
        List<Recipe<?>> list = this.getRecipesToAwardAndPopExperience(p_155004_.getLevel(), p_155004_.position());
        p_155004_.awardRecipes(list);
        this.recipesUsed.clear();
    }

    public List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel p_154996_, Vec3 p_154997_) {
        List<Recipe<?>> list = Lists.newArrayList();
        ObjectIterator var4 = this.recipesUsed.object2IntEntrySet().iterator();

        while(var4.hasNext()) {
            Object2IntMap.Entry<ResourceLocation> entry = (Object2IntMap.Entry)var4.next();
            p_154996_.getRecipeManager().byKey((ResourceLocation)entry.getKey()).ifPresent((p_155023_) -> {
                list.add(p_155023_);
                createExperience(p_154996_, p_154997_, entry.getIntValue(), ((AbstractCookingRecipe)p_155023_).getExperience());
            });
        }

        return list;
    }

    private static void createExperience(ServerLevel p_154999_, Vec3 p_155000_, int p_155001_, float p_155002_) {
        int i = Mth.floor((float)p_155001_ * p_155002_);
        float f = Mth.frac((float)p_155001_ * p_155002_);
        if (f != 0.0F && Math.random() < (double)f) {
            ++i;
        }

        ExperienceOrb.award(p_154999_, p_155000_, i);
    }

    public void fillStackedContents(StackedContents p_58342_) {
        Iterator var2 = this.items.iterator();

        while(var2.hasNext()) {
            ItemStack itemstack = (ItemStack)var2.next();
            p_58342_.accountStack(itemstack);
        }

    }

    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP) {
                return this.handlers[0].cast();
            } else {
                return facing == Direction.DOWN ? this.handlers[1].cast() : this.handlers[2].cast();
            }
        } else {
            return super.getCapability(capability, facing);
        }
    }

    public void invalidateCaps() {
        super.invalidateCaps();

        for(int x = 0; x < this.handlers.length; ++x) {
            this.handlers[x].invalidate();
        }

    }

    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = SidedInvWrapper.create(this, new Direction[]{Direction.UP, Direction.DOWN, Direction.NORTH});
    }
}
