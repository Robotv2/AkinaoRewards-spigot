package fr.robotv2.akinaorewards.quest;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.robotv2.akinaorewards.AkinaoRewards;
import fr.robotv2.akinaorewards.ui.DailyQuestUI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QuestManager {

    private static final Map<UUID, String> datas = new HashMap<>();
    public static void actualizeAndOpenGui(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("daily-quest-gui");
        player.sendPluginMessage(AkinaoRewards.getInstance(), AkinaoRewards.DAILY_CHANNEL, out.toByteArray());
    }

    public static void openGui(Player player, String data) {
        datas.put(player.getUniqueId(), data);
        AkinaoRewards.getInstance().getGuiManager().open(player, DailyQuestUI.class);
    }

    public static String getLastData(Player player) {
        return datas.get(player.getUniqueId());
    }

    public static @Nullable Quest getQuestById(int id) {
        for(Quest quest : Quest.values()) {
            if(quest.getId() == id)
                return quest;
        }
        return null;
    }
}
