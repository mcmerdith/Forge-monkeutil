package net.mcmerdith.monkeutil.common.items;

import net.mcmerdith.monkeutil.MonkeUtil;
import net.mcmerdith.monkeutil.client.render.gui.RemoteControlGUI;
import net.mcmerdith.monkeutil.client.util.ClientUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class RemoteControlItem extends Item {
    public RemoteControlItem() {
        super(new Item.Properties().group(MonkeUtil.ITEM_GROUP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        tooltip.add(ClientUtils.tooltipTranslation("remote_control.description"));
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, PlayerEntity playerIn, @Nonnull Hand handIn) {
        // Open GUI here
        RemoteControlGUI.open();
        System.out.println("Opening GUI");
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        System.out.println("Used");

    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        LogManager.getLogger().info("Item Used");
        System.out.println("Item Used");
        return super.onItemUse(context);
    }

    //VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5, 8, 8, 11, 16, 10), Block.makeCuboidShape(10, 16, 8, 11, 22, 9), IBooleanFunction.OR)
}
