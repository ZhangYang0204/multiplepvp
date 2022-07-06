package pers.zhangyang.multiplepvp.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InventoryUtil {

    @NotNull
    public static ItemStack getItemInMainHand(Player player) {
        int big= MinecraftVersionUtil.getBigVersion();
        int middle= MinecraftVersionUtil.getMiddleVersion();
        if (big==1&&middle<9){
            return player.getInventory().getItemInHand();
        }else {
            return player.getInventory().getItemInMainHand();
        }
    }
    public static void addItem(Player player, ItemStack itemStack, int amount){
        while (amount>=itemStack.getMaxStackSize()){
            ItemStack item=itemStack.clone();
            item.setAmount(item.getMaxStackSize());
            player.getInventory().addItem(item);
            amount-=item.getMaxStackSize();
        }
        ItemStack item=itemStack.clone();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    public static void removeItem(Player player,ItemStack itemStack,int amount){

        for (int i=0;i<36;i++){
            ItemStack itemStack2=player.getInventory().getItem(i);
            if (itemStack2==null) continue;
            itemStack.setAmount(itemStack2.getAmount());
            if (itemStack.equals(itemStack2)){

                if (amount>0&&amount>=itemStack2.getAmount()){
                    amount-=itemStack2.getAmount();
                    player.getInventory().clear(i);
                }else if (amount>0&&amount<itemStack2.getAmount()){
                    itemStack2.setAmount(itemStack2.getAmount()-amount);
                    amount=0;
                }
            }
        }
    }


    //由计算出玩家背包能已拥有的物品数量
    public static int computeItemHave(ItemStack itemStack,Player player){

        int number=0;
        Inventory inventory=player.getInventory();
        for (int i=0;i<36;i++){
            ItemStack itemStack2=inventory.getItem(i);
            if (itemStack2==null) continue;
            itemStack.setAmount(itemStack2.getAmount());
            if (itemStack2.equals(itemStack)){
                number+=itemStack2.getAmount();
            }
        }
        return number;
    }

    //由所有物品数量和每页能容纳的物品数量，计算出最后一页的下标
    public static int computeMaxPage(int allItemAmount,int pageContent){

        int maxPage=0;
        if (allItemAmount!=0)
            maxPage=(allItemAmount%pageContent==0?allItemAmount/pageContent-1:allItemAmount/pageContent);

        return maxPage;
    }

    //算出玩家背包内容纳某物品的数量 itemStack的数量无视
    public static int checkSpace(Player player,ItemStack itemStack){

        int maxStack=itemStack.getMaxStackSize();
        Inventory inventory=player.getInventory();
        int space=0;

        for (int i=0;i<36;i++){
            ItemStack stack=inventory.getItem(i);

            if (stack==null){
                space+=maxStack;continue;
            }
            itemStack.setAmount(stack.getAmount());
            if (itemStack.equals(stack)){
                space+=(maxStack>=stack.getAmount()?(maxStack-stack.getAmount()):0);
            }
        }
        return space;

    }

}