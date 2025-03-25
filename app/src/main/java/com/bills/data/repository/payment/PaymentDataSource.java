package com.bills.data.repository.payment;

import com.bills.data.model.PaymentType;
import java.util.ArrayList;

/**
 *  Payment DataSource Interface class.
 */
public interface PaymentDataSource {

    /**
     * Payment Local Data Source Interface class.
     */
    interface Local {

        /**
         * Save payments details to txt file.
         *
         * @param paymentDetails array list of payment details with type.
         */
        void savePaymentsDetails(ArrayList<PaymentType> paymentDetails);

        /**
         * Get payments details from txt file.
         *
         * @return array list of payment details with payment type from txt file json data.
         */
        default ArrayList<PaymentType> getPaymentsDetails() {
            return null;
        }
    }
}
