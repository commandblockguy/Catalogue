package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.commandblockguy.catalogue.Catalogue;
import com.commandblockguy.catalogue.gui.ChestDisplay;
import com.commandblockguy.catalogue.gui.Icon;
import com.commandblockguy.catalogue.tasks.CloseInventory;

public class HelpIcon extends Icon {
	public HelpIcon(int slot, ChestDisplay display) {
		item = new ItemStack(Material.PAPER);
		meta = item.getItemMeta();
		meta.setDisplayName("Help");
		this.xPos = slotX(slot);
		this.yPos = slotY(slot);
		item.setItemMeta(meta);
		this.display = display;
	}
	
	ChestDisplay display;

	@Override
	public void clickAction() {
		Player player = display.getPlayer();
		player.sendMessage("Usage: ");
		player.sendMessage(Catalogue.getPlugin().getCommand("catalogue").getUsage());
		player.sendMessage(Catalogue.getPlugin().localization.getString("help.underscores"));
		player.sendMessage(Catalogue.getPlugin().localization.getString("help.compasses"));
		player.sendMessage(Catalogue.getPlugin().localization.getString("help.afilter"));
		player.sendMessage("Filter Types: Amount, Buy Price, Item, Player, Sell Price, Town");
		player.sendMessage("Sort Types: Amount, Buy Price, Item Name, Per Item Buy Price, Per Item Sell Price, Sell Price, Town Name");
		player.sendMessage(Catalogue.getPlugin().localization.getString("help.desusage"));
		Bukkit.getScheduler().scheduleSyncDelayedTask(Catalogue.getPlugin(), new CloseInventory(display));
	}
}
