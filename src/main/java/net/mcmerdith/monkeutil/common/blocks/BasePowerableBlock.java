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
import java.util.function.ToIntFunction;

public class BasePowerableBlock extends Block {

    public BasePowerableBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(BlockStateProperties.POWERED, false));

        LOGGER.info("Created Powerable Block");
    }

    static ToIntFunction<BlockState> illumination() {
        return (state) -> state.get(BlockStateProperties.POWERED) ? 15 : 0;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(BlockStateProperties.POWERED, context.getWorld().isBlockPowered(context.getPos()));
    }

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

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(BlockStateProperties.POWERED);
    }
}
