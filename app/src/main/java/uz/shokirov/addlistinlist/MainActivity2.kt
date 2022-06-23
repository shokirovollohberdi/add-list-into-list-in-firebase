package uz.shokirov.addlistinlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import uz.shokirov.addlistinlist.databinding.ActivityMain2Binding
import uz.shokirov.addlistinlist.model.RvAdapter
import uz.shokirov.addlistinlist.model.Test

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var firebaseFirestore: FirebaseFirestore
    private val TAG = "MainActivity2"
    lateinit var list: ArrayList<Test>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseFirestore.collection("tests").get().addOnCompleteListener {
            if (it.isSuccessful) {
                list = ArrayList()
                val result = it.result
                result?.forEach { queryDocumentSnapshot ->
                    var test = queryDocumentSnapshot.toObject(Test::class.java)
                    list.add(test)
                }
                var adapter = RvAdapter(list, this)
                binding.rv.adapter = adapter

            } else {
                Log.d(TAG, "onCreate: ${it.exception}")
            }
        }

    }
}