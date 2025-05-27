package com.eirmax.autototem.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;

public class TotemFinderUtil {
    public static boolean auto_equip = false;

    public static void toggleAutoEquip() {
        auto_equip = !auto_equip;
    }

    public static void setAutoEquip(boolean value) {
        auto_equip = value;
    }

    public static boolean isTotem(ItemStack stack) {
        return stack != null && stack.getItem() == Items.TOTEM_OF_UNDYING;
    }

    public static List<Integer> getTotemSlots(Minecraft client){
        List<Integer> totemSlots = new ArrayList<>();
        if(client.player == null) return totemSlots;

        for (int i = 0; i < client.player.getInventory().getContainerSize(); i++){
            if((i >= 1 && i <= 8) || (i >= 9 && i <= 35)){
                ItemStack stack = client.player.getInventory().getItem(i);
                if(isTotem(stack)){
                    totemSlots.add(i);
                }
            }
        }

        return totemSlots;
    }

    public static int getBestTotemSlot(Minecraft client) {
        List<Integer> totemSlots = getTotemSlots(client);
        if (totemSlots.isEmpty()) {
            return -1;
        }
        return totemSlots.get(0);
    }



    public static void swap(int slotInInventory, Minecraft client) {
        if (client.gameMode == null || client.player == null || client.player.containerMenu == null) {
            return;
        }

        int guiSlotSource = slotInInventory;

        if (guiSlotSource == 40) {
            guiSlotSource = 45;
        } else if (guiSlotSource >= 0 && guiSlotSource <= 8) {
            guiSlotSource += 36;
        }
        int guiSlotTarget = 45;
        try {
            client.gameMode.handleInventoryMouseClick(
                    client.player.containerMenu.containerId, guiSlotSource, 0, ClickType.PICKUP, client.player
            );
            client.gameMode.handleInventoryMouseClick(
                    client.player.containerMenu.containerId, guiSlotTarget, 0, ClickType.PICKUP, client.player
            );
            client.gameMode.handleInventoryMouseClick(
                    client.player.containerMenu.containerId, guiSlotSource, 0, ClickType.PICKUP, client.player
            );
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public static void swapToMainHand(int slotInInventory, Minecraft client) {
        if (client.gameMode == null || client.player == null || client.player.containerMenu == null) {
            return;
        }

        int guiSlotSource = slotInInventory;

        if (guiSlotSource == 40) {
            guiSlotSource = 45;
        } else if (guiSlotSource >= 0 && guiSlotSource <= 8) {
            guiSlotSource += 36;
        }

        int guiSlotTarget = 36;

        if (slotInInventory == 0) {
            return;
        }

        try {
            client.gameMode.handleInventoryMouseClick(
                    client.player.containerMenu.containerId, guiSlotSource, 0, ClickType.PICKUP, client.player
            );
            client.gameMode.handleInventoryMouseClick(
                    client.player.containerMenu.containerId, guiSlotTarget, 0, ClickType.PICKUP, client.player
            );
            client.gameMode.handleInventoryMouseClick(
                    client.player.containerMenu.containerId, guiSlotSource, 0, ClickType.PICKUP, client.player
            );
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public static void tryEquipTotemItemOffhand(Minecraft client){
        if(!auto_equip || client.player == null || client.player.isDeadOrDying()){
            return;
        }
        ItemStack offhandItem = client.player.getInventory().getItem(40);

        if(!isTotem(offhandItem) || offhandItem.isEmpty()){
            int totemslot = getBestTotemSlot(client);
            if(totemslot != -1){
                swap(totemslot, client);
            }
        }
    }

    public static void tryEquipTotemInMainHand(Minecraft client){
        if(!auto_equip || client.player == null || client.player.isDeadOrDying()){
            return;
        }
        ItemStack mainHandItem = client.player.getMainHandItem();
        if(!isTotem(mainHandItem)){
            int totemslot = getBestTotemSlot(client);

            if(totemslot != -1){
                swapToMainHand(totemslot, client);
            }
        }
    }

    public static void performManualTotemSwap(Minecraft client) {
        if (client.player == null || client.player.isDeadOrDying()) {
            return;
        }

        int totemSlot = getBestTotemSlot(client);

        if (totemSlot != -1) {
            swapToMainHand(totemSlot, client);
        }
    }

}
