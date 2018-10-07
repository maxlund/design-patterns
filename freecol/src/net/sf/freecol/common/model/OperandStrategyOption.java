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


public class OperandStrategyOption extends OperandStrategy {
    public OperandStrategyOption() {}

    @Override
    public Integer calculateGameValue(Game game) {
        return game.getSpecification().getInteger(getType());
    }

    @Override
    public Integer calculatePlayerValue(Player player) {
        final String methodName = getMethodName();
        
        return player.invokeMethod(methodName, Integer.class,
                                    (Integer)null);       
    }

    @Override
    public Integer calculateSettlementValue(Settlement settlement) {
        Colony colony = (Colony) settlement;
        return colony.invokeMethod(getMethodName(), Integer.class,
                                    (Integer)null);
    }
}