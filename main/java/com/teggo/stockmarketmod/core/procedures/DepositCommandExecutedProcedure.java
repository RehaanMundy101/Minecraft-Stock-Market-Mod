package com.teggo.stockmarketmod.core.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import com.teggo.stockmarketmod.core.network.Variables;
import com.teggo.stockmarketmod.core.init.ItemInit;

import java.util.concurrent.atomic.AtomicReference;
import java.util.HashMap;

public class DepositCommandExecutedProcedure {
	public static void execute(Entity entity, HashMap cmdparams) {
		if (entity == null || cmdparams == null)
			return;
		double loop = 0;
		double item = 0;
		loop = 0;
		item = 0;
		for (int index0 = 0; index0 < (int) (36); index0++) {
			if (ItemInit.MINECOIN.get() == (new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack((int) loop, entity)).getItem()) {
				item = item + ((new Object() {
					public ItemStack getItemStack(int sltid, Entity entity) {
						AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).copy());
						});
						return _retval.get();
					}
				}.getItemStack((int) loop, entity))).getCount();
			}
			loop = loop + 1;
		}
		if (entity instanceof Player _playerHasItem
				? _playerHasItem.getInventory().contains(new ItemStack(ItemInit.MINECOIN.get()))
				: false) {
			if (item >= new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "")) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(ItemInit.MINECOIN.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(cmdparams.containsKey("0") ? cmdparams.get("0").toString() : ""), _player.inventoryMenu.getCraftSlots());
				}
				{
					double _setval = (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new Variables.PlayerVariables())).balance + 1000 * new Object() {
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
					_player.displayClientMessage(
							Component.literal(
									"The amount of Minecoins that you tried to deposit exceeds the amount that you currently have in your inventory"),
							(false));
			}
		} else {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("You don't have any Minecoins to deposit"), (false));
		}
	}
}