package fr.robotv2.akinaorewards.ui;

import fr.robotv2.akinaorewards.AkinaoRewards;
import fr.robotv2.akinaorewards.impl.RewardPlayer;
import fr.robotv2.akinaorewards.utilities.api.ItemAPI;
import fr.robotv2.akinaorewards.utilities.ui.GUI;
import fr.robotv2.akinaorewards.utilities.ui.ItemConstant;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DailyRewardUi implements GUI {

    private final AkinaoRewards plugin;

    public DailyRewardUi(AkinaoRewards instance) {
        this.plugin = instance;
    }

    @Override
    public String getName(Player player, Object... objects) {
        return "&8» &7Récompenses quotidiennes";
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

        inv.setItem(2, ItemConstant.getDailyReward(Material.BOOK_AND_QUILL));
        inv.setItem(4, ItemConstant.getDailyQuest(Material.BOOK));
        inv.setItem(6, new ItemStack(Material.BOOK));

        RewardPlayer session = plugin.getRewardPlayerManager().getRewardPlayer(player);
        int nextReward = plugin.getDailyRewardUtil().getNextReward(session);

        for(int i = 18; i < 49; i++) {
            int day = i - 17;
            if(nextReward > day) {
                inv.setItem(i, getRewardAlreadyTakenItem(day));
            } else if(nextReward == day && this.canTakeReward(session, day)) {
                inv.setItem(i, getRewardOfToday(day));
            } else {
                inv.setItem(i, getRewardLater(day));
            }
        }
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot, ClickType clickType) {
        if(current.getType() == Material.ENDER_CHEST) {
            RewardPlayer session = plugin.getRewardPlayerManager().getRewardPlayer(player);
            int nextReward = plugin.getDailyRewardUtil().getNextReward(session);
            if(this.canTakeReward(session, nextReward)) {
                plugin.getDailyRewardUtil().claimReward(player, nextReward);
                player.closeInventory();
            }
        } else switch (slot) {
            case 4:
                AkinaoRewards.getInstance().getGuiManager()
                        .open(player, DailyQuestUI.class);
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

    public boolean canTakeReward(RewardPlayer session, int day) {
        int lastRewardDay = session.getLastRewardDay();

        if(lastRewardDay == -1 && day == 1)
            return true;

        if(lastRewardDay == plugin.getDailyRewardUtil().generateDayOfToday())
            return false;

        return session.getCumulativeDay() + 1 == day;
    }

    public ItemStack getRewardAlreadyTakenItem(int day) {
        return new ItemAPI.ItemBuilder()
                .setType(Material.CHEST)
                .setName("&8» &7Récompense journalière &8- &e&m" + day)
                .setLore("", "&cVous avez déjà récupéré", "&ccette récompense.")
                .build();
    }

    public ItemStack getRewardOfToday(int day) {
        return new ItemAPI.ItemBuilder()
                .setType(Material.ENDER_CHEST)
                .setName("&8» &7Récompense journalière &8- &e&n" + day)
                .setLore("", "&3Cliquez-ici pour récupérer votre récompense !")
                .build();
    }

    public ItemStack getRewardLater(int day) {
        return new ItemAPI.ItemBuilder()
                .setType(Material.CHEST)
                .setName("&8» &7Récompense journalière &8- &e" + day)
                .setLore("", "&cVous ne pouvez pas encore", "&crécupérer cette récompense.")
                .build();
    }
}
