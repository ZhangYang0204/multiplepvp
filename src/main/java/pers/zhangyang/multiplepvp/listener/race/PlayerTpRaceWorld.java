package pers.zhangyang.multiplepvp.listener.race;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import pers.zhangyang.multiplepvp.domain.Race;
import pers.zhangyang.multiplepvp.manager.ActiveManager;
import pers.zhangyang.multiplepvp.manager.RaceManager;

public class PlayerTpRaceWorld implements Listener {
    @EventHandler
    public void on(PlayerTeleportEvent event){

        Player player= event.getPlayer();
        Race race= ActiveManager.INSTANCE.active.getRace(player);
        if (race!=null){
            return;
        }

        World worldTo=event.getTo().getWorld();
        if (worldTo.equals(RaceManager.INSTANCE.yellow.getLocation1().getWorld())||worldTo.equals(RaceManager.INSTANCE.orange.getLocation1().getWorld())
        ||worldTo.equals(RaceManager.INSTANCE.green.getLocation1().getWorld())||worldTo.equals(RaceManager.INSTANCE.blue.getLocation1().getWorld())
        ||worldTo.equals(RaceManager.INSTANCE.purple.getLocation1().getWorld())){
            event.setCancelled(true);
        }



    }
}