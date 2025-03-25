package com.bills.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Payment type class.
 */
public class PaymentType implements Parcelable {

        /**
         * Payment type.
         */
        public String type;

        /**
         * Payment details of payment types.
         */
        public PaymentDetails paymentDetails;

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public PaymentDetails getPaymentDetails() {
                return paymentDetails;
        }

        public void setPaymentDetails(PaymentDetails paymentDetails) {
                this.paymentDetails = paymentDetails;
        }

        @Override
        public int describeContents() {
                return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.type);
                dest.writeParcelable(this.paymentDetails, flags);
        }

        public PaymentType() {
        }

        protected PaymentType(Parcel in) {
                this.type = in.readString();
                this.paymentDetails = in.readParcelable(PaymentDetails.class.getClassLoader());
        }

        public static final Parcelable.Creator<PaymentType> CREATOR = new Parcelable.Creator<PaymentType>() {
                @Override
                public PaymentType createFromParcel(Parcel source) {
                        return new PaymentType(source);
                }

                @Override
                public PaymentType[] newArray(int size) {
                        return new PaymentType[size];
                }
        };
}
