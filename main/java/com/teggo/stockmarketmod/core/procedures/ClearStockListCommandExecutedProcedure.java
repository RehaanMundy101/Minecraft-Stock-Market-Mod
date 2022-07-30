package com.teggo.stockmarketmod.core.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import com.teggo.stockmarketmod.core.network.Variables;

public class ClearStockListCommandExecutedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		(entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new Variables.PlayerVariables())).StockList.clear();
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(Component.literal("Your Stock List has been cleared"), (false));
	}
}
