/*
 * Copyright 2010-2019 Australian Signals Directorate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.gov.asd.tac.constellation.graph.schema.visual.attribute;

import au.gov.asd.tac.constellation.graph.attribute.AbstractObjectAttributeDescription;
import au.gov.asd.tac.constellation.graph.attribute.AbstractObjectAttributeDescription;
import au.gov.asd.tac.constellation.graph.attribute.AttributeDescription;
import au.gov.asd.tac.constellation.graph.attribute.AttributeDescription;
import au.gov.asd.tac.constellation.graph.schema.visual.VertexDecorators;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author twilight_sparkle
 */
@ServiceProvider(service = AttributeDescription.class)
public class DecoratorsAttributeDescription extends AbstractObjectAttributeDescription<VertexDecorators> {

    public static final String ATTRIBUTE_NAME = "decorators";
    public static final Class<VertexDecorators> NATIVE_CLASS = VertexDecorators.class;
    private static final VertexDecorators DEFAULT_VALUE = VertexDecorators.NO_DECORATORS;

    public DecoratorsAttributeDescription() {
        super(ATTRIBUTE_NAME, NATIVE_CLASS, DEFAULT_VALUE);
    }

    /**
     * Extract a Decorators from an Object.
     *
     * @param object An Object.
     *
     * @return A Decorators.
     */
    @Override
    @SuppressWarnings("unchecked") // Casts are manually checked
    protected VertexDecorators convertFromObject(final Object object) {
        if (object == null) {
            return DEFAULT_VALUE;
        } else if (object instanceof VertexDecorators) {
            return (VertexDecorators) object;
        } else if (object instanceof String) {
            return convertFromString((String) object);
        } else {
            final String msg = String.format("Error converting Object '%s' to Decorators", object.getClass());
            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    protected VertexDecorators convertFromString(String string) {
        return VertexDecorators.valueOf(string);
    }
}
