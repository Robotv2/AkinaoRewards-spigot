package fr.robotv2.akinaorewards.utilities.ui;

import fr.robotv2.akinaorewards.AkinaoRewards;
import fr.robotv2.akinaorewards.utilities.StringUtil;
import fr.robotv2.akinaorewards.utilities.api.TaskAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class GuiManager implements Listener {

    private final HashMap<Class<? extends GUI>, GUI> menus = new HashMap<>();
    private final HashMap<UUID, GUI> players = new HashMap<>();

    public GuiManager(AkinaoRewards instance) {
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        ItemStack item = e.getCurrentItem();

        if(item == null) return;
        if(!players.containsKey(playerUUID)) return;

        e.setCancelled(true);
        GUI menu = players.get(playerUUID);
        menu.onClick(player, e.getInventory(), item, e.getRawSlot(), e.getClick());
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        UUID playerUUID = e.getPlayer().getUniqueId();

        if(players.containsKey(playerUUID)) {

            GUI menu = players.get(playerUUID);
            menu.onClose(player, e);

            TaskAPI.runTaskLater(() -> {
                if(player.getOpenInventory().getType() != InventoryType.CHEST)
                    players.remove(playerUUID);
            }, 1L);
        }
    }

    public void addMenu(GUI gui){
        menus.put(gui.getClass(), gui);
    }

    public void open(Player player, Class<? extends GUI> gClass){
        if(!menus.containsKey(gClass)) {
            AkinaoRewards.getInstance().getLogger().warning(StringUtil.colorize("&cVous devez enregistrer l'inventaire avant de pouvoir l'ouvrir"));
            return;
        }

        GUI menu = menus.get(gClass);
        Inventory inv = Bukkit.createInventory(null, menu.getSize(), StringUtil.colorize(menu.getName(player)));
        menu.contents(player, inv);
        players.put(player.getUniqueId(), menu);

        TaskAPI.runTaskLater(() -> {
            player.openInventory(inv);
        }, 2L);
    }
}
