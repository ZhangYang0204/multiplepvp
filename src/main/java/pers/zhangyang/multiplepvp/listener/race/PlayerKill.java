package pers.zhangyang.multiplepvp.listener.race;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pers.zhangyang.multiplepvp.domain.Race;
import pers.zhangyang.multiplepvp.manager.ActiveManager;
import pers.zhangyang.multiplepvp.util.MessageUtil;
import pers.zhangyang.multiplepvp.util.ReplaceUtil;
import pers.zhangyang.multiplepvp.yaml.MessageYaml;
import pers.zhangyang.multiplepvp.yaml.SettingYaml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerKill implements Listener {
    @EventHandler
public void on(EntityDamageByEntityEvent event){

        if (!(event.getEntity() instanceof Player)){
            return;
        }
        if (!(event.getDamager() instanceof Player)){
            return;
        }
        if (((Player) event.getEntity()).getHealth()- event.getDamage()>0){
            return;
        }
        Player player= (Player) event.getDamager();
        Player target= (Player) event.getEntity();
    Race race= ActiveManager.INSTANCE.active.getRace(player);
    if (race==null){
        return;
    }
    if (race.isFriend(player,target)){
        return;
    }
    if (target.isDead()){
        return;
    }
    race.kill(player);
        List<String> stringList=MessageYaml.INSTANCE.getStringList("message.chat.out");
        Map<String,String> rep=new HashMap<>();
        rep.put("{player}",player.getName());
        rep.put("{target}",target.getName());
        if (stringList!=null){
            ReplaceUtil.replace(stringList,rep);
        }

    for(Player p:race.getPlayerList1()){
        MessageUtil.sendMessageTo(p, stringList);
    }for(Player p:race.getPlayerList2()){
            MessageUtil.sendMessageTo(p, stringList);
        }


    }
}
