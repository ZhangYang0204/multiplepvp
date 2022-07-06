package pers.zhangyang.multiplepvp.yaml;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import pers.zhangyang.multiplepvp.base.YamlBase;
import pers.zhangyang.multiplepvp.domain.Race;

public class SettingYaml extends YamlBase {

    public static final SettingYaml INSTANCE = new SettingYaml();

    private SettingYaml() {
        super("setting.yml");
    }


    public Race getRace(String path){
        Location[] locations=new Location[2];
        for (int i=1;i<3;i++){
            double yaw=getDoubleDefault(path+".spawnLocation."+i+".yaw");
            double pitch= getDoubleDefault(path+".spawnLocation."+i+".pitch");
            locations[i-1]=new Location(Bukkit.getWorld(getStringDefault(path+".world")),
                    getDoubleDefault(path+".spawnLocation."+i+".x"),getDoubleDefault(path+".spawnLocation."+i+".y"),
                    getDoubleDefault(path+".spawnLocation."+i+".z"),(float) yaw,(float)pitch);
        }
        Location lobbyLocation1;
        double yaw=getDoubleDefault(path+".lobby1.yaw");
        double pitch= getDoubleDefault(path+".lobby1.pitch");
        lobbyLocation1 = new Location(Bukkit.getWorld(getStringDefault(path+".world")),
                getDoubleDefault(path+".lobby1.x"),getDoubleDefault(path+".lobby1.y"),
                getDoubleDefault(path+".lobby1.z"),(float) yaw,(float)pitch);

        Location lobbyLocation2;
        double yaw2=getDoubleDefault(path+".lobby2.yaw");
        double pitch2= getDoubleDefault(path+".lobby2.pitch");
        lobbyLocation2 = new Location(Bukkit.getWorld(getStringDefault(path+".world")),
                getDoubleDefault(path+".lobby2.x"),getDoubleDefault(path+".lobby2.y"),
                getDoubleDefault(path+".lobby2.z"),(float) yaw2,(float)pitch2);
        return new Race(locations[0],locations[1],lobbyLocation1,lobbyLocation2,getStringDefault(path+".name"),getIntegerDefault(path+".time"),getIntegerDefault(path+".respawn"));
    }



}
