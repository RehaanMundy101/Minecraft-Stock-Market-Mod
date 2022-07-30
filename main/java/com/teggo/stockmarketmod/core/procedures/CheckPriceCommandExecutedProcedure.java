package com.teggo.stockmarketmod.core.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.IOException;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.util.HashMap;

public class CheckPriceCommandExecutedProcedure {
	public Stock getStock(String stockName) throws IOException {
		return YahooFinance.get(stockName);
	}
	public static void execute(Entity entity, HashMap cmdparams) throws IOException {
		if (entity == null || cmdparams == null)
			return;
		String stockName = "";
		stockName = cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "";
		CheckPriceCommandExecutedProcedure yahooStockAPI = new CheckPriceCommandExecutedProcedure();
		String stockData = String.valueOf(yahooStockAPI.getStock(stockName));
		if (entity instanceof Player _player && !_player.level.isClientSide())
			if (stockData == "null") {
				_player.displayClientMessage(Component.literal("That is not a valid stock symbol"), (false));
			} else {
			_player.displayClientMessage(Component.literal(stockData), (false));
			}
		}
}
	