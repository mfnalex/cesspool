package com.jeff_media.cesspool.nms.aggregator;

import com.jeff_media.cesspool.CesspoolLogger;
import com.jeff_media.cesspool.McVersion;
import com.jeff_media.cesspool.exceptions.NMSNotSupportedException;
import com.jeff_media.cesspool.nms.generic.NMSHandler;
import com.jeff_media.cesspool.reflection.ReflectionUtils;

/**
 * Provides access to the NMS handler for the current server version
 */
public class CesspoolNMS {

    private static final CesspoolLogger LOG = CesspoolLogger.getLogger(CesspoolNMS.class);
    private static final McVersion MC_VERSION = McVersion.current();
    private static final String CRAFTBUKKIT_NMS_VERSION = ReflectionUtils.getNMSVersion();

    private static boolean hasTriedCreatingNmsHandler = false;
    private static NMSHandler nmsHandler;

    /**
     * Enables NMS support for the current server version
     * @return True if NMS support was enabled, false otherwise (because the current NMS version is not supported)
     */
    public static boolean enableNMS() {
        if(nmsHandler != null) {
            return true;
        }

        nmsHandler = createNmsHandler();
        hasTriedCreatingNmsHandler = true;
        return nmsHandler != null;
    }

    /**
     * Gets the NMS handler for the current server version
     * @return NMS handler
     * @throws NMSNotSupportedException if the current NMS version is not supported
     */
    public static NMSHandler getNMSHandler() throws NMSNotSupportedException {
        if(nmsHandler == null && !hasTriedCreatingNmsHandler) {
            LOG.warning("NMS support was not enabled yet, enabling it lazily now. Avoid this by calling CesspoolNMS.enableNMS() during plugin initialization.");
            enableNMS();
        }
        if(nmsHandler == null) {
            throw new NMSNotSupportedException("Failed to create NMS handler for McVersion " + MC_VERSION + " (CraftBukkit NMS version " + CRAFTBUKKIT_NMS_VERSION + ")");
        }
        return nmsHandler;
    }

    private static NMSHandler createNmsHandler() {
        try {
            // 1.20.1+ using v1_20_R1
            // 1.20.1
            // 1.20
            if (MC_VERSION.isAtLeast(1, 20) && ReflectionUtils.getNMSVersion().equalsIgnoreCase("v1_20_R1")) {
                return new com.jeff_media.cesspool.nms.versions.v1_20_1.NMSHandlerImpl();
            }
        } catch (Throwable throwable) {
            LOG.severe("Failed to create NMS handler for McVersion " + MC_VERSION + " (CraftBukkit NMS version " + CRAFTBUKKIT_NMS_VERSION + ") although this NMS version should be supported", throwable);
        }

        return null;
    }

}
