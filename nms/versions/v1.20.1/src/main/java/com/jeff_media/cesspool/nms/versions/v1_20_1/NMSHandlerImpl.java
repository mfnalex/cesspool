package com.jeff_media.cesspool.nms.versions.v1_20_1;

import com.jeff_media.cesspool.nms.generic.NMSHandler;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class NMSHandlerImpl implements NMSHandler {

    private static final NMSAdapterImpl NMS = new NMSAdapterImpl();

    @Override
    public void playTotemAnimation(Player player) {
        ServerPlayer nmsPlayer = NMS.nms(player);
        nmsPlayer.connection.send(new ClientboundEntityEventPacket(nmsPlayer, (byte) 35));
    }

    @Override
    public void setHeadTexture(@NotNull final Block block, @NotNull final GameProfile gameProfile) {
        final ServerLevel world = ((CraftWorld) block.getWorld()).getHandle();
        final BlockPos blockPosition = new BlockPos(block.getX(), block.getY(), block.getZ());
        final SkullBlockEntity skull = (SkullBlockEntity) world.getBlockEntity(blockPosition, false);
        assert skull != null;
        skull.setOwner(gameProfile);
    }

}
