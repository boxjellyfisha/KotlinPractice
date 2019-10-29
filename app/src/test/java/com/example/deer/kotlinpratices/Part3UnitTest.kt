package com.example.deer.kotlinpratices

import org.junit.Test

class Part3UnitTest {

	@Test
	fun grayCodeTest() {
		val result = grayCode(2)
		println(result)
		assert(result == listOf(0,1,3,2))
	}

	private fun grayCode(n: Int): List<Int> {
		val finalNum = if(n > 0) Math.pow(2.0, n.toDouble()) - 1
							   else 0.0

		val list = MutableList<Int>((finalNum+1).toInt()) {0}

		if(n > 0) {
			for(i in 1..n) {
				if (i == 1) {
					list.add(1,1)

				} else {
					var possible = Math.pow(2.0, i.toDouble())
					for (j in 0 until possible.toInt()) {

					}
				}
			}
		}

		return list
	}
}