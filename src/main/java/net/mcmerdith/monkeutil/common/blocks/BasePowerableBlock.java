package net.mcmerdith.monkeutil.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * A block capable of receiving Redstone Power
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public class BasePowerableBlock extends Block {

    /**
     * Create a new powerable block
     *
     * @param properties The properties to initialize this block with
     * @apiNote {@link BlockStateProperties#POWERED} will be added automatically
     */
    public BasePowerableBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(BlockStateProperties.POWERED, false));
    }

    /**
     * Get the default properties
     */
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(BlockStateProperties.POWERED, context.getWorld().isBlockPowered(context.getPos()));
    }

    /**
     * Check if the block is receiving power when a neighboring block changes state
     */
    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote) {
            boolean isPowered = state.get(BlockStateProperties.POWERED);
            boolean shouldBePowered = worldIn.isBlockPowered(pos);
            if (isPowered != shouldBePowered) {
                worldIn.setBlockState(pos, state.with(BlockStateProperties.POWERED, shouldBePowered));
            }
        }
    }

    /**
     * Register the default properties
     */
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(BlockStateProperties.POWERED);
    }
}
