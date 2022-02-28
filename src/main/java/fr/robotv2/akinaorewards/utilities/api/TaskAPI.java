package fr.robotv2.akinaorewards.utilities.api;

import fr.robotv2.akinaorewards.AkinaoRewards;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class TaskAPI {
    private static final Plugin plugin = AkinaoRewards.getInstance();

    public static BukkitTask runTaskLater(Runnable task, Long timer) {
        return Bukkit.getScheduler().runTaskLater(plugin, task, timer);
    }

    public static BukkitTask runTaskLaterAsync(Runnable task, Long timer) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, timer);
    }

    public static BukkitTask runTask(Runnable task) {
        return Bukkit.getScheduler().runTask(plugin, task);
    }

    public static BukkitTask runAsync(Runnable task) {
        return Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
    }

    public static BukkitTask runTaskTimer(Runnable task, Long delay, Long timer) {
        return Bukkit.getScheduler().runTaskTimer(plugin, task, delay, timer);
    }

    public static BukkitTask runTaskTimerAsync(Runnable task, Long delay, Long timer) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, delay, timer);
    }
}
