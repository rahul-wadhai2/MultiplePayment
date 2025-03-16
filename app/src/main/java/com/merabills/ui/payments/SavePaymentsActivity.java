package com.merabills.ui.payments;

import static com.merabills.utils.Constant.REQUEST_CODE_WRITE_EXTERNAL_STORAGE;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.chip.Chip;
import com.merabills.R;
import com.merabills.data.DataManager;
import com.merabills.data.model.PaymentType;
import com.merabills.data.repository.payment.PaymentRepository;
import com.merabills.databinding.ActivitySavepaymentsBinding;
import com.merabills.ui.base.BaseActivity;
import com.merabills.utils.ToastUtils;
import com.merabills.utils.Utils;

/**
 * Save payments activity class.
 */
public class SavePaymentsActivity
        extends BaseActivity<ActivitySavepaymentsBinding, SavePaymentsViewModel> {

    @NonNull
    @Override
    protected SavePaymentsViewModel createViewModel() {
        PaymentRepository paymentRepository = DataManager.getInstance(this)
                .getPaymentRepository();
        SavePaymentsViewModelFactory factory = new SavePaymentsViewModelFactory(paymentRepository);
        return new ViewModelProvider(this, factory).get(SavePaymentsViewModel.class);
    }

    @NonNull
    @Override
    protected ActivitySavepaymentsBinding createViewBinding(LayoutInflater layoutInflater) {
        return ActivitySavepaymentsBinding.inflate(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        observeViewModel();
        getPaymentsDetails();
    }

    /**
     * Set listeners method.
     */
    private void setListeners() {
        binding.addPaymentButton.setOnClickListener(v -> {
            AddPaymentsDialog addPaymentsDialog =
                    new AddPaymentsDialog(SavePaymentsActivity.this);
            addPaymentsDialog.show(viewModel.processPaymentTypes(), viewModel);
        });

        binding.saveButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this
                    ,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions();
            } else {
                viewModel.savePaymentsDetails();
            }
        });
    }

    /**
     * Observe view model.
     */
    private void observeViewModel() {
        viewModel.getPaymentTypeList().observe(this, chips -> {
            binding.paymentsChipGroup.removeAllViews();
            double totalAmount = 0;
            for (PaymentType paymentType : chips) {
                addChipToGroup(paymentType.getType()
                        +" = Rs."+ Utils.getFormatedAmount(paymentType.paymentDetails.getAmount())
                                ,paymentType);
                totalAmount = totalAmount + paymentType.paymentDetails.getAmount();
            }
            binding.totalAmount.setText(Utils.getFormatedAmount(totalAmount));
        });
    }

    /**
     * Get payments details.
     */
    private void getPaymentsDetails() {
        if (ContextCompat.checkSelfPermission(this
                ,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        } else {
            viewModel.getPaymentsDetails();
        }
    }

    /**
     * Add chip to group.
     *
     * @param paymentTypeChip Payment type chip.
     * @param paymentType Payment type.
     */
    private void addChipToGroup(String paymentTypeChip, PaymentType paymentType) {
        Chip chip = new Chip(this);
        chip.setText(paymentTypeChip);
        chip.setCloseIconVisible(true);
        chip.setOnCloseIconClickListener(v -> viewModel.removePayment(paymentType));
        binding.paymentsChipGroup.addView(chip);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions
            ,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.savePaymentsDetails();
            } else {
                ToastUtils.showLongMessage(getString(R.string.message_permission_denied), this);
            }
        }
    }

    /**
     * Request permissions.
     */
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
    }
}
