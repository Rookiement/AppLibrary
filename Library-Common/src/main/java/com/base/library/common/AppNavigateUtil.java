package com.base.library.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author reber
 */
public class AppNavigateUtil {

    private static final String ARG_ACTIVITY_BUNDLE = "arg_activity_bundle";

    public static void addFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment showFragment, @IdRes int containerId) {
        addFragment(fragmentManager, showFragment, containerId, null, null);
    }

    public static void addFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment showFragment, @IdRes int containerId,
                                   String fragmentTag) {
        addFragment(fragmentManager, showFragment, containerId, null, fragmentTag);
    }

    public static void addFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment showFragment, @IdRes int containerId,
                                   Fragment hideFragment, String fragmentTag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, showFragment, fragmentTag);
        if (hideFragment != null) {
            transaction.hide(hideFragment);
        }
        transaction.commitAllowingStateLoss();
    }

    public static void replaceFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment showFragment, @IdRes int containerId) {
        replaceFragment(fragmentManager, showFragment, containerId, null, false);
    }

    public static void replaceFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment showFragment, @IdRes int containerId,
                                       String fragmentTag) {
        replaceFragment(fragmentManager, showFragment, containerId, fragmentTag, false);
    }

    public static void replaceFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment showFragment, @IdRes int containerId,
                                       String fragmentTag, boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, showFragment, fragmentTag);
        if (!TextUtils.isEmpty(fragmentTag) && addToBackStack) {
            transaction.addToBackStack(fragmentTag);
        }
        transaction.commitAllowingStateLoss();
    }

    public static void startActivity(@NonNull Activity activity, @NonNull Class<? extends Activity> clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtra(ARG_ACTIVITY_BUNDLE, bundle);
        }
        activity.startActivity(intent);
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Class<? extends Activity> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtra(ARG_ACTIVITY_BUNDLE, bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivity(@NonNull Fragment fragment, @NonNull Class<? extends Activity> clazz, Bundle bundle) {
        Intent intent = new Intent(fragment.requireContext(), clazz);
        if (bundle != null) {
            intent.putExtra(ARG_ACTIVITY_BUNDLE, bundle);
        }
        fragment.startActivity(intent);
    }

    public static void startActivityForResult(@NonNull Fragment fragment, @NonNull Class<? extends Activity> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(fragment.requireContext(), clazz);
        if (bundle != null) {
            intent.putExtra(ARG_ACTIVITY_BUNDLE, bundle);
        }
        fragment.startActivityForResult(intent, requestCode);
    }

    public static Bundle getArgumentsParams(@NonNull Fragment fragment) {
        return fragment.getArguments();
    }

    public static Bundle getBundleParams(@NonNull Activity activity) {
        return activity.getIntent().getBundleExtra(ARG_ACTIVITY_BUNDLE);
    }
}
