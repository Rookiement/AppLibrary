package com.base.library.common;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.base.library.common.delegate.FragmentFillingType;

/**
 * @author reber
 */
public class AppNavigateUtil {

    public static final String ARG_ACTIVITY_BUNDLE = "arg_activity_bundle";

    public final void startFragment(@NonNull FragmentManager fragmentManager,
                                    @IdRes int containerId,
                                    @NonNull Fragment showFragment,
                                    Fragment hideFragment,
                                    @FragmentFillingType int fillingType,
                                    String fragmentTag,
                                    boolean addToBackStack) {
        switch (fillingType) {
            case FragmentFillingType.ADD:
                fillingFragmentWithAdd(fragmentManager, containerId, showFragment, hideFragment, fragmentTag);
                break;
            case FragmentFillingType.REPLACE:
                fillingFragmentWithReplace(fragmentManager, containerId, showFragment, fragmentTag, addToBackStack);
                break;
        }
    }

    private void fillingFragmentWithAdd(@NonNull FragmentManager fragmentManager,
                                        @IdRes int containerId,
                                        @NonNull Fragment showFragment,
                                        Fragment hideFragment,
                                        String fragmentTag) {
        FragmentTransaction transaction = fragmentManager
                .beginTransaction()
                .add(containerId, showFragment, fragmentTag);
        if (hideFragment != null) {
            transaction.hide(hideFragment);
        }
        transaction.commitAllowingStateLoss();
    }

    private void fillingFragmentWithReplace(@NonNull FragmentManager fragmentManager,
                                            @IdRes int containerId,
                                            @NonNull Fragment showFragment,
                                            String fragmentTag,
                                            boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager
                .beginTransaction()
                .replace(containerId, showFragment, fragmentTag);
        if (addToBackStack) {
            transaction.addToBackStack(fragmentTag);
        }
        transaction.commitAllowingStateLoss();
    }

    public void startActivity(@NonNull AppCompatActivity activity, @NonNull Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtra(ARG_ACTIVITY_BUNDLE, bundle);
        }
        activity.startActivity(intent);
    }

    public void startActivityResult(@NonNull AppCompatActivity activity, @NonNull Class<?> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtra(ARG_ACTIVITY_BUNDLE, bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public void startActivityFromFragment(@NonNull Fragment fragment, @NonNull Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(fragment.requireContext(), clazz);
        if (bundle != null) {
            intent.putExtra(ARG_ACTIVITY_BUNDLE, bundle);
        }
        fragment.startActivity(intent);
    }

    public void startActivityResultFromFragment(@NonNull Fragment fragment, @NonNull Class<?> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(fragment.requireContext(), clazz);
        if (bundle != null) {
            intent.putExtra(ARG_ACTIVITY_BUNDLE, bundle);
        }
        fragment.startActivityForResult(intent, requestCode);
    }
}
