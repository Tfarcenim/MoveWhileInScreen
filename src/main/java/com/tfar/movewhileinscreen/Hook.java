package com.tfar.movewhileinscreen;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MovementInput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class Hook {

  @SubscribeEvent
  public static void update(InputUpdateEvent e) {
    if (Minecraft.getInstance().currentScreen != null && !Minecraft.getInstance().currentScreen.isPauseScreen()) {
      MovementInput movementInput = e.getMovementInput();
      movementInput.forwardKeyDown = Minecraft.getInstance().gameSettings.keyBindForward.pressed;
      movementInput.backKeyDown = Minecraft.getInstance().gameSettings.keyBindBack.pressed;
      movementInput.leftKeyDown = Minecraft.getInstance().gameSettings.keyBindLeft.pressed;
      movementInput.rightKeyDown = Minecraft.getInstance().gameSettings.keyBindRight.pressed;
      movementInput.moveForward = movementInput.forwardKeyDown == movementInput.backKeyDown ? 0.0F : (movementInput.forwardKeyDown ? 1.0F : -1.0F);
      movementInput.moveStrafe = movementInput.leftKeyDown == movementInput.rightKeyDown ? 0.0F : (movementInput.leftKeyDown ? 1.0F : -1.0F);
      movementInput.jump = Minecraft.getInstance().gameSettings.keyBindJump.pressed;
      movementInput.field_228350_h_ = Minecraft.getInstance().gameSettings.field_228046_af_.isKeyDown();
      if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.isCrouching()) {
        movementInput.moveStrafe = (float) ((double) movementInput.moveStrafe * 0.3D);
        movementInput.moveForward = (float) ((double) movementInput.moveForward * 0.3D);
      }
    }
  }
}
