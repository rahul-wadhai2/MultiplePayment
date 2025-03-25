package com.bills.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast utils class.
 */
public class ToastUtils {

  /**
   * Show long message.
   *
   * @param message message to show.
   * @param context context of activity.
   */
  public static void showLongMessage(String message, Context context) {
    getToast(message, context).show();
  }

  /**
   * Get toast.
   *
   *
   * @param message message to show.
   * @param context context of activity.
   * @return toast instance.
   */
  private static Toast getToast(String message, Context context) {
    return Toast.makeText(context, message, Toast.LENGTH_LONG);
  }
}