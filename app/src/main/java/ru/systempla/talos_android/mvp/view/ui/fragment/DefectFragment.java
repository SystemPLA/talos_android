package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.presenter.ArrivingAndRefundPresenter;
import ru.systempla.talos_android.mvp.view.ArrivingAndRefundView;
import ru.systempla.talos_android.mvp.view.ui.DatePicker.MyDatePicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefectFragment extends MvpAppCompatFragment implements ArrivingAndRefundView {

    @InjectPresenter
    ArrivingAndRefundPresenter presenter;

    private Unbinder unbinder;

    private ArrayList<TextInputEditText> sendFields;

    private static final String type = "брак";

    public DefectFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.defect_edit_date)
    TextInputEditText editDate;

    @BindView(R.id.defect_edit_stairs_frame)
    TextInputEditText editStairsFrame;
    @BindView(R.id.defect_edit_pass_frame)
    TextInputEditText editPassFrame;
    @BindView(R.id.defect_edit_diagonal_connection)
    TextInputEditText editDiagonalConnection;
    @BindView(R.id.defect_edit_horizontal_connection)
    TextInputEditText editHorizontalConnection;
    @BindView(R.id.defect_edit_crossbar)
    TextInputEditText editCrossbar;
    @BindView(R.id.defect_edit_deck)
    TextInputEditText editDeck;
    @BindView(R.id.defect_edit_supports)
    TextInputEditText editSupports;

    @OnClick(R.id.button_send_defect)
    void onClickSend() {
        if (!checkFields(sendFields)) return;

        presenter.clickSendBad(getDateText(), "Брак", getStairsFrameText(), getPassFrameText(), getDiagonalConnectionText(), getHorizontalConnectionText(),
                getCrossbarText(), getDeckText(), getSupportsText(), type);
    }

    @OnClick(R.id.defect_edit_date)
    void onClickDate() {
        new MyDatePicker(getContext(), editDate);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_defect, container, false);
        unbinder = ButterKnife.bind(this, root);
        makeSendFieldsList();
        return root;
    }

    @Override
    public void showSuccess() {
        Toast.makeText(getContext(), "Успешно!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFailure() {
        Toast.makeText(getContext(), "Ошибка, попробуйте еще раз!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //TODO Добавить этот и следующий методы в отдельный класс
    private boolean checkFields(ArrayList<TextInputEditText> fieldsToCheck) {
        boolean result = true;
        for (int i = 0; i < fieldsToCheck.size(); i++ ) {
            if (fieldsToCheck.get(i).getText().toString().equals("")) {
                fieldsToCheck.get(i).setError("Не может быть пустым");
                result = false;
            } else {
                fieldsToCheck.get(i).setError(null);
            }
        }
        return result;
    }
    private void makeSendFieldsList(){
        sendFields = new ArrayList<>();
        sendFields.add(editDate);

        sendFields.add(editStairsFrame);
        sendFields.add(editPassFrame);
        sendFields.add(editDiagonalConnection);
        sendFields.add(editHorizontalConnection);
        sendFields.add(editCrossbar);
        sendFields.add(editDeck);
        sendFields.add(editSupports);

    }

    private String getStairsFrameText() {
        return editStairsFrame.getText().toString();
    }

    private String getPassFrameText() {
        return editPassFrame.getText().toString();
    }

    private String getDiagonalConnectionText() {
        return editDiagonalConnection.getText().toString();
    }

    private String getHorizontalConnectionText() {
        return editHorizontalConnection.getText().toString();
    }

    private String getCrossbarText() {
        return editCrossbar.getText().toString();
    }

    private String getDeckText() {
        return editDeck.getText().toString();
    }

    private String getSupportsText() {
        return editSupports.getText().toString();
    }






    private String getDateText() {
        return editDate.getText().toString();
    }
}

