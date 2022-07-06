package pers.zhangyang.multiplepvp.listener.race;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import pers.zhangyang.multiplepvp.yaml.SettingYaml;

import java.util.ArrayList;
import java.util.List;

public class PlayerTeleteportAntiFlyWorld implements Listener {
    @EventHandler
    public void o(PlayerTeleportEvent event){

        Player player= event.getPlayer();
        List<World> worldList=new ArrayList<>();

        if (SettingYaml.INSTANCE.getStringList("setting.antiFly")==null){
            return;
        }
        for (String wn: SettingYaml.INSTANCE.getStringList("setting.antiFly")){
            worldList.add(Bukkit.getWorld(wn));
        }
        if (event.getTo()==null){
            return;
        }
        if (worldList.contains(event.getTo().getWorld())) {
            player.setAllowFlight(false);
        }
    }
}
