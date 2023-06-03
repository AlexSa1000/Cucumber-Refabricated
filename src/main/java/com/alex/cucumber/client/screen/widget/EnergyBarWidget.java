package com.alex.cucumber.client.screen.widget;

import com.alex.cucumber.Cucumber;
import com.alex.cucumber.util.Formatting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import team.reborn.energy.api.EnergyStorage;

public class EnergyBarWidget extends AbstractWidget {
    private static final ResourceLocation WIDGETS_TEXTURE = new ResourceLocation(Cucumber.MOD_ID, "textures/gui/widgets.png");
    private final EnergyStorage energy;
    private final AbstractContainerScreen<?> screen;

    public EnergyBarWidget(int x, int y, EnergyStorage energy, AbstractContainerScreen<?> screen) {
        super(x, y, 14, 78, Component.literal("Energy Bar"));
        this.energy = energy;
        this.screen = screen;
    }

    @Override
    public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, WIDGETS_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();

        int offset = this.getEnergyBarOffset();

        this.blit(matrix, this.x, this.y, 0, 0, this.width, this.height);
        this.blit(matrix, this.x, this.y + this.height - offset, 14, this.height - offset, this.width,  offset + 1);

        if (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height) {
            var text = Component.literal(Formatting.number(this.energy.getAmount()).getString() + " / " + Formatting.energy(this.energy.getCapacity()).getString());

            this.screen.renderTooltip(matrix, text, mouseX, mouseY);
        }
    }

    @Override
    public void updateNarration(NarrationElementOutput narration) { }

    private int getEnergyBarOffset() {
        long i = this.energy.getAmount();
        long j = this.energy.getCapacity();
        return (int)(j != 0 && i != 0 ? i * (long)this.height / (long)j : 0L);
    }
}
