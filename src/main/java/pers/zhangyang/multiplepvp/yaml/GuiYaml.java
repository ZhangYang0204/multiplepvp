package pers.zhangyang.multiplepvp.yaml;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import pers.zhangyang.multiplepvp.base.YamlBase;
import pers.zhangyang.multiplepvp.util.ItemStackUtil;
import pers.zhangyang.multiplepvp.util.MinecraftVersionUtil;

import java.util.ArrayList;
import java.util.List;

public class GuiYaml extends YamlBase {

    public static final GuiYaml INSTANCE = new GuiYaml();

    private GuiYaml() {
        super("gui.yml");
    }

    public ItemStack getButton(String path) {
        String materialName = getStringDefault(path + ".materialName");
        Material material=Material.matchMaterial(materialName);
        if (material==null || material.equals(Material.AIR)){
            materialName=backUpConfiguration.getString(path + ".materialName");
            assert materialName!=null;
            material=Material.matchMaterial(materialName);
        }
        assert material!=null;
        String displayName = getString(path + ".displayName");
        List<String> lore = getStringList(path + ".lore");
        int amount = getIntegerDefault(path + ".amount");
        List<String> itemFlagName = getStringListDefault(path + ".itemFlag");
        List<ItemFlag> itemFlagList = new ArrayList<>();
        Integer customModelData = getInteger(path + ".amount");
        for (String s : itemFlagName) {
            try {
                itemFlagList.add(ItemFlag.valueOf(s));
            } catch (IllegalArgumentException ignored) {
            }
        }
        if (MinecraftVersionUtil.getBigVersion() == 1 && MinecraftVersionUtil
                .getMiddleVersion() <13){
            return ItemStackUtil.getItemStack(material, displayName, lore, itemFlagList,amount);
        }else {
            return ItemStackUtil.getItemStack(material, displayName, lore, itemFlagList,amount,customModelData);
        }
    }
}

