package net.mcmerdith.monkeutil.common.items;

import net.mcmerdith.monkeutil.Main;
import net.mcmerdith.monkeutil.client.render.gui.RemoteControlGUI;
import net.mcmerdith.monkeutil.client.util.ClientUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Remote Control Item
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public class RemoteControlItem extends Item {
    /**
     * Create a new Item
     */
    public RemoteControlItem() {
        super(new Item.Properties().group(Main.ITEM_GROUP));
    }

    /**
     * Add tooltips IF we're on the client side
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        tooltip.add(ClientUtils.tooltipTranslation("remote_control.description"));
        tooltip.add(ClientUtils.tooltipTranslation("remote_control.help"));
    }

    /**
     * Open the {@link RemoteControlGUI} if the player is Sneaking
     */
    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, PlayerEntity playerIn, @Nonnull Hand handIn) {
        // Open GUI here
        ItemStack thisItem = playerIn.getHeldItem(handIn);
        if (!playerIn.isSneaking()) return ActionResult.resultPass(thisItem);
        RemoteControlGUI.open();
        return ActionResult.resultSuccess(thisItem);
    }
}
