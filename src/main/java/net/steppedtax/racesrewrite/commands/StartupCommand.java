package net.steppedtax.racesrewrite.commands;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class StartupCommand implements CommandExecutor {
    private final RacesRewrite plugin;
    public StartupCommand(RacesRewrite plugin) {
        this.plugin = plugin;
    }
    // love doing random stuff when I'm bored
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String formattedArgs = Arrays.toString(args) // Expensive!
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
        if (args.length >= 1) {
            this.plugin.getConfig().set("startup-message", formattedArgs);
            sender.sendMessage("Startup message changed to: " + formattedArgs);
        }
        else {
            final String DEFAULT_STARTUP_MESSAGE = "The plugin has been loaded!";
            this.plugin.getConfig().set("startup-message", DEFAULT_STARTUP_MESSAGE);
            sender.sendMessage("Startup message reverted to default.");
        }
        this.plugin.saveConfig();
        return true;
    }
}
