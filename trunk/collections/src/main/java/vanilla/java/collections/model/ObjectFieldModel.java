package vanilla.java.collections.model;

/*
 * Copyright 2011 Peter Lawrey
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import java.lang.reflect.Array;

public class ObjectFieldModel<T> extends AbstractFieldModel<T> {
    private final Class<T> type;

    public ObjectFieldModel(String fieldName, int fieldNumber, Class<T> type) {
        super(fieldName, fieldNumber);
        this.type = type;
    }

    @Override
    public Object arrayOfField(int size) {
        return newArrayOfField(type, size);
    }

    @Override
    public Class storeType() {
        return Object[].class;
    }

    public static <T> T[] newArrayOfField(Class<T> type, int size) {
        return (T[]) Array.newInstance(type, size);
    }

    @Override
    public T getAllocation(Object[] arrays, int index) {
        T[] array = (T[]) arrays[fieldNumber];
        return get(array, index);
    }

    public static <T> T get(T[] array, int index) {
        return array[index];
    }

    @Override
    public void setAllocation(Object[] arrays, int index, T value) {
        T[] array = (T[]) arrays[fieldNumber];
        set(array, index, value);
    }

    public static <T> void set(T[] array, int index, T value) {
        array[index] = value;
    }

    @Override
    public Class<T> type() {
        return type;
    }

    @Override
    public BCType bcType() {
        return BCType.Reference;
    }
}