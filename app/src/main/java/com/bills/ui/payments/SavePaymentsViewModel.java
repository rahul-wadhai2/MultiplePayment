package com.bills.ui.payments;

import static com.bills.utils.Constant.BANK_TRANSFER;
import static com.bills.utils.Constant.CASH;
import static com.bills.utils.Constant.CREDIT_CARD;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bills.data.model.PaymentType;
import com.bills.data.repository.payment.PaymentRepository;
import com.bills.ui.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Save payments view model class.
 */
public class SavePaymentsViewModel extends BaseViewModel {

    /**
     * Payment repository instance.
     */
    private final PaymentRepository paymentRepository;

    /**
     * Mutable live data for payment type list.
     */
    private final MutableLiveData<ArrayList<PaymentType>> paymentTypeList;

    /**
     * Constructor for SavePaymentsViewModel class.
     *
     * @param paymentRepository Payment repository instance.
     */
    SavePaymentsViewModel(PaymentRepository paymentRepository) {
       this.paymentRepository = paymentRepository;
        paymentTypeList = new MutableLiveData<>(new ArrayList<>());
    }

    /**
     * Process payment types.
     *
     * @return Array of payment types.
     */
    public String[] processPaymentTypes() {
        String[] paymentTypes = {CASH, BANK_TRANSFER, CREDIT_CARD};
        for (String paymentType : paymentTypes) {
            if (!Objects.requireNonNull(getPaymentTypeList().getValue()).isEmpty()) {
                for (PaymentType payment : getPaymentTypeList().getValue()) {
                    if (payment.getType().equals(paymentType)) {
                        List<String> paymentTypeList = new ArrayList<>(Arrays.asList(paymentTypes));
                        paymentTypeList.remove(paymentType);
                        paymentTypes = paymentTypeList.toArray(new String[0]);
                    }
                }
            }
        }
        return paymentTypes;
    }

    /**
     * Get payment type list.
     *
     * @return Live data for payment type list.
     */
    public LiveData<ArrayList<PaymentType>> getPaymentTypeList() {
        return paymentTypeList;
    }

    /**
     * Add payment.
     *
     * @param paymentType Payment type.
     */
    public void addPayment(PaymentType paymentType) {
        ArrayList<PaymentType> currentList = paymentTypeList.getValue();
        if (currentList != null) {
            currentList.add(paymentType);
            paymentTypeList.setValue(currentList);
        }
    }

    /**
     * Remove payment type.
     *
     * @param paymentType Payment type.
     */
    public void removePayment(PaymentType paymentType) {
        ArrayList<PaymentType> currentPaymentTypeList = paymentTypeList.getValue();
        if (currentPaymentTypeList != null) {
            currentPaymentTypeList.remove(paymentType);
            paymentTypeList.setValue(currentPaymentTypeList);
        }
    }

    /**
     * Save payments details.
     */
    public void savePaymentsDetails() {
        ArrayList<PaymentType> paymentDetails = paymentTypeList.getValue();
        paymentRepository.savePaymentsDetails(paymentDetails);
    }

    /**
     * Get payments details.
     */
    public void getPaymentsDetails() {
        paymentTypeList.setValue(paymentRepository.getPaymentsDetails());
    }
}
