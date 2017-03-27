package com.example.davidc.uiwrapper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

class FragmentManagerHelper {

    static boolean hasFragment(final FragmentManager fm, final String tag) {
        return fragment(fm, tag) != null;
    }

    static void addFragment(final FragmentManager fm, final Fragment fragment, final String tag) {
        fm.beginTransaction().add(fragment, tag).commit();
    }

    static Fragment fragment(final FragmentManager fm, final String tag) {
        return fm.findFragmentByTag(tag);
    }
}
