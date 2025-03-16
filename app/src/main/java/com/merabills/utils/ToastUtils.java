package com.merabills.utils;

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
    getToast(message, context, Toast.LENGTH_LONG).show();
  }

  /**
   * Show short message.
   *
   * @param message message to show.
   * @param context context of activity.
   */
  public static void showShortMessage(String message, Context context) {
    getToast(message, context, Toast.LENGTH_SHORT).show();
  }

  /**
   * Get toast.
   *
   * @param message message to show.
   * @param context context of activity.
   * @param length length of toast.
   * @return toast instance.
   */
  private static Toast getToast(String message, Context context, int length) {
    return Toast.makeText(context, message, length);
  }
}