package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import com.commandblockguy.catalogue.gui.Icon;
import com.commandblockguy.catalogue.gui.SortedDisplay;

public class DownIcon extends Icon {
	
	BannerMeta bannerMeta;
	SortedDisplay display;
	SortedDisplay updatedDisplay;
	
	public DownIcon(int slot, SortedDisplay display) {
		item = new ItemStack(Material.BANNER);
		meta = item.getItemMeta();
		bannerMeta = (BannerMeta) meta;
		bannerMeta.setBaseColor(DyeColor.WHITE);
		bannerMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.TRIANGLE_TOP));
		bannerMeta.setDisplayName("Down");
		this.xPos = slotX(slot);
		this.yPos = slotY(slot);
		item.setItemMeta(bannerMeta);
		this.display = display;
	}
	
	@Override
	public void clickAction() {
		updatedDisplay = new SortedDisplay(display.getScroll() + 1, display.getTitle());
		updatedDisplay.addIcons(display.getIcons());
		updatedDisplay.display(display.getPlayer());
	}
}
