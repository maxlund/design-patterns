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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A factory class for creating <code>Resource</code> instances.
 * @see Resource
 */
public class ResourceFactory {

    private static final Logger logger = Logger.getLogger(ResourceFactory.class.getName());

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
        	
            if ("urn".equals(uri.getScheme())) {
                if (uri.getSchemeSpecificPart().startsWith(ColorResource.SCHEME)) {
                    ColorResource cr = new ColorResource(uri);
                    rmap.add(key, cr);
                    
                } else if (uri.getSchemeSpecificPart().startsWith(FontResource.SCHEME)) {
                    FontResource fr = new FontResource(uri);
                    rmap.add(key, fr);
                }
            } else if (uri.getPath().endsWith("\"")
                    && uri.getPath().lastIndexOf('"',
                            uri.getPath().length()-1) >= 0) {
                StringResource sr = new StringResource(uri);
                rmap.add(key, sr);
            } else if (uri.getPath().endsWith(".faf")) {
                FAFileResource far = new FAFileResource(uri);
                rmap.add(key, far);
            } else if (uri.getPath().endsWith(".sza")) {
                SZAResource szr = new SZAResource(uri);
                rmap.add(key, szr);
            } else if (uri.getPath().endsWith(".ttf")) {
                FontResource fr = new FontResource(uri);
                rmap.add(key, fr);
            } else if (uri.getPath().endsWith(".wav")) {
                AudioResource ar = new AudioResource(uri);
                rmap.add(key, ar);
            } else if (uri.getPath().endsWith(".ogg")) {
                if (uri.getPath().endsWith(".video.ogg")) {
                    VideoResource vr = new VideoResource(uri);
                    rmap.add(key, vr);
                } else {
                    AudioResource ar = new AudioResource(uri);
                    rmap.add(key, ar);
                }
            } else {
                ImageResource ir = new ImageResource(uri);
                rmap.add(key, ir);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to create resource with URI: " + uri, e);
        }
    }

}
