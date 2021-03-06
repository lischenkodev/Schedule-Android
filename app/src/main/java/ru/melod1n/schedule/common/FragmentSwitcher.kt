package ru.melod1n.schedule.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

object FragmentSwitcher {

    fun getCurrentFragment(fragmentManager: FragmentManager): Fragment? {
        val fragments = fragmentManager.fragments

        if (fragments.isEmpty()) throw RuntimeException("FragmentManager's fragments is empty")

        for (fragment in fragments) {
            if (fragment.isVisible) {
                return fragment
            }
        }

        return null
    }

    fun addFragments(
            fragmentManager: FragmentManager,
            containerId: Int,
            fragments: Collection<Fragment>
    ) {
        val transaction = fragmentManager.beginTransaction()

        for (fragment in fragments) {
            transaction.add(containerId, fragment, fragment.javaClass.simpleName)
        }

        transaction.commitNow()
    }

    fun showFragment(fragmentManager: FragmentManager, tag: String) {
        showFragment(fragmentManager, tag, false)
    }

    fun showFragment(fragmentManager: FragmentManager, tag: String, hideOthers: Boolean) {
        val fragments = fragmentManager.fragments

        if (fragments.isEmpty()) throw RuntimeException("FragmentManager's fragments is empty")

        var fragmentToShow: Fragment? = null

        for (fragment in fragments) {
            if (fragment.tag != null && fragment.tag == tag) {
                fragmentToShow = fragment
                break
            }
        }

        if (fragmentToShow == null) throw NullPointerException("Fragment with tag \"$tag\" not found.")

        val transaction = fragmentManager.beginTransaction()

        transaction.show(fragmentToShow)

        if (hideOthers) {
            for (fragment in fragments) {
                if (fragment.tag != null && fragment.tag == tag) continue
                transaction.hide(fragment)
            }
        }

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commitNow()
    }

    fun clearFragments(fragmentManager: FragmentManager) {
        val fragments = fragmentManager.fragments

        if (fragments.isEmpty()) throw RuntimeException("FragmentManager's fragments is empty")

        val transaction = fragmentManager.beginTransaction()

        for (fragment in fragments) {
            transaction.remove(fragment)
        }

        transaction.commitNow()
    }

    fun hideFragments(fragmentManager: FragmentManager) {
        val fragments = fragmentManager.fragments

        if (fragments.isEmpty()) throw RuntimeException("FragmentManager's fragments is empty")

        val transaction = fragmentManager.beginTransaction()

        for (fragment in fragments) {
            transaction.hide(fragment)
        }

        transaction.commitNow()
    }
}