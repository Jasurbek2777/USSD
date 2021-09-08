package uz.juo.ussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.juo.ussd.databinding.RvItem2Binding
import uz.juo.ussd.models.Ussd

class RvAdapterUssd(var list: ArrayList<Ussd>, var itemCLick: setOnCLick) :
    RecyclerView.Adapter<RvAdapterUssd.Vh>() {
    inner class Vh(var item: RvItem2Binding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(data: Ussd, position: Int) {
            item.rvItemDescTv.text = data.code
            item.rvItemTv.text = data.name
            item.root.setOnClickListener {
                itemCLick.itemOnClick(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh = Vh(
        RvItem2Binding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
    interface setOnCLick {
        fun itemOnClick(data: Ussd, position: Int)
    }
}