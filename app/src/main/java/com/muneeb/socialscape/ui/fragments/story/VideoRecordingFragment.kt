package com.muneeb.socialscape.ui.fragments.story

import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flashbid.luv.extensions.viewBinding
import com.google.firebase.storage.FirebaseStorage
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentVideoRecordingBinding
import com.muneeb.socialscape.ui.fragments.search.SearchFragmentDirections
import com.muneeb.socialscape.utils.RequestCodes
import com.muneeb.socialscape.utils.loadImageFromUrl
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class VideoRecordingFragment : Fragment(R.layout.fragment_video_recording) {

    private val binding by viewBinding(FragmentVideoRecordingBinding::bind)
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService
    private var currentCameraId: Int = Camera.CameraInfo.CAMERA_FACING_BACK

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cameraExecutor = Executors.newSingleThreadExecutor()

        binding.ivClose.setOnClickListener {
            findNavController().popBackStack()
        }

        // Request camera permissions (not shown here for brevity)

        // Initialize CameraX
        startCamera()

        // Set up capture button click listener
        binding.btnCameraClick.setOnClickListener {
            takePhoto()
        }

        val galleryImage = registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback { uri ->
                if (uri != null) {
                    val action =
                        VideoRecordingFragmentDirections.actionVideoRecordingFragmentToNewPostFragment(
                            uri.toString()
                        )
                    findNavController().navigate(action)
                }
            })

        binding.ivGallery.setOnClickListener {
            galleryImage.launch("image/*")
        }

        binding.ivRepeat.setOnClickListener {
            // Toggle between front and back cameras
            currentCameraId = if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                Camera.CameraInfo.CAMERA_FACING_FRONT
            } else {
                Camera.CameraInfo.CAMERA_FACING_BACK
            }
            // Call a method to open the selected camera
            openCamera(currentCameraId)
        }

    }

    private fun openCamera(cameraId: Int) {
        // Your code to handle camera opening goes here
        // You may use the camera API or CameraX library to open the camera
        // Ensure proper camera release and error handling
        // Example: openCameraUsingYourPreferredLibrary(cameraId)
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Set up preview
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.previewView.surfaceProvider)
            }

            // Set up image capture
            imageCapture = ImageCapture.Builder().build()

            // Select back camera as the default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                // Handle camera initialization errors
                exc.printStackTrace()
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        // Create a timestamped file in the app's cache directory
        val photoFile = File(requireActivity().cacheDir, "photo_${System.currentTimeMillis()}.jpg")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    // Photo saved successfully, you can handle the saved file here
                    val uri = outputFileResults.savedUri
                    val action =
                        VideoRecordingFragmentDirections.actionVideoRecordingFragmentToNewPostFragment(
                            uri.toString()
                        )
                    findNavController().navigate(action)
                }

                override fun onError(exception: ImageCaptureException) {
                    // Photo capture failed, handle the error
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

}