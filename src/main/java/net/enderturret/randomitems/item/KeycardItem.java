package net.enderturret.randomitems.item;

import net.enderturret.randomitems.block.KeycardReaderBlock;
import net.enderturret.randomitems.tileentity.KeycardReaderTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class KeycardItem extends Item {

	public KeycardItem() {}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.getBlockState(pos).getBlock() instanceof KeycardReaderBlock && !worldIn.isRemote && worldIn.getTileEntity(pos) != null)
			if (worldIn.getTileEntity(pos) instanceof KeycardReaderTE) {
				final KeycardReaderTE te = (KeycardReaderTE) worldIn.getTileEntity(pos);
				if (player.isSneaking() && te.isOwner(EntityPlayer.getUUID(player.getGameProfile()))) {
					te.setKeycardName(player.getHeldItemMainhand().getDisplayName());
					player.sendMessage(new TextComponentTranslation("randomitems.keycard.setname", player.getHeldItemMainhand().getDisplayName()));
					return EnumActionResult.SUCCESS;
				}
			}
		return EnumActionResult.FAIL;
	}
}