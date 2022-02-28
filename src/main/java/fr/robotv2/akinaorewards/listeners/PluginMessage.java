package fr.robotv2.akinaorewards.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.robotv2.akinaorewards.AkinaoRewards;
import fr.robotv2.akinaorewards.quest.QuestManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessage implements PluginMessageListener {

    private final AkinaoRewards plugin;
    public PluginMessage(AkinaoRewards akinaoRewards) {
        this.plugin = akinaoRewards;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals(AkinaoRewards.DAILY_CHANNEL)) return;

        final ByteArrayDataInput in = ByteStreams.newDataInput(message);
        final String sub = in.readUTF();

        switch (sub.toLowerCase()) {
            case "register-player":
                int lastRewardDay = in.readInt();
                int cumulativeDay = in.readInt();
                plugin.getRewardPlayerManager().registerPlayer(player, cumulativeDay, lastRewardDay);
                break;
            case "quest-data":
                String data = in.readUTF();
                QuestManager.openGui(player, data);
                break;
        }
    }
}
