package org.felixselin.sharedchest;

import net.fabricmc.api.ModInitializer;
import org.felixselin.sharedchest.block.ModBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedChestMod implements ModInitializer {
	public static final String MOD_ID = "sharedchestmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
	}
}
