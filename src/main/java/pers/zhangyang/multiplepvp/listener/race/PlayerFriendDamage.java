package pers.zhangyang.multiplepvp.listener.race;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pers.zhangyang.multiplepvp.domain.Race;
import pers.zhangyang.multiplepvp.manager.ActiveManager;
import pers.zhangyang.multiplepvp.util.MessageUtil;
import pers.zhangyang.multiplepvp.yaml.MessageYaml;
import pers.zhangyang.multiplepvp.yaml.SettingYaml;

public class PlayerFriendDamage implements Listener {
    @EventHandler
    public void on(EntityDamageByEntityEvent event){

        if (!(event.getEntity() instanceof Player)){
            return;
        }
        if (!(event.getDamager() instanceof Player)){
            return;
        }
        Player player= (Player) event.getDamager();
        Player target= (Player) event.getEntity();
        Race race= ActiveManager.INSTANCE.active.getRace(player);
        if (race==null){
            return;
        }
        if (!SettingYaml.INSTANCE.getBooleanDefault("setting.friendDamage")&&race.isFriend(player,target)){
            event.setCancelled(true);
            MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFriendDamage"));
        }
    }
}
