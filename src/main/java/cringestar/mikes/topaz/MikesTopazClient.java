package cringestar.mikes.topaz;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)

public class MikesTopazClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(MikesTopaz.HEATER,RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MikesTopaz.SUPER_CONTAINER,RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MikesTopaz.PICKAXE_MOLD,RenderLayer.getCutout());
        
    }
    
}
