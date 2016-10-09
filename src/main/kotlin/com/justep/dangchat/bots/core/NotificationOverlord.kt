package com.justep.dangchat.bots.core

import im.actor.bots.BotMessages
import im.actor.bots.framework.HookData
import im.actor.bots.framework.MagicOverlord
import im.actor.bots.framework.MagicOverlordScope
import im.actor.bots.framework.OutPeer
import im.actor.bots.framework.PeerType
import im.actor.bots.framework.outPeerFromJson
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

/**
 * 通知类机器人
 * @author Lining
 * @date 2016/9/2
 *
 */
class NotificationOverlord(scope: MagicOverlordScope) : MagicOverlord(scope) {

    val keyValue = scope.botKeyValue
    val subscribers = ArrayList<OutPeer>()
    val adminSubscribers = ArrayList<OutPeer>()

    // Events

    fun onText(text: String) {
        for (s in subscribers) {
            sendText(s, text)
        }
        onAdminText("Broadcasted message\n$text")
    }

    fun onNotification(text: String, users: ArrayList<BotMessages.User>) {
        for (user in users) {
            val peer = OutPeer(PeerType.PRIVATE, user.id(), user.accessHash())
            sendText(peer, text)
        }
    }

    fun onAdminText(text: String) {
        for (s in adminSubscribers) {
            sendText(s, text)
        }
    }

    fun onSubscribe(peer: OutPeer) {
        if (subscribers.contains(peer)) {
            return
        }
        subscribers.add(peer)
        saveSubscribers()

        onAdminText("Subscribed $peer")
    }

    fun onUnsubscribe(peer: OutPeer) {
        subscribers.remove(peer)
        saveSubscribers()

        onAdminText("Unsubscribed $peer")
    }

    fun onSubscribeAdmin(peer: OutPeer) {
        if (adminSubscribers.contains(peer)) {
            return
        }
        adminSubscribers.add(peer)
        saveSubscribers()
    }

    fun onUnsubscribeAdmin(peer: OutPeer) {
        adminSubscribers.remove(peer)
        saveSubscribers()
    }

    fun saveSubscribers() {
        val storage = JSONObject()

        val peers = JSONArray()
        for (s in subscribers) {
            peers.put(s.toJson())
        }
        storage.put("peers", peers)

        val adminPeers = JSONArray()
        for (s in adminSubscribers) {
            adminPeers.put(s.toJson())
        }
        storage.put("adminPeers", adminPeers)

        keyValue.setStringValue("storage", storage.toString())
    }

    fun loadSubscribers() {
        subscribers.clear()
        adminSubscribers.clear()
        try {
            val storage = JSONObject(keyValue.getStringValue("storage"))
            val peers = storage.getJSONArray("peers")
            for (i in 0..peers.length()) {
                try {
                    subscribers.add(outPeerFromJson(peers.getJSONObject(i)))
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
            val adminPeers = storage.getJSONArray("adminPeers")
            for (i in 0..adminPeers.length()) {
                try {
                    adminSubscribers.add(outPeerFromJson(peers.getJSONObject(i)))
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
	
	

    // Processor

    override fun preStart() {
        super.preStart()
    }

    override fun onReceive(update: Any?) {
        if (update is Subscribe) {
            onSubscribe(update.peer)
        } else if (update is Unsubscribe) {
            onUnsubscribe(update.peer)
        } else if (update is SubscribeAdmin) {
            onSubscribeAdmin(update.peer)
        } else if (update is UnsubscribeAdmin) {
            onUnsubscribeAdmin(update.peer)
        } else if (update is DoBroadcast) {
            onNotification(update.message, update.users)
        } else if (update is DoNotification) {
            onNotification(update.message, update.users)
        } else {
            super.onReceive(update)
        }
    }

    override fun onWebHookReceived(hook: HookData) {
        val jsonBody = hook.jsonBody
        if (jsonBody != null) {
            val text = jsonBody.optString("text")
            if (text != null) {
                onText(text)
            }
        }
    }

    data class DoBroadcast(val message: String, val users: ArrayList<BotMessages.User>)

    data class DoNotification(val message: String, val users: ArrayList<BotMessages.User>)

    data class Subscribe(val peer: OutPeer)

    data class Unsubscribe(val peer: OutPeer)

    data class SubscribeAdmin(val peer: OutPeer)

    data class UnsubscribeAdmin(val peer: OutPeer)
}