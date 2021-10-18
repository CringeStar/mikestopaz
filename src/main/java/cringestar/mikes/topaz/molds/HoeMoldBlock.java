package cringestar.mikes.topaz.molds;

import cringestar.mikes.topaz.HeaterBlock;
import cringestar.mikes.topaz.MikesTopaz;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class HoeMoldBlock extends Block{

    public static int maxFull = 16;
    public static int maxMelt = 4;

    public static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

    public static final IntProperty FULLNESS = IntProperty.of("fullness", 0, 16);
    public static final IntProperty MELTED = IntProperty.of("melted", 0, 4);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FULLNESS).add(MELTED);
    }

    public HoeMoldBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FULLNESS, 0).with(MELTED, 0));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        int i = state.get(FULLNESS);
        if (i < maxFull){
            if (itemStack.getItem() == MikesTopaz.TOPAZ) {
                if (!world.isClient) {
                    i++;
                    world.setBlockState(pos, state.with(FULLNESS, i));
                    if (!playerEntity.getAbilities().creativeMode) {itemStack.decrement(1);}

                }
            }
            return ActionResult.SUCCESS;
        }else{
            return ActionResult.PASS;
        }

    }

    @Override


    public void randomTick(BlockState blockState, ServerWorld world, BlockPos pos, Random random){
        int i = blockState.get(FULLNESS);
        int a = blockState.get(MELTED);
        if(i == maxFull) {
            if (a < maxMelt){
                if (world.getBlockState(pos.down()).isOf(MikesTopaz.HEATER))
                    if (!world.getBlockState(pos.down()).get(HeaterBlock.EMPTY)){
                        a++;
                        world.setBlockState(pos, blockState.with(MELTED, a));
                        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        if (a == maxMelt) {
                            if (!world.isClient) {
                                world.setBlockState(pos, blockState.with(MELTED, 4));
                                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY_LAVA, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            }
                        }
                    }
            }
        }
    }

}
