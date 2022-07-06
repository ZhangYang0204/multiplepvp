package pers.zhangyang.multiplepvp.yaml;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import pers.zhangyang.multiplepvp.MultiplePvp;
import pers.zhangyang.multiplepvp.base.YamlBase;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataYaml extends YamlBase {

    public static final DataYaml INSTANCE = new DataYaml();

    private DataYaml() {
        super("data.yml");
    }

    public void add(Player player){

        if (getInteger("data."+player.getName())==null){
            yamlConfiguration.set("data."+player.getName(),1);
        }else {
            yamlConfiguration.set("data."+player.getName(),1+getInteger("data."+player.getName()));
        }
        try {
            yamlConfiguration.save(new File(MultiplePvp.instance.getDataFolder(), filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Map.Entry<String,Integer>> rank(){
        HashMap<String,Integer> rank=new HashMap<>();
        for (String key:yamlConfiguration.getConfigurationSection("data").getKeys(false)){
            rank.put(key, yamlConfiguration.getInt("data."+key));
        }

        List<Map.Entry<String,Integer>> list=new ArrayList<>();
        list.addAll(rank.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }

        });
        return list;
    }

}