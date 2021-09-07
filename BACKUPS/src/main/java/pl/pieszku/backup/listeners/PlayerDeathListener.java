package pl.pieszku.backup.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.pieszku.backup.basic.Backup;

public class PlayerDeathListener implements Listener {


    @EventHandler(priority = EventPriority.NORMAL)
    public void onDeath(PlayerDeathEvent event){
        Player playerDeath = event.getEntity();
        Player playerKiller = event.getEntity().getKiller();

        event.setDeathMessage(null);

        new Backup(playerDeath, (playerKiller == null) ? "Samobójstwo" : playerKiller.getName());
    }
}
