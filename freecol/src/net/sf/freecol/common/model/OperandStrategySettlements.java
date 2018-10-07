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

public class OperandStrategySettlements extends OperandStrategy {
    public OperandStrategySettlements() {}

    @Override
    public Integer calculateGameValue(Game game) {        
        List<FreeColObject> list = new LinkedList<>();
        for (Player player : game.getLivePlayers(null)) {
            list.addAll(player.getSettlements());
        }
        return count(list);
    }

    @Override
    public Integer calculatePlayerValue(Player player) {
        final Specification spec = player.getSpecification();
        final String methodName = getMethodName();
        
        if (methodName == null) {
            return count(player.getSettlements())
                + spec.getInteger(GameOptions.SETTLEMENT_LIMIT_MODIFIER);
        } else {
            final String methodValue = getMethodValue();
            int result = 0;
            for (Settlement settlement : player.getSettlements()) {
                Boolean b = settlement.invokeMethod(methodName,
                    Boolean.class, Boolean.FALSE);
                if (String.valueOf(b).equals(methodValue)) result++;
            }
            return result;
        }
    }

    @Override
    public Integer calculateSettlementValue(Settlement settlement) {
        Colony colony = (Colony) settlement;
        return colony.invokeMethod(getMethodName(), Integer.class,
                                    (Integer)null);
    }
}