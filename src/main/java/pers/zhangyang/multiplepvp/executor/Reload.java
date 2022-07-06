package pers.zhangyang.multiplepvp.executor;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pers.zhangyang.multiplepvp.base.ExecutorBase;
import pers.zhangyang.multiplepvp.domain.Race;
import pers.zhangyang.multiplepvp.manager.ActiveManager;
import pers.zhangyang.multiplepvp.manager.RaceManager;
import pers.zhangyang.multiplepvp.util.MessageUtil;
import pers.zhangyang.multiplepvp.yaml.DataYaml;
import pers.zhangyang.multiplepvp.yaml.GuiYaml;
import pers.zhangyang.multiplepvp.yaml.MessageYaml;
import pers.zhangyang.multiplepvp.yaml.SettingYaml;

import java.io.IOException;

public class Reload extends ExecutorBase {
    public Reload(@NotNull CommandSender sender, boolean forcePlayer, @NotNull String[] args) {
        super(sender, forcePlayer, args);
    }

    @Override
    protected void run() {

        try {
            SettingYaml.INSTANCE.init();
            MessageYaml.INSTANCE.init();
            DataYaml.INSTANCE.init();
            GuiYaml.INSTANCE.init();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            return;
        }
        RaceManager.INSTANCE.yellow.clear();
        RaceManager.INSTANCE.green.clear();
        RaceManager.INSTANCE.orange.clear();
        RaceManager.INSTANCE.blue.clear();
        RaceManager.INSTANCE.purple.clear();

        RaceManager.INSTANCE.yellow= SettingYaml.INSTANCE.getRace("setting.race.yellow");
        RaceManager.INSTANCE.orange= SettingYaml.INSTANCE.getRace("setting.race.orange");
        RaceManager.INSTANCE.green= SettingYaml.INSTANCE.getRace("setting.race.green");
        RaceManager.INSTANCE.blue= SettingYaml.INSTANCE.getRace("setting.race.blue");
        RaceManager.INSTANCE.purple= SettingYaml.INSTANCE.getRace("setting.race.purple");


        MessageUtil.sendMessageTo(sender, MessageYaml.INSTANCE.getStringList("message.chat.reload"));

    }
}