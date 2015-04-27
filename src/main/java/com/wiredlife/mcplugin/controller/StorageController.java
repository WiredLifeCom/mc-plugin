package com.wiredlife.mcplugin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;

import com.wiredlife.jsonformatjava.model.Inventory;

public class StorageController {

	Map<String, Material> materialMappings;

	public StorageController() {
		this.materialMappings = new HashMap<String, Material>();
		this.materialMappings.put("Dirt", Material.DIRT);
		this.materialMappings.put("Stone", Material.STONE);
		this.materialMappings.put("DiamondPickaxe", Material.DIAMOND_PICKAXE);
		this.materialMappings.put("WoodenAxe", Material.WOOD_AXE);
	}

	public List<File> getLocalFiles(String playerName) {
		File[] files = new File(String.format("../data/%s", playerName)).listFiles();
		return Arrays.asList(files);
	}

	public List<String> getLocalFilesContents(String playerName) throws IOException {
		List<File> files = getLocalFiles(playerName);

		List<String> contents = new ArrayList<String>();
		for (File file : files) {
			if (file.isFile()) {
				String fileContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
				contents.add(fileContent);
			}
		}
		return contents;
	}

	public void deleteLocalFiles(String playerName) {
		File[] files = new File(String.format("../data/%s", playerName)).listFiles();

		for (File file : files) {
			file.delete();
		}
	}

	public Map<String, Integer> getResources(Inventory inventory) {
		Map<String, Integer> resources = new HashMap<String, Integer>();
		for (String resource : inventory.getResources()) {
			if (resources.containsKey(resource)) {
				resources.put(resource, resources.get(resource) + 1);
			} else {
				resources.put(resource, 1);
			}
		}
		return resources;
	}

	public Map<String, Integer> getItems(Inventory inventory) {
		Map<String, Integer> items = new HashMap<String, Integer>();
		for (String item : inventory.getItems()) {
			if (items.containsKey(item)) {
				items.put(item, items.get(item) + 1);
			} else {
				items.put(item, 1);
			}
		}
		return items;
	}

	public Map<String, Material> getMaterialMappings() {
		return this.materialMappings;
	}

}