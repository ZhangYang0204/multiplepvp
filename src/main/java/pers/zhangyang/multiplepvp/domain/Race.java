package pers.zhangyang.multiplepvp.domain;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pers.zhangyang.multiplepvp.MultiplePvp;
import pers.zhangyang.multiplepvp.manager.RaceManager;
import pers.zhangyang.multiplepvp.util.MessageUtil;
import pers.zhangyang.multiplepvp.util.ReplaceUtil;
import pers.zhangyang.multiplepvp.yaml.DataYaml;
import pers.zhangyang.multiplepvp.yaml.MessageYaml;
import pers.zhangyang.multiplepvp.yaml.SettingYaml;

import java.util.*;

public class Race {
    //本场比赛的玩家，掉线的自动移除
    protected final List<Player> playerList1=new ArrayList<>();
    protected final List<Player> playerList2=new ArrayList<>();
    //复活点
    protected Location location1;
    protected Location location2;
    //大厅集合坐标
    protected Location lobbyLocation1;
    protected Location lobbyLocation2;
    //玩家来之前的坐标
    protected final Map<Player,Location> playerLocationMap1=new HashMap<>();
    protected final Map<Player,Location> playerLocationMap2=new HashMap<>();
    protected String name;
    protected int time;
    protected final Map<Player,Integer> killAmount=new HashMap<>();
    protected int killAmount1;
    protected int killAmount2;
    protected Player killKing;
    protected long startTime;
    protected int respawnTime;
    protected final  Map<Player,Long> outTime =new HashMap<>();
    protected boolean racing=false;
    protected boolean readying=true;
    public Race(Location location1, Location location2, Location lobbyLocation1,Location lobbyLocation2, String name, int time, int respawnTime) {
        this.respawnTime = respawnTime;
        this.location1 = location1;
        this.location2 = location2;
        this.lobbyLocation1 = lobbyLocation1;
        this.lobbyLocation2 = lobbyLocation2;
        this.name=name;
        this.time=time;
    }

    public void clear(){
        List<Player> l=new ArrayList<>(playerList1);
        for (Player p:l){
            remove(p);
        }
        l=new ArrayList<>(playerList2);
        for (Player p:l){
            remove(p);
        }
        playerList1.clear();
        playerList2.clear();
        playerLocationMap1.clear();
        playerLocationMap2.clear();
        killAmount.clear();
        killAmount1=0;
        killAmount2=0;
        outTime.clear();
        racing=false;
        readying=true;
    }

    //删除玩家在该队伍的信息 并传送虎丘  用于掉线 和离开队伍
    public void remove(Player player){

        if (playerList1.contains(player)) {
            playerList1.remove(player);

            boolean f=player.teleport(playerLocationMap1.get(player));
            playerLocationMap1.remove(player);
            player.setGameMode(GameMode.SURVIVAL);
        }
        if (playerList2.contains(player)) {
            playerList2.remove(player);
            boolean f=player.teleport(playerLocationMap2.get(player));

            playerLocationMap2.remove(player);
            player.setGameMode(GameMode.SURVIVAL);
        }
        killAmount.remove(player);
        outTime.remove(player);
    }

    //加入1 并前往等待地点
    public void join1(Player player){
        removeFromAllRace(player);
        playerLocationMap1.put(player,player.getLocation());
        playerList1.add(player);
        player.teleport(lobbyLocation1);
        killAmount.put(player,0);
        player.setAllowFlight(false);
    }

    //加入2 并前往等待地点
    public void join2(Player player){
        removeFromAllRace(player);
        playerLocationMap2.put(player,player.getLocation());
        playerList2.add(player);
        player.teleport(lobbyLocation2);
        killAmount.put(player,0);
        player.setAllowFlight(false);
    }


    private static void removeFromAllRace(Player player){
        RaceManager.INSTANCE.purple.remove(player);
        RaceManager.INSTANCE.yellow.remove(player);
        RaceManager.INSTANCE.orange.remove(player);
        RaceManager.INSTANCE.green.remove(player);
        RaceManager.INSTANCE.blue.remove(player);
    }

    public void start(){
        racing=true;
        readying=false;
        startTime=System.currentTimeMillis();
        if (playerList1.isEmpty()||playerList2.isEmpty()){
            for (Player p:playerList1){
                MessageUtil.sendMessageTo(p, MessageYaml.INSTANCE.getStringList("message.chat.notEnoughPeople"));
            }
            for (Player p:playerList2){
                MessageUtil.sendMessageTo(p, MessageYaml.INSTANCE.getStringList("message.chat.notEnoughPeople"));
            }

            clear();
            return;
        }
        new BukkitRunnable() {
            private int timeRest=Race.this.time;
            @Override
            public void run() {
                String title=MessageYaml.INSTANCE.getString("message.actionBar.rest");
                if (title!=null) {
                    String m = ReplaceUtil.replace(title, Collections.singletonMap("{s}", timeRest + ""));
                    m = ChatColor.translateAlternateColorCodes('&', m);
                    BaseComponent text = new TextComponent(m);
                    for (Player p : playerList1) {

                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
                    }
                    for (Player p : playerList2) {

                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
                    }
                }
                timeRest--;
                if (time* 1000L +startTime< System.currentTimeMillis()){
                    this.cancel();
                    stop();
                    return;
                }
                /*List<Player> list=new ArrayList<>(outTime.keySet());
                for (Player p: list){
                    if (outTime.get(p)+ respawnTime *1000L< System.currentTimeMillis()){
                        respawn(p);
                        outTime.remove(p);
                    }
                }*/

            }
        }.runTaskTimer(MultiplePvp.instance,1,20);

        for (int i=0;i<playerList1.size();i++){
            playerList1.get(i).teleport(location1);
            MessageUtil.sendMessageTo(playerList1.get(i),MessageYaml.INSTANCE.getStringList("message.chat.raceStart"));
        }for (int i=0;i<playerList2.size();i++){
            playerList2.get(i).teleport(location2);
            MessageUtil.sendMessageTo(playerList2.get(i),MessageYaml.INSTANCE.getStringList("message.chat.raceStart"));
        }
    }

    public void respawn(Player player){
        List<String> stringList=MessageYaml.INSTANCE.getStringList("message.chat.respawn");
        Map<String,String> rep=new HashMap<>();
        rep.put("{player}",player.getName());
        if (stringList!=null){
            ReplaceUtil.replace(stringList,rep);
        }
        for(Player p:playerList1){
            MessageUtil.sendMessageTo(p, stringList);
        }for(Player p:playerList2){
            MessageUtil.sendMessageTo(p, stringList);
        }

        if (playerList1.contains(player)){
            player.teleport(location1);
            player.setGameMode(GameMode.SURVIVAL);
            player.setFoodLevel(10);
        }
        if (playerList2.contains(player)){
            player.teleport(location2);
            player.setGameMode(GameMode.SURVIVAL);
            player.setFoodLevel(10);
        }
    }

    public void out(Player player){
        outTime.put(player,System.currentTimeMillis());
        if (playerList1.contains(player)){
            Location l=player.getLocation();
            player.spigot().respawn();
            player.teleport(location1);/*
            player.setGameMode(GameMode.SPECTATOR);*/
        }
        if (playerList2.contains(player)){
            Location l=player.getLocation();
            player.spigot().respawn();
            player.teleport(location2);/*
            player.setGameMode(GameMode.SPECTATOR);*/
        }
    }

    public boolean isRacing() {
        return racing;
    }

    public boolean isReadying() {
        return readying;
    }

    public void stop(){
        if (!racing){
            return;
        }

        racing=false;
        //广播
        List<String> stringList=MessageYaml.INSTANCE.getStringList("message.chat.raceStop");
        Map<String,String> rep=new HashMap<>();
        rep.put("{name}",name);
        if (killAmount1>killAmount2){
            rep.put("{team}","1");
        }else {
            rep.put("{team}","2");
        }


        int max=0;
        for (Player p:killAmount.keySet()){
            if (killAmount.get(p)>max){
                killKing=p;
                max=killAmount.get(p);
            }
        }
        if (killKing!=null) {
            rep.put("{king}", killKing.getName());
            DataYaml.INSTANCE.add(killKing);
        }else {
            rep.put("{king}", "");
        }


        if (stringList!=null) {
            ReplaceUtil.replace(stringList, rep);
        }
        MessageUtil.sendMessageTo(Bukkit.getOnlinePlayers(), stringList);


        List<String> winCommandList=SettingYaml.INSTANCE.getStringList("setting.reward.win.command");
        List<String> loseCommandList=SettingYaml.INSTANCE.getStringList("setting.reward.lose.command");
        List<String> mvpCommandList=SettingYaml.INSTANCE.getStringList("setting.reward.mvp.command");
        int min=SettingYaml.INSTANCE.getIntegerDefault("setting.reward.mvp.min");

        if (killKing!=null&&killAmount.get(killKing)>=min){
            for (String s:mvpCommandList){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),s.replace("{player}",killKing.getName()));
            }
        }

        if (killAmount1>killAmount2){
            if (winCommandList!=null){
                for (Player p:playerList1){
                    if (killAmount.get(p)<SettingYaml.INSTANCE.getIntegerDefault("setting.reward.win.min")){
                        continue;
                    }
                    for (String s:winCommandList){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),s.replace("{player}",p.getName()));
                    }
                }
            }
            if (loseCommandList!=null) {
                for (Player p : playerList2) {
                    if (killAmount.get(p)<SettingYaml.INSTANCE.getIntegerDefault("setting.reward.lose.min")){
                        continue;
                    }
                    for (String s:loseCommandList){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),s.replace("{player}",p.getName()));
                    }
                }
            }
        }else {
            if (loseCommandList!=null){
                for (Player p:playerList1){
                    if (killAmount.get(p)<SettingYaml.INSTANCE.getIntegerDefault("setting.reward.lose.min")){
                        continue;
                    }
                    for (String s:loseCommandList){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),s.replace("{player}",p.getName()));
                    }
                }
            }
            if (winCommandList!=null) {
                for (Player p : playerList2) {
                    if (killAmount.get(p)<SettingYaml.INSTANCE.getIntegerDefault("setting.reward.win.min")){
                        continue;
                    }
                    for (String s:winCommandList){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),s.replace("{player}",p.getName()));
                    }
                }
            }
        }

        clear();

    }

    public boolean isFull1(){
        if (playerList1.size()>=SettingYaml.INSTANCE.getIntegerDefault("setting.maxPeople")){
            return true;
        }
        return false;
    }
    public boolean isFull2(){
        if (playerList2.size()>=SettingYaml.INSTANCE.getIntegerDefault("setting.maxPeople")){
            return true;
        }
        return false;
    }
    public boolean canJoin1(){
        return playerList1.size() - playerList2.size() <= SettingYaml.INSTANCE.getIntegerDefault("setting.fairGap");
    }public boolean canJoin2(){
        return playerList2.size() - playerList1.size() <= SettingYaml.INSTANCE.getIntegerDefault("setting.fairGap");
    }

    public boolean contains(Player player){
        return playerList1.contains(player)||playerList2.contains(player);
    }

    public void kill(Player player){
        killAmount.put(player,killAmount.get(player)+1);
        if (playerList1.contains(player)){
            killAmount1++;
        }
        if (playerList2.contains(player)){
            killAmount2++;
        }
    }

    public Location getLocation1() {
        return location1;
    }

    public Location getLocation2() {
        return location2;
    }

    public Location getLobbyLocation1() {
        return lobbyLocation1;
    }
    public Location getLobbyLocation2() {
        return lobbyLocation2;
    }
    public boolean isFriend(Player player,Player player2){
        if (playerList1.contains(player)&&playerList1.contains(player2)){
            return true;
        }
        if (playerList2.contains(player)&&playerList2.contains(player2)){
            return true;
        }
        return false;
    }

    public List<Player> getPlayerList1() {
        return playerList1;
    }

    public List<Player> getPlayerList2() {
        return playerList2;
    }
}
