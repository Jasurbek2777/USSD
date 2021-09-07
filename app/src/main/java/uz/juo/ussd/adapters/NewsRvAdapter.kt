package uz.juo.ussd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.juo.ussd.databinding.NewsItemBinding
import uz.juo.ussd.models.News

class NewsRvAdapter(var list: ArrayList<News>, var itemCLick: setOnCLick) :
    RecyclerView.Adapter<NewsRvAdapter.VhNews>() {
    inner class VhNews(var item: NewsItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(data: News, position: Int) {
            item.rvItemDate.text = data.date
            item.rvItemTv.text = data.name
            item.rvItemDescTv.text = data.info1
            item.root.setOnClickListener {
                itemCLick.itemOnClick(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhNews = VhNews(
        NewsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: VhNews, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
    interface setOnCLick {
        fun itemOnClick(data: News, position: Int)
    }

}