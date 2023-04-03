package org.dromara.hutool.core.lang.func;

import org.dromara.hutool.core.collection.SetUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PredicateUtilTest {

	@Test
	public void notContainsTest() {
		final Set<String> sets = SetUtil.of("1", "2", "3");
		final List<String> collect = Stream.of("3", "4", "5")
				.filter(PredicateUtil.negate(sets::contains))
				.collect(Collectors.toList());

		Assertions.assertEquals(2, collect.size());
		Assertions.assertEquals("4", collect.get(0));
		Assertions.assertEquals("5", collect.get(1));
	}

	@Test
	public void andTest() {
		boolean condition = Stream.of(1, 3, 5)
				.allMatch(
						PredicateUtil.and(
								Objects::nonNull,
								i -> i < 10,
								i -> i % 2 == 1
						)
				);
		Assertions.assertTrue(condition);
	}

	@Test
	public void orTest() {
		boolean condition = Stream.of(1, 3, 5)
				.anyMatch(
						PredicateUtil.or(
								Objects::isNull,
								i -> i > 10,
								i -> i % 2 == 0
						)
				);
		Assertions.assertFalse(condition);
	}


}