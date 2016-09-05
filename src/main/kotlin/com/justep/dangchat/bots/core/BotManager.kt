package com.justep.dangchat.bots.core

import im.actor.bots.framework.*

/**
 * 机器人管理器类
 * @author Lining
 * @date 2016/9/2
 *
 */
public class BotManager(val endpoint: String) {

    fun runBot(botName: String, botToken: String) {
        farm("dangtalk-bots-farm", endpoint) {
            bot(DangchatBot::class) {
                name = botName
                token = botToken
                overlordClazz = (NotificationOverlord::class).java
            }
        }
    }

}