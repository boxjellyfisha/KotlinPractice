package com.example.deer.kotlinpratices

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class TreeUnitTest {

    /* Definition for a binary tree node. */
    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }


    @Test
    fun createTreeTest() {
        val node = createTree(arrayOf(1,null,2,3,3))
        printNodePreOrder(node)
        print(" end\n")
        printNodeInOrder(node)
        print(" end\n")
        printNodeLevelOrder(node)
        print(" end")
    }
    /* array with level-order BFS */
    private fun createTree(array: Array<Int?>): TreeNode? {
        var root: TreeNode? = null
        var tmp: TreeNode? = null
        var visitQueue : LinkedList<TreeNode?> = LinkedList()

        array.forEachIndexed { index, i ->
            if(index == 0) {
                root = createNode(i)
                visitQueue.push(root)
                tmp = visitQueue.pollFirst()

            } else if (index % 2 == 1) { // left leaf
                tmp!!.left = createNode(i)
                if(tmp!!.left != null)
                    visitQueue.offer(tmp!!.left)

            } else if (index % 2 == 0) { // right leaf
                tmp!!.right = createNode(i)
                if(tmp!!.right != null)
                    visitQueue.offer(tmp!!.right)
                tmp = visitQueue.pollFirst()
            }
        }
        return root
    }
    private fun createNode(i: Int?): TreeNode? {
        return if (i != null) {
            TreeNode(i)
        } else {
            null
        }
    }

    @Test
    fun printTreeTest() {
        val a = TreeNode(1)
        val a1 = TreeNode(2)
        val a2 = TreeNode(3)
        val a3 = TreeNode(4)
        val a4 = TreeNode(5)

        a.left = a1
        a.right = a2
        a1.left = a3
        a3.left = a4

        printNodeLevelOrder(a)
    }
    private fun printNodePreOrder(p: TreeNode?) {
        if(p == null) return
        println(p.`val`)
        printNodePreOrder(p.left)
        printNodePreOrder(p.right)
    }
    private fun printNodeInOrder(p: TreeNode?) {
        if(p == null) return
        printNodeInOrder(p.left)
        println(p.`val`)
        printNodeInOrder(p.right)
    }
    private fun printNodeLevelOrder(p: TreeNode?) {
        var visitQueue : LinkedList<TreeNode?> = LinkedList()
        visitQueue.push(p)

        while (!visitQueue.isEmpty()) {
            val v: TreeNode? = visitQueue.pollFirst()
            if(v == null) {
                println("null")
                continue
            }
            println(v!!.`val`)
            visitQueue.offer(v!!.left)
            visitQueue.offer(v!!.right)
        }
    }

    @Test
    fun sameTreeTest() {
        assertTrue(isSameTree(createTree(arrayOf(1,2,3)), createTree(arrayOf(1,2,3))))
        assertFalse(isSameTree(createTree(arrayOf(1,2)), createTree(arrayOf(1,null,2))))
        assertFalse(isSameTree(createTree(arrayOf(12, null, -60)), createTree(arrayOf(12, null, 72))))
        assertFalse(isSameTree(createTree(arrayOf(12, -72, 2)), createTree(arrayOf(12, -72))))
        assertFalse(isSameTree(createTree(arrayOf(12, -72)), createTree(arrayOf(12, -60))))

    }
    /* Try BFS breadth-first search */
    private fun isSameTreeA(p: TreeNode?, q: TreeNode?): Boolean {
        val visitpQueue : ArrayList<TreeNode?> = ArrayList()
        val visitqQueue : ArrayList<TreeNode?> = ArrayList()

        visitpQueue.add(p)
        visitqQueue.add(q)

        while (visitpQueue.isNotEmpty() && visitqQueue.isNotEmpty()) {
            val vp: TreeNode? = visitpQueue.first()
            val vq: TreeNode? = visitqQueue.first()
            visitpQueue.remove(vp)
            visitqQueue.remove(vq)

            if(vp != null && vq != null) {
                if(vp.`val` != vq.`val`)
                    return false
                else {
                    if(((vp.left == null && vq.right == null) &&
                         (vq.left == null && vq.right == null))) {
                        continue

                    } else {
                        visitpQueue.add(vp.left)
                        visitpQueue.add(vp.right)
                        visitqQueue.add(vq.left)
                        visitqQueue.add(vq.right)
                    }
                }
            }
            if((vp == null && vq != null) || (vp != null && vq == null)) {
                return false
            }
        }
        if(visitpQueue.lastOrNull() != null || visitqQueue.lastOrNull() != null) {
            return false
        }

        return true
    }

    private fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return if(p == null && q == null)
            true
        else if (p == null || q == null)
            false
        else if (p.`val` !=  q.`val`)
            false
        else {
            isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }
    }
}
