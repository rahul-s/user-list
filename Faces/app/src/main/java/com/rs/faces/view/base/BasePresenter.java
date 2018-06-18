package com.rs.faces.view.base;

import android.support.annotation.CallSuper;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends BasePresenter.View> {

    private WeakReference<V> viewWeakRef;

    protected void viewCreated() {

    }

    /**
     * Attach the view to the presenter. The presenter holds a weak reference to the view.
     * This is called from the relevant lifecycle method in the view.
     */
    @CallSuper
    protected void attachView(V view) {
        this.viewWeakRef = new WeakReference<>(view);
    }

    /**
     * Detach the view from the presenter.
     * This is called from the relevant lifecycle method in the view.
     */
    @CallSuper
    public void detachView() {
        if (viewWeakRef != null) {
            viewWeakRef.clear();
            viewWeakRef = null;
        }
    }

    /**
     * Returns the view reference if attached, else null. Use the {@link #isViewAttached()} method
     * before calling this.
     */
    protected V getView() {
        return viewWeakRef != null ? viewWeakRef.get() : null;
    }

    /**
     * Returns a boolean stating whether the view is still attached or not
     */
    protected boolean isViewAttached() {
        return getView() != null;
    }

    public interface View {

    }
}
