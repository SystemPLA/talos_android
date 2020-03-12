package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.presenter.ArrivingRefundDefectJunkPresenter;
import ru.systempla.talos_android.mvp.view.ArrivingRefundDefectJunkView;
import ru.systempla.talos_android.mvp.view.ui.DatePicker.MyDatePicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class JunkFragment extends MvpAppCompatFragment implements ArrivingRefundDefectJunkView {

    @InjectPresenter
    ArrivingRefundDefectJunkPresenter presenter;

    public JunkFragment() {
        // Required empty public constructor
    }

    private Unbinder unbinder;
    private ArrayList<TextInputEditText> criticalFields;
    private ArrayList<TextInputEditText> sendFields;
    private static final String type = "Утилизация";

    @BindView(R.id.junk_edit_date)
    TextInputEditText editDate;

    @BindView(R.id.junk_edit_stairs_frame)
    TextInputEditText editStairsFrame;
    @BindView(R.id.junk_edit_pass_frame)
    TextInputEditText editPassFrame;
    @BindView(R.id.junk_edit_diagonal_connection)
    TextInputEditText editDiagonalConnection;
    @BindView(R.id.junk_edit_horizontal_connection)
    TextInputEditText editHorizontalConnection;
    @BindView(R.id.junk_edit_crossbar)
    TextInputEditText editCrossbar;
    @BindView(R.id.junk_edit_deck)
    TextInputEditText editDeck;
    @BindView(R.id.junk_edit_supports)
    TextInputEditText editSupports;

    @BindView(R.id.junk_edit_stairs_frame_bad)
    TextInputEditText editStairsFrameBad;
    @BindView(R.id.junk_edit_pass_frame_bad)
    TextInputEditText editPassFrameBad;
    @BindView(R.id.junk_edit_diagonal_connection_bad)
    TextInputEditText editDiagonalConnectionBad;
    @BindView(R.id.junk_edit_horizontal_connection_bad)
    TextInputEditText editHorizontalConnectionBad;
    @BindView(R.id.junk_edit_crossbar_bad)
    TextInputEditText editCrossbarBad;
    @BindView(R.id.junk_edit_deck_bad)
    TextInputEditText editDeckBad;
    @BindView(R.id.junk_edit_supports_bad)
    TextInputEditText editSupportsBad;

    @BindView(R.id.junk_loading_layout)
    FrameLayout loadingLayout;

    @OnClick(R.id.button_send_junk)
    void onClickSend() {
        if (!checkFields(criticalFields)) return;

        presenter.clickSendFull(getDateText(), type, getStairsFrameText(), getPassFrameText(), getDiagonalConnectionText(), getHorizontalConnectionText(),
                getCrossbarText(), getDeckText(), getSupportsText(), getStairsFrameBadText(), getPassFrameBadText(), getDiagonalConnectionBadText(),
                getHorizontalConnectionBadText(), getCrossbarBadText(), getDeckBadText(), getSupportsBadText(), type);
    }

    @OnClick(R.id.junk_edit_date)
    void onClickDate() {
        new MyDatePicker(getContext(), editDate);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_junk, container, false);
        unbinder = ButterKnife.bind(this, root);
        makeSendFieldsList();
        return root;
    }

    @Override
    public void showSuccess() {
        Toast.makeText(getContext(), "Успешно!", Toast.LENGTH_LONG).show();
        clearFields(sendFields);

    }

    @Override
    public void showFailure() {
        Toast.makeText(getContext(), "Ошибка, попробуйте еще раз!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

        loadingLayout.setVisibility(View.VISIBLE);

    }
    @Override
    public void hideLoading() {
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingLayout.setVisibility(View.GONE);
            }
        }, 1000);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private boolean checkFields(ArrayList<TextInputEditText> fieldsToCheck) {
        boolean result = true;
        for (int i = 0; i < fieldsToCheck.size(); i++) {
            if (fieldsToCheck.get(i).getText().toString().equals("")) {
                fieldsToCheck.get(i).setError("Не может быть пустым");
                result = false;
            } else {
                fieldsToCheck.get(i).setError(null);
            }
        }
        return result;
    }

    private void clearFields(ArrayList<TextInputEditText> fieldsToClear) {

        for (int i = 0; i < fieldsToClear.size(); i++) {

                fieldsToClear.get(i).setText("");


        }

    }



    private void makeSendFieldsList() {
        criticalFields = new ArrayList<>();
        criticalFields.add(editDate);

        sendFields  = new ArrayList<>();
        sendFields.add(editDate);

        sendFields.add(editStairsFrame);
        sendFields.add(editPassFrame);
        sendFields.add(editDiagonalConnection);
        sendFields.add(editHorizontalConnection);
        sendFields.add(editCrossbar);
        sendFields.add(editDeck);
        sendFields.add(editSupports);

        sendFields.add(editStairsFrameBad);
        sendFields.add(editPassFrameBad);
        sendFields.add(editDiagonalConnectionBad);
        sendFields.add(editHorizontalConnectionBad);
        sendFields.add(editCrossbarBad);
        sendFields.add(editDeckBad);
        sendFields.add(editSupportsBad);

    }

    private String getDateText() {
        return editDate.getText().toString();
    }


    private String getStairsFrameText() {
        return getTextInputEditText(editStairsFrame);
    }

    private String getPassFrameText() {
        return getTextInputEditText(editPassFrame);
    }

    private String getDiagonalConnectionText() {
        return getTextInputEditText(editDiagonalConnection);
    }

    private String getHorizontalConnectionText() {
        return getTextInputEditText(editHorizontalConnection);
    }

    private String getCrossbarText() {
        return getTextInputEditText(editCrossbar);
    }

    private String getDeckText() {
        return getTextInputEditText(editDeck);
    }

    private String getSupportsText() {
        return getTextInputEditText(editSupports);
    }


    private String getStairsFrameBadText() {
        return getTextInputEditText(editStairsFrameBad);
    }

    private String getPassFrameBadText() {
        return getTextInputEditText(editPassFrameBad);
    }

    private String getDiagonalConnectionBadText() {
        return getTextInputEditText(editDiagonalConnectionBad);
    }

    private String getHorizontalConnectionBadText() {
        return getTextInputEditText(editHorizontalConnectionBad);
    }

    private String getCrossbarBadText() {
        return getTextInputEditText(editCrossbarBad);
    }

    private String getDeckBadText() {
        return getTextInputEditText(editDeckBad);
    }

    private String getSupportsBadText() {
        return getTextInputEditText(editSupportsBad);
    }

    private String getTextInputEditText(TextInputEditText ti) {
        if (ti.getText().toString().equals("")) {
            return "0";
        } else {
            return ti.getText().toString();
        }
    }

}
