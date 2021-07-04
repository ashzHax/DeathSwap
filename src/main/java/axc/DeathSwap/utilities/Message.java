package axc.DeathSwap.utilities;

import axc.DeathSwap.DeathSwap;
import org.bukkit.ChatColor;

import java.util.Map;

public class Message {

    public enum LogType {
        PERMISSION_NO_PERM,
        DEATHSWAP_RUNNING,
        DEATHSWAP_START,
        DEATHSWAP_STOP,
        DEATHSWAP_TEN_SEC_WARN,
        DEATHSWAP_ON_SWAP,
        DEATHSWAP_END,
        COMMAND_INVALID_ARGUMENT_TYPE,
        COMMAND_REQUIRE_ARGUMENTS,
    };

    public enum StringType {
        NUMBER,
    };

    private static String convertColorString(String target)
    {
        target = target.replaceAll("\\{AQUA\\}", ChatColor.AQUA+"");
        target = target.replaceAll("\\{BLACK\\}", ChatColor.BLACK+"");
        target = target.replaceAll("\\{BLUE\\}", ChatColor.BLUE+"");
        target = target.replaceAll("\\{BOLD\\}", ChatColor.BOLD+"");
        target = target.replaceAll("\\{COLOR_CHAR\\}", ChatColor.COLOR_CHAR+"");
        target = target.replaceAll("\\{DARK_AQUA\\}", ChatColor.DARK_AQUA+"");
        target = target.replaceAll("\\{DARK_BLUE\\}", ChatColor.DARK_BLUE+"");
        target = target.replaceAll("\\{DARK_GRAY\\}", ChatColor.DARK_GRAY+"");
        target = target.replaceAll("\\{DARK_GREEN\\}", ChatColor.DARK_GREEN+"");
        target = target.replaceAll("\\{DARK_PURPLE\\}", ChatColor.DARK_PURPLE+"");
        target = target.replaceAll("\\{DARK_RED\\}", ChatColor.DARK_RED+"");
        target = target.replaceAll("\\{GOLD\\}", ChatColor.GOLD+"");
        target = target.replaceAll("\\{GRAY\\}", ChatColor.GRAY+"");
        target = target.replaceAll("\\{GREEN\\}", ChatColor.GREEN+"");
        target = target.replaceAll("\\{ITALIC\\}", ChatColor.ITALIC+"");
        target = target.replaceAll("\\{LIGHT_PURPLE\\}", ChatColor.LIGHT_PURPLE+"");
        target = target.replaceAll("\\{MAGIC\\}", ChatColor.MAGIC+"");
        target = target.replaceAll("\\{RED\\}", ChatColor.RED+"");
        target = target.replaceAll("\\{RESET\\}", ChatColor.RESET+"");
        target = target.replaceAll("\\{STRIKETHROUGH\\}", ChatColor.STRIKETHROUGH+"");
        target = target.replaceAll("\\{UNDERLINE\\}", ChatColor.UNDERLINE+"");
        target = target.replaceAll("\\{WHITE\\}", ChatColor.WHITE+"");
        target = target.replaceAll("\\{YELLOW\\}", ChatColor.YELLOW+"");

        return target;
    }

    private static String convertGeneralString(StringType strT, String target, Map<StringType, String> data)
    {
        switch(strT) {
            case NUMBER: {
                if(data == null) {
                    target = target.replaceAll("\\{NUMBER\\}", "NULL");
                } else {
                    target = target.replaceAll("\\{NUMBER\\}", data.get(strT));
                }
                break;
            }
        }

        return target;
    }

    public static String getConfigMessage(DeathSwap plugin, LogType logT, Map<StringType, String> data)
    {
        String returnString;

        switch(logT) {
            case PERMISSION_NO_PERM: {
                returnString = plugin.getConfig().getString("messages.permission_no_perm");
                returnString = convertColorString(returnString);
                break;
            }
            case DEATHSWAP_RUNNING: {
                returnString = plugin.getConfig().getString("messages.deathswap_running");
                returnString = convertColorString(returnString);
                break;
            }
            case DEATHSWAP_START: {
                returnString = plugin.getConfig().getString("messages.deathswap_start");
                returnString = convertGeneralString(StringType.NUMBER, returnString, data);
                returnString = convertColorString(returnString);
                break;
            }
            case DEATHSWAP_STOP: {
                returnString = plugin.getConfig().getString("messages.deathswap_stop");
                returnString = convertColorString(returnString);
                break;
            }
            case DEATHSWAP_TEN_SEC_WARN: {
                returnString = plugin.getConfig().getString("messages.deathswap_ten_sec_warn");
                returnString = convertGeneralString(StringType.NUMBER, returnString, data);
                returnString = convertColorString(returnString);
                break;
            }
            case DEATHSWAP_ON_SWAP: {
                returnString = plugin.getConfig().getString("messages.deathswap_on_swap");
                returnString = convertColorString(returnString);
                break;
            }
            case DEATHSWAP_END: {
                returnString = plugin.getConfig().getString("messages.deathswap_end");
                returnString = convertColorString(returnString);
                break;
            }
            case COMMAND_INVALID_ARGUMENT_TYPE: {
                returnString = plugin.getConfig().getString("messages.invalid_argument_type");
                returnString = convertColorString(returnString);
                break;
            }
            case COMMAND_REQUIRE_ARGUMENTS: {
                returnString = plugin.getConfig().getString("messages.require_arguments");
                returnString = convertColorString(returnString);
                break;
            }
            default: {
                returnString = "NULL";
            }
        }

        return returnString;
    }

}
