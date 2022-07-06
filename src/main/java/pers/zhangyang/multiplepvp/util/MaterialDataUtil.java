package pers.zhangyang.multiplepvp.util;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

public class MaterialDataUtil {

    @NotNull
    public static MaterialData deserializeMaterialData(@NotNull String data){
        if (data==null){throw new NullPointerException();}
        Material material=Material.matchMaterial(data.split(",")[0]);
        try {
            Byte.valueOf(data.split(",")[1]);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException();
        }
        if (material==null){throw new IllegalArgumentException();}
     return new MaterialData(material,Byte.valueOf(data.split(",")[1]));
    }

    @NotNull
    public static String serializeMaterialData(@NotNull MaterialData materialData){
        if (materialData==null){throw new NullPointerException();}
        return materialData.getItemType().name()+","+materialData.getData();
    }
}
