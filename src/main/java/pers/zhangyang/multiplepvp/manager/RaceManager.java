package pers.zhangyang.multiplepvp.manager;

import pers.zhangyang.multiplepvp.domain.Race;
import pers.zhangyang.multiplepvp.yaml.SettingYaml;

public class RaceManager {
    public static final RaceManager INSTANCE = new RaceManager();

    public Race yellow= SettingYaml.INSTANCE.getRace("setting.race.yellow");
    public Race orange= SettingYaml.INSTANCE.getRace("setting.race.orange");
    public Race green= SettingYaml.INSTANCE.getRace("setting.race.green");
    public Race blue= SettingYaml.INSTANCE.getRace("setting.race.blue");
    public Race purple= SettingYaml.INSTANCE.getRace("setting.race.purple");

}
