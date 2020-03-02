package ru.systempla.talos_android.navigation;

import androidx.fragment.app.Fragment;

import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.view.ui.fragment.WarehouseCreationFragment;
import ru.systempla.talos_android.mvp.view.ui.fragment.WarehouseDetailsFragment;
import ru.systempla.talos_android.mvp.view.ui.fragment.ShipmentsFragment;
import ru.systempla.talos_android.mvp.view.ui.fragment.ToolsFragment;
import ru.systempla.talos_android.mvp.view.ui.fragment.WarehouseEditFragment;
import ru.systempla.talos_android.mvp.view.ui.fragment.WarehouseFragment;
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

    public static class DetailsScreen extends SupportAppScreen {
        Product product;

        public DetailsScreen(Product product) {
            this.product = product;
        }

        @Override
        public Fragment getFragment() {
            return WarehouseDetailsFragment.newInstance(product);
        }
    }
    public static class CreationScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return WarehouseCreationFragment.newInstance();
        }
    }
    public static class EditScreen extends SupportAppScreen {
        Product product;

        public EditScreen(Product product) {
            this.product = product;
        }

        @Override
        public Fragment getFragment() {
            return WarehouseEditFragment.newInstance(product);
        }
    }
}
