package com.example.wbc.ui.login.signup

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.wbc.R
import com.example.wbc.databinding.ActivitySignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()
    private var uriInfo: Uri? = null

    private val permissionList = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_MEDIA_IMAGES
    )

    private val requestMultiplePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            result.forEach {
                if (!it.value) {
                    Toast.makeText(applicationContext, "${it.key}권한 허용 필요", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            openDialog(this)
        }

    private val readImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.data?.let { uri ->
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                Glide.with(this).load(uri).into(binding.imageAddProfile)
                uriInfo = uri
            }
        }

    private val getTakePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                uriInfo.let { Glide.with(this).load(uriInfo).into(binding.imageAddProfile) }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageAddProfile.setOnClickListener {
            requestMultiplePermission.launch(permissionList)
        }

        binding.btnRegister.setOnClickListener {
            if (binding.editCreateId.text.toString().isEmpty() && binding.editCreatePw1.text.toString().isEmpty()) {
                Toast.makeText(this, "빈칸을 전부 채워주세요", Toast.LENGTH_SHORT).show()
            } else if (binding.editCreatePw1.text.toString() != binding.editCreatePw2.text.toString()) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else {
                signUpViewModel.createAccount(binding.editCreateId.text.toString(), binding.editCreatePw1.text.toString())
            }
        }

        signUpViewModel.imageUploadSuccess.observe(this, Observer {
            if (it == false) {
                Toast.makeText(this, "프로필 사진 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "계정 생성에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        })

        signUpViewModel.createSuccess.observe(this, Observer {
            if (it == false) {
                Toast.makeText(this, "계정 생성에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            } else {
                /**
                 * 계정이 생성된 후 생성된 계정의 userId로 이미지 파일이 생성되기 때문에 여기서 이미지 업로드를 진행한다.
                 * 이미지를 등록 안 해도 되기 때문에 조건문으로 한 번 걸러준다.
                 */
                if (uriInfo != null) {
                    signUpViewModel.uploadProfileImage(uriInfo!!)
                }
            }
        })
    }

    private fun createImageFile(): Uri? {
        val now = SimpleDateFormat("yy_MM_dd_HH_mm", Locale.KOREA).format(Date())
        val content = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "img_$now.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        }
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
    }

    private fun openDialog(context: Context) {
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_choose_image, null)
        val dialogBuild = AlertDialog.Builder(context).apply {
            setView(dialogLayout)
        }
        val dialog = dialogBuild.create().apply { show() }
        val cameraAddBtn = dialogLayout.findViewById<Button>(R.id.btn_camera)
        val fileAddBtn = dialogLayout.findViewById<Button>(R.id.btn_file)
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        cameraAddBtn.setOnClickListener {
            uriInfo = createImageFile()
            getTakePicture.launch(uriInfo)
            dialog.dismiss()
        }
        fileAddBtn.setOnClickListener {
            readImage.launch(intent)
            dialog.dismiss()
        }
    }
}