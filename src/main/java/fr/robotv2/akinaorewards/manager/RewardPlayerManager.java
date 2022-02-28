package fr.robotv2.akinaorewards.manager;

import fr.robotv2.akinaorewards.impl.RewardPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class RewardPlayerManager {

    private final Map<UUID, RewardPlayer> rewardPlayerMap = new HashMap<>();

    public RewardPlayer getRewardPlayer(UUID playerUUID) {
        Objects.requireNonNull(playerUUID, "PlayerUUID can't be null");
        return rewardPlayerMap.get(playerUUID);
    }

    public RewardPlayer getRewardPlayer(Player player) {
        Objects.requireNonNull(player, "Player can't be null");
        return this.getRewardPlayer(player.getUniqueId());
    }

    public boolean hasSession(Player player) {
        return hasSession(player.getUniqueId());
    }

    public boolean hasSession(UUID playerUUID) {
        return this.getRewardPlayer(playerUUID) != null;
    }

    public void registerPlayer(Player player, int cumulativeDay, int lastRewardDay) {
        if(hasSession(player)) {
            RewardPlayer rewardPlayer = this.getRewardPlayer(player);
            rewardPlayer.setLastRewardDay(lastRewardDay);
            rewardPlayer.setCumulativeDay(cumulativeDay);
        } else {
            RewardPlayer rewardPlayer = new RewardPlayer(player, lastRewardDay, cumulativeDay);
            this.registerPlayer(player, rewardPlayer);
        }
    }

    public void registerPlayer(Player player, RewardPlayer playerSession) {
        this.rewardPlayerMap.put(player.getUniqueId(), playerSession);
    }
}
