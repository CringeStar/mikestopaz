package cringestar.mikes.topaz;

import cringestar.mikes.topaz.molds.AxeMoldBlock;
import cringestar.mikes.topaz.molds.HoeMoldBlock;
import cringestar.mikes.topaz.molds.PickaxeMoldBlock;
import cringestar.mikes.topaz.molds.SwordMoldBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

@SuppressWarnings("deprecation")

public class HeaterBlock extends Block{

    public static final BooleanProperty EMPTY = BooleanProperty.of("empty");


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(EMPTY);
    }

    public HeaterBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(EMPTY, true));

    }



    @Override


    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        boolean bl = state.get(EMPTY);
        if (bl){
        if (itemStack.getItem() == MikesTopaz.CONTAINED_COMPACT_LAVA.asItem()) {
            if (!world.isClient) {
              world.setBlockState(pos, state.with(EMPTY, false));
                if (!playerEntity.getAbilities().creativeMode) {playerEntity.setStackInHand(hand, new ItemStack(MikesTopaz.SUPER_CONTAINER));}

            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY_LAVA , SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, playerEntity, hand, hit);
        }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
       if (world.getBlockState(pos.down()).isOf(MikesTopaz.HOE_MOLD) || world.getBlockState(pos.down()).isOf(MikesTopaz.SWORD_MOLD) || world.getBlockState(pos.down()).isOf(MikesTopaz.PICKAXE_MOLD) || world.getBlockState(pos.down()).isOf(MikesTopaz.AXE_MOLD)){
            if (world.getBlockState(pos.down()).get(AxeMoldBlock.MELTED) == 4 || world.getBlockState(pos.down()).get(SwordMoldBlock.MELTED) == 2 || world.getBlockState(pos.down()).get(PickaxeMoldBlock.MELTED) == 2 || world.getBlockState(pos.down()).get(HoeMoldBlock.MELTED) == 4) {
               if (!world.isClient){
                   world.setBlockState(pos, state.with(EMPTY, true));
                   world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH , SoundCategory.BLOCKS, 1.0F, 1.0F);
               }
           }
       }
    }
}