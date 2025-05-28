package com.eirmax.neoforge.client;

import com.eirmax.autototem.utils.TotemFinderUtil;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.util.Lazy;

@EventBusSubscriber(modid = "elytraswapperplus", bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientNeoforgeEvent {
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();

        for (Lazy<KeyMapping> keyLazy : ClientNeoforgeKeybindEvent.keyMappings) {
            KeyMapping key = keyLazy.get();
            while (minecraft.player != null && key.consumeClick()) {
                if (key.getName().equals("key.elytraswapplus.swap")) {
                    TotemFinderUtil.performManualOffhandSwap(minecraft);
                }
                if (key.getName().equals("key.elytraswapplus.auto_swap")) {
                    TotemFinderUtil.toggleAutoEquip();

                    Component message = Component.translatable(
                            "msg.elytraswapperplus.auto_swap." + (TotemFinderUtil.auto_equip ? "enabled" : "disabled")
                    );
                    if (minecraft.player != null) {
                        minecraft.player.displayClientMessage(message, true);
                    }
                }
            }
        }
    }
}