package fr.doron.uhc.plugin.tools;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void send(Player player, String message) {
        if (player == null) return;

        try {
            IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a(
                    "{\"text\":\"" + message.replace("\"", "\\\"") + "\"}"
            );

            PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}