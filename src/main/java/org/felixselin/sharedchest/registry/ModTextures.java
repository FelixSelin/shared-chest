package org.felixselin.sharedchest.registry;

import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.TexturedRenderLayers;
import org.felixselin.sharedchest.block.DarkChestBlock;

public class ModTextures {
    public static void registerTextures() {
        ClientSpriteRegistryCallback.event(TexturedRenderLayers.CHEST_ATLAS_TEXTURE).register((texture, registry) -> {
            registry.register(DarkChestBlock.texture);
        });

    }
}
