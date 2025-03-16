package com.merabills.ui.payments;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.merabills.data.repository.payment.PaymentRepository;

/**
 * view model factory class.
 */
public class SavePaymentsViewModelFactory implements ViewModelProvider.Factory {

    /**
     * Payment repository instance.
     */
    private final PaymentRepository paymentRepository;

    /**
     * Constructor for SavePaymentsViewModelFactory class.
     *
     * @param paymentRepository payment repository class instance.
     */
    public SavePaymentsViewModelFactory(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SavePaymentsViewModel.class)) {
            return (T) new SavePaymentsViewModel(paymentRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
