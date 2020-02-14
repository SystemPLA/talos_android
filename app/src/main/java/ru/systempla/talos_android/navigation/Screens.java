package ru.systempla.talos_android.navigation;

import androidx.fragment.app.Fragment;

import ru.systempla.talos_android.mvp.view.ui.ShipmentsFragment;
import ru.systempla.talos_android.mvp.view.ui.ToolsFragment;
import ru.systempla.talos_android.mvp.view.ui.WarehouseFragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static class WarehouseScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return WarehouseFragment.newInstance();
        }
    }

    public static class ShipmentsScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return ShipmentsFragment.newInstance();
        }
    }

    public static class ToolsScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return ToolsFragment.newInstance();
        }
    }
}
