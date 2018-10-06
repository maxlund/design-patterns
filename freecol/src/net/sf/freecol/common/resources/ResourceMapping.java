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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 * Represents a mapping between identifiers and resources.
 *
 * @see Resource
 */
public final class ResourceMapping {

    private static final Logger logger = Logger.getLogger(ResourceMapping.class.getName());

    /** Mappings between an object identifier and a resource. */    
    private final HashMap<String, Resource> allResources;

    private final Set<String> resourceStrings;  // stuff that resource identifiers starts with
    
    /**
     * Creates a new empty <code>ResourceMapping</code>.
     */
    public ResourceMapping() {
    	allResources = new HashMap<>();
    	// if we don't want to break the logging, we need to have something like this i think
    	resourceStrings = new HashSet<>(Arrays.asList(
    			"color.", 
    			"font.", 
    			"animatedfont.",
    			"animation.", 
    			"sound.", 
    			"video.", 
    			"image."
    			));
    }


    // TODO: Consider cutting off the type prefixes after validation,
    //       to reduce processing time and memory use for strings.

    /**
     * Adds a mapping between the given object identifier and a
     * <code>Resource</code>.
     *
     * @param key The identifier for the given resource in the mapping.
     * @param value The <code>Resource</code> identified by the
     *     identifier in the mapping,.
     * @return true on success
     */
    
    public boolean add(String key, Resource r) {
    	boolean hasMalformedKey = true;
    	for (String s : resourceStrings) {
    		if (key.startsWith(s)) {
    			hasMalformedKey = false;
    			break;
    		}
    	}
    	
    	if (!hasMalformedKey) {
    		allResources.put(key, r);
    	    return true;
    	}
    	logger.warning("Rejecting malformed resource key: " + key);
    	return false;
    }

    /**
     * Create another mapping for a Resource under a different key.
     *
     * @param key The key to find the existing Resource.
     * @param keyNew The new key for the duplicate.
     * @return true on success
     */
    public boolean duplicateResource(String key, String keyNew) {
    	Resource r = allResources.get(key);
    	if(r != null) {
    		return add(keyNew, r);
    	}
        return false;
    }

    /**
     * Adds all mappings from the given <code>ResourceMapping</code> to
     * this object.
     *
     * @param rc The <code>ResourceMapping</code>.
     */
    public void addAll(ResourceMapping rc) {
        if (rc != null) {
        	allResources.putAll(rc.allResources);
        }
    }

    /**
     * Returns all the mappings between IDs and <code>Resource</code>s
     * that are kept by this object.
     *
     * @return An unmodifiable <code>Map</code>.
     */
    public Map<String, Resource> getResources() {
        HashMap<String, Resource> result = new HashMap<>();
        result.putAll(allResources);
        return result;
    }

    public Map<String, ImageResource> getImageResources() {
    	HashMap<String, ImageResource> imageResources = new HashMap<String, ImageResource>();
    	for (String key : allResources.keySet()) {
    		if (key.startsWith("image."))
    			imageResources.put(key, (ImageResource) allResources.get(key));
    	}
        return new HashMap<>(imageResources);
    }

    public boolean containsKey(String key) {
    	return allResources.containsKey(key);
    }

    public boolean containsColorKey(String key) {
    	return containsKey(key);
    }

    public boolean containsImageKey(String key) {
    	return containsKey(key);
    }

    /**
     * Gets the <code>Resource</code> by identifier.
     *
     * @param key The resource identifier.
     * @return The <code>Resource</code>.
     */
    public Resource getResource(String key) {
        return allResources.get(key);
    }

    /**
     * Get the image keys in this mapping with a given prefix as a list.
     *
     * @param prefix The prefix to check for.
     * @return A list of keys.
     */
    public List<String> getImageKeys(String prefix) {
        return allResources.keySet().stream()
            .filter(k -> k.startsWith(prefix)).collect(Collectors.toList());
    }

    /**
     * Get the image keys in this mapping with a given prefix as a set.
     *
     * @param prefix The prefix to check for.
     * @return The set of keys.
     */
    public Set<String> getImageKeySet(String prefix) {
        return allResources.keySet().stream()
            .filter(k -> k.startsWith(prefix)).collect(Collectors.toSet());
    }

    /**
     * Get the image keys in this mapping with a given prefix and
     * suffix as a list.
     *
     * @param prefix The prefix to check for.
     * @param suffix The suffix to check for.
     * @return A list of keys.
     */
    public List<String> getImageKeys(String prefix, String suffix) {
        return allResources.keySet().stream()
            .filter(k -> k.startsWith(prefix) && k.endsWith(suffix))
            .collect(Collectors.toList());
    }
}
