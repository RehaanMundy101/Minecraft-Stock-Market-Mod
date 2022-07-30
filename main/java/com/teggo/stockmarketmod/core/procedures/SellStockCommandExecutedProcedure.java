package com.teggo.stockmarketmod.core.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import com.teggo.stockmarketmod.core.network.Variables;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class SellStockCommandExecutedProcedure {
	public static void execute(Entity entity, HashMap cmdparams) throws IOException {
		if (entity == null || cmdparams == null)
			return;
		String stockName = "";
		int quantity = 0;
		int x = 0;
		boolean stockbool = false;
		boolean numberbool = false;
		stockName = cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "";
		Stock stock = YahooFinance.get(stockName);
		BigDecimal price = stock.getQuote(true).getPrice();
		quantity = new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "");
		x = 0;
		while (x != (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new Variables.PlayerVariables())).StockList.size()) {
			if (((entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new Variables.PlayerVariables())).StockList.get(x)).equals(stockName)) {
				if (Integer.valueOf((entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new Variables.PlayerVariables())).StockList.get((x + 1))) >= quantity) {
					if (Integer.valueOf((entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new Variables.PlayerVariables())).StockList.get((x + 1))) == quantity) {
						(entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new Variables.PlayerVariables())).StockList.remove(x);
						(entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new Variables.PlayerVariables())).StockList.remove(x); 
						{
							double _setval = (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new Variables.PlayerVariables())).balance + price.doubleValue() * quantity;
							entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.balance = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					} else {
						(entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new Variables.PlayerVariables())).StockList.set((x + 1), String.valueOf((Integer.valueOf((entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new Variables.PlayerVariables())).StockList.get(x + 1)) - quantity)));
						{
							double _setval = (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new Variables.PlayerVariables())).balance + price.doubleValue() * quantity;
							entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.balance = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
					stockbool = true;
					numberbool = true;
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(
								Component.literal(("Balance: " + (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new Variables.PlayerVariables())).balance)),
								(false));
					break;
				} else {
					numberbool = false;
					stockbool = true;
				}
			} else {
				stockbool = false;
			}
			x = x + 2;
		}
		if (false == stockbool) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("You don't own that stock"), (false));
		}
		if (false == numberbool) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("You don't own that amount of stock"), (false));
		}
	}
}
