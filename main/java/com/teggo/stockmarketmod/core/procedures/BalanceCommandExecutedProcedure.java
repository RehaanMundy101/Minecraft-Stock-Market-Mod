package com.teggo.stockmarketmod.core.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import com.teggo.stockmarketmod.core.network.Variables;

public class BalanceCommandExecutedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					Component.literal(("Balance: " + (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new Variables.PlayerVariables())).balance)),
					(false));
	}
}
