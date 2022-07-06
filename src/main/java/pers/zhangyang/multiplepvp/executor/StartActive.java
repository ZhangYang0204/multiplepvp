package pers.zhangyang.multiplepvp.executor;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import pers.zhangyang.multiplepvp.MultiplePvp;
import pers.zhangyang.multiplepvp.base.ExecutorBase;
import pers.zhangyang.multiplepvp.domain.ActivePage;
import pers.zhangyang.multiplepvp.manager.ActiveManager;
import pers.zhangyang.multiplepvp.manager.RaceManager;
import pers.zhangyang.multiplepvp.util.MessageUtil;
import pers.zhangyang.multiplepvp.util.ReplaceUtil;
import pers.zhangyang.multiplepvp.yaml.MessageYaml;
import pers.zhangyang.multiplepvp.yaml.SettingYaml;

import java.util.Collections;

public class StartActive extends ExecutorBase {
    public StartActive(@NotNull CommandSender sender, boolean forcePlayer, @NotNull String[] args) {
        super(sender, forcePlayer, args);
    }

    @Override
    protected void run() {
        if (RaceManager.INSTANCE.yellow.isRacing()||RaceManager.INSTANCE.orange.isRacing()||RaceManager.INSTANCE.green.isRacing()
        ||RaceManager.INSTANCE.blue.isRacing()||RaceManager.INSTANCE.purple.isRacing()){
            MessageUtil.sendMessageTo(sender, MessageYaml.INSTANCE.getStringList("message.chat.raceNotAllStop"));
            return;
        }
        String m=MessageYaml.INSTANCE.getString("message.component.activeStart");
        if (m!=null){
            m= ChatColor.translateAlternateColorCodes('&',m);
        }
        BaseComponent text = new TextComponent(m);

        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/multiplepvp join"));
        for (Player p:Bukkit.getOnlinePlayers()){
            p.spigot().sendMessage(text);
        }
        if (ActiveManager.INSTANCE.active.isEnable()){
            return;
        }
        ActiveManager.INSTANCE.active.setEnable(true);
        final int[] activeTime = {SettingYaml.INSTANCE.getIntegerDefault("setting.activeTime")};
        new BukkitRunnable() {
            @Override
            public void run() {


                String title=MessageYaml.INSTANCE.getString("message.actionBar.ready");

                for (Player p:Bukkit.getOnlinePlayers()){
                    String m=ReplaceUtil.replace(title, Collections.singletonMap("{s}",activeTime[0]+""));
                    if (m!=null){
                        m= ChatColor.translateAlternateColorCodes('&',m);
                    }
                    BaseComponent text = new TextComponent(m);
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,text);
                }
                activeTime[0]--;

                if(activeTime[0]==0){
                    ActiveManager.INSTANCE.active.setEnable(false);
                    for (Player p: Bukkit.getOnlinePlayers()){
                        Inventory inventory=p.getOpenInventory().getTopInventory();
                        InventoryHolder holder=inventory.getHolder();
                        if (holder instanceof ActivePage){
                            p.closeInventory();
                            MessageUtil.sendMessageTo(sender,MessageYaml.INSTANCE.getStringList("message.chat.activeStop"));
                        }
                    }
                    RaceManager.INSTANCE.yellow.start();
                    RaceManager.INSTANCE.orange.start();
                    RaceManager.INSTANCE.green.start();
                    RaceManager.INSTANCE.blue.start();
                    RaceManager.INSTANCE.purple.start();
                    cancel();
                    return;
                }
            }
        }.runTaskTimer(MultiplePvp.instance,1,20);
        MessageUtil.sendMessageTo(sender, MessageYaml.INSTANCE.getStringList("message.chat.startActive"));
    }
}
