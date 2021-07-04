package axc.DeathSwap;

import axc.DeathSwap.command.start_deathswap;
import axc.DeathSwap.event.PlayerRespawnEventHandle;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathSwap extends JavaPlugin {
    @Override
    public void onEnable()
    {
        this.saveDefaultConfig();

        this.getCommand("start_deathswap").setExecutor(new start_deathswap(this));
        this.getServer().getPluginManager().registerEvents(new PlayerRespawnEventHandle(), this);
    }
    @Override
    public void onDisable()
    {

    }
}
