package net.enderturret.randomitems.proxy;

import net.enderturret.randomitems.RandomItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	// Register the item renderer
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("randomitems"+ ":" + id, "inventory"));
	}
}