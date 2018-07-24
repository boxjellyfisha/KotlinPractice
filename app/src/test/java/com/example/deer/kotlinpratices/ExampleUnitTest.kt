package com.example.deer.kotlinpratices

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun twoSumTest() {
        val num = twoSum(intArrayOf(0,4,3,0), 0 )

        for (n in num)
           n.also(::print) // do the method print and can get the value n
    }
    private fun twoSum(nums: IntArray, target: Int):IntArray {
        if(nums.size == 2)
            return intArrayOf(0, 1)

        val halfT = target / 2
        val tmpNums = nums.copyOf()
        tmpNums.sort()
        val splitSmall = IntArray(tmpNums.size)
        for (i in 0..tmpNums.lastIndex) {
            if(tmpNums[i] >= halfT) {
                for(k in 0 until i) {
                    for (j in i..tmpNums.lastIndex) {
                        if (splitSmall[k] == tmpNums[j] && k != j) {
                            return ints(nums, tmpNums, k, j)
                        }
                        if (tmpNums[j] >= target)
                            break
                    }
                }
            }
            splitSmall[i] = target - tmpNums[i]
        }
        return intArrayOf(0, 0)
    }
    private fun ints(nums: IntArray, tmpNums: IntArray, k: Int, j: Int): IntArray {
        val index1 = nums.indexOf(tmpNums[k])
        val index2 = nums.indexOf(tmpNums[j])
        return when(index1 != index2) {
            true -> intArrayOf(index1, index2)
            false -> intArrayOf(index1, nums.indexOfLast { it == tmpNums[j] })
        }
    }

    @Test
    fun longestPalindromeTest() {
        val s = longestPalindrome("miycvxmqggnmmcwlmizfojwrurwhwygwfykyefxbgveixykdebenzitqnciigfjgrzzbtgeazyrbiirmejhdwcgjzwqolrturjlqpsgunuqerqjevbheblmbvgxyedxshswsokbhzapfuojgyfhctlaifrisgzqlczageirnukgnmnbwogknyyuynwsuwbumdmoqwxprykmazghcpmkdcjduepjmjdxrhvixxbfvhybjdpvwjbarmbqypsylgtzyuiqkexgvirzylydrhrmuwpmfkvqllqvekyojoacvyrzjevaupypfrdguhukzuqojolvycgpjaendfetkgtojepelhcltorueawwjpltehbbjrvznxhahtuaeuairvuklctuhcyzomwrrznrcqmovanxmiyilefybkbveesrxkmqrqkowyrimuejqtikcjfhizsmumajbqglxrvevexnleflocxoqgoyrzgqflwiknntdcykuvdcpzlakljidclhkllftxpinpvbngtexngdtntunzgahuvfnqjedcafzouopiixw")
        System.out.println(s)
    }
    private fun longestPalindrome(s: String): String {

        if(s.length == 1)
            return s[0].toString()

        for (i in  s.length downTo 2) {
            val sLength = s.length - i
            val halfSLength = sLength / 2
            find@ for(j in 0..halfSLength) {
                // L -> R
                val subLRString = s.subSequence(j, (s.length-sLength+j)).toString()
                if(subLRString == subLRString.reversed())
                    return subLRString
                if(j == Math.abs(-sLength+j))
                    break@find
                // R -> L
                val subRLString = s.subSequence(sLength-j, (s.length-j)).toString()
                if(subRLString == subRLString.reversed())
                    return subRLString
            }
        }

        return s[0].toString()
    }

    @Test
    fun strStrTest() {
        assertEquals(2, strStr("hello","ll"))
        assertEquals(-1, strStr("aaaaa","bba"))
        assertEquals(4, strStr("mississippi","issip"))
    }
    private fun strStr(haystack: String, needle: String): Int {

        if(needle.isNotEmpty()) {
            if (haystack.isNotEmpty() &&
                    haystack.length >= needle.length) {

                for (index in 0..haystack.length) {
                    if(haystack.substring(index, index + needle.length) == needle)
                        return index
                    if(index >= (haystack.length - needle.length))
                        return -1
                }

            } else
                return -1
        }
        return 0
    }

    @Test
    fun validParentTest() {
        assertFalse(isValid("({]}()})"))
        assertTrue(isValid("()[]{}"))
        assertTrue(isValid("{[]}"))
        assertFalse(isValid("([)]"))
    }
    private fun isValid(s: String): Boolean {
        val charStart = "([{"
        val charEnd = ")]}"
        val list = ArrayList<Char>()

        if(s.length % 2 == 0) {
            for (value in s) {
                if (charStart.contains(value)) {
                    list.add(value)
                } else {
                    if (list.isEmpty())
                        return false
                    if (charEnd[charStart.indexOf(list.last())] == value)
                        list.removeAt(list.lastIndex)
                    else
                        return false
                }
            }
            return list.isEmpty()
        } else
            return false
    }

    @Test
    fun romanToIntTest() {
        assertEquals(1, romanToInt("I"))
        assertEquals(4, romanToInt("IV"))
        assertEquals(9, romanToInt("IX"))
        assertEquals(58, romanToInt("LVIII"))
        assertEquals(1994, romanToInt("MCMXCIV"))

        var test = 9
        test.plus(6).plus(5)
        test.also { print(it) }
    }
    private fun romanToInt(s: String): Int {
        val rmap = hashMapOf(
                'I' to 1   ,
                'V' to 5   ,
                'X' to 10  ,
                'L' to 50  ,
                'C' to 100 ,
                'D' to 500 ,
                'M' to 1000
        )

        var sum = 0
        var sub = 0
        var com = 0
        var tocom = 0

        for((index, char) in s.withIndex()) {

            com = if(index == 0) rmap.getValue(char) else tocom

            if(index != s.lastIndex) {
                tocom = rmap.getValue(s[index+1])
                when {
                    com > tocom -> {
                        sum += com + sub
                        sub = 0
                    }
                    com == tocom -> sub += com
                    com < tocom -> sub -= com
                }
                if(index+1 == s.lastIndex) {
                    com = tocom
                    break
                }
            }
        }
        sum += com + sub
        return sum
    }

    @Test
    fun removeElementTest() {
        val intArray: IntArray = intArrayOf(2,3,3,2)
        intArray.forEach (::print)
        println()
        val newSize = removeElement(intArray, 3)
        intArray.forEach {
            if(intArray.indexOf(it) < newSize)
                print(it)
        }
    }
    private fun removeElement(nums: IntArray, `val`: Int): Int {
        var newCount = 0

        nums.forEach {
            if(it != `val`) {
                nums[newCount] = it
                newCount++
            }
        }

        return newCount
    }

    @Test
    fun removeDuplicatesTest() {
        val intArray: IntArray = intArrayOf(1,1,1,1,1,1)
        val len = removeDuplicates(intArray)
        for(index in 0 until len) {
            print(intArray[index])
        }
    }
    private fun removeDuplicates(nums: IntArray): Int {
        var visit = 0

        nums.sort()
        for((index,num) in nums.withIndex()) {

            if(index == nums.lastIndex) {
                nums[visit] = num
                visit++
                break
            }
            var left = index+1
            var right = nums.lastIndex
            var isSame = false
            while((right-left)/2.0 > 0) {
                var p = (right-left)/2 + left

                if(num < nums[p]) {
                    isSame = false
                    right = p

                } else if(num > nums[p]) {
                    isSame = false
                    left = p

                } else {
                    isSame = true
                    break
                }
            }
            if(left == right)
                isSame = num == nums[left]

            if(!isSame){
                nums[visit] = num
                visit++
            }
        }

        return visit
    }
}
