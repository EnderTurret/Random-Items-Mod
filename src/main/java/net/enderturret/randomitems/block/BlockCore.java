package net.enderturret.randomitems.block;

import net.enderturret.randomitems.ConfigHandler;
import net.enderturret.randomitems.init.ModItems;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCore extends BlockBase {
	public BlockCore(String name) {
		super(name, SoundType.GLASS, Material.PISTON);
		setLightLevel(1F);
		setCreativeTab(null);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (ConfigHandler.coreThingEnabled && !worldIn.isRemote && !worldIn.isAirBlock(pos.up()))
			return craft(worldIn, pos, state, playerIn, worldIn.getBlockState(pos.up()));
		else
			return false;
	}
	private boolean craft(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, IBlockState stateAbove) {
		if (stateAbove == Blocks.END_STONE.getDefaultState()) {
			worldIn.destroyBlock(pos.up(), false);
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5, new ItemStack(ModItems.questionableCheese)));
			return true;
		}
		else if (stateAbove == Blocks.QUARTZ_BLOCK.getDefaultState()) {
			worldIn.destroyBlock(pos.up(), false);
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5, new ItemStack(ModItems.flard)));
			return true;
		}
		/*else if (worldIn.getBlockState(pos.up(2)) == Blocks.REEDS.getDefaultState()) {
			worldIn.destroyBlock(pos.up(2), false);
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5, new ItemStack(ModItems.syntacticSugar)));
			return true;
		}*/
		return false;
	}
}