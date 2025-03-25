package com.bills.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  Payment details class.
 */
public class PaymentDetails implements Parcelable {

    /**
     * Amount of payment types.
     */
    private double amount;

    /**
     * Provider of payment types.
     */
    private String provider;

    /**
     * Transaction reference of payment types.
     */
    private String transactionReference;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.amount);
        dest.writeString(this.provider);
        dest.writeString(this.transactionReference);
    }

    public PaymentDetails() {
    }

    protected PaymentDetails(Parcel in) {
        this.amount = in.readDouble();
        this.provider = in.readString();
        this.transactionReference = in.readString();
    }

    public static final Parcelable.Creator<PaymentDetails> CREATOR = new Parcelable.Creator<>() {
        @Override
        public PaymentDetails createFromParcel(Parcel source) {
            return new PaymentDetails(source);
        }

        @Override
        public PaymentDetails[] newArray(int size) {
            return new PaymentDetails[size];
        }
    };
}