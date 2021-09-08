package uz.juo.ussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.juo.ussd.databinding.RvTestItemBinding
import uz.juo.ussd.models.MinutPackage

class RvAdapterMinut(var list: ArrayList<MinutPackage>, var itemCLick: setOnCLick) :
    RecyclerView.Adapter<RvAdapterMinut.Vh>() {
    inner class Vh(var item: RvTestItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(data: MinutPackage, position: Int) {
            item.rvItemTv.text = data.name
            item.rvItemDescTv.text = data.name + "\n"+data.day_month+ "\n"+data.onCode
            item.active.setOnClickListener {
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
        fun itemOnClick(data: MinutPackage, position: Int)
    }
}