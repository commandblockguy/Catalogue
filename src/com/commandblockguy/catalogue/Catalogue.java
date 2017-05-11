package com.commandblockguy.catalogue;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.commandblockguy.catalogue.commands.CatalogueCommand;
import com.commandblockguy.catalogue.commands.RegisterShopCommand;
import com.commandblockguy.catalogue.listeners.ShopCreated;
import com.commandblockguy.catalogue.listeners.ShopDestroyed;
import com.commandblockguy.catalogue.listeners.Transaction;


public class Catalogue extends JavaPlugin {
	private static Catalogue plugin;
    private String username; 
    private String password; 
    private String url;
    private String database;
   

    public Catalogue() {
    	plugin = this;
    }
    
    public FileConfiguration config = this.getConfig();
    public FileConfiguration localization;
    public static Connection connection;
    
    @Override
    public void onEnable() {
    	createConfig();
    	username = config.getString("SQL_username");
    	password = config.getString("SQL_password");
    	url = config.getString("SQL_URL");
    	database = config.getString("SQL_database");
    	this.getCommand("catalogue").setExecutor(new CatalogueCommand());
    	this.getCommand("registershop").setExecutor(new RegisterShopCommand());
    	this.registerEvent(new ShopCreated());
    	this.registerEvent(new ShopDestroyed());
    	this.registerEvent(new Transaction());
    	try { 
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("jdbc driver unavailable!");
            return;
        }
        try {
            connection = DriverManager.getConnection(url + database, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String createTable = "CREATE TABLE IF NOT EXISTS shops(ID integer NOT NULL AUTO_INCREMENT, PosX integer, PosY integer, PosZ integer, BuyPrice decimal(12,2), SellPrice decimal(12,2), ItemType varchar(32), Amount integer, PlayerName varchar(36), TownName varchar(32) DEFAULT 'None', TimeStamp timestamp DEFAULT NOW(), PRIMARY KEY (ID));";
        try {
            PreparedStatement table = connection.prepareStatement(createTable);
            table.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	@Override
    public void onDisable() {
        try {
                if(connection!=null && !connection.isClosed()){
                        connection.close();
                }
        }catch(Exception e){
                        e.printStackTrace();
                }

        }
    
    public static Catalogue getPlugin() {
    	return plugin;
    }
    
    private void createConfig() {
    	File localizationFile = new File(getDataFolder(), "local.yml");
    	localization = YamlConfiguration.loadConfiguration(localizationFile);
    	localization.addDefault("commands.register.error.generic", "Error occured! Where did I/you go wrong???");
    	localization.addDefault("commands.register.error.nonplayer", "Please specify coords while running command from console");
    	localization.addDefault("commands.register.error.invalid", "Target block at (coords) is not a valid shop!");
    	localization.addDefault("commands.register.error.registered", "Target block at (coords) is already registered!");
    	localization.addDefault("commands.register.success", "Shop at (coords) was successfully registered!");
    	localization.addDefault("commands.register.error.nonsign", "Target block at (coords) is not a sign!");
    	localization.addDefault("icon.error.invalidclickaction", "Error: Icon with no run action was clicked!");
    	localization.addDefault("help.desusage", "Use \"des\" after a sort to invert the sort order");
    	localization.addDefault("help.underscores", "Use \"_\" instead of spaces in item names");
    	localization.addDefault("help.compasses", "If you click a shop, compasses in your inventory will point towards it");
    	localization.addDefault("commands.catalogue.windowtitle", "Catalogue");
    	localization.options().copyDefaults(true);
    	try {
			localization.save(localizationFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		config.addDefault("registerExistingShopsOnTransaction", true);
		config.addDefault("registerShopTargetDistance", 32);
		config.addDefault("SQL_username", "minecraft");
		config.addDefault("SQL_password", "minecraft");
		config.addDefault("SQL_URL", "jdbc:mysql://localhost:3306/");
		config.addDefault("SQL_database", "minecraft");
		
		config.options().copyDefaults(true);
		saveConfig();
	}
    
    public void registerEvent(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
     }
}
