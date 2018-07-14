package com.dmitmit.game.GameObjects.Player;

import com.dmitmit.game.GameObjects.Player.Carl;

public class CarlController {
    private Carl carl;

    public CarlController(Carl carl){
        this.carl = carl;
    }

    public void update(){
        switch (carl.getState()){
            case JUMPING:
               // carl.addVelocity();
        }
    }
}
