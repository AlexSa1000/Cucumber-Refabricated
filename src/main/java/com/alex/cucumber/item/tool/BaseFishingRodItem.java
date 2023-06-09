package com.alex.cucumber.item.tool;

import com.alex.cucumber.iface.Enableable;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public class BaseFishingRodItem extends FishingRodItem {
    public BaseFishingRodItem(Function<Properties, Properties> properties) {
        super(properties.apply(new Properties()));
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (this instanceof Enableable enableable) {
            if (enableable.isEnabled())
                super.fillItemCategory(group, items);
        } else {
            super.fillItemCategory(group, items);
        }
    }

    public static ClampedItemPropertyFunction getCastPropertyGetter() {
        return (stack, level, entity, _unused) -> {
            if (entity == null)
                return 0.0F;

            boolean flag = entity.getMainHandItem() == stack;
            boolean flag1 = entity.getOffhandItem() == stack;
            if (entity.getMainHandItem().getItem() instanceof FishingRodItem) {
                flag1 = false;
            }

            return (flag || flag1) && entity instanceof Player player && player.fishing != null ? 1.0F : 0.0F;
        };
    }
}