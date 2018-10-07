package net.sf.freecol.common.model;

import net.sf.freecol.common.model.Game;
import net.sf.freecol.common.model.Player;
import net.sf.freecol.common.model.Settlement;
import net.sf.freecol.common.model.Colony;


public class OperandStrategyYear extends OperandStrategy {
    public OperandStrategyYear() {}

    @Override
    public Integer calculateGameValue(Game game) {
        return game.getTurn().getYear();
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