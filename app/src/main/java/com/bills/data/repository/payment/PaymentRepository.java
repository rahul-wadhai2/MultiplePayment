package com.bills.data.repository.payment;

import com.bills.data.model.PaymentType;
import java.util.ArrayList;

/**
 *  Payment Repository Interface class.
 */
public interface PaymentRepository {

    /**
     * Save payments details to txt file.
     *
     * @param paymentDetails Array list of payment details with type.
     */
    void savePaymentsDetails(ArrayList<PaymentType> paymentDetails);

    /**
     * Get payments details from txt file.
     *
     * @return Array list of payment details with payment type from txt file json data.
     */
    ArrayList<PaymentType> getPaymentsDetails();
}
