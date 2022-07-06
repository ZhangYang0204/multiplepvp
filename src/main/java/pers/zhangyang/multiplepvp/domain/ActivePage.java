package pers.zhangyang.multiplepvp.domain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pers.zhangyang.multiplepvp.manager.RaceManager;
import pers.zhangyang.multiplepvp.util.ReplaceUtil;
import pers.zhangyang.multiplepvp.yaml.DataYaml;
import pers.zhangyang.multiplepvp.yaml.GuiYaml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ActivePage implements InventoryHolder {

    private Inventory inventory;
    private Player player;

    public ActivePage(Player player) {
        this.player=player;
        String title = GuiYaml.INSTANCE.getString("gui.title.activePage");
        if (title == null) {
            this.inventory = Bukkit.createInventory(this, 54);
        } else {
            this.inventory = Bukkit.createInventory(this, 54, ChatColor.translateAlternateColorCodes('&', title));
        }
    }

    public static void refresh(){
        for (Player p:Bukkit.getOnlinePlayers()){
            if (p.getOpenInventory().getTopInventory().getHolder() instanceof ActivePage){
                ActivePage activePage= (ActivePage) p.getOpenInventory().getTopInventory().getHolder();
                activePage.send();
            }
        }
    }

    public void send(){

        ItemStack yellow1 = GuiYaml.INSTANCE.getButton("gui.button.activePage.yellow1");
        ReplaceUtil.replaceDisplayName(yellow1, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.yellow.playerList1.size())));
        ReplaceUtil.replaceLore(yellow1, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.yellow.playerList1.size())));
        List<String> name=new ArrayList<>();
        for (Player p:RaceManager.INSTANCE.yellow.playerList1){
            name.add(p.getName());
        }
        ReplaceUtil.formatLore(yellow1, "{(member)}",name);
        inventory.setItem(9, yellow1);
        ItemStack yellowGuide = GuiYaml.INSTANCE.getButton("gui.button.activePage.yellowGuide");
        inventory.setItem(18, yellowGuide);
        ItemStack yellow2 = GuiYaml.INSTANCE.getButton("gui.button.activePage.yellow2");
        ReplaceUtil.replaceDisplayName(yellow2, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.yellow.playerList2.size())));
        ReplaceUtil.replaceLore(yellow2, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.yellow.playerList2.size())));
        name=new ArrayList<>();
        for (Player p:RaceManager.INSTANCE.yellow.playerList2){
            name.add(p.getName());
        }
        ReplaceUtil.formatLore(yellow2, "{(member)}",name);
        inventory.setItem(27, yellow2);

        ItemStack orange1 = GuiYaml.INSTANCE.getButton("gui.button.activePage.orange1");
        ReplaceUtil.replaceDisplayName(orange1, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.orange.playerList1.size())));
        ReplaceUtil.replaceLore(orange1, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.orange.playerList1.size())));
        name=new ArrayList<>();
        for (Player p:RaceManager.INSTANCE.orange.playerList1){
            name.add(p.getName());
        }
        ReplaceUtil.formatLore(orange1, "{(member)}",name);
        inventory.setItem(11, orange1);
        ItemStack orangeGuide = GuiYaml.INSTANCE.getButton("gui.button.activePage.orangeGuide");
        inventory.setItem(20, orangeGuide);
        ItemStack orange2 = GuiYaml.INSTANCE.getButton("gui.button.activePage.orange2");
        ReplaceUtil.replaceDisplayName(orange2, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.orange.playerList2.size())));
        ReplaceUtil.replaceLore(orange2, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.orange.playerList2.size())));
        name=new ArrayList<>();
        for (Player p:RaceManager.INSTANCE.orange.playerList2){
            name.add(p.getName());
        }
        ReplaceUtil.formatLore(orange2, "{(member)}",name);
        inventory.setItem(29, orange2);

        ItemStack green1 = GuiYaml.INSTANCE.getButton("gui.button.activePage.green1");
        ReplaceUtil.replaceDisplayName(green1, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.green.playerList1.size())));
        ReplaceUtil.replaceLore(green1, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.green.playerList1.size())));
        name=new ArrayList<>();
        for (Player p:RaceManager.INSTANCE.green.playerList1){
            name.add(p.getName());
        }
        ReplaceUtil.formatLore(green1, "{(member)}",name);
        inventory.setItem(13, green1);
        ItemStack greenGuide = GuiYaml.INSTANCE.getButton("gui.button.activePage.greenGuide");
        inventory.setItem(22, greenGuide);
        ItemStack green2 = GuiYaml.INSTANCE.getButton("gui.button.activePage.green2");
        ReplaceUtil.replaceDisplayName(green2, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.green.playerList2.size())));
        ReplaceUtil.replaceLore(green2, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.green.playerList2.size())));
        name=new ArrayList<>();
        for (Player p:RaceManager.INSTANCE.green.playerList2){
            name.add(p.getName());
        }
        ReplaceUtil.formatLore(green2, "{(member)}",name);
        inventory.setItem(31, green2);

        ItemStack blue1 = GuiYaml.INSTANCE.getButton("gui.button.activePage.blue1");
        ReplaceUtil.replaceDisplayName(blue1, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.blue.playerList1.size())));
        ReplaceUtil.replaceLore(blue1, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.blue.playerList1.size())));
        name=new ArrayList<>();
        for (Player p:RaceManager.INSTANCE.blue.playerList1){
            name.add(p.getName());
        }
        ReplaceUtil.formatLore(blue1, "{(member)}",name);
        inventory.setItem(15, blue1);
        ItemStack blueGuide = GuiYaml.INSTANCE.getButton("gui.button.activePage.blueGuide");
        inventory.setItem(24, blueGuide);
        ItemStack blue2 = GuiYaml.INSTANCE.getButton("gui.button.activePage.blue2");
        ReplaceUtil.replaceDisplayName(blue2, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.blue.playerList2.size())));
        ReplaceUtil.replaceLore(blue2, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.blue.playerList2.size())));
        name=new ArrayList<>();
        for (Player p:RaceManager.INSTANCE.blue.playerList2){
            name.add(p.getName());
        }
        ReplaceUtil.formatLore(blue2, "{(member)}",name);
        inventory.setItem(33, blue2);

        ItemStack purple1 = GuiYaml.INSTANCE.getButton("gui.button.activePage.purple1");
        ReplaceUtil.replaceDisplayName(purple1, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.purple.playerList1.size())));
        ReplaceUtil.replaceLore(purple1, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.purple.playerList1.size())));
        name=new ArrayList<>();
        for (Player p:RaceManager.INSTANCE.purple.playerList1){
            name.add(p.getName());
        }
        ReplaceUtil.formatLore(purple1, "{(member)}",name);
        inventory.setItem(17, purple1);
        ItemStack purpleGuide = GuiYaml.INSTANCE.getButton("gui.button.activePage.purpleGuide");
        inventory.setItem(26, purpleGuide);
        ItemStack purple2 = GuiYaml.INSTANCE.getButton("gui.button.activePage.purple2");
        ReplaceUtil.replaceDisplayName(purple2, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.purple.playerList2.size())));
        ReplaceUtil.replaceLore(purple2, Collections.singletonMap("{amount}", String.valueOf(RaceManager.INSTANCE.purple.playerList2.size())));
        name=new ArrayList<>();
        for (Player p:RaceManager.INSTANCE.purple.playerList2){
            name.add(p.getName());
        }
        ReplaceUtil.formatLore(purple2, "{(member)}",name);
        inventory.setItem(35, purple2);

        ItemStack introduction = GuiYaml.INSTANCE.getButton("gui.button.activePage.introduction");
        name=new ArrayList<>();
        for (Map.Entry m:DataYaml.INSTANCE.rank()){
            if (name.size()==10){
                break;
            }
            name.add(m.getKey()+" "+m.getValue());
        }
        ReplaceUtil.formatLore(introduction, "{(rank)}",name);
        inventory.setItem(49, introduction);

        player.openInventory(this.inventory);
    }
    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
