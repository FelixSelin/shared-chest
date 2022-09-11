package org.felixselin.sharedchest.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.felixselin.sharedchest.SharedChestMod;

public class ModBlockEntityType {
    public static final BlockEntityType<DarkChestBlockEntity> DARK_CHEST = FabricBlockEntityTypeBuilder.create(DarkChestBlockEntity::new, ModBlocks.DARK_CHEST).build(null);
    public static void registerBlockEntities() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(SharedChestMod.MOD_ID, "darkchest_block"), DARK_CHEST);
    }
}
