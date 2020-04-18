package srpfacadelab;

public class DamageFuncs {

    private IGameEngine gameEngine;

    public DamageFuncs(IGameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
    }

    public void takeDamage(int damage, RpgPlayer player)
    {
        if (damage < player.getArmour()) {
            gameEngine.playSpecialEffect("parry");
        }

        int damageToDeal = damage - player.getArmour();
        player.setHealth(player.getHealth() - damageToDeal);

        gameEngine.playSpecialEffect("lots_of_gore");
    }
}
