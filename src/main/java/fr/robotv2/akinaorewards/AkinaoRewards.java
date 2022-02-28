package fr.robotv2.akinaorewards;

import co.aikar.commands.PaperCommandManager;
import fr.robotv2.akinaorewards.command.OpenGuiCommand;
import fr.robotv2.akinaorewards.listeners.PluginMessage;
import fr.robotv2.akinaorewards.manager.RewardPlayerManager;
import fr.robotv2.akinaorewards.ui.DailyQuestUI;
import fr.robotv2.akinaorewards.ui.DailyRewardUi;
import fr.robotv2.akinaorewards.utilities.DailyRewardUtil;
import fr.robotv2.akinaorewards.utilities.PlaceholderClip;
import fr.robotv2.akinaorewards.utilities.ui.GuiManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.plugin.java.JavaPlugin;

public final class AkinaoRewards extends JavaPlugin {

    private static AkinaoRewards instance;
    public static final String DAILY_CHANNEL = "akinoreward:daily";

    private RewardPlayerManager rewardPlayerManager;
    private GuiManager guiManager;
    private DailyRewardUtil dailyRewardUtil;

    private PlaceholderExpansion placeholderClip;

    @Override
    public void onEnable() {
        instance = this;

        this.loadManagers();
        this.loadUtilities();
        this.loadChannels();
        this.loadGuis();

        this.registerPlaceholderClip();

        PaperCommandManager paperCommandManager = new PaperCommandManager(this);
        paperCommandManager.registerCommand(new OpenGuiCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
        this.unregisterPlaceholderClip();
    }

    //<-- GETTERS ->>

    public static AkinaoRewards getInstance() {
        return instance;
    }

    public RewardPlayerManager getRewardPlayerManager() {
        return rewardPlayerManager;
    }

    public GuiManager getGuiManager() {
        return guiManager;
    }

    public DailyRewardUtil getDailyRewardUtil() {
        return dailyRewardUtil;
    }

    //<-- LOADERS -->

    private void loadManagers() {
        this.rewardPlayerManager = new RewardPlayerManager();
        this.guiManager = new GuiManager(this);
    }

    private void loadUtilities() {
        this.dailyRewardUtil = new DailyRewardUtil(this);
    }

    private void loadChannels() {
        getServer().getMessenger().registerIncomingPluginChannel(this, DAILY_CHANNEL, new PluginMessage(this));
        getServer().getMessenger().registerOutgoingPluginChannel(this, DAILY_CHANNEL);
    }

    private void loadGuis() {
        this.getGuiManager().addMenu(new DailyRewardUi(this));
        this.getGuiManager().addMenu(new DailyQuestUI());
    }

    //<-- CLIP PLACEHOLDER API -->

    private boolean isPlaceholderApiEnabled() {
        return getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    private void registerPlaceholderClip() {
        if(isPlaceholderApiEnabled()) {
            this.placeholderClip = new PlaceholderClip(this);
            placeholderClip.register();
        }
    }

    private void unregisterPlaceholderClip() {
        if(this.placeholderClip != null && isPlaceholderApiEnabled()) {
            placeholderClip.unregister();
        }
    }
}
