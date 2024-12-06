package github.com.gengyoubo.la.block.entity;

import github.com.gengyoubo.la.world.BIMenuProvider;
import github.com.gengyoubo.la.world.inventory.BIAbstractContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.LockCode;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public abstract class BIBaseContainerBlockEntity extends BlockEntity implements Container, BIMenuProvider, Nameable {
    private LockCode lockKey;
    private Component name;
    private LazyOptional<?> itemHandler;

    protected BIBaseContainerBlockEntity(BlockEntityType<?> p_155076_, BlockPos p_155077_, BlockState p_155078_) {
        super(p_155076_, p_155077_, p_155078_);
        this.lockKey = LockCode.NO_LOCK;
        this.itemHandler = LazyOptional.of(() -> {
            return this.createUnSidedHandler();
        });
    }

    public void load(CompoundTag p_155080_) {
        super.load(p_155080_);
        this.lockKey = LockCode.fromTag(p_155080_);
        if (p_155080_.contains("CustomName", 8)) {
            this.name = Component.Serializer.fromJson(p_155080_.getString("CustomName"));
        }

    }

    protected void saveAdditional(CompoundTag p_187461_) {
        super.saveAdditional(p_187461_);
        this.lockKey.addToTag(p_187461_);
        if (this.name != null) {
            p_187461_.putString("CustomName", Component.Serializer.toJson(this.name));
        }

    }

    public void setCustomName(Component p_58639_) {
        this.name = p_58639_;
    }

    public Component getName() {
        return this.name != null ? this.name : this.getDefaultName();
    }

    public Component getDisplayName() {
        return this.getName();
    }

    @Nullable
    public Component getCustomName() {
        return this.name;
    }

    protected abstract Component getDefaultName();

    public boolean canOpen(Player p_58645_) {
        return canUnlock(p_58645_, this.lockKey, this.getDisplayName());
    }

    public static boolean canUnlock(Player p_58630_, LockCode p_58631_, Component p_58632_) {
        if (!p_58630_.isSpectator() && !p_58631_.unlocksWith(p_58630_.getMainHandItem())) {
            p_58630_.displayClientMessage(new TranslatableComponent("container.isLocked", new Object[]{p_58632_}), true);
            p_58630_.playNotifySound(SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS, 1.0F, 1.0F);
            return false;
        } else {
            return true;
        }
    }

    @Nullable
    public BIAbstractContainerMenu BIcreateMenu(int p_58641_, Inventory p_58642_, Player p_58643_) {
        return this.canOpen(p_58643_) ? this.BIcreateMenu(p_58641_, p_58642_) : null;
    }

    protected abstract BIAbstractContainerMenu BIcreateMenu(int var1, Inventory var2);

    protected IItemHandler createUnSidedHandler() {
        return new InvWrapper(this);
    }

    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        return !this.remove && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? this.itemHandler.cast() : super.getCapability(cap, side);
    }

    public void invalidateCaps() {
        super.invalidateCaps();
        this.itemHandler.invalidate();
    }

    public void reviveCaps() {
        super.reviveCaps();
        this.itemHandler = LazyOptional.of(() -> {
            return this.createUnSidedHandler();
        });
    }
}
