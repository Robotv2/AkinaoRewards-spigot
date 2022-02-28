package fr.robotv2.akinaorewards.utilities.api;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.robotv2.akinaorewards.utilities.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ItemAPI {

    public static HashMap<String, ItemStack> heads = new HashMap<>();
    public static HashMap<String, ItemStack> getCachedHeads() {
        return heads;
    }

    public static ItemStack getHead(UUID playerUUID) {
        if(heads.containsKey(playerUUID.toString()))
            return heads.get(playerUUID.toString());

        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        meta.setOwner(Bukkit.getOfflinePlayer(playerUUID).getName());
        head.setItemMeta(meta);

        heads.put(playerUUID.toString(), head);
        return head;
    }

    public static ItemStack getHead(Player player) {
        return getHead(player.getUniqueId());
    }

    public static ItemStack createSkull(String texture) {
        if(heads.containsKey(texture))
            return heads.get(texture);

        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (texture.isEmpty()) return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));

        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (IllegalArgumentException|NoSuchFieldException|SecurityException | IllegalAccessException error) {
            error.printStackTrace();
        }
        head.setItemMeta(headMeta);
        heads.put(texture, head);
        return head;
    }

    public static ItemStack createSkull(String texture, String name, List<String> lore) {
        ItemStack skull = createSkull(texture);
        ItemMeta meta = skull.getItemMeta();
        meta.setDisplayName(StringUtil.colorize(name));
        meta.setLore(lore.stream().map(StringUtil::colorize).collect(Collectors.toList()));
        skull.setItemMeta(meta);
        return skull;
    }

    public static ItemBuilder toBuilder(ItemStack item) {
        ItemBuilder builder = new ItemBuilder();
        builder.setMeta(item.getItemMeta());
        builder.setType(item.getType());
        builder.setAmount(item.getAmount());
        return builder;
    }

    public static class ItemBuilder {
        private Material type = Material.AIR;
        private int amount = 1;
        private Short durability = 0;
        private ItemMeta meta = new ItemStack(Material.GRASS).getItemMeta();

        public ItemBuilder setType(Material type) {
            this.type = type;
            return this;
        }

        public ItemBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public ItemBuilder setName(String name) {
            this.meta.setDisplayName(StringUtil.colorize(name));
            return this;
        }

        public ItemBuilder setLore(String... lore) {
            this.meta.setLore(Arrays.stream(lore).map(StringUtil::colorize).collect(Collectors.toList()));
            return this;
        }

        public ItemBuilder setLore(List<String> lore) {
            this.meta.setLore(lore.stream().map(StringUtil::colorize).collect(Collectors.toList()));
            return this;
        }

        public ItemBuilder addLore(String line) {
            List<String> lore = this.meta.getLore();
            if(lore == null)
                lore = new ArrayList<>();
            lore.add(StringUtil.colorize(line));
            this.meta.setLore(lore);
            return this;
        }

        public ItemBuilder addEnchant(Enchantment enchant, int level, boolean ignoreLevelRestriction) {
            this.meta.addEnchant(enchant, level, ignoreLevelRestriction);
            return this;
        }

        public ItemBuilder addFlags(ItemFlag... flags) {
            this.meta.addItemFlags(flags);
            return this;
        }

        public ItemBuilder setMeta(ItemMeta meta) {
            this.meta = meta;
            return this;
        }

        public ItemBuilder setDurability(int durability) {
            this.durability = (short) durability;
            return this;
        }

        public ItemStack build() {
            ItemStack item = new ItemStack(type, amount);
            item.setItemMeta(meta);
            item.setDurability(durability);
            return item;
        }
    }
}
