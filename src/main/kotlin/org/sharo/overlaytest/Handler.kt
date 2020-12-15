package org.sharo.overlaytest

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = OverlayTest.MODID)
class Handler {
    companion object {
        @JvmStatic
        val mc: Minecraft = Minecraft.getInstance()

        @JvmStatic
        @SubscribeEvent
        fun onRenderGameOverlay(event: RenderGameOverlayEvent.Pre) {
            if (
                // これがないとすべてのPreイベントで描画されて激重になる
                event.type != RenderGameOverlayEvent.ElementType.ALL
                // ContainerScreenを開いてるときは描画しない
                || mc.currentScreen is ContainerScreen<*>
                // F3デバッグ画面を開いてるときは描画しない
                || mc.gameSettings.showDebugInfo
            ) {
                return
            }

            // 矩形を描画
            GuiHelper.drawRect(0, 0, 100, 100, 0xff00ff)
            GuiHelper.drawRect(100, 0, 100, 100, 0xffff00)
            GuiHelper.drawRect(mc.mainWindow.scaledWidth - 100, 0, 100, 100, 0xff0000)
            GuiHelper.drawRect(mc.mainWindow.scaledWidth - 20, mc.mainWindow.scaledHeight - 20, 20, 20, 0x0000ff)

            // Stringを描画
            GuiHelper.drawString("text", 0, 80, 0xff0000)

            // インベントリにあるアイテムを描画
            for (i in 0..8) {
                for (j in 0..3) {
                    val index = i + j * 9
                    val itemStack = mc.player?.inventory?.mainInventory?.get(index)
                    GuiHelper.drawItem(itemStack, i * 20, j * 20)
                }
            }
        }
    }
}
