package srpfacadelab;
import java.util.List;
import java.util.ArrayList;

public class ItemFuncs {

    private IGameEngine gameEngine;

    public ItemFuncs(IGameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
    }

    public void useItem(Item item, RpgPlayer player)
    {
        if (item.getName().equals("Stink Bomb"))
        {
            List<IEnemy> enemies = gameEngine.getEnemiesNear(player);

            for (IEnemy enemy: enemies)
            {
                enemy.takeDamage(100);
            }
        }
    }

    public boolean pickUpItem(Item item, RpgPlayer player)
    {
        int weight = calculateInventoryWeight(player);
        if (weight + item.getWeight() > player.getCarryingCapacity())
            return false;

        if (item.isUnique() && checkIfItemExistsInInventory(item, player))
            return false;

        // Don't pick up items that give health, just consume them.
        if (item.getHeal() > 0)
        {
            player.setHealth(player.getHealth() + item.getHeal());

            if (player.getHealth() > player.getMaxHealth())
                player.setHealth(player.getMaxHealth());

            if (item.getHeal() > 500) {
                gameEngine.playSpecialEffect("green_swirly");
            }

            return true;
        }

        if (item.isRare())
            gameEngine.playSpecialEffect("cool_swirly_particles");

        player.setInventory(item);

        calculateStats(player);

        return true;
    }

    private void calculateStats(player)
    {
        for (Item i: player.getInventory())
        {
            player.setArmour(player.getArmour() + i.getArmour());
        }
    }

    private boolean checkIfItemExistsInInventory(Item item, RpgPlayer player)
    {
        for (Item i: player.getInventory())
        {
            if (i.getId() == item.getId())
                return true;
        }
        return false;
    }

    private int calculateInventoryWeight(RpgPlayer player)
    {
        int sum=0;
        for (Item i: player.getInventory())
        {
            sum += i.getWeight();
        }
        return sum;
    }

}
