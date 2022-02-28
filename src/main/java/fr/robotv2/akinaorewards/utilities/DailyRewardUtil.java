package fr.robotv2.akinaorewards.utilities;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.robotv2.akinaorewards.AkinaoRewards;
import fr.robotv2.akinaorewards.impl.RewardPlayer;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.time.ZoneId;

public class DailyRewardUtil {

    private final AkinaoRewards plugin;
    public DailyRewardUtil(AkinaoRewards instance) {
        this.plugin = instance;
    }

    public void claimReward(Player player, int cumulativeDay) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("claim-reward");
        out.writeInt(cumulativeDay);
        player.sendPluginMessage(plugin, AkinaoRewards.DAILY_CHANNEL, out.toByteArray());
    }

    public int generateDayOfToday() {
        return Instant.now().atZone(ZoneId.of("Europe/Paris")).toLocalDate().getDayOfYear();
    }

    public int getNextReward(RewardPlayer session) {
        return session.getCumulativeDay() + 1;
    }
}
