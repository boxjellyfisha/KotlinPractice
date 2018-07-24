package com.example.deer.kotlinpratices

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

class Part2UnitTest {

    @Test
    fun myPowTest() {
        myPow(2.0, 3).also(::println)
    }
    private fun myPow(x: Double, n: Int): Double {
//        -100.0 < x < 100.0
//        n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]

        if(n > 0){

        }
        else {

        }

        return 0.0
    }

    @Test
    fun searchInsertTest() {
        println(searchInsert(intArrayOf(1,3,5), 0))
        println(searchInsert(intArrayOf(1,2,3,4,5), 6))
        println(searchInsert(intArrayOf(1,3,6), 2))
        println(searchInsert(intArrayOf(1,3,6), 4))
        println(searchInsert(intArrayOf(2,3,5,6,9), 7))
        println(searchInsert(intArrayOf(1), 5))
    }
    private fun searchInsert(nums: IntArray, target: Int): Int {
        if(nums.isNotEmpty()) {
            val size = nums.size
            val start = 0
            return binarySearch(start, size, target, nums)
        }
        return 0
    }
    private fun binarySearch(start: Int, size: Int, target: Int, nums: IntArray): Int{
        var index = start.plus(size.div(2)).plus(size.rem(2))

        return if(size <= 1 || index > nums.lastIndex) {
            when {
                target > nums[start] -> {
                    index
                }
                else -> start
            }
        } else {
             when {
                target > nums[index] -> {
                    binarySearch(index, nums.size-index, target, nums)
                }
                target < nums[index] -> {
                    binarySearch(start, index-start, target, nums)
                }
                else -> index
            }
        }
    }

    @Test
    fun transposeTest() {
        transpose(arrayOf(
                intArrayOf(1),
                intArrayOf(1),
                intArrayOf(1))).forEach {
            for(item in it)
                print(item)
            println()
        }
    }
    private fun transpose(A: Array<IntArray>): Array<IntArray> {
        val transA: Array<IntArray>

        //Square matrix :just swap inplace
        if(A.size == A[0].size) {
            return transposeSquareMatrix(A.lastIndex, A)

        } else {
            transA = Array(A[0].size, { _-> IntArray(A.size) })
            transposeNonSquareMatrix(A, transA)
        }

        return transA
    }
    private fun transposeNonSquareMatrix(A: Array<IntArray>, transA: Array<IntArray>): Array<IntArray> {
        val smallSize: Int = if(A.size - A[0].size > 0) A[0].size else A.size
        for (i in 0..A.lastIndex) {
            val dim= A[0].lastIndex
                for (j in 0..dim) {
                    if(i == j)
                        transA[i][j] =  A[i][j]
                    else {
                        if(i < smallSize && j < smallSize)
                            transA[i][j] = A[j][i]
                        transA[j][i] = A[i][j]
                    }
                }
        }
        return A
    }
    private fun transposeSquareMatrix(lastIndex:Int, A: Array<IntArray>): Array<IntArray> {
        for (i in 1..lastIndex) {
            for (j in 0..(i - 1)) {
                matrixSwap(i, j, A)
            }
        }
        return A
    }
    private fun matrixSwap(i:Int, j:Int, A: Array<IntArray>) {
        val tmp = A[i][j]
        A[i][j] = A[j][i]
        A[j][i] = tmp
    }

    @Test
    fun isHappyNumTest() {
        assertTrue(isHappy(1111111))
        assertTrue(isHappy(13))
        assertTrue(isHappy(19))
        assertTrue(isHappy(23))
        assertFalse(isHappy(119))
    }
    private fun isHappy(n: Int): Boolean {
        var sum = squareSum(n)
        return sum == 1 || sum == 7
    }
    private fun squareSum(n: Int): Int {
        val digits = n.toString()
        var sum = 0
        for (char in digits) {
            val tmp = char.toString().toInt()
            sum += tmp*tmp
        }
        return if(sum > 9) squareSum(sum) else sum
    }

    @Test
    fun findLengthTest() {
        findLength(intArrayOf(1,0,0,0,0),
                intArrayOf(0,0,0,0,1)).also(::println)
    }
    private fun findLength(A: IntArray, B: IntArray): Int {
        val cache = Array(A.size+1, {IntArray(B.size+1, {0})})
        var max = 0

        for((i, a) in A.withIndex()) {
            for((j, b) in B.withIndex()) {

                if(a == b) {
                    cache[i+1][j+1] = cache[i][j]+1
                    if(cache[i+1][j+1] > max)
                        max = cache[i+1][j+1]
                }
            }
        }
        return max
    }

    @Test
    fun intersectionTest() {
        intersection2(intArrayOf(1,1,1,12,1), intArrayOf(1,2)).also {
            for(i in it)
                println(i) }
    }
    private fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val resultArray: MutableMap<Int, Int> = hashMapOf()

        for(i in 0..nums2.lastIndex) {
            if(!resultArray.contains(nums2[i])) {
                for (j in 0 until nums1.size) {
                    if (nums1[j] == nums2[i]) {
                        resultArray.put(nums2[i], i)
                        break
                    }
                }
            }
        }

        return resultArray.keys.toIntArray()
    }
    private fun intersection2(nums1: IntArray, nums2: IntArray): IntArray {

        var index = 0
        nums2.sort()

        for(i in 0..nums2.lastIndex) {
            //TODO optimization ˊ～ˋ
        }

        return nums2.copyOfRange(0, index)
    }


}