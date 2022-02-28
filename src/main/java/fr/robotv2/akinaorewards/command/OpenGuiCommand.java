package fr.robotv2.akinaorewards.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import fr.robotv2.akinaorewards.quest.QuestManager;
import org.bukkit.entity.Player;

@CommandAlias("opengui")
public class OpenGuiCommand extends BaseCommand {

    @Default
    public void openGui(Player player) {
        QuestManager.actualizeAndOpenGui(player);
    }
}
