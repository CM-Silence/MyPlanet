package com.example.myplanet.page.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.annotation.NonNull
import androidx.core.widget.addTextChangedListener
import com.example.myplanet.R
import com.example.myplanet.model.LoginModel
import com.google.android.material.textfield.TextInputEditText

/**
 * @ClassName CompleteInformationDialog
 * @author Silence~
 * @date 2022/5/2
 * @Description 用于完善用户信息的窗口
 */
class CompleteInformationDialog(@NonNull context: Context, private val name: String, private val signature: String,private val listener: OnCloseListener) : Dialog(context){
    private lateinit var mTieName : TextInputEditText
    private lateinit var mTieSignature : TextInputEditText
    private lateinit var mBtnConfirm : Button
    private lateinit var mBtnCancel : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //去掉系统的黑色矩形边框
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 加上这句后就可以在 xml 中自定义高和宽

        setContentView(R.layout.dialog_completeinformation)
        initView()
        initEvent()
    }

    private fun initView(){
        this.mTieName = findViewById(R.id.dialog_tie_complete_name)
        mTieSignature = findViewById(R.id.dialog_tie_complete_signature)
        mBtnConfirm = findViewById(R.id.dialog_et_complete_confirm)
        mBtnCancel = findViewById(R.id.dialog_btn_complete_cancel)

        this.mTieName.setText(name)
        mTieSignature.setText(signature)
    }

    private fun initEvent(){
        mBtnConfirm.setOnClickListener {
            val name = mTieName.text.toString()
            val signature = mTieSignature.text.toString()
            var isOk = true
            if(isOk && (name.isEmpty() || name.length > 10)){
                isOk = false
                mTieName.error = "昵称的长度为1-10!"
            }
            if(isOk && signature.length > 20){
                isOk = false
                mTieSignature.error = "个性签名的长度为0-20!"
            }
            if (isOk){
                listener.onClose(name, signature)
            }
        }

        mBtnCancel.setOnClickListener {
            dismiss()
        }

        mTieName.addTextChangedListener {
            if (mTieName.text.toString().isEmpty() ||
                mTieName.text.toString().length > 10) {
                mTieName.error = "昵称的长度为1-10!"
            }else {
                mTieName.error = null
            }
        }

        mTieSignature.addTextChangedListener {
            if (mTieSignature.text.toString().length > 20) {
                mTieSignature.error = "个性签名的长度为0-20!"
            }else {
                mTieSignature.error = null
            }
        }
    }

    interface OnCloseListener{
        fun onClose(name : String, signature: String)
    }
}