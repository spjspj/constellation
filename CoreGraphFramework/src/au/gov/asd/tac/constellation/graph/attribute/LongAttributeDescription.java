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
package au.gov.asd.tac.constellation.graph.attribute;

import au.gov.asd.tac.constellation.graph.GraphReadMethods;
import au.gov.asd.tac.constellation.graph.NativeAttributeType;
import au.gov.asd.tac.constellation.graph.locking.ParameterReadAccess;
import au.gov.asd.tac.constellation.graph.locking.ParameterWriteAccess;
import java.util.Arrays;
import org.openide.util.lookup.ServiceProvider;

/**
 * This describes a type of attribute whose values are primitive longs.
 * <p>
 *
 * When setting these attribute values from numeric types, the values are
 * implicitly or explicitly cast as necessary. The
 * {@link #setString setString()} method will utilise {@link Long#parseLong}.
 * The {@link #setBoolean setBoolean()} method will yield 1 for true and 0 for
 * false.
 * <p>
 * When retrieving these attribute values as numeric types the values are
 * implicitly or explicitly cast as necessary. The
 * {@link #getString getString()} method will utilise {@link String#valueOf}.
 * The {@link #getBoolean getBoolean()} method will return false for 0, and true
 * for any other value.
 *
 * @author sirius
 */
@ServiceProvider(service = AttributeDescription.class)
public final class LongAttributeDescription extends AbstractAttributeDescription {

    private static final long DEFAULT_VALUE = 0L;
    private long[] data = new long[0];
    private long defaultValue = DEFAULT_VALUE;
    public static final String ATTRIBUTE_NAME = "long";

    @Override
    public String getName() {
        return ATTRIBUTE_NAME;
    }

    @Override
    public Class<?> getNativeClass() {
        return long.class;
    }

    @Override
    public NativeAttributeType getNativeType() {
        return NativeAttributeType.LONG;
    }

    @Override
    public Object getDefault() {
        return defaultValue;
    }

    @Override
    public void setDefault(final Object value) {
        defaultValue = setObject(value);
    }

    @Override
    public int getCapacity() {
        return data.length;
    }

    @Override
    public void setCapacity(final int capacity) {
        final int len = data.length;
        data = Arrays.copyOf(data, capacity);
        if (capacity > len) {
            Arrays.fill(data, len, capacity, defaultValue);
        }
    }

    private static long setObject(final Object value) {
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            return Long.parseLong((String) value);
        } else if (value instanceof Boolean) {
            return ((Boolean) value) ? 1L : 0L;
        } else if (value instanceof Character) {
            return (char) value;
        } else {
            return DEFAULT_VALUE;
        }
    }

    @Override
    public byte getByte(final int id) {
        return (byte) data[id];
    }

    @Override
    public void setByte(final int id, final byte value) {
        data[id] = value;
    }

    @Override
    public short getShort(final int id) {
        return (short) data[id];
    }

    @Override
    public void setShort(final int id, final short value) {
        data[id] = value;
    }

    @Override
    public int getInt(final int id) {
        return (int) data[id];
    }

    @Override
    public void setInt(final int id, final int value) {
        data[id] = value;
    }

    @Override
    public long getLong(final int id) {
        return data[id];
    }

    @Override
    public void setLong(final int id, final long value) {
        data[id] = value;
    }

    @Override
    public float getFloat(final int id) {
        return data[id];
    }

    @Override
    public void setFloat(final int id, final float value) {
        data[id] = (long) value;
    }

    @Override
    public double getDouble(final int id) {
        return data[id];
    }

    @Override
    public void setDouble(final int id, final double value) {
        data[id] = (long) value;
    }

    @Override
    public boolean getBoolean(final int id) {
        return data[id] != 0;
    }

    @Override
    public void setBoolean(final int id, final boolean value) {
        data[id] = value ? 1L : 0L;
    }

    @Override
    public char getChar(final int id) {
        return (char) data[id];
    }

    @Override
    public void setChar(final int id, final char value) {
        data[id] = value;
    }

    @Override
    public Object getObject(final int id) {
        return data[id];
    }

    @Override
    public void setObject(final int id, final Object value) {
        data[id] = setObject(value);
    }

    @Override
    public String getString(final int id) {
        return String.valueOf(data[id]);
    }

    @Override
    public void setString(final int id, final String value) {
        if (value == null || value.isEmpty()) {
            data[id] = DEFAULT_VALUE;
        } else {
            try {
                data[id] = Long.parseLong(value);
            } catch (final NumberFormatException ex) {
                data[id] = DEFAULT_VALUE;
            }
        }
    }

    @Override
    public boolean isClear(final int id) {
        return data[id] == defaultValue;
    }

    @Override
    public void clear(final int id) {
        data[id] = defaultValue;
    }

    @Override
    public AttributeDescription copy(GraphReadMethods graph) {
        final LongAttributeDescription attribute = new LongAttributeDescription();
        attribute.data = Arrays.copyOf(data, data.length);
        attribute.defaultValue = this.defaultValue;
        attribute.graph = graph;
        return attribute;
    }

    @Override
    public int hashCode(final int id) {
        return (int) data[id];
    }

    @Override
    public boolean equals(final int id1, final int id2) {
        return data[id1] == data[id2];
    }

    @Override
    public void save(final int id, final ParameterWriteAccess access) {
        access.setLong(data[id]);
    }

    @Override
    public int ordering() {
        return 4;
    }

    @Override
    public void restore(final int id, final ParameterReadAccess access) {
        data[id] = access.getUndoLong();
    }

    @Override
    public Object saveData() {
        return Arrays.copyOf(data, data.length);
    }

    @Override
    public void restoreData(final Object savedData) {
        final long[] sd = (long[]) savedData;
        data = Arrays.copyOf(sd, sd.length);
    }
}
