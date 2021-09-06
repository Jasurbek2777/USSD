package uz.juo.ussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.juo.ussd.databinding.RvTestItemBinding
import uz.juo.ussd.models.Tariflar

class RvAdapterTarif(var list: ArrayList<Tariflar>, var itemCLick: setOnCLick) :
    RecyclerView.Adapter<RvAdapterTarif.Vh>() {
    inner class Vh(var item: RvTestItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(data: Tariflar, position: Int) {
            item.rvItemTv.text = data.name
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
        fun itemOnClick(data: Tariflar, position: Int)
    }
}