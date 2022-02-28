package fr.robotv2.akinaorewards.utilities;

import fr.robotv2.akinaorewards.AkinaoRewards;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceholderClip extends PlaceholderExpansion {

    private final AkinaoRewards plugin;
    public PlaceholderClip(AkinaoRewards instance) {
        this.plugin = instance;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "rewards";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().get(0);
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, @NotNull String placeholder) {
        if(offlinePlayer.getPlayer() == null)
            return null;
        return placeholder; // Placeholder is unknown by the Expansion
    }
}
