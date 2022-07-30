package com.teggo.stockmarketmod.common.command.impl;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;

import com.teggo.stockmarketmod.core.procedures.CheckPriceCommandExecutedProcedure;

import java.util.HashMap;
import java.util.Arrays;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

import java.io.IOException;

@Mod.EventBusSubscriber
public class CheckPriceCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) throws IOException {
		event.getDispatcher().register(Commands.literal("checkprice")
				.then(Commands.argument("arguments", StringArgumentType.greedyString()).executes(context -> {
					try {
						return execute(context);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return 0;
				}))
				.executes(context -> {
					try {
						return execute(context);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return 0;
				}));
	}

	private static int execute(CommandContext<CommandSourceStack> ctx) throws IOException {
		ServerLevel world = ctx.getSource().getLevel();
		double x = ctx.getSource().getPosition().x();
		double y = ctx.getSource().getPosition().y();
		double z = ctx.getSource().getPosition().z();
		Entity entity = ctx.getSource().getEntity();
		if (entity == null)
			entity = FakePlayerFactory.getMinecraft(world);
		HashMap<String, String> cmdparams = new HashMap<>();
		int[] index = {-1};
		Arrays.stream(ctx.getInput().split("\\s+")).forEach(param -> {
			if (index[0] >= 0)
				cmdparams.put(Integer.toString(index[0]), param);
			index[0]++;
		});

		CheckPriceCommandExecutedProcedure.execute(entity, cmdparams);
		return 0;
	}
}
