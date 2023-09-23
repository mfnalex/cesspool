package com.jeff_media.cesspool.nms.versions.v1_20_1;

import com.jeff_media.cesspool.nms.generic.NMSHandler;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class NMSHandlerImpl implements NMSHandler {

    private static final NMSAdapterImpl NMS = new NMSAdapterImpl();

    @Override
    public void playTotemAnimation(Player player) {
        ServerPlayer nmsPlayer = NMS.nms(player);
        nmsPlayer.connection.send(new ClientboundEntityEventPacket(nmsPlayer, (byte) 35));
    }
    
}
