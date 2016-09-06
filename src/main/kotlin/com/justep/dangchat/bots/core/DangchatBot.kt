package com.justep.dangchat.bots.core

import im.actor.bots.framework.MagicBotFork
import im.actor.bots.framework.MagicBotMessage
import im.actor.bots.framework.MagicBotTextMessage
import im.actor.bots.framework.MagicForkScope
import im.actor.bots.BotMessages

import java.util.*

import com.justep.dangchat.bots.core.NotificationOverlord.DoNotification;
import com.justep.dangchat.bots.command.CommandHandler;

/**
 * 铛铛机器人
 * @author Lining
 * @date 2016/9/1
 *
 */
class DangchatBot(scope: MagicForkScope) : MagicBotFork(scope) {

	override fun onMessage(message: MagicBotMessage) {
		when (message) {
			is MagicBotTextMessage -> {
				try {
					val cmdHandler: CommandHandler = CommandHandler.createHandler(message.text);
					cmdHandler.handleCommand(this)
				} catch(e: Exception) {
					println(e.message)
				}
			}
		}
	}

}
