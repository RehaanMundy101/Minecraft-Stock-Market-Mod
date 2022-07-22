package com.teggo.stockmarketmod.core.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import com.teggo.stockmarketmod.core.network.Variables;

public class StockListCommandExecutedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		int x = (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new Variables.PlayerVariables())).StockList.size();
		while (x != 0) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(
						Component.literal(
								((entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new Variables.PlayerVariables())).StockList.get(x) + " " + (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new Variables.PlayerVariables())).StockList.get((x + 1)))),
						(false));
			x = x - 2;
		}
	}
}
