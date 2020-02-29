package ru.systempla.talos_android.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.view.ShipmentsView;
import ru.systempla.talos_android.mvp.view.ui.adapter.ShipmentsAdapter;

@InjectViewState
public class ShipmentsPresenter extends MvpPresenter<ShipmentsView> {
    private ListShipmentsPresenter listShipmentsPresenter;


    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;

    public ShipmentsPresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;

        listShipmentsPresenter = new ListShipmentsPresenter();

    }

    public ListShipmentsPresenter getListShipmentsPresenter() {
        return listShipmentsPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public class ListShipmentsPresenter implements ShipmentsListPresenter {
        //это заглушка
        public List<Shipment> shipments = build();

        //это заглушка тоже
        public class Shipment {
            public String customer;
            public String date;
            public String type;
            public Boolean status;

            public Shipment(String customer, String date, String type, Boolean status) {
                this.customer = customer;
                this.date = date;
                this.type = type;
                this.status = status;
            }
        }

        private List<Shipment> build() {
            String[] customers = {"ООО Ромашка", "ООО Василек", "ООО Снежинка", "ИП Денис с района"};
            String[] dates = {"04.02.20", "03.02.20", "02.02.20", "01.02.20"};
            String[] types = {"Отгрузка", "Возврат", "Отгрузка", "Поступление нового товара"};
            Boolean[] statuses = {true, false, true, false};
            List<Shipment> abc = new ArrayList<>(customers.length);
            for (int i = 0; i < customers.length; i++) {
                abc.add(new Shipment(customers[i], dates[i], types[i], statuses[i]));
            }
            return abc;
        }

        //конец заглушки
        @Override
        public void bindViewHolder(ShipmentsAdapter.ShipmentsViewHolder holder, int position) {
                Shipment shipment = shipments.get(position);
                holder.setCustomer(shipment.customer);
                holder.setDate(shipment.date);
                holder.setType(shipment.type);
                holder.setStatus(shipment.status);
        }

        @Override
        public int getCount() {
            return shipments.size();
        }

        @Override
        public void showMore() {

        }
    }

    public void setTitle() {
        getViewState().setToolbarTitle("Отгрузки");
    }
}
