package axc.DeathSwap.event;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnEventHandle implements Listener {

    @EventHandler
    public boolean onPlayerRespawn(PlayerRespawnEvent event)
    {
        event.getPlayer().setGameMode(GameMode.SPECTATOR);
        return true;
    }

}
