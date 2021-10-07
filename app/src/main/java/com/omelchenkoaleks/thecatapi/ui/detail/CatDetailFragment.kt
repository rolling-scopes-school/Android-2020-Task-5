package com.omelchenkoaleks.thecatapi.ui.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.omelchenkoaleks.thecatapi.MainActivity
import com.omelchenkoaleks.thecatapi.R
import com.omelchenkoaleks.thecatapi.databinding.FragmentDetailsBinding
import com.omelchenkoaleks.thecatapi.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class CatDetailFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var saveButton: FloatingActionButton? = null
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString(MainActivity.URL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgSingleCat: ImageView = binding.imgSingleCat
        saveButton = binding.fabSave

        Glide.with(requireActivity())
            .load(url)
            .placeholder(R.drawable.default_cat)
            .into(imgSingleCat)

        saveButton?.setOnClickListener {
            if (!isPermissionGranted(requireContext())) com.omelchenkoaleks.thecatapi.utils.requestPermissions(
                requireContext()
            )
            val fileName = File(url).name
            val folderName = FOLDER_NAME
            CoroutineScope(Dispatchers.IO).launch {
                if (saveImage(
                        Glide.with(requireActivity())
                            .asBitmap()
                            .load(url)
                            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                            .error(android.R.drawable.stat_notify_error)
                            .submit()
                            .get(), fileName, folderName
                    ) != null
                ) withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "image $fileName saved to $folderName",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun saveImage(
        image: Bitmap,
        fileName: String,
        folderName: String = ""
    ): String? {
        var savedImagePath: String? = null

        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .toString() + "/" + folderName
        )
        var success = true
        if (!storageDir.exists()) {
            success = storageDir.mkdir()
        }
        if (success) {
            val imageFile = File(storageDir, fileName)
            savedImagePath = imageFile.absolutePath
            try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, QUALITY, fOut)
                fOut.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            addImageToGallery(requireContext(), savedImagePath)
        }
        return savedImagePath
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
