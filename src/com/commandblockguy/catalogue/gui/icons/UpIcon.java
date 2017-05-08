package com.commandblockguy.catalogue.gui.icons;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import com.commandblockguy.catalogue.gui.Icon;
import com.commandblockguy.catalogue.gui.SortedDisplay;

public class UpIcon extends Icon {
	
	BannerMeta bannerMeta;
	SortedDisplay display;
	SortedDisplay updatedDisplay;
	
	public UpIcon(int slot, SortedDisplay display) {
		item = new ItemStack(Material.BANNER);
		meta = item.getItemMeta();
		bannerMeta = (BannerMeta) meta;
		bannerMeta.setBaseColor(DyeColor.WHITE);
		bannerMeta.addPattern(new Pattern(DyeColor.BLACK, PatternType.TRIANGLE_BOTTOM));
		bannerMeta.setDisplayName("Up");
		this.xPos = slotX(slot);
		this.yPos = slotY(slot);
		item.setItemMeta(bannerMeta);
		if (display.getScroll() <= 64 && display.getScroll() > 0)
			item.setAmount(display.getScroll() + 1);
		this.display = display;
	}

	@Override
	public void clickAction() {
		if (display.getScroll() > 0) {
			updatedDisplay = new SortedDisplay(display.getScroll() - 1, display.getTitle());
		}
		else {
			updatedDisplay = new SortedDisplay(display.getScroll(), display.getTitle());
		}
		updatedDisplay.addIcons(display.getIcons());
		updatedDisplay.display(display.getPlayer());
	}
}
