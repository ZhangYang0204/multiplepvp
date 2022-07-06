package pers.zhangyang.multiplepvp.util;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemMetaUtil {
    public static String serializeItemMeta(ItemMeta meta){
        YamlConfiguration yml = new YamlConfiguration();
        yml.set("data", meta);
        return yml.saveToString();
    }

    public static ItemMeta deserializeItemMeta(String data){
        YamlConfiguration yml = new YamlConfiguration();
        try {
            yml.loadFromString(data);
        } catch (InvalidConfigurationException e) {
            throw new IllegalArgumentException();
        }
        Object obj = yml.getSerializable("data",ItemMeta.class);
        if (obj == null){
         throw new IllegalArgumentException();
        }
        return (ItemMeta) obj;
    }
}
