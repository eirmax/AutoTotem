package com.eirmax.autototem.mixin;

import com.eirmax.autototem.utils.TotemFinderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class FlyingHelper {

    @Inject(method = "aiStep", at = @At("TAIL"))
    public void onAiStep(CallbackInfo ci) {

            Minecraft client = Minecraft.getInstance();
            if (TotemFinderUtil.auto_equip) {
                TotemFinderUtil.tryEquipTotemInMainHand(client);

        }
    }
}