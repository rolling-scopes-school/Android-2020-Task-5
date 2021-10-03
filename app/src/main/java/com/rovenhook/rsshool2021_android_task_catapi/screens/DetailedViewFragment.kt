package com.rovenhook.rsshool2021_android_task_catapi.screens

import android.Manifest
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import coil.api.load
import com.rovenhook.rsshool2021_android_task_catapi.databinding.FragmentDetailedViewBinding
import java.lang.Exception
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import android.content.ContentValues

import android.content.ContentResolver
import android.content.pm.PackageManager
import android.net.Uri

import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import java.io.OutputStream
import java.text.SimpleDateFormat


class DetailedViewFragment(private val imageView: ImageView) : Fragment() {
    private val writeStorageRequestCode: Int = 69
    private var _binding: FragmentDetailedViewBinding? = null
    private val binding: FragmentDetailedViewBinding
        get() = _binding ?: throw Exception("Binding error")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedViewBinding.inflate(inflater, container, false)

        binding.imageViewDetailed.load(imageView.drawable)

        binding.imageViewDetailed.setOnClickListener {
            requireActivity().onBackPressed()
        }

        // save photo
        binding.imageViewDetailed.setOnLongClickListener {
            askPermissionToWriteToStorage(binding.imageViewDetailed)

            Log.i("log-tag", "long click confirmed")
            true
        }
        return binding.root
    }

    fun askPermissionToWriteToStorage(imageView: ImageView) {
        val per = ContextCompat
            .checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (per == PackageManager.PERMISSION_GRANTED) {
            copyPhotoToImagesStorage(imageView)
        } else {
            Toast.makeText(requireContext(), "Allow Access to save", Toast.LENGTH_LONG).show()
            val wesp = Manifest.permission.WRITE_EXTERNAL_STORAGE
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(wesp), writeStorageRequestCode)
        }
    }

    fun copyPhotoToImagesStorage(imageView: ImageView) {
        val bitmap: Bitmap = imageView.drawable.toBitmap()

        val storageDir = File(
            Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .toString()
        )
        if (!storageDir.exists()) storageDir.mkdirs()

        var outputStream: FileOutputStream? = null

        val filename = String.format("%d.png", System.currentTimeMillis())
        val outFile = File(storageDir, filename)
        try {
            outputStream = FileOutputStream(outFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            outputStream?.close()
        }
        Toast.makeText(requireContext(), "Saved to the gallery", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}