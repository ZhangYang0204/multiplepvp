package pers.zhangyang.multiplepvp.listener.race;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import pers.zhangyang.multiplepvp.domain.Race;
import pers.zhangyang.multiplepvp.manager.ActiveManager;

public class PlayerQuit implements Listener {
    @EventHandler
    public void on(PlayerQuitEvent event){

        Player player= event.getPlayer();
        Race race= ActiveManager.INSTANCE.active.getRace(player);
        if (race==null){
            return;
        }
        race.remove(player);

    }
}
