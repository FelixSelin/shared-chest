package org.felixselin.sharedchest.registry;

import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import org.felixselin.sharedchest.client.ChestEntityRenderer;

public class ModBlockEntityRenderer {
    public static void registerBlockEntityRenderer() {
        BlockEntityRendererRegistry.register(ModBlockEntityType.DARK_CHEST, ChestEntityRenderer::new);
    }
}
