package dev.ujjwal.databinding

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class Contact(_name: String, _email: String) : BaseObservable() {
    @get:Bindable
    var name: String = _name
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var email: String = _email
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(imageView: ImageView, imageUrl: String) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    }
}