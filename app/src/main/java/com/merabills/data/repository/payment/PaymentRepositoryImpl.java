package com.merabills.data.repository.payment;

import com.merabills.data.model.PaymentType;
import java.util.ArrayList;

/**
 *  Payment Repository Implementation class.
 */
public class PaymentRepositoryImpl implements PaymentRepository {

    /**
     *  Payment Local Data Source class instance.
     */
    private final PaymentDataSource.Local paymentLocal;

    /**
     *  Payment Repository Implementation class instance.
     */
    private static PaymentRepositoryImpl instance;

    /**
     * Constructor for PaymentRepositoryImpl class.
     *
     * @param paymentLocal Payment Local Data Source class instance.
     */
    private PaymentRepositoryImpl(PaymentLocalDataSource paymentLocal) {
        this.paymentLocal = paymentLocal;
    }

    /**
     * Get instance of PaymentRepositoryImpl class.
     *
     * @param paymentLocalDataSource Payment Local Data Source class instance.
     * @return Instance of PaymentRepositoryImpl class.
     */
    public static PaymentRepositoryImpl getInstance(PaymentLocalDataSource paymentLocalDataSource) {
        if (instance == null) {
            instance = new PaymentRepositoryImpl(paymentLocalDataSource);
        }
        return instance;
    }

    /**
     * Save payments details to txt file.
     *
     * @param paymentDetails Array list of payment details with type.
     */
    @Override
    public void savePaymentsDetails(ArrayList<PaymentType> paymentDetails) {
        paymentLocal .savePaymentsDetails(paymentDetails);
    }

    /**
     * Get payments details from txt file.
     *
     * @return Array list of payment details with payment type from txt file json data.
     */
    @Override
    public ArrayList<PaymentType> getPaymentsDetails() {
        return paymentLocal.getPaymentsDetails();
    }
}
