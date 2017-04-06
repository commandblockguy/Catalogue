package com.commandblockguy.catalogue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.commandblockguy.catalogue.commands.CatalogueCommand;
import com.commandblockguy.catalogue.listeners.ShopCreated;
import com.commandblockguy.catalogue.listeners.ShopDestroyed;
import com.commandblockguy.catalogue.listeners.Transaction;


public class Catalogue extends JavaPlugin {
	private static Catalogue plugin;
    private String username = "minecraft"; 
    private String password = "minecraft"; 
    private String url = "jdbc:mysql://localhost:3306/";
    private String database = "minecraft";

    public Catalogue() {
    	plugin = this;
    }
    
    public FileConfiguration config = this.getConfig();    
    public static Connection connection;
    
    @Override
    public void onEnable() {
    	createConfig();
    	this.getCommand("catalogue").setExecutor(new CatalogueCommand());
    	getServer().getPluginManager().registerEvents(new ShopCreated(), this);
    	getServer().getPluginManager().registerEvents(new ShopDestroyed(), this);
    	getServer().getPluginManager().registerEvents(new Transaction(), this);
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
        String createTable = "CREATE TABLE IF NOT EXISTS shops(ID integer NOT NULL AUTO_INCREMENT, PosX integer, PosY integer, PosZ integer, BuyPrice decimal(12,2), SellPrice decimal(12,2), ItemType varchar(32), PlayerName varchar(32), TownName varchar(32) DEFAULT 'None', TimeStamp timestamp DEFAULT NOW(), PRIMARY KEY (ID));";
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
		config.addDefault("registerExistingShopsOnTransaction", true);
		config.addDefault("SQL_username", "minecraft");
		config.addDefault("SQL_password", "minecraft");
		config.addDefault("SQL_URL", "jdbc:mysql://localhost:3306/");
		config.addDefault("SQL_database", "minecraft");
		
		config.options().copyDefaults(true);
		saveConfig();
	}
}
