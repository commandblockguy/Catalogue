package com.commandblockguy.catalogue.commands;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import com.commandblockguy.catalogue.Catalogue;
import com.commandblockguy.catalogue.Shop;

public class RegisterShopCommand implements CommandExecutor {

	FileConfiguration local = Catalogue.getPlugin().localization;
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(args.length == 3) {
			//coordinates
			try {
				int xPos = Integer.parseInt(args[0]);
				int yPos = Integer.parseInt(args[1]);
				int zPos = Integer.parseInt(args[2]);
				Block block = Bukkit.getWorlds().get(0).getBlockAt(xPos, yPos, zPos);
				register(block, sender);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				sender.sendMessage(local.getString("commands.register.error.generic"));
				return true;
			}
		} else {
			//we were given something besides coordinates
			if(args.length != 0) return false;
			if(!(sender instanceof Player)) {
				System.out.println(local.getString("commands.register.error.nonplayer"));
				return false;
			}
			Player player = (Player) sender;
			Block target = player.getTargetBlock((Set<Material>) null, Catalogue.getPlugin().config.getInt("registerShopTargetDistance"));
			register(target, sender);
			return true;
		}
	}

	private void register(Block block, CommandSender sender) {
		Shop shop;
		Location pos = block.getLocation();
		String coords = pos.getBlockX() + ", " + pos.getBlockY() + ", "  + pos.getBlockZ();
		if(block.getType().equals(Material.WALL_SIGN)) {
			try {
				shop = new Shop((Sign) block.getState());
			} catch (Exception e) {
				sender.sendMessage(local.getString("commands.register.error.invalid").replace("(coords)", coords));
				return;
			}
			if(shop.isRegistered()) {
				sender.sendMessage(local.getString("commands.register.error.registered").replace("(coords)", coords));
			} else {
					shop.register();
					sender.sendMessage(local.getString("commands.register.success").replace("(coords)", coords));
			}
		} else {
			sender.sendMessage(local.getString("commands.register.error.nonsign").replace("(coords)", coords));
		}
	}
}
