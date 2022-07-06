package pers.zhangyang.multiplepvp.executor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pers.zhangyang.multiplepvp.base.ExecutorBase;
import pers.zhangyang.multiplepvp.domain.ActivePage;
import pers.zhangyang.multiplepvp.manager.ActiveManager;
import pers.zhangyang.multiplepvp.util.MessageUtil;
import pers.zhangyang.multiplepvp.yaml.MessageYaml;

public class Join extends ExecutorBase {
    public Join(@NotNull CommandSender sender, boolean forcePlayer, @NotNull String[] args) {
        super(sender, forcePlayer, args);
    }

    @Override
    protected void run() {
        Player player= (Player) sender;
        if (!ActiveManager.INSTANCE.active.isEnable()){
            MessageUtil.sendMessageTo(sender, MessageYaml.INSTANCE.getStringList("message.chat.activeStop"));
            return;
        }
        new ActivePage(player).send();
    }
}
