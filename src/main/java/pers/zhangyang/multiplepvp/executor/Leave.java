package pers.zhangyang.multiplepvp.executor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pers.zhangyang.multiplepvp.base.ExecutorBase;
import pers.zhangyang.multiplepvp.domain.ActivePage;
import pers.zhangyang.multiplepvp.domain.Race;
import pers.zhangyang.multiplepvp.manager.ActiveManager;
import pers.zhangyang.multiplepvp.util.MessageUtil;
import pers.zhangyang.multiplepvp.yaml.MessageYaml;

public class Leave extends ExecutorBase {
    public Leave(@NotNull CommandSender sender, boolean forcePlayer, @NotNull String[] args) {
        super(sender, forcePlayer, args);
    }

    @Override
    protected void run() {
        Player player= (Player) sender;
        Race race= ActiveManager.INSTANCE.active.getRace(player);
        if (race==null){
            MessageUtil.sendMessageTo(sender, MessageYaml.INSTANCE.getStringList("message.chat.notInRace"));
            return;
        }

        race.remove(player);
        MessageUtil.sendMessageTo(sender, MessageYaml.INSTANCE.getStringList("message.chat.leaveRace"));
    }
}
