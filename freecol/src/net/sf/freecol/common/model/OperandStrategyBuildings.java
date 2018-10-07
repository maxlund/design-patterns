package net.sf.freecol.common.model;

import java.util.List;

import java.util.LinkedList;

import net.sf.freecol.common.model.Game;
import net.sf.freecol.common.model.Player;
import net.sf.freecol.common.model.Settlement;
import net.sf.freecol.common.model.FreeColObject;
import net.sf.freecol.common.model.Colony;;

public class OperandStrategyBuildings extends OperandStrategy {
    public OperandStrategyBuildings() {}

    @Override
    public Integer calculateGameValue(Game game) {
        List<FreeColObject> list = new LinkedList<>();
        for (Player player : game.getLivePlayers(null)) {
            for (Colony colony : player.getColonies()) {
                list.addAll(colony.getBuildings());
            }
        }            

        return count(list);
    }

    @Override
    public Integer calculatePlayerValue(Player player) {
        List<FreeColObject> list = new LinkedList<>();
        
        for (Colony colony : player.getColonies()) {
            list.addAll(colony.getBuildings());
        }
        return count(list);      
    }

    @Override
    public Integer calculateSettlementValue(Settlement settlement) {
        Colony colony = (Colony) settlement;
        List<FreeColObject> list = new LinkedList<>();

        list.addAll(colony.getBuildings());
        return count(list);
    }
}