package net.sf.freecol.common.model;

import java.util.List;

import java.util.LinkedList;

import net.sf.freecol.common.model.Game;
import net.sf.freecol.common.model.Player;
import net.sf.freecol.common.model.Settlement;
import net.sf.freecol.common.model.FreeColObject;
import net.sf.freecol.common.model.Colony;;

public class OperandStrategyUnits extends OperandStrategy {
    public OperandStrategyUnits() {}

    @Override
    public Integer calculateGameValue(Game game) {
        List<FreeColObject> list = new LinkedList<>();
        for (Player player : game.getLivePlayers(null)) {
            list.addAll(player.getUnits());

        }
        return count(list);
    }

    @Override
    public Integer calculatePlayerValue(Player player) {
            return count(player.getUnits());
    }        

    @Override
    public Integer calculateSettlementValue(Settlement settlement) {
        Colony colony = (Colony) settlement;
        List<FreeColObject> list = new LinkedList<>();
        list.addAll(colony.getUnitList());

        return count(list);
    }
}