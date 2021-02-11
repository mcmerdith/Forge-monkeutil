package net.mcmerdith.monkeutil.client.render.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.mcmerdith.monkeutil.MonkeUtil;
import net.mcmerdith.monkeutil.client.util.ClientUtils;
import net.mcmerdith.monkeutil.core.enums.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public abstract class BaseScreen extends Screen {
    protected final ResourceLocation GUI;
    private final boolean pause;
    protected final int WIDTH;
    protected final int HEIGHT;

    protected BaseScreen(String name, int WIDTH, int HEIGHT, boolean pause) {
        super(ClientUtils.screenTranslation("remote_control"));
        GUI = new ResourceLocation(Keys.MODID, "textures/gui/" + name + ".png");
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.pause = pause;
    }
    protected BaseScreen(String name, int WIDTH, int HEIGHT) { this(name, WIDTH, HEIGHT, false); }

    public static void open(Screen s) {
        Minecraft.getInstance().displayGuiScreen(s);
    }

    @Override
    public boolean isPauseScreen() {
        return pause;
    }

    @Override
    abstract protected void init();

    @Override
    public void render(@Nonnull MatrixStack stack, int x, int y, float ticks) {
        RenderSystem.color4f(1F, 1F, 1F, 1F);
        if (this.minecraft == null) {
            MonkeUtil.LOGGER.error("Could not bind GUI texture");
        } else {
            this.minecraft.textureManager.bindTexture(GUI);
        }
        this.blit(stack,(this.width - WIDTH) / 2, (this.height - HEIGHT) / 2, 0, 0, WIDTH, HEIGHT);
        super.render(stack, x, y, ticks);
    }
}
