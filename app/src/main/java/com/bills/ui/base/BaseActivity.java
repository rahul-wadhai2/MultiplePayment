package com.bills.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

/**
 * Activity base class.
 *
 * @param <Binding> View binding class.
 * @param <ViewModel> View model class.
 */
public abstract class BaseActivity <Binding extends ViewBinding, ViewModel extends BaseViewModel>
        extends AppCompatActivity {

    /**
     * View Model class instance.
     */
    protected ViewModel viewModel;

    /**
     * View binding class instance.
     */
    protected Binding binding;

    @NonNull
    protected abstract ViewModel createViewModel();

    @NonNull
    protected abstract Binding createViewBinding(LayoutInflater layoutInflater);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = createViewBinding(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        viewModel = createViewModel();
    }
}
