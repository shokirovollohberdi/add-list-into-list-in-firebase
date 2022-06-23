package uz.shokirov.addlistinlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import uz.shokirov.addlistinlist.databinding.ActivityMainBinding
import uz.shokirov.addlistinlist.model.Test
import uz.shokirov.addlistinlist.model.Variant

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var firebaseFirestore: FirebaseFirestore
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseFirestore = FirebaseFirestore.getInstance()

        binding.btnSave.setOnClickListener {
            var test = binding.edtTest.text.toString()
            var a = binding.edtA.text.toString()
            var b = binding.edtB.text.toString()
            var c = binding.edtC.text.toString()
            var d = binding.edtD.text.toString()
            addToCloud(test, a, b, c, d)
        }

        binding.btnSe.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

    }

    private fun addToCloud(test: String, a: String, b: String, c: String, d: String) {
        var variant = Variant(a, b, c, d)
        var test = Test(test, variant)
        firebaseFirestore.collection("tests")
            .add(test)
            .addOnSuccessListener {
                Log.d(TAG, "addToCloud: Success")
            }
            .addOnFailureListener {
                Log.d(TAG, "addToCloud: ${it.message}")
            }
    }
}