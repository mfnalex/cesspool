package com.jeff_media.cesspool.nms.versions.v1_20_1;

import com.jeff_media.cesspool.nms.generic.NMSHandler;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSHandlerImpl implements NMSHandler {

    private ServerPlayer nms(Player player) {
        return ((CraftPlayer)player).getHandle();
    }

    @Override
    public void playTotemAnimation(Player player) {
        ServerPlayer nmsPlayer = nms(player);
        nmsPlayer.connection.send(new ClientboundEntityEventPacket(nmsPlayer, (byte) 35));
    }
}
