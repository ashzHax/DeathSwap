package axc.DeathSwap.runnable;

import axc.DeathSwap.DeathSwap;
import axc.DeathSwap.utilities.Message;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwapTimer {

    long Tick2Sec = 20L; /* 20 Tick = 1 Second */

    private DeathSwap plugin;
    private int _INTERVAL_CLEAR_;
    private int timeLeft;
    private int pid;

    public SwapTimer(DeathSwap pl, int time) {
        this.plugin = pl;
        this._INTERVAL_CLEAR_ = time;
        this.timeLeft = time;

        Map<Message.StringType, String> data = new HashMap<Message.StringType, String>();
        data.put(Message.StringType.NUMBER, ""+time);
        plugin.getServer().broadcastMessage(Message.getConfigMessage(plugin, Message.LogType.DEATHSWAP_START, data));

        this.init_Runnable();
    }

    Runnable _DeathSwap = new Runnable(){
        @Override
        public void run() {

            if(timeLeft == 0) {

                int cnt = plugin.getServer().getOnlinePlayers().size();
                int i;
                int vidx=0;
                Player[] pl_arr = new Player[cnt];
                Location[] pl_loc = new Location[cnt];

                plugin.getServer().broadcastMessage(Message.getConfigMessage(plugin, Message.LogType.DEATHSWAP_ON_SWAP, null));

                for(Player idx : plugin.getServer().getOnlinePlayers()) {
                    if(idx.getGameMode() == GameMode.SURVIVAL) {
                        pl_arr[vidx]=idx;
                        pl_loc[vidx]=idx.getLocation();
                        vidx++;
                    }
                }

                if(vidx == 1) {
                    plugin.getServer().getScheduler().cancelTask(pid);
                    plugin.getConfig().set("deathswap.in_progress",0 );
                    plugin.saveConfig();
                    plugin.getServer().broadcastMessage(Message.getConfigMessage(plugin, Message.LogType.DEATHSWAP_END, null));
                    return;
                }


                for(i=0; i<vidx; i++) {
                    if(i == (vidx-1)) {
                        pl_arr[i].teleport(pl_loc[0]);
                        plugin.getServer().broadcastMessage(ChatColor.GRAY+"["+ChatColor.YELLOW+">"+ChatColor.GRAY+"] "+ChatColor.YELLOW+pl_arr[i].getName()+ChatColor.GRAY+" -> "+ ChatColor.YELLOW+pl_arr[0].getName());
                    } else {
                        pl_arr[i].teleport(pl_loc[i+1]);
                        plugin.getServer().broadcastMessage(ChatColor.GRAY+"["+ChatColor.YELLOW+">"+ChatColor.GRAY+"] "+ChatColor.YELLOW+pl_arr[i].getName()+ChatColor.GRAY+" -> "+ ChatColor.YELLOW+pl_arr[i+1].getName());
                    }
                }

                timeLeft = _INTERVAL_CLEAR_;
            }
            else if(timeLeft <= 9) {
                Map<Message.StringType, String> data = new HashMap<Message.StringType, String>();
                data.put(Message.StringType.NUMBER, ""+timeLeft);
                plugin.getServer().broadcastMessage(Message.getConfigMessage(plugin, Message.LogType.DEATHSWAP_TEN_SEC_WARN, data));
            }

            timeLeft--;
        }

    };

    public void init_Runnable() {
        pid=this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, _DeathSwap, 0L /* Run Cycle Count (0=Inf) */, Tick2Sec /* Run Runnable per X Tick */);
    }

}
