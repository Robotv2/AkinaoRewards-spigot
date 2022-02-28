package fr.robotv2.akinaorewards.utilities.ui;

import fr.robotv2.akinaorewards.utilities.api.ItemAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemConstant {

    public enum VitreType {
        VITRE_NOIR,
        VITRE_BLANCHE,
        VITRE_ROUGE,
        VITRE_VERT,
        VITRE_GRAY,
        VITRE_PINK,
        VITRE_YELLOW
    }

    private static final Map<VitreType, ItemStack> vitres = new HashMap<>();

    static {
        ItemStack VITRE_NOIR = new ItemAPI.ItemBuilder()
                .setType(Material.STAINED_GLASS_PANE)
                .setName("&8")
                .setDurability(15)
                .build();
        vitres.put(VitreType.VITRE_NOIR, VITRE_NOIR);

        ItemStack VITRE_BLANCHE = new ItemAPI.ItemBuilder()
                .setType(Material.STAINED_GLASS_PANE)
                .setName("&8")
                .setDurability(0)
                .build();
        vitres.put(VitreType.VITRE_BLANCHE, VITRE_BLANCHE);

        ItemStack VITRE_ROUGE = new ItemAPI.ItemBuilder()
                .setType(Material.STAINED_GLASS_PANE)
                .setName("&8")
                .setDurability(14)
                .build();
        vitres.put(VitreType.VITRE_ROUGE, VITRE_ROUGE);

        ItemStack VITRE_VERT = new ItemAPI.ItemBuilder()
                .setType(Material.STAINED_GLASS_PANE)
                .setName("&8")
                .setDurability(13)
                .build();
        vitres.put(VitreType.VITRE_VERT, VITRE_VERT);

        ItemStack VITRE_GRAY = new ItemAPI.ItemBuilder()
                .setType(Material.STAINED_GLASS_PANE)
                .setName("&8")
                .setDurability(7)
                .build();
        vitres.put(VitreType.VITRE_GRAY, VITRE_GRAY);

        ItemStack VITRE_PINK = new ItemAPI.ItemBuilder()
                .setType(Material.STAINED_GLASS_PANE)
                .setName("&8")
                .setDurability(6)
                .build();
        vitres.put(VitreType.VITRE_PINK, VITRE_PINK);

        ItemStack VITRE_YELLOW = new ItemAPI.ItemBuilder()
                .setType(Material.STAINED_GLASS_PANE)
                .setName("&8")
                .setDurability(4)
                .build();
        vitres.put(VitreType.VITRE_YELLOW, VITRE_YELLOW);
    }

    public static ItemStack getVitre(VitreType type) {
        return vitres.get(type);
    }

    public static ItemStack getDailyReward(Material material) {
        return new ItemAPI.ItemBuilder().setType(material)
                .setName("&8» &7Récompenses quotidiennes")
                .addFlags(ItemFlag.HIDE_ATTRIBUTES)
                .build();
    }

    public static ItemStack getDailyQuest(Material material) {
        return new ItemAPI.ItemBuilder().setType(material)
                .setName("&8» &7Missions quotidiennes")
                .addFlags(ItemFlag.HIDE_ATTRIBUTES)
                .build();
    }
}
