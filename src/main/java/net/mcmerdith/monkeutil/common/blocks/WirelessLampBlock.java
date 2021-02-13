package net.mcmerdith.monkeutil.common.blocks;

import net.mcmerdith.monkeutil.Main;
import net.mcmerdith.monkeutil.client.render.gui.NetworkIDGui;
import net.mcmerdith.monkeutil.client.render.tileentity.WirelessLampTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A redstone lamp that can be enabled or disabled wirelessly
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public class WirelessLampBlock extends BasePowerableBlock {
    /**
     * Create a new block
     */
    public WirelessLampBlock() {
        super(AbstractBlock.Properties.create(Material.REDSTONE_LIGHT).setLightLevel((state) -> state.get(BlockStateProperties.POWERED) ? 15 : 0).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid());
    }

    /**
     * Expose that this block has a {@link WirelessLampTileEntity}
     */
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    /**
     * Create a new {@link WirelessLampTileEntity} when requested
     *
     * @return A new instance of {@link WirelessLampTileEntity}
     */
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new WirelessLampTileEntity();
    }

    /**
     * Open the {@link NetworkIDGui} if the player is holding a {@link net.mcmerdith.monkeutil.core.init.ItemInit#REMOTE_CONTROL} and there is a TileEntity registered at the blocks location
     */
    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.getHeldItem(handIn).isItemEqual(Main.getInstance().ITEMS.REMOTE_CONTROL.get().getDefaultInstance())) {
            NetworkIDGui.open(worldIn.getTileEntity(pos));
            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.PASS;
        }
    }


}
