package com.jeff_media.cesspool.nms.aggregator;

import com.jeff_media.cesspool.McVersion;
import com.jeff_media.cesspool.nms.generic.NMSHandler;

public class NMSHandlerFactory {

    public static NMSHandler getNMSHandler() {
        McVersion version = McVersion.current();
        if(version.isAtLeast(1,20,1)) {
            return new com.jeff_media.cesspool.nms.versions.v1_20_1.NMSHandlerImpl();
        }
        // Other versions
        throw new IllegalStateException("Unsupported Minecraft version: " + version);
    }

}
