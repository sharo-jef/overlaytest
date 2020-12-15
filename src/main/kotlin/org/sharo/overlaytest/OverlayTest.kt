package org.sharo.overlaytest

import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(OverlayTest.MODID)
class OverlayTest {
    companion object {
        const val MODID = "overlaytest"
        @JvmStatic
        val logger: Logger = LogManager.getLogger()
    }
}
