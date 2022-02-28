package fr.robotv2.akinaorewards.impl;

import org.bukkit.entity.Player;

public class RewardPlayer {

    private final Player player;
    private int lastRewardDay;
    private int cumulativeDay;

    public RewardPlayer(Player player, int lastRewardDay, int cumulativeDay) {
        this.player = player;
        this.lastRewardDay = lastRewardDay;
        this.cumulativeDay = cumulativeDay;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCumulativeDay() {
        return cumulativeDay;
    }

    public int getLastRewardDay() {
        return lastRewardDay;
    }

    public void setCumulativeDay(int cumulativeDay) {
        this.cumulativeDay = cumulativeDay;
    }

    public void setLastRewardDay(int lastRewardDay) {
        this.lastRewardDay = lastRewardDay;
    }
}
