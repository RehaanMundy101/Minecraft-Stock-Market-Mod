package com.teggo.stockmarketmod.core.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import com.teggo.stockmarketmod.core.network.Variables;
import com.teggo.stockmarketmod.core.init.ItemInit;

import java.util.HashMap;

public class WithdrawCommandExecutedProcedure {
	public static void execute(Entity entity, HashMap cmdparams) {
		if (entity == null || cmdparams == null)
			return;
		if ((entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new Variables.PlayerVariables())).balance >= 1000 * new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "")) {
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(ItemInit.MINECOIN.get());
				_setstack.setCount((int) new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(cmdparams.containsKey("0") ? cmdparams.get("0").toString() : ""));
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			{
				double _setval = (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new Variables.PlayerVariables())).balance - 1000 * new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "");
				entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.balance = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(
						Component.literal(("Balance: " + (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new Variables.PlayerVariables())).balance)),
						(false));
		} else {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("You don't have the sufficient funds to withdraw that amount"), (false));
		}
	}
}