package axc.DeathSwap.command;

import axc.DeathSwap.DeathSwap;
import axc.DeathSwap.runnable.SwapTimer;
import axc.DeathSwap.utilities.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class start_deathswap implements CommandExecutor {
    private DeathSwap plugin;
    public start_deathswap(DeathSwap pl)
    {
        this.plugin = pl;
    }

    public boolean onCommand(CommandSender commandExecutor, Command cmd, String triggerCommand, String[] cmdArgs)
    {
        Player commandPlayer = (Player) commandExecutor;

        if(!commandExecutor.isOp()) {
            commandPlayer.sendMessage(Message.getConfigMessage(plugin, Message.LogType.PERMISSION_NO_PERM, null));
            return false;
        }

        if(plugin.getConfig().getInt("deathswap.in_progress") != 0) {
            commandPlayer.sendMessage(Message.getConfigMessage(plugin, Message.LogType.DEATHSWAP_RUNNING, null));
            return false;
        }

        int mval;
        if(cmdArgs.length == 1) {
            try {
                mval = Integer.parseInt(cmdArgs[0]);
            } catch(NumberFormatException e) {
                commandPlayer.sendMessage(Message.getConfigMessage(plugin, Message.LogType.COMMAND_INVALID_ARGUMENT_TYPE, null));
                return false;
            }

            plugin.getConfig().set("deathswap.in_progress", 1);
            plugin.saveConfig();
            SwapTimer _swap = new SwapTimer(plugin, mval);
        } else {
            commandPlayer.sendMessage(Message.getConfigMessage(plugin, Message.LogType.COMMAND_REQUIRE_ARGUMENTS, null));
        }


        return true;
    }

}
