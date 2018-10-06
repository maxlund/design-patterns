/**
 *  Copyright (C) 2002-2015   The FreeCol Team
 *
 *  This file is part of FreeCol.
 *
 *  FreeCol is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  FreeCol is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with FreeCol.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.freecol.common.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A factory class for creating <code>Resource</code> instances.
 * @see Resource
 */
public class ResourceFactory {

    private static final Logger logger = Logger.getLogger(ResourceFactory.class.getName());

    private static final List<Resource> resourcePrototypes = Arrays.asList(
    		new ColorResource(),
    		new FontResource(),
    		new StringResource(),
    		new FAFileResource(),
    		new SZAResource(),
    		new AudioResource(),
    		new VideoResource()
    		);
    
    // since the base case when the URI doesn't match anything is to create an ImageResource object,
    // perhaps this should be kept from without the list resourcePrototypes to better allow for extension?
    // if we by mistake added something after the ImageResource prototype in the list, it would never get called.
    private static final ImageResource imageResourcePrototype = new ImageResource();
    
    /**
     * Returns an instance of <code>Resource</code> with the
     * given <code>URI</code> as the parameter.
     *
     * @param uri The <code>URI</code> used when creating the
     *      instance.
     * @param output Where a previously created instance of <code>Resource</code>
     *      with the given <code>URI</code> is put if such an object has
     *      already been created, or a new instance if not.
     */
    public static void createResource(URI uri, ResourceMapping rmap, String key) {
        if(rmap.containsKey(key))
            return;
        
        try {
        	boolean matched = false;
        	for (Resource prototypeResource : resourcePrototypes) {
        		if (prototypeResource.matchURI(uri)) {
        			Resource concreteResource = prototypeResource.clone();
        			concreteResource.initialize(uri);
        			rmap.add(key, concreteResource);
        			matched = true;
        			break;
        		}
        	}
        	
        	// in case URI didn't match anything else, this will always fire
        	if (!matched) {
        		Resource concreteImageResource = imageResourcePrototype.clone();
        		concreteImageResource.initialize(uri);
        		rmap.add(key, concreteImageResource);
        	}
      	
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to create resource with URI: " + uri, e);
        }
    }

}
