package com.book.logviewtool.util;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.logviewtool.R;

/**
 * 使用DataBinding的图片设置
 */
public class DataBindingImageViewSet {
    //获取文件夹或者文件
    @BindingAdapter("bind:imageSrc")
    public static void getImage(ImageView view, String infoType) {
        int dra = 0;
        if (!TextUtils.isEmpty(infoType)) {
            if (infoType.equals(GetFilesUtils.FILE_TYPE_FOLDER)) {
                dra = R.drawable.ic_baseline_folder_10;
            } else {
                dra = R.drawable.ic_baseline_insert_drive_file_10;
            }
        }else{
            dra = R.drawable.ic_baseline_folder_10;
        }
        view.setImageResource(dra);
    }
}
