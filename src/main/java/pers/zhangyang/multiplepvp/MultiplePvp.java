package pers.zhangyang.multiplepvp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import pers.zhangyang.multiplepvp.domain.ActivePage;
import pers.zhangyang.multiplepvp.executor.*;
import pers.zhangyang.multiplepvp.listener.acitvepage.PlayerClickActivePage;
import pers.zhangyang.multiplepvp.listener.race.*;
import pers.zhangyang.multiplepvp.manager.RaceManager;
import pers.zhangyang.multiplepvp.yaml.DataYaml;
import pers.zhangyang.multiplepvp.yaml.GuiYaml;
import pers.zhangyang.multiplepvp.yaml.MessageYaml;
import pers.zhangyang.multiplepvp.yaml.SettingYaml;

import java.io.IOException;

public class MultiplePvp extends JavaPlugin {
    public static MultiplePvp instance;

    @Override
    public void onEnable() {
        instance = this;
        // 初始化yml,出错直接关闭插件
        try {
            SettingYaml.INSTANCE.init();
            MessageYaml.INSTANCE.init();
            DataYaml.INSTANCE.init();
            GuiYaml.INSTANCE.init();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            setEnabled(false);
            return;
        }
        registerListener();
    }

    @Override
    public void onDisable() {
        for (Player p: Bukkit.getOnlinePlayers()){
            Inventory inventory=p.getOpenInventory().getTopInventory();
            InventoryHolder holder=inventory.getHolder();
            if (holder instanceof ActivePage){
                p.closeInventory();
            }
        }
        RaceManager.INSTANCE.yellow.clear();
        RaceManager.INSTANCE.green.clear();
        RaceManager.INSTANCE.orange.clear();
        RaceManager.INSTANCE.blue.clear();
        RaceManager.INSTANCE.purple.clear();


    }    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length<1){
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "startactive":
                new StartActive(sender, false, args).process();
                break;
            case "join":
                new Join(sender, true, args).process();
                break;
            case "help":
                new Help(sender, false, args).process();
                break;
            case "leave":
                new Leave(sender, true, args).process();
                break;
            case "reload":
                new Reload(sender, false, args).process();
                break;
        }
        return true;
    }
    private void registerListener() {
        Bukkit.getPluginManager().registerEvents(new PlayerClickActivePage(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerDead(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerKill(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerTeleportOtherWorld(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerFriendDamage(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerTpRaceWorld(),this);
       Bukkit.getPluginManager().registerEvents(new PlayerFly(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerTeleteportAntiFlyWorld(),this);

    }
}
