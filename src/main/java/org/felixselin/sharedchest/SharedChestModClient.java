package org.felixselin.sharedchest;

import net.fabricmc.api.ClientModInitializer;

import static org.felixselin.sharedchest.registry.ModBlockEntityRenderer.registerBlockEntityRenderer;
import static org.felixselin.sharedchest.registry.ModTextures.registerTextures;

public class SharedChestModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerBlockEntityRenderer();
        registerTextures();
    }
}
