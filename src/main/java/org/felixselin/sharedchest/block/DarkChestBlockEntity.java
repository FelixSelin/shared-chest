package org.felixselin.sharedchest.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.felixselin.sharedchest.registry.ModBlocks;

import static org.felixselin.sharedchest.registry.ModBlockEntityType.DARK_CHEST;

public class DarkChestBlockEntity
        extends BlockEntity
        implements LidOpenable {
    private final ChestLidAnimator lidAnimator = new ChestLidAnimator();

    private static final DarkChestInventory inventory = new DarkChestInventory();
    private final ViewerCountManager stateManager = new ViewerCountManager(){

        @Override
        protected void onContainerOpen(World world, BlockPos pos, BlockState state) {
            world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_ENDER_CHEST_OPEN, SoundCategory.BLOCKS, 0.5f, world.random.nextFloat() * 0.1f + 0.9f);
        }

        @Override
        protected void onContainerClose(World world, BlockPos pos, BlockState state) {
            world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_ENDER_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5f, world.random.nextFloat() * 0.1f + 0.9f);
        }

        @Override
        protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
            world.addSyncedBlockEvent(DarkChestBlockEntity.this.pos, ModBlocks.DARK_CHEST, 1, newViewerCount);
        }

        @Override
        protected boolean isPlayerViewing(PlayerEntity player) {
            return DarkChestBlockEntity.getDarkChestInventory().isActiveBlockEntity(DarkChestBlockEntity.this);
        }
    };

    public DarkChestBlockEntity(BlockPos pos, BlockState state) {
        super(DARK_CHEST, pos, state);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, DarkChestBlockEntity blockEntity) {
        blockEntity.lidAnimator.step();
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if (type == 1) {
            this.lidAnimator.setOpen(data > 0);
            return true;
        }
        return super.onSyncedBlockEvent(type, data);
    }

    public void onOpen(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.openContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    public void onClose(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.closeContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        }
        return !(player.squaredDistanceTo((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) > 64.0);
    }

    public void onScheduledTick() {
        if (!this.removed) {
            this.stateManager.updateViewerCount(this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    public static DarkChestInventory getDarkChestInventory(){
        return inventory;
    }

    @Override
    public float getAnimationProgress(float tickDelta) {
        return this.lidAnimator.getProgress(tickDelta);
    }
}

