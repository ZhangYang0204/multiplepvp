package pers.zhangyang.multiplepvp.listener.race;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import pers.zhangyang.multiplepvp.domain.Active;
import pers.zhangyang.multiplepvp.domain.Race;
import pers.zhangyang.multiplepvp.manager.ActiveManager;

public class PlayerTeleportOtherWorld implements Listener {
    @EventHandler
    public void on(PlayerTeleportEvent event){

        Player player= event.getPlayer();
        Race race=ActiveManager.INSTANCE.active.getRace(player);
        if (race==null){
            return;
        }
        if (race.getLocation1().getWorld().equals(event.getTo().getWorld())||race.getLocation2().getWorld().equals(event.getTo().getWorld())){
            return;
        }
        if (!race.isRacing()&&!race.isReadying()){
            return;
        }

        event.setCancelled(true);

    }
}
