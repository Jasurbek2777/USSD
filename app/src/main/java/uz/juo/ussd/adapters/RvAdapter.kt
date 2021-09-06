package uz.juo.ussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.juo.ussd.databinding.RvItemBinding
import uz.juo.ussd.databinding.RvTestItemBinding

class RvAdapter(var list: ArrayList<String>, var itemCLick: setOnCLick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var item: RvTestItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(data: String, position: Int) {
            item.root.setOnClickListener {
                itemCLick.itemOnClick(list[position], position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh = Vh(
        RvTestItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
    interface setOnCLick {
        fun itemOnClick(data: String, position: Int)
    }
}