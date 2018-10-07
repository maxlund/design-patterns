package net.sf.freecol.common.model;

import java.util.List;

import java.util.LinkedList;

import net.sf.freecol.common.model.Game;
import net.sf.freecol.common.model.Player;
import net.sf.freecol.common.model.Settlement;
import net.sf.freecol.common.model.FreeColObject;
import net.sf.freecol.common.model.Colony;
import net.sf.freecol.common.model.Specification;
import net.sf.freecol.common.model.GameOptions;

public class OperandStrategyFoundingFathers extends OperandStrategy {
    public OperandStrategyFoundingFathers() {}

    @Override
    public Integer calculateGameValue(Game game) {        
        List<FreeColObject> list = new LinkedList<>();
        for (Player player : game.getLivePlayers(null)) {
            list.addAll(player.getFathers());
        }
        return count(list);
    }

    @Override
    public Integer calculatePlayerValue(Player player) {
        List<FreeColObject> list = new LinkedList<>();

        list.addAll(player.getFathers());
        return count(list);      
    }

    @Override
    public Integer calculateSettlementValue(Settlement settlement) {
        Colony colony = (Colony) settlement;
        return colony.invokeMethod(getMethodName(), Integer.class,
                                    (Integer)null);
    }
}