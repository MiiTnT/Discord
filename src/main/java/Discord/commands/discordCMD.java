package Discord.commands;

import Discord.discord;
import com.google.common.collect.Lists;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.*;

public class discordCMD implements CommandExecutor{
    private final discord main;

    public discordCMD(discord main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String link = this.main.getConfig().getString("DiscordLink");

        if (args.length == 0) {
            sender.sendMessage("§c[Discord] §e" + link);
        } else {
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("§7=-=-=-=-=-=§c[Discord]§7=-=-=-=-=-=");
                sender.sendMessage("§e/Discord §7Obtenir le lien du discord.");
                if (sender.hasPermission("discord.admin") || sender.isOp()) {
                    sender.sendMessage("§7=-=-=-=-=-=§c[ADMIN]§7=-=-=-=-=-=");
                    sender.sendMessage("§e/Discord link <link> §7Configurer le lien vers le discord.");
                    sender.sendMessage("§e/Discord reload §7Reload la configuration du plugin.");
                }
                sender.sendMessage("§7=-=-=-=-=-=§c[Discord]§7=-=-=-=-=-=");
            }
            if (args[0].equalsIgnoreCase("link")) {
                if (sender.hasPermission("discord.link") || sender.isOp()) {
                    try {
                        if (args[1].length() >= 1) {
                            if (args[1].contains("https://") || args[1].contains("http://")) {
                                sender.sendMessage("§c[Discord] §eNouveau lien: §a" + args[1]);
                                this.main.getConfig().set("DiscordLink", args[1]);
                                this.main.saveConfig();
                                this.main.reloadConfig();
                            } else {
                                sender.sendMessage("§c[Discord] §cErreur: §7Veuilez indiquer un lien !");
                            }

                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        sender.sendMessage("§c[Discord] §cErreur: §7Veuilez indiquer un lien !");
                    }
                } else {
                    sender.sendMessage("§c[Discord] §cErreur: §7Vous n'avez pas la permission !");
                }
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("discord.reload") || sender.isOp()) {
                    this.main.reloadConfig();
                    sender.sendMessage("§c[Discord] §eConfiguration rechargée avec succès !");
                } else {
                    sender.sendMessage("§c[Discord] §cErreur: §7Vous n'avez pas la permission !");
                }
            }
        }
        return false;
    }

}
