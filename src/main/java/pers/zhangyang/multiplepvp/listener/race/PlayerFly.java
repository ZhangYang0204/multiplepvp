package pers.zhangyang.multiplepvp.listener.race;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import pers.zhangyang.multiplepvp.domain.Race;
import pers.zhangyang.multiplepvp.manager.ActiveManager;
import pers.zhangyang.multiplepvp.yaml.SettingYaml;

import java.util.ArrayList;
import java.util.List;

public class PlayerFly implements Listener {
    @EventHandler
    public void on(PlayerToggleFlightEvent event){

        Player player= event.getPlayer();
        List<World> worldList=new ArrayList<>();


        if (SettingYaml.INSTANCE.getStringList("setting.antiFly")==null){
            return;
        }
        for (String wn: SettingYaml.INSTANCE.getStringList("setting.antiFly")){
            worldList.add(Bukkit.getWorld(wn));
        }

        if (worldList.contains(player.getWorld())) {
            player.setAllowFlight(false);
        }
    }
}