package com.merabills.ui.payments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.merabills.utils.Constant.BANK_TRANSFER;
import static com.merabills.utils.Constant.CREDIT_CARD;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import com.merabills.R;
import com.merabills.data.model.PaymentDetails;
import com.merabills.data.model.PaymentType;
import com.merabills.utils.ToastUtils;
import java.util.Objects;

/**
 * Add payments dialog class.
 */
public class AddPaymentsDialog {

    /**
     * Dialog class instance.
     */
    private final Dialog dialog;

    /**
     * The context. Used for accessing resources.
     */
    private final Context context;

    /**
     * Constructor for AddPaymentsDialog class.
     *
     * @param context The context.
     */
    public AddPaymentsDialog(Context context) {
        dialog = new Dialog(context);
        this.context = context;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add_payments);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * Show add payments dialog.
     *
     * @param paymentTypes Array of payment types.
     * @param savePaymentsViewModel View model class instance.
     */
    public void show(String[] paymentTypes, SavePaymentsViewModel savePaymentsViewModel) {
        AppCompatButton dialogOkButton = dialog.findViewById(R.id.buttonOk);
        AppCompatButton dialogCancelButton = dialog.findViewById(R.id.buttonCancel);
        AppCompatSpinner spinnerPayments = dialog.findViewById(R.id.spinnerPayments);
        LinearLayout providerLayout = dialog.findViewById(R.id.providerLayout);
        AppCompatEditText providerText = dialog.findViewById(R.id.providerEditText);
        AppCompatEditText transactionRef = dialog.findViewById(R.id.transactionRefEditText);
        AppCompatEditText amount = dialog.findViewById(R.id.rupeeEditText);

        if (paymentTypes.length == 0) {
            spinnerPayments.setVisibility(GONE);
        } else {
            spinnerPayments.setVisibility(VISIBLE);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_spinner_item, paymentTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPayments.setAdapter(adapter);

        spinnerPayments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (paymentTypes.length != 0) {
                    String paymentType =  paymentTypes[position];
                    if(paymentType.equals(BANK_TRANSFER) || paymentType.equals(CREDIT_CARD)) {
                        providerLayout.setVisibility(VISIBLE);
                    } else {
                        providerLayout.setVisibility(GONE);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        dialogOkButton.setOnClickListener(view -> {
                if (paymentTypes.length == 0) {
                    ToastUtils.showLongMessage(context.getString(R.string.message_payment_type_required),
                            dialog.getContext());
                    dialog.dismiss();
                    return;
                }
                String selectedPaymentType = spinnerPayments.getSelectedItem().toString();
                PaymentDetails paymentDetails = new PaymentDetails();
                paymentDetails.setAmount(Double
                        .parseDouble(Objects.requireNonNull(amount.getText()).toString()));
                paymentDetails.setProvider(Objects
                        .requireNonNull(providerText.getText()).toString());
                paymentDetails.setTransactionReference(Objects
                        .requireNonNull(transactionRef.getText()).toString());
                PaymentType paymentType = new PaymentType();
                paymentType.setType(selectedPaymentType);
                paymentType.setPaymentDetails(paymentDetails);

                savePaymentsViewModel.addPayment(paymentType);

            dialog.dismiss();
        });

        dialogCancelButton.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}