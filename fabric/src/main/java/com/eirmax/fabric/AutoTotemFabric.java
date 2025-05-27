package com.eirmax.fabric;

import com.eirmax.AutoTotemChange;
import net.fabricmc.api.ModInitializer;


public final class AutoTotemFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AutoTotemChange.init();
    }
}
