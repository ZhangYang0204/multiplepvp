package pers.zhangyang.multiplepvp.listener.acitvepage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import pers.zhangyang.multiplepvp.domain.ActivePage;
import pers.zhangyang.multiplepvp.manager.ActiveManager;
import pers.zhangyang.multiplepvp.manager.RaceManager;
import pers.zhangyang.multiplepvp.util.MessageUtil;
import pers.zhangyang.multiplepvp.yaml.MessageYaml;

public class PlayerClickActivePage implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inventory=event.getInventory();
        InventoryHolder holder=inventory.getHolder();
        if (!(holder instanceof ActivePage)) {
            return;
        }
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        event.setCancelled(true);
        int slot=event.getRawSlot();

        Player player= (Player) event.getWhoClicked();

        switch (slot){
            case 9:
                if (RaceManager.INSTANCE.yellow.isFull1()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.full"));
                }else {
                    if (RaceManager.INSTANCE.yellow.canJoin1()) {
                        RaceManager.INSTANCE.yellow.join1(player);
                        ActivePage.refresh();
                    } else {
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFair"));
                    }
                }
                break;
            case 27:
                if (RaceManager.INSTANCE.yellow.isFull2()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.full"));
                }else {
                    if (RaceManager.INSTANCE.yellow.canJoin2()) {
                        RaceManager.INSTANCE.yellow.join2(player);
                        ActivePage.refresh();
                    } else {
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFair"));
                    }
                }
                break;
            case 11:
                if (RaceManager.INSTANCE.orange.isFull1()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.full"));
                }else {
                    if (RaceManager.INSTANCE.orange.canJoin1()) {
                        RaceManager.INSTANCE.orange.join1(player);
                        ActivePage.refresh();
                    } else {
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFair"));
                    }
                }
                break;
            case 29:
                if (RaceManager.INSTANCE.orange.isFull2()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.full"));
                }else {
                    if (RaceManager.INSTANCE.orange.canJoin2()) {
                        RaceManager.INSTANCE.orange.join2(player);
                        ActivePage.refresh();
                    } else {
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFair"));
                    }
                }
                break;
            case 13:
                if (RaceManager.INSTANCE.green.isFull1()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.full"));
                }else {
                    if (RaceManager.INSTANCE.green.canJoin1()) {
                        RaceManager.INSTANCE.green.join1(player);
                        ActivePage.refresh();
                    } else {
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFair"));
                    }
                }
                break;
            case 31:
                if (RaceManager.INSTANCE.green.isFull2()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.full"));
                }else {
                    if (RaceManager.INSTANCE.green.canJoin2()) {
                        RaceManager.INSTANCE.green.join2(player);
                        ActivePage.refresh();
                    } else {
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFair"));
                    }
                }
                break;
            case 15:
                if (RaceManager.INSTANCE.blue.isFull1()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.full"));
                }else {
                    if (RaceManager.INSTANCE.blue.canJoin1()) {
                        RaceManager.INSTANCE.blue.join1(player);
                        ActivePage.refresh();
                    } else {
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFair"));
                    }
                }
                break;
            case 33:
                if (RaceManager.INSTANCE.blue.isFull2()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.full"));
                }else {
                    if (RaceManager.INSTANCE.blue.canJoin2()) {
                        RaceManager.INSTANCE.blue.join2(player);
                        ActivePage.refresh();
                    } else {
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFair"));
                    }
                }
                break;
            case 17:
                if (RaceManager.INSTANCE.purple.isFull1()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.full"));
                }else {
                    if (RaceManager.INSTANCE.purple.canJoin1()) {
                        RaceManager.INSTANCE.purple.join1(player);
                        ActivePage.refresh();
                    } else {
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFair"));
                    }
                }
                break;
            case 35:
                if (RaceManager.INSTANCE.purple.isFull2()){
                    MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.full"));
                }else {
                    if (RaceManager.INSTANCE.purple.canJoin2()) {
                        RaceManager.INSTANCE.purple.join2(player);
                        ActivePage.refresh();
                    } else {
                        MessageUtil.sendMessageTo(player, MessageYaml.INSTANCE.getStringList("message.chat.notFair"));
                    }
                }
                break;
        }

    }
}