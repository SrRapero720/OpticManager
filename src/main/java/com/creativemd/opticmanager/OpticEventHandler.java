package com.creativemd.opticmanager;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class OpticEventHandler {
	
	@SubscribeEvent
	public void onRenderNameTag(RenderLivingEvent.Specials.Pre event)
	{
		if(!OpticManager.renderNameTag)
			event.setCanceled(true);
	}
	
	public static float defaultGammaSetting;
	public Minecraft mc = Minecraft.getMinecraft();
	
	@SubscribeEvent
	public void renderTick(RenderTickEvent event)
	{
		if(event.phase == Phase.START)
		{
			defaultGammaSetting = mc.gameSettings.gammaSetting;
			mc.gameSettings.gammaSetting = OpticManager.brightness;
		}else{
			mc.gameSettings.gammaSetting = defaultGammaSetting;
			
			if(OpticManager.visibilty == null)
				OpticManager.visibilty = EnumChatVisibility.FULL;
			if(OpticManager.visibilty != null && mc.gameSettings.chatVisibility != OpticManager.visibilty)
				mc.gameSettings.chatVisibility = OpticManager.visibilty;
		}
	}
	
}
