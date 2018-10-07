package net.sf.freecol.common.model;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import net.sf.freecol.common.io.FreeColXMLReader;
import net.sf.freecol.common.model.Scope;
import net.sf.freecol.common.model.Game;
import net.sf.freecol.common.model.Player;
import net.sf.freecol.common.model.Settlement;
import net.sf.freecol.common.model.FreeColObject;


public abstract class OperandStrategy extends Scope {
    /**
 * Deliberately empty constructor.
 */
public OperandStrategy() {}

/**
 * Create a new operand by reading a stream.
 *
 * @param xr The <code>FreeColXMLReader</code> to read.
 * @exception XMLStreamException if there is a problem reading the stream.
 */
protected OperandStrategy(FreeColXMLReader xr) throws XMLStreamException {
    readFromXML(xr);
}

public Integer calculateGameValue(Game game) {return null;}
public Integer calculatePlayerValue(Player player) {return null;}
public Integer calculateSettlementValue(Settlement settlement) {return null;}

/**
 * Count the number of objects in a list that this operand is
 * applicable to.
 *
 * @param objects The list of objects to check.
 * @return The number of applicable objects.
 */
protected int count(List<? extends FreeColObject> objects) {
    int result = 0;
    for (FreeColObject object : objects) {
        if (appliesTo(object)) {
            result++;
        }
    }
    return result;
}

}