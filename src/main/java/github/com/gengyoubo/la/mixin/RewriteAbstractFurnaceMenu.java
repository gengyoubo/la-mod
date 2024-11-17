package github.com.gengyoubo.la.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import github.com.gengyoubo.la.event.FurnaceFuelBurnTimeEventExtension;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.IRegistryDelegate;
import org.spongepowered.asm.mixin.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@Mixin(AbstractFurnaceMenu.class)
abstract class RewriteAbstractFurnaceMenu extends RecipeBookMenu<Container> implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {
    @Unique
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    @Unique
    private static final Map<IRegistryDelegate<Item>, Integer> VANILLA_BURNS = new HashMap<>();
    @Unique
    private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    RewriteAbstractFurnaceMenu(MenuType<?> p_38966_, RecipeType<? extends AbstractCookingRecipe> p_38967_, RecipeBookType p_38968_, int p_38969_, Inventory p_38970_, Container p_38971_, ContainerData p_38972_){
        super(p_38966_, p_38969_);
        this.data = p_38972_;
        this.recipeType = p_38967_;
    }
    /**
     * @author gengyoubo
     * @reason Expanded to Long
     */
    @Overwrite
    protected boolean isFuel(ItemStack p_38989_) {
        return NewGetBurnTime(p_38989_, this.recipeType) > 0;
    }
    @Unique
    private static long NewGetBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
        if (stack.isEmpty())
        {
            return 0L;
        }
        else
        {
            Item item = stack.getItem();
            long ret = stack.getBurnTime(recipeType);
            return NewGetItemBurnTime(stack, ret == -1 ? VANILLA_BURNS.getOrDefault(item.delegate, 0) : ret, recipeType);
        }
    }
    @Unique
    private static long NewGetItemBurnTime(@Nonnull ItemStack itemStack, long burnTime, @Nullable RecipeType<?> recipeType)
    {
        FurnaceFuelBurnTimeEventExtension event=new FurnaceFuelBurnTimeEventExtension(itemStack, burnTime, recipeType);
        MinecraftForge.EVENT_BUS.post(event);
        return event.getBurnTime();
    }
    @Shadow private final ContainerData data;
    @Unique
    public long NewGetBurnProgress() {
        long i = this.data.get(2);
        long j = this.data.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }
    @Unique
    public long NewGetLitProgress() {
        long i = this.data.get(1);
        if (i == 0) {
            i = 200;
        }
        return this.data.get(0) * 13L / i;
    }
    @Shadow
    public boolean isLit() {
        return this.data.get(0) > 0;
    }
}
@Mixin(AbstractFurnaceBlockEntity.class)
abstract class RewriteAbstractFurnaceBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible{
    @Shadow private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    protected RewriteAbstractFurnaceBlockEntity(BlockEntityType<?> p_154991_, BlockPos p_154992_, BlockState p_154993_, RecipeType<? extends AbstractCookingRecipe> p_154994_) {
        super(p_154991_, p_154992_, p_154993_);
        this.recipeType = p_154994_;
    }
    @Shadow int litTime;
    @Shadow int litDuration;
    @Shadow int cookingProgress;
    @Shadow int cookingTotalTime;
    @Unique long New_LitTime=litTime;
    @Unique long New_LitDuration=litDuration;
    @Unique long New_CookingProgress=cookingProgress;
    @Unique long New_CookingTotalTime=cookingTotalTime;

    protected final NewContainerData dataAccess = new NewContainerData() {
        public long NewGet(int p_58431_) {
            switch(p_58431_) {
                case 0:
                    return RewriteAbstractFurnaceBlockEntity.this.New_LitTime;
                case 1:
                    return RewriteAbstractFurnaceBlockEntity.this.New_LitDuration;
                case 2:
                    return RewriteAbstractFurnaceBlockEntity.this.New_CookingProgress;
                case 3:
                    return RewriteAbstractFurnaceBlockEntity.this.New_CookingTotalTime;
                default:
                    return 0;
            }
        }

        @Override
        public int get(int p_58431_) {
                switch(p_58431_) {
                    case 0:
                        return RewriteAbstractFurnaceBlockEntity.this.litTime;
                    case 1:
                        return RewriteAbstractFurnaceBlockEntity.this.litDuration;
                    case 2:
                        return RewriteAbstractFurnaceBlockEntity.this.cookingProgress;
                    case 3:
                        return RewriteAbstractFurnaceBlockEntity.this.cookingTotalTime;
                    default:
                        return 0;
            }
        }

        public void set(int p_58433_, int p_58434_) {
            switch(p_58433_) {
                case 0:
                    RewriteAbstractFurnaceBlockEntity.this.New_LitTime = p_58434_;
                    break;
                case 1:
                    RewriteAbstractFurnaceBlockEntity.this.New_LitDuration = p_58434_;
                    break;
                case 2:
                    RewriteAbstractFurnaceBlockEntity.this.New_CookingProgress = p_58434_;
                    break;
                case 3:
                    RewriteAbstractFurnaceBlockEntity.this.New_CookingTotalTime = p_58434_;
            }

        }

        public int getCount() {
            return 4;
        }
    };
    @Shadow protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    @Shadow private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    @Shadow protected abstract int getBurnDuration(ItemStack p_58343_);
    /**
     * @author gengyoubo
     * @reason Expanded to Long
     */
    @Overwrite
    public void load(CompoundTag p_155025_) {
        super.load(p_155025_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(p_155025_, this.items);
        this.New_LitTime = p_155025_.getInt("BurnTime");
        this.New_CookingProgress = p_155025_.getInt("CookTime");
        this.New_CookingTotalTime = p_155025_.getInt("CookTimeTotal");
        this.New_LitDuration = this.getBurnDuration(this.items.get(1));
        CompoundTag compoundtag = p_155025_.getCompound("RecipesUsed");

        for(String s : compoundtag.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
        }

    }
    /**
     * @author gengyoubo
     * @reason Expanded to Long
     */
    @Overwrite
    protected void saveAdditional(CompoundTag p_187452_) {
        super.saveAdditional(p_187452_);
        p_187452_.putLong("BurnTime", this.New_LitTime);
        p_187452_.putLong("CookTime", this.New_CookingProgress);
        p_187452_.putLong("CookTimeTotal", this.New_CookingTotalTime);
        ContainerHelper.saveAllItems(p_187452_, this.items);
        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((p_187449_, p_187450_) -> {
            compoundtag.putLong(p_187449_.toString(), p_187450_);
        });
        p_187452_.put("RecipesUsed", compoundtag);
    }
    @Unique
    public boolean IsLit() {
        return this.litTime > 0;
    }
    @Shadow
    private boolean burn(@Nullable Recipe<?> p_155027_, NonNullList<ItemStack> p_155028_, int p_155029_){
        if (p_155027_ != null && this.canBurn(p_155027_, p_155028_, p_155029_)) {
            ItemStack itemstack = p_155028_.get(0);
            ItemStack itemstack1 = ((Recipe<WorldlyContainer>) p_155027_).assemble(this);
            ItemStack itemstack2 = p_155028_.get(2);
            if (itemstack2.isEmpty()) {
                p_155028_.set(2, itemstack1.copy());
            } else if (itemstack2.is(itemstack1.getItem())) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !p_155028_.get(1).isEmpty() && p_155028_.get(1).is(Items.BUCKET)) {
                p_155028_.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
            return true;
        } else {
            return false;
        }
    }
    @Shadow
    private boolean canBurn(@Nullable Recipe<?> p_155006_, NonNullList<ItemStack> p_155007_, int p_155008_) {
        if (!p_155007_.get(0).isEmpty() && p_155006_ != null) {
            ItemStack itemstack = ((Recipe<WorldlyContainer>) p_155006_).assemble(this);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = p_155007_.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.sameItem(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= p_155008_ && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    @Unique
    private static void serverTick(Level p_155014_, BlockPos p_155015_, BlockState p_155016_, RewriteAbstractFurnaceBlockEntity p_155017_) {
        boolean flag = p_155017_.IsLit();
        boolean flag1 = false;
        if (p_155017_.IsLit()) {
            --p_155017_.New_LitTime;
        }

        ItemStack itemstack = p_155017_.items.get(1);
        if (p_155017_.IsLit() || !itemstack.isEmpty() && !p_155017_.items.get(0).isEmpty()) {
            Recipe<?> recipe = p_155014_.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>)p_155017_.recipeType, p_155017_, p_155014_).orElse(null);
            int i = p_155017_.getMaxStackSize();
            if (!p_155017_.IsLit() && p_155017_.canBurn(recipe, p_155017_.items, i)) {
                p_155017_.New_LitTime = p_155017_.getBurnDuration(itemstack);
                p_155017_.New_LitDuration = p_155017_.New_LitTime;
                if (p_155017_.IsLit()) {
                    flag1 = true;
                    if (itemstack.hasContainerItem())
                        p_155017_.items.set(1, itemstack.getContainerItem());
                    else
                    if (!itemstack.isEmpty()) {
                        Item item = itemstack.getItem();
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            p_155017_.items.set(1, itemstack.getContainerItem());
                        }
                    }
                }
            }

            if (p_155017_.IsLit() && p_155017_.canBurn(recipe, p_155017_.items, i)) {
                ++p_155017_.New_CookingProgress;
                if (p_155017_.New_CookingProgress == p_155017_.New_CookingTotalTime) {
                    p_155017_.New_CookingProgress = 0;
                    p_155017_.New_CookingProgress = getTotalCookTime(p_155014_, p_155017_.recipeType, p_155017_);
                    if (p_155017_.burn(recipe, p_155017_.items, i)) {
                        p_155017_.setRecipeUsed(recipe);
                    }

                    flag1 = true;
                }
            } else {
                p_155017_.New_CookingProgress = 0;
            }
        } else if (!p_155017_.IsLit() && p_155017_.New_CookingProgress > 0) {
            p_155017_.New_CookingProgress = Mth.clamp(p_155017_.New_CookingProgress - 2, 0, p_155017_.New_CookingTotalTime);
        }

        if (flag != p_155017_.IsLit()) {
            flag1 = true;
            p_155016_ = p_155016_.setValue(AbstractFurnaceBlock.LIT, Boolean.valueOf(p_155017_.IsLit()));
            p_155014_.setBlock(p_155015_, p_155016_, 3);
        }

        if (flag1) {
            setChanged(p_155014_, p_155015_, p_155016_);
        }

    }
    @Shadow
    private static int getTotalCookTime(Level p_155010_, RecipeType<? extends AbstractCookingRecipe> p_155011_, Container p_155012_) {
        return p_155010_.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>)p_155011_, p_155012_, p_155010_).map(AbstractCookingRecipe::getCookingTime).orElse(200);
    }
    /**
     * @author gengyoubo
     * @reason Expanded to Long
     */
    @Overwrite
    public void setItem(int p_58333_, ItemStack p_58334_) {
        ItemStack itemstack = this.items.get(p_58333_);
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
}
@Mixin(AbstractFurnaceScreen.class)
abstract class RewriteAbstractFurnaceScreenMixin <T extends RewriteAbstractFurnaceMenu> extends AbstractContainerScreen<T> implements RecipeUpdateListener {
    @Shadow private final ResourceLocation texture;
    @Shadow public final AbstractFurnaceRecipeBookComponent recipeBookComponent;
    @Shadow public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }
    @Shadow public RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }

    public RewriteAbstractFurnaceScreenMixin(T p_97825_, AbstractFurnaceRecipeBookComponent p_97826_, Inventory p_97827_, Component p_97828_, ResourceLocation p_97829_) {
        super(p_97825_, p_97827_, p_97828_);
        this.recipeBookComponent = p_97826_;
        this.texture = p_97829_;
    }


    protected void renderBg(PoseStack p_97853_, float p_97854_, int p_97855_, int p_97856_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.texture);
        long i = this.leftPos;
        long j = this.topPos;
        this.blit(p_97853_, Math.toIntExact(i), Math.toIntExact(j), 0, 0, this.imageWidth, this.imageHeight);
        if (this.menu.isLit()) {
            long k = this.menu.NewGetLitProgress();
            this.blit(p_97853_, Math.toIntExact(i + 56), Math.toIntExact(j + 36 + 12 - k), 176, Math.toIntExact(12 - k), 14, Math.toIntExact(k + 1));
        }

        long l = this.menu.NewGetBurnProgress();
        this.blit(p_97853_, Math.toIntExact(i + 79), Math.toIntExact(j + 34), 176, 14, Math.toIntExact(l + 1), 16);
    }
}
interface NewContainerData extends ContainerData{
    long NewGet(int p_39284_);
}