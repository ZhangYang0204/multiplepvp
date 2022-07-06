package pers.zhangyang.multiplepvp.domain;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import pers.zhangyang.multiplepvp.manager.RaceManager;

public class Active {
    protected boolean enable=false;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Nullable
    public Race getRace(Player player){
        if (RaceManager.INSTANCE.yellow.contains(player)){
            return RaceManager.INSTANCE.yellow;
        }
        if (RaceManager.INSTANCE.green.contains(player)){
            return RaceManager.INSTANCE.green;
        }
        if (RaceManager.INSTANCE.blue.contains(player)){
            return RaceManager.INSTANCE.blue;
        }
        if (RaceManager.INSTANCE.orange.contains(player)){
            return RaceManager.INSTANCE.orange;
        }
        if (RaceManager.INSTANCE.purple.contains(player)){
            return RaceManager.INSTANCE.purple;
        }
        return null;
    }
}
