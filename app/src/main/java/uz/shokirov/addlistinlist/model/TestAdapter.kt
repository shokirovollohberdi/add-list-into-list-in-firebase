package uz.shokirov.addlistinlist.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.shokirov.addlistinlist.databinding.ItemRvBinding

class RvAdapter(var list: ArrayList<Test>, var context: Context) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(test: Test, position: Int) {

           itemRv.tvTest.text = test.test
            itemRv.a.text = test.variant?.aVariant
            itemRv.b.text = test.variant?.bVariant
            itemRv.c.text = test.variant?.cVariant
            itemRv.d.text = test.variant?.dVariant


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}