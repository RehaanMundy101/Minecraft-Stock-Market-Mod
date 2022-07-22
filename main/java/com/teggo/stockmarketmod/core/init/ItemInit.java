package com.teggo.stockmarketmod.core.init;

import com.teggo.stockmarketmod.StockMarketMod;
import com.google.common.base.Supplier;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			StockMarketMod.MOD_ID);

	public static final RegistryObject<Item> MINECOIN = register("minecoin",
			() -> new Item(new Item.Properties().tab(StockMarketMod.Stock_Market_Tab)));

	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
		return ITEMS.register(name, item);
	}
}
