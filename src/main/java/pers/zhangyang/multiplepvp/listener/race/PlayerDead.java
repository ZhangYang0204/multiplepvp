package pers.zhangyang.multiplepvp.listener.race;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pers.zhangyang.multiplepvp.domain.Race;
import pers.zhangyang.multiplepvp.manager.ActiveManager;

public class PlayerDead implements Listener {
    @EventHandler
    public void on(PlayerDeathEvent event){

        Player player= event.getEntity();
        Race race= ActiveManager.INSTANCE.active.getRace(player);
        if (race==null){
            return;
        }
        event.setDeathMessage(null);
        if (!race.isRacing()){
            player.spigot().respawn();
            if (race.getPlayerList1().contains(player)) {
                player.teleport(race.getLobbyLocation1());
            }
            if (race.getPlayerList2().contains(player)) {
                player.teleport(race.getLobbyLocation2());
            }
            return;
        }
        race.out(player);

    }
}
