package pers.zhangyang.multiplepvp.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pers.zhangyang.multiplepvp.exception.UnsupportedMinecraftVersionException;

import java.util.List;

public class ItemStackUtil {

    @NotNull
    public static ItemStack setLore(@NotNull ItemStack itemStack, @Nullable List<String> lore){
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.setLore(lore);
         itemStack.setItemMeta(itemMeta);
         return itemStack;
    }

    @NotNull
    public static ItemStack setDisplayName(@NotNull ItemStack itemStack, @Nullable String displayName){
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @NotNull
    public static ItemStack getItemStack(@NotNull Material material, @Nullable String displayName,
                                         @Nullable List<String> lore, @Nullable List<ItemFlag> flagList,
                                         int amount) {

        ItemStack itemStack = new ItemStack(material,amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            throw new IllegalArgumentException(material.name());
        }
        if (lore != null) {
            for (int i = 0; i < lore.size(); i++) {
                lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
            }
            itemMeta.setLore(lore);
        }
        if (displayName != null) {
            displayName = ChatColor.translateAlternateColorCodes('&', displayName);
            itemMeta.setDisplayName(displayName);
        }
        if (flagList != null) {
            for (ItemFlag itemFlag : flagList) {
                itemMeta.addItemFlags(itemFlag);
            }
        }
        if (!itemStack.setItemMeta(itemMeta)) {
            throw new IllegalArgumentException();
        }
        return itemStack;

    }

    @NotNull
    public static ItemStack getItemStack(@NotNull Material material, @Nullable String displayName,
                                         @Nullable List<String> lore, @Nullable List<ItemFlag> flagList,
                                         int amount, @Nullable Integer customModelData) {
        if (MinecraftVersionUtil.getBigVersion() == 1 && MinecraftVersionUtil
                .getMiddleVersion() <13){
            throw new UnsupportedMinecraftVersionException();
        }
        ItemStack itemStack=getItemStack(material,displayName,lore,flagList,amount);
        ItemMeta itemMeta=itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setCustomModelData(customModelData);
        assert itemStack.setItemMeta(itemMeta);
        return itemStack;

    }

    @NotNull
    public static String itemStackSerialize(@NotNull ItemStack itemStack) {
        YamlConfiguration yml = new YamlConfiguration();
        yml.set("data", itemStack);
        return yml.saveToString();
    }

    @NotNull
    public static ItemStack itemStackDeserialize(@NotNull String str) {
        YamlConfiguration yml = new YamlConfiguration();
        ItemStack item;
        try {
            yml.loadFromString(str);
        } catch (InvalidConfigurationException e) {
            throw new IllegalArgumentException();
        }
        item = yml.getItemStack("data");
        if (item == null) {
            throw new IllegalArgumentException();
        }
        return item;
    }
}
