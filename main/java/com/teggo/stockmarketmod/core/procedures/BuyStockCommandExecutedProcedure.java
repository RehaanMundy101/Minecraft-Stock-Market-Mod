package com.teggo.stockmarketmod.core.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.LevelAccessor;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import com.teggo.stockmarketmod.core.network.Variables;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class BuyStockCommandExecutedProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap cmdparams) throws IOException {
		if (entity == null || cmdparams == null)
			return;
		double quantity = 0;
		String stockName = "";	
		stockName = cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "";
		Stock stock = YahooFinance.get(stockName);
		BigDecimal price = stock.getQuote(true).getPrice();
		quantity = new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "");
		if (price != null) {
			if ((entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new Variables.PlayerVariables())).balance - (price.doubleValue() * quantity) >= 0) {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(Component.literal(("You have purchased " + (quantity + "" + (" stocks of " + stockName)))), (false));
				(entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new Variables.PlayerVariables())).StockList.add(stockName);
				(entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new Variables.PlayerVariables())).StockList.add(String.valueOf(quantity));
				{
					double _setval = (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new Variables.PlayerVariables())).balance - (price.doubleValue() * quantity);
					entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.balance = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(Component.literal(("Balance: " + (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new Variables.PlayerVariables())).balance)),
							(false));
			} else {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(Component.literal("You don't have the sufficient funds to purchase the desired quantity of stocks"),
							(false));
			}
		} else {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("That is not a valid stock symbol"), (false));
		}
	}
}
