package com.teggo.stockmarketmod.core.procedures;

import org.checkerframework.checker.units.qual.s;

import com.ibm.icu.util.Calendar;

import net.minecraft.world.entity.player.Player;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import java.util.HashMap;
import com.ibm.icu.util.Calendar;
import java.io.IOException;

public class ViewDataCommandExecutedProcedure {
	public static void execute(Entity entity, HashMap cmdparams) throws IOException {
		if (entity == null || cmdparams == null)
			return;
		int length_of_period = 0;
		String stock = "";
		String period = "";
		String interval = "";
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		stock = cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "";
		period = cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "";
		interval = cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "";
		length_of_period = new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(cmdparams.containsKey("3") ? cmdparams.get("3").toString() : "");
		if (("Year").equals(period)) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				from.add(Calendar.YEAR, (0 - length_of_period));
		} else {
			if (("Month").equals(period)) {
				from.add(Calendar.MONTH, (0 - length_of_period));;
			} else {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(Component.literal("That isn't a valid period of time "), (false));
			}
		}
		Stock stockdata = YahooFinance.get(stock, Interval.MONTHLY);
		if (("Monthly").equals(interval)) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal(String.valueOf(YahooFinance.get(stock, Interval.MONTHLY))), (false));
		} else {
			if (("Weekly").equals(interval)) {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(Component.literal(String.valueOf(YahooFinance.get(stock, Interval.WEEKLY))), (false));
			} else {
				if (("Daily").equals(interval)) {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(Component.literal(String.valueOf(YahooFinance.get(stock, Interval.DAILY))), (false));
				} else {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(Component.literal("That isn't a valid interval"), (false));
				}
			}
		}
	}
}