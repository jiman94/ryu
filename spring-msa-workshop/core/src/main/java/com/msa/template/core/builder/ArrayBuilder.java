package com.msa.template.core.builder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ArrayBuilder<E> {

	private final ArrayList<E[]> values = new ArrayList<>();
	private int size = 0;

	public ArrayBuilder<E> add(final E[] target) {
		if (target == null) {
			return this;
		}

		values.add(target);
		this.size += target.length;
		return this;
	}

	@SuppressWarnings("unchecked")
	public E[] build() {
		Class<?> clazz = this.getClazz();
		if (clazz == null) {
			return null;
		}

		E[] value = (E[]) Array.newInstance(clazz, this.size);
		int pos = 0;
		for (E[] array : values) {
			System.arraycopy(array, 0, value, pos, array.length);
			pos += array.length;
		}
		return value;
	}

	private Class<?> getClazz() {
		return values.stream()
			.flatMap(Arrays::stream)
			.filter(Objects::nonNull)
			.findFirst()
			.map(Object::getClass)
			.orElse(null);
	}
}
