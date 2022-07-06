package pers.zhangyang.multiplepvp.executor;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import pers.zhangyang.multiplepvp.base.ExecutorBase;
import pers.zhangyang.multiplepvp.util.MessageUtil;
import pers.zhangyang.multiplepvp.yaml.MessageYaml;

public class Help extends ExecutorBase {
    public Help(@NotNull CommandSender sender, boolean forcePlayer, @NotNull String[] args) {
        super(sender, forcePlayer, args);
    }

    @Override
    protected void run() {
        MessageUtil.sendMessageTo(sender, MessageYaml.INSTANCE.getStringList("message.chat.help"));

    }
}
