package me.itsatacoshop247.UnderWaterTorch;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockListener;

public class UnderWaterTorchBlockListener extends BlockListener{
	public static UnderWaterTorch plugin;
	
	public UnderWaterTorchBlockListener(UnderWaterTorch instance) {
		plugin = instance;
	}
    public void onBlockFromTo(BlockFromToEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Block blockFrom = event.getBlock();
        Block blockTo = event.getToBlock();

        boolean CheckWater = blockFrom.getTypeId() == 8 || blockFrom.getTypeId() == 9;
        boolean CheckLava = blockFrom.getTypeId() == 10 || blockFrom.getTypeId() == 11;
            
            if ((blockFrom.getTypeId() == 0 || CheckWater || CheckLava) && 
                    blockTo.getTypeId() == 50) {
                event.setCancelled(true);
                return;
        }
            if ((blockFrom.getTypeId() == 0 || CheckWater || CheckLava) && 
                    blockTo.getTypeId() == 66) {
                event.setCancelled(true);
                return;
        }
            /*if ((blockFrom.getTypeId() == 0 || CheckWater || CheckLava) && 
                    blockTo.getTypeId() == 0) {
                event.setCancelled(true);
                return;
        }*/
}
}

//set block above to air. Make another onBLocktoFrom event, and if
//the block is air and (y-1)==66 then cancel the event.
