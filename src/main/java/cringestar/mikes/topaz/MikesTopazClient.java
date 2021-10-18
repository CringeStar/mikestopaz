package cringestar.mikes.topaz;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)

public class MikesTopazClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(MikesTopaz.HEATER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MikesTopaz.SUPER_CONTAINER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MikesTopaz.PICKAXE_MOLD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MikesTopaz.SWORD_MOLD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MikesTopaz.AXE_MOLD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MikesTopaz.HOE_MOLD, RenderLayer.getCutout());
        //noinspection UnstableApiUsage
        EntityModelLayerRegistry.registerModelLayer(MikesTopaz.TOPAZ_SHIELD_MODEL_LAYER, ShieldEntityModel::getTexturedModelData);
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(new Identifier("mikestopaz", "entity/topaz_shield_base"));
            registry.register(new Identifier("mikestopaz", "entity/topaz_shield_base_nopattern"));
        });
    }
}
