package com.merabills.data;

import android.annotation.SuppressLint;
import android.content.Context;
import com.merabills.data.repository.payment.PaymentLocalDataSource;
import com.merabills.data.repository.payment.PaymentRepository;
import com.merabills.data.repository.payment.PaymentRepositoryImpl;

/**
 *  Data Manager class use for manage all types of data.
 */
public class DataManager {

    /**
     *  Data Manager class instance.
     */
    @SuppressLint("StaticFieldLeak")
    private static DataManager sInstance;

    /**
     * The context. Used for accessing resources.
     */
    private final Context context;

    /**
     * Constructor for DataManager class.
     *
     * @param context The context.
     */
    private DataManager(Context context) {
        this.context = context;
    }

    /**
     * Get instance of DataManager class.
     *
     * @param context The context.
     * @return Instance of DataManager class.
     */
    public static synchronized DataManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataManager(context);
        }
        return sInstance;
    }

    /**
     * Get instance of PaymentRepository class.
     *
     * @return Instance of PaymentRepository class.
     */
    public PaymentRepository getPaymentRepository() {
        PaymentLocalDataSource paymentLocalDataSource = PaymentLocalDataSource.getInstance(context);
        return PaymentRepositoryImpl.getInstance(paymentLocalDataSource);
    }
}
