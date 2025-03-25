package com.bills.data.repository.payment;

import static com.bills.utils.Constant.FILE_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bills.R;
import com.bills.data.model.PaymentType;
import com.bills.utils.ToastUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *  Payment Local Data Source class.
 */
public class PaymentLocalDataSource implements PaymentDataSource.Local {

    /**
     *  Payment Local Data Source class instance.
     */
    @SuppressLint("StaticFieldLeak")
    private static PaymentLocalDataSource instance;

    /**
     * The context. Used for accessing resources.
     */
    private final Context context;

    /**
     * Constructor for PaymentLocalDataSource class.
     *
     * @param context The context.
     */
    private PaymentLocalDataSource(Context context) {
        this.context = context;
    }

    /**
     * Get instance of PaymentLocalDataSource class.
     *
     * @param context The context.
     * @return Instance of PaymentLocalDataSource class.
     */
    public static PaymentLocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new PaymentLocalDataSource(context);
        }
        return instance;
    }

    /**
     * Save payments details to txt file.
     *
     * @param paymentDetails array list of payment details with type.
     */
    @Override
    public void savePaymentsDetails(ArrayList<PaymentType> paymentDetails) {

        String fileContent = new Gson().toJson(paymentDetails);

        File externalStorageDir = Environment.getExternalStorageDirectory();
        File file = new File(externalStorageDir, FILE_NAME);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContent.getBytes());
            fos.flush();
            ToastUtils.showLongMessage(context.getString(com.bills.R.string.message_payment_details_save)
                    +"\n"+file.getAbsolutePath(), context);
        } catch (IOException e) {
            ToastUtils.showLongMessage(context.getString(R.string.message_error_saving_file)
                    +":"+ e.getMessage(), context);
        }
    }

    /**
     * Get payments details from txt file.
     *
     * @return array list of payment details with payment type from txt file json data.
     */
    @Override
    public ArrayList<PaymentType> getPaymentsDetails() {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            ToastUtils.showLongMessage(context
                    .getString(R.string.message_error_reading_file) +":"+ e.getMessage(), context);
        }
        Type userListType = new TypeToken<ArrayList<PaymentType>>() {}.getType();
        return new Gson().fromJson(stringBuilder.toString(), userListType);
    }
}
