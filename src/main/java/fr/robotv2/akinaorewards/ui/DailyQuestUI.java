package fr.robotv2.akinaorewards.ui;

import fr.robotv2.akinaorewards.AkinaoRewards;
import fr.robotv2.akinaorewards.quest.Quest;
import fr.robotv2.akinaorewards.quest.QuestManager;
import fr.robotv2.akinaorewards.utilities.api.ItemAPI;
import fr.robotv2.akinaorewards.utilities.ui.GUI;
import fr.robotv2.akinaorewards.utilities.ui.ItemConstant;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DailyQuestUI implements GUI {

    @Override
    public String getName(Player player, Object... objects) {
        return "&8» &7Missions quotidiennes";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public void contents(Player player, Inventory inv, Object... objects) {
        for(int i = 0; i <= 8; i++)
            inv.setItem(i, ItemConstant.getVitre(ItemConstant.VitreType.VITRE_VERT));
        for(int i = 9; i <= 17; i++)
            inv.setItem(i, ItemConstant.getVitre(ItemConstant.VitreType.VITRE_ROUGE));
        for(int i = 18; i <= 53; i++)
            inv.setItem(i, ItemConstant.getVitre(ItemConstant.VitreType.VITRE_GRAY));

        inv.setItem(2, ItemConstant.getDailyReward(Material.BOOK));
        inv.setItem(4, ItemConstant.getDailyQuest(Material.BOOK_AND_QUILL));
        inv.setItem(6, new ItemStack(Material.BOOK));

        List<ItemStack> stacks = this.getItems(QuestManager.getLastData(player));

        inv.setItem(29, stacks.get(0));
        inv.setItem(33, stacks.get(1));
        inv.setItem(38, stacks.get(2));
        inv.setItem(42, stacks.get(3));
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot, ClickType clickType) {
        switch (slot) {
            case 2:
                AkinaoRewards.getInstance().getGuiManager()
                        .open(player, DailyRewardUi.class);
                break;
        }
    }

    @Override
    public void onClose(Player player, InventoryCloseEvent event) {
    }

    @Override
    public boolean cancelClose() {
        return false;
    }

    public List<ItemStack> getItems(String data) {

        List<ItemStack> items = new ArrayList<>();
        String[] quests = data.split(";");

        for(String questData : quests) {

            String[] args = questData.split("!");
            int id = Integer.parseInt(args[0]);
            int value = Integer.parseInt(args[1]);
            boolean success = Boolean.parseBoolean(args[2]);

            ItemAPI.ItemBuilder builder = new ItemAPI.ItemBuilder();

            Quest quest = QuestManager.getQuestById(id);
            Material type = success ? Material.GOLD_BLOCK : Material.COAL_BLOCK;

            List<String> lore = new ArrayList<>();

            lore.add("&8&m&l----------");
            lore.add("&fRécompenses:");
            lore.addAll(quest.getRewards());
            lore.add("&8&m&l----------");
            lore.add("&fProgrès:");
            lore.add("&f" + value + "/" + quest.getTargetValue());
            lore.add("&8&m&l----------");

            if (success)
                lore.add("&aMission terminée.");
            else
                lore.add("&cMission en cours...");

            builder.setName(quest.getTitle()).setType(type).setLore(lore).addFlags(ItemFlag.HIDE_ATTRIBUTES);
            items.add(builder.build());
        }
        return items;
    }
}
