package pl.pieszku.backup.tasks;

import org.bukkit.Bukkit;
import pl.pieszku.backup.Main;

public class SaveTasks implements Runnable{
    @Override
    public void run() {
        Main.getMySQL().saveBackups();
    }
    public void start(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), this, 1L, 1200L);
    }
}
