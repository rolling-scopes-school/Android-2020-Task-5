package ng_designs.android_2021_task_5.presentation.screens.details

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import ng_designs.android_2021_task_5.databinding.DetailsFragmentBinding


class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel
    private var binding: DetailsFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DetailsFragmentBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.backButton?.setOnClickListener {
            findNavController().popBackStack()
        }

        binding?.saveButton?.setOnClickListener {
            val url = arguments?.getString("imageUrl")
            val filename = url?.substringAfterLast("/")
            val request = DownloadManager.Request(Uri.parse(url))
                .setTitle(filename)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

            if(checkPremissions()) {
                (context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager)
                    .enqueue(request)
            }
        }

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        binding?.detailsImageView?.load(arguments?.getString("imageUrl"))
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun checkPremissions():Boolean{
        return when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                true
            }
            else -> {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
                false
            }
        }
    }

    private fun <T> views(block: DetailsFragmentBinding.() -> T): T? = binding?.block()

    companion object {
        fun newInstance() = DetailsFragment()
    }
}