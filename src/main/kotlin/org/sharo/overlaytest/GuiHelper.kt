package org.sharo.overlaytest

import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.platform.GlStateManager
import net.minecraft.client.Minecraft
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import org.lwjgl.opengl.GL11

class GuiHelper {
    companion object {
        @JvmStatic
        private val mc: Minecraft = Minecraft.getInstance()

        /**
         * draw string in screen
         * @param text text to draw
         * @param x x coord
         * @param y y coord
         * @param color RGB 0xff0000: red, 0x00ff00: green, 0x0000ff: blue
         */
        @JvmStatic
        @OnlyIn(Dist.CLIENT)
        fun drawString(text: String, x: Float, y: Float, color: Int) {
            mc.fontRenderer.drawString(MatrixStack(), text, x, y, color)
        }
        /**
         * draw string in screen
         * @param text text to draw
         * @param x x coord
         * @param y y coord
         * @param color RGB 0xff0000: red, 0x00ff00: green, 0x0000ff: blue
         */
        @JvmStatic
        @OnlyIn(Dist.CLIENT)
        fun drawString(text: String, x: Int, y: Int, color: Int) {
            drawString(text, x.toFloat(), y.toFloat(), color)
        }

        /**
         * draw rectangle in screen
         * @param x x coord
         * @param y y coord
         * @param width width
         * @param height height
         * @param color RGB 0xff0000: red, 0x00ff00: green, 0x0000ff: blue
         */
        @JvmStatic
        @OnlyIn(Dist.CLIENT)
        fun drawRect(x: Int, y: Int, width: Int, height: Int, color: Int) {
            GlStateManager.disableCull()
            GL11.glColor3f((color ushr 16 and 0xff) / 256f, (color ushr 8 and 0xff) / 256f, (color and 0xff) / 256f)
            GL11.glRecti(x, y, x + width, y + height)
            GlStateManager.enableCull()
        }

        /**
         * draw item in screen
         * @param itemStack itemStack to draw
         * @param x x coord
         * @param y y coord
         * @param text text
         */
        @JvmStatic
        @OnlyIn(Dist.CLIENT)
        fun drawItem(itemStack: ItemStack?, x: Int, y: Int, text: String?): Boolean {
            if (
                itemStack == null
                || mc.player == null
            ) {
                return false
            }
            mc.itemRenderer.renderItemAndEffectIntoGUI(mc.player as LivingEntity, itemStack, x, y)
            mc.itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, itemStack, x, y, text)
            return true
        }
        /**
         * draw item in screen
         * @param itemStack itemStack to draw
         * @param x x coord
         * @param y y coord
         */
        @JvmStatic
        @OnlyIn(Dist.CLIENT)
        fun drawItem(itemStack: ItemStack?, x: Int, y: Int): Boolean {
            return drawItem(itemStack, x, y, null)
        }
    }
}
