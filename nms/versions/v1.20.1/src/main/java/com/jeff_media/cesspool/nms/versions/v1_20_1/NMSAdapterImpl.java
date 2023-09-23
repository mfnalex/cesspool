package com.jeff_media.cesspool.nms.versions.v1_20_1;

import com.jeff_media.cesspool.nms.generic.NMSAdapter;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSAdapterImpl implements NMSAdapter {

    @Override
    public String getNMSVersionPackage() {
        return "v1_20_R1";
    }

    @Override
    public ServerPlayer nms(Player player) {
        return ((CraftPlayer)player).getHandle();
    }

}
