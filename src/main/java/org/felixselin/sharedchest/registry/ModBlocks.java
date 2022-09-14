package org.felixselin.sharedchest.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.EnderChestBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.felixselin.sharedchest.SharedChestMod;
import org.felixselin.sharedchest.block.DarkChestBlock;

public class ModBlocks {

    public static final Block DARK_CHEST = registerBlock("darkchest_block",
            new DarkChestBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()), ItemGroup.MISC);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(SharedChestMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(SharedChestMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks() {
        SharedChestMod.LOGGER.info("Registering ModBlocks for " + SharedChestMod.MOD_ID);
    }
}
