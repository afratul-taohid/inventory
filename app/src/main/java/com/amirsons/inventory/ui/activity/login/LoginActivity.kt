package com.amirsons.inventory.ui.activity.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.amirsons.inventory.R
import com.amirsons.inventory.ui.activity.MainActivity
import com.amirsons.inventory.ui.base.BaseActivity
import com.amirsons.inventory.ui.widgets.MySnackBar
import com.amirsons.inventory.utils.KeyboardUtils
import com.amirsons.inventory.utils.MyCountDownTimer
import com.amirsons.inventory.utils.MyUtils
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_login_verification.*
import java.util.concurrent.TimeUnit



/**
 * Created by Taohid on 17, July, 2019
 * Email: taohid32@gmail.com
 */

class LoginActivity : BaseActivity(), TextWatcher {

    private lateinit var  firebaseAuth: FirebaseAuth
    private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var  mResendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var mVerificationId: String

    override val contentLayout: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser == null) {

            ll_login_view.visibility = View.VISIBLE

        } else {

            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)

            finish()
        }

        initFireBasePhoneAuth()

        btn_send_code.setOnClickListener {

            // Hide The Keyboard
            KeyboardUtils.hideSoftInput(et_mobile_number)

            val userMobileNumber = et_mobile_number.text.toString()

            if (userMobileNumber.isEmpty()) {
                MySnackBar.instance.setMessage(getString(R.string.mobile_number_required)).setTextColor(Color.RED).show(this)
                return@setOnClickListener
            }

            if (!MyUtils.isNumeric(userMobileNumber) || !userMobileNumber.startsWith("0") || userMobileNumber.length < 11) {
                MySnackBar.instance.setMessage(getString(R.string.invalid_phone_number)).setTextColor(Color.RED).show(this)
                return@setOnClickListener
            }

            btn_send_code.visibility = View.GONE
            startPhoneNumberVerification(userMobileNumber)
        }

        btn_login.setOnClickListener {

            val code = mobile_one.text.toString() +
                    mobile_two.text.toString() +
                    mobile_three.text.toString() +
                    mobile_four.text.toString() +
                    mobile_five.text.toString() +
                    mobile_six.text.toString()

            if (code.isEmpty()) {

                MySnackBar.instance.setMessage(getString(R.string.verification_code_required)).setTextColor(Color.RED).show(this@LoginActivity)

            } else {

                showProgressDialog("Processing...")
                val credential = PhoneAuthProvider.getCredential(mVerificationId, code)
                signInWithPhoneAuthCredential(credential)
                MyCountDownTimer.stopCountDown()
            }
        }

        ib_resend.setOnClickListener {
            tv_count_down.visibility = View.GONE
            ib_resend.visibility = View.GONE
            ll_otp_verification_view.visibility = View.GONE
            btn_send_code.visibility = View.VISIBLE
        }
    }

    /**
     * initialize the fireBase phone authentication
     */
    private fun initFireBasePhoneAuth() {

        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:$credential")

                signInWithPhoneAuthCredential(credential)

                MyCountDownTimer.stopCountDown()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {

                    et_mobile_number.error = getString(R.string.invalid_phone_number)

                } else if (e is FirebaseTooManyRequestsException) {

                    MySnackBar.instance.setMessage("Quota exceeded.").setTextColor(Color.RED).show(this@LoginActivity)
                }
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(TAG, "onCodeSent:$verificationId")

                mVerificationId = verificationId
                mResendToken = token

                progress_bar.visibility = View.GONE
                ib_resend.visibility = View.VISIBLE
                ll_otp_verification_view.visibility = View.VISIBLE
                tv_count_down.visibility = View.VISIBLE

                MySnackBar.instance.setMessage(getString(R.string.code_sent)).setActionTextColor(Color.RED).show(this@LoginActivity)

                // first stop if previous countdown already started
                MyCountDownTimer.stopCountDown()
                MyCountDownTimer.run(60000, object : MyCountDownTimer.CountDownListener {

                   override fun onTick(formattedString: String, second: String, minute: String, hour: String) {

                       tv_count_down.text = formattedString
                    }

                   override fun onFinish() {

                       tv_count_down.visibility = View.GONE
                       ll_otp_verification_view.visibility = View.GONE
                       btn_send_code.visibility = View.VISIBLE
                    }
                })
            }
        }

        mobile_one.addTextChangedListener(this)
        mobile_two.addTextChangedListener(this)
        mobile_three.addTextChangedListener(this)
        mobile_four.addTextChangedListener(this)
        mobile_five.addTextChangedListener(this)
        mobile_six.addTextChangedListener(this)
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {

        progress_bar.visibility = View.VISIBLE

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+88$phoneNumber", // Phone number to verify
                1, // Timeout duration
                TimeUnit.MINUTES, // Unit of timeout
                this, // Activity (for callback binding)
                mCallbacks)        // OnVerificationStateChangedCallbacks
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this) { task ->

            hideProgressDialog()

            if (task.isSuccessful) {

                Log.d(TAG, "signInWithCredential:success")

                Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()

//                final FirebaseUser user = Objects.requireNonNull(task.getResult()).getUser();

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)

                finish()

            } else {

                Log.w(TAG, "signInWithCredential:failure", task.exception)

                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    MySnackBar.instance.setMessage(getString(R.string.invalid_code)).setTextColor(Color.RED).show(this@LoginActivity)
                }
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        val mobileOneLength = mobile_one.text?.length
        val mobileTwoLength = mobile_two.text?.length
        val mobileThreeLength = mobile_three?.text?.length
        val mobileFourLength = mobile_four.text?.length
        val mobileFiveLength = mobile_five.text?.length
        val mobileSixLength = mobile_six.text?.length

        if (mobileOneLength!! >= 1) {
            mobile_two.requestFocus()
            mobile_one.setBackgroundResource(R.drawable.circle_white_with_gray_edge)

            if (mobileTwoLength!! >= 1) {
                mobile_three.requestFocus()
                mobile_two.setBackgroundResource(R.drawable.circle_white_with_gray_edge)

                if (mobileThreeLength!! >= 1) {
                    mobile_four.requestFocus()
                    mobile_three.setBackgroundResource(R.drawable.circle_white_with_gray_edge)

                    if (mobileFourLength!! >= 1) {
                        mobile_five.requestFocus()
                        mobile_four.setBackgroundResource(R.drawable.circle_white_with_gray_edge)

                        if (mobileFiveLength!! >= 1) {
                            mobile_six.requestFocus()
                            mobile_five.setBackgroundResource(R.drawable.circle_white_with_gray_edge)

                            if (mobileSixLength!! >= 1) {
                                mobile_six.setBackgroundResource(R.drawable.circle_white_with_gray_edge)
                            }
                        }
                    }
                }
            }
        }
    }
}