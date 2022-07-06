package pers.zhangyang.multiplepvp.yaml;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pers.zhangyang.multiplepvp.base.YamlBase;

public class MessageYaml extends YamlBase {

    public static final MessageYaml INSTANCE = new MessageYaml();

    private MessageYaml() {
        super("message.yml");
    }


}
