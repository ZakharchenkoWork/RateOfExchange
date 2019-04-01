package com.znshadows.exchangerate.auxilary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;
import android.text.Html;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Natali Zakharchenko on 02.08.2016.
 */

public class OneButtonDialog extends AlertDialog.Builder {
    public static final int DEFAULT = -1;

    private static OneButtonDialog lastDialog;
    private static Typeface typeface = null;
    private static Integer textStyle = null;

    private Typeface customTypeface = null;
    private Integer customTextStyle = null;

    private DIALOG_TYPE dialogType = DIALOG_TYPE.MESSAGE_ONLY;
    private String message = null;
    private String editTextDefaultData = null;
    private String editTextHint = null;
    private String positiveButtonText = "OK";
    private int inputType = DEFAULT;

    private OKListener okListener = null;

    /**
     * Easy to use tool for simple dialogs.
     *
     * @param ctx        any Android Context
     * @param dialogType type of the dialog, to show.
     */
    public OneButtonDialog(final Context ctx, DIALOG_TYPE dialogType) {
        super(ctx);

        if (lastDialog != null) {
            return;
        } else {
            lastDialog = this;
        }

        create();

        this.dialogType = dialogType;
    }

    /**
     * Set input type for EditText inside this dialog, or OneButtonDialog.DEFAULT.
     * <p>
     * Call of this method is not necessary.
     *
     * @param inputType InputType constant
     * @return this
     */

    public OneButtonDialog setInputType(int inputType) { // TODO: Think about InputType anotation
        this.inputType = inputType;
        return this;
    }

    /**
     * Set text for the positive button inside this dialog.
     * <p>
     * Call of this method is not necessary.
     *
     * @param positiveButtonText text for button.
     * @return this
     */
    public OneButtonDialog setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    /**
     * Set typeface for any text inside all of the OneButtonDialogs.
     * Ignored if setCustomTypeface() is called with no null value.
     * <p>
     * Call of this method is not necessary.
     *
     * @param typeface text for button.
     * @return this
     */
    public static void seAllDialogsTypeface(Typeface typeface) {
        OneButtonDialog.typeface = typeface;
    }

    /**
     * Set typeface for any text inside this and only this OneButtonDialog.
     * Ignoring typeface setted for all other OneButtonDialogs.
     * <p>
     * Call of this method is not necessary.
     *
     * @param typeface text for button.
     * @return this
     */
    public void setCustomTypeface(Typeface typeface) {
        this.customTypeface = typeface;
    }

    /**
     * Set Style for any text inside all of the OneButtonDialogs.
     * Ignored if setCustomTextStyle() is called with no null and no DEFAULT value.
     * <p>
     * Call of this method is not necessary.
     *
     * @param textStyle text for button.
     * @return this
     */
    public static void setAllDialogsTextStyle(@StyleRes int textStyle) {
        OneButtonDialog.textStyle = textStyle;
    }

    /**
     * Set Style for any text inside this and only this OneButtonDialog.
     * Ignoring Style setted for all other OneButtonDialogs.
     * <p>
     * Call of this method is not necessary.
     *
     * @param textStyle text for button.
     * @return this
     */
    public void setCustomTextStyle(@StyleRes int textStyle) {
        this.customTextStyle = textStyle;
    }

    /**
     * Set Title for this dialog.
     * <p>
     * Call of this method is not necessary.
     *
     * @param title to set, may be null or empty String if you don't need a title bar
     * @return
     */
    public OneButtonDialog setTitle(String title) {
        if (!isDefault(title)) {
            super.setTitle(title);
        }
        return this;
    }


    /**
     * @param message use getString() to get string from resources.
     * @return this
     */
    public OneButtonDialog setMessage(String message) {
        this.message = message;
        return this;
    }


    public OneButtonDialog setOkListener(OKListener okListener) {
        this.okListener = okListener;
        return this;
    }

    /**
     * @param icon pass Resource id for icon, or OneButtonDialog.DEFAULT if no icon needed
     */
    @Override
    public OneButtonDialog setIcon(@DrawableRes int icon) {
        if (icon != DEFAULT) {
            setIcon(icon);
        }
        return this;
    }

    /**
     * @return AlertDialog
     * @deprecated you don't need to call show(), it will be called automaticly after build();
     */
    @Override
    @Deprecated
    public AlertDialog show() {
        return super.show();
    }

    /**
     * @return
     */
    public OneButtonDialog build() {
        EditText input = new EditText(getContext());

        if (dialogType == DIALOG_TYPE.MESSAGE_ONLY) {
            if (!isDefault(message)) {
                setMessage(Html.fromHtml(message)); // TODO: check android versions
            }
        } else {

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

            input.setLayoutParams(lp);

            if (!isDefault(editTextDefaultData)) {
                input.setText(editTextDefaultData);
            }
            if (!isDefault(editTextHint)) {
                input.setHint(editTextHint);
            }
            if (inputType != DEFAULT) {
                input.setInputType(inputType);

            }
            configureStyle(input);


            if (dialogType == DIALOG_TYPE.MESSAGE_AND_INPUT) {
                LinearLayout layout = new LinearLayout(getContext());
                layout.setLayoutParams(lp);
                layout.setOrientation(LinearLayout.VERTICAL);

                TextView textView = new TextView(getContext());

                textView.setLayoutParams(lp);
                textView.setText(Html.fromHtml(message));
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                configureStyle(textView);

                layout.addView(textView);
                layout.addView(input);
                setView(layout);
            } else if (dialogType == DIALOG_TYPE.INPUT_ONLY) {
                setView(input);
            }
        }

        setPositiveButton(positiveButtonText, (DialogInterface dialog, int which) -> {

            if (okListener != null) {
                if (dialogType != DIALOG_TYPE.MESSAGE_ONLY) {
                    okListener.onOKpressed(!isDefault(input.getText().toString()) ? input.getText().toString() : "");
                } else {
                    okListener.onOKpressed("");
                }
            }
            lastDialog = null;
            dialog.dismiss();
        });

        setCancelable(false);
        super.show();
        return this;
    }

    private void configureStyle(TextView view) {
        if (customTypeface != null) {
            view.setTypeface(customTypeface);
        } else if (typeface != null) {
            view.setTypeface(typeface);
        }

        int styleRes = DEFAULT;
        if (customTextStyle != null && customTextStyle != DEFAULT) {
            styleRes = customTextStyle;
        } else if (textStyle != null && textStyle != DEFAULT) {
            styleRes = textStyle;
        }

        if (styleRes != DEFAULT) {
            if (Build.VERSION.SDK_INT < 23) {
                view.setTextAppearance(getContext(), textStyle);
            } else {
                view.setTextAppearance(textStyle);
            }
        }
    }

    private boolean isDefault(String data) {
            return data == null || data.equals("") || data.equals("" + DEFAULT);
    }

    public enum DIALOG_TYPE {
        MESSAGE_ONLY,
        INPUT_ONLY,
        MESSAGE_AND_INPUT
    }

    public interface OKListener {
        void onOKpressed(String userInput);
    }
}

