package srpfacadelab;

public class FacadeFuncs {

    private IGameEngine gameEngine;

    private ItemFuncs = itemFuncs;

    private DamageFuncs =  damageFuncs;

    public FacadeFuncs(IGameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        this.itemFuncs = new ItemFuncs(gameEngine);
        this.damageFuncs = new damageFuncs(gameEngine);
    }

    public void useItem(Item item, RpgPlayer player)
    {
        this.itemFuncs.useItem(item, player);
    }

    public boolean pickUpItem(Item item, RpgPlayer player)
    {
        return this.itemFuncs.pickUpItem(item, player);
    }

    public void takeDamage(int damage, RpgPlayer player)
    {
        this.damageFuncs.takeDamage(damage, player);
    }
}
