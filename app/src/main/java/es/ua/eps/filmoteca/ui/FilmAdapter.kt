package es.ua.eps.filmoteca.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.filmoteca.R
import es.ua.eps.filmoteca.model.Film

class FilmAdapter(
    private val films: List<Film>,
    private val onClick: (Film) -> Unit
) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtYear: TextView = view.findViewById(R.id.txtYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = films[position]
        holder.txtTitle.text = film.title
        holder.txtYear.text = "Año: ${film.year}"
        holder.itemView.setOnClickListener { onClick(film) }
    }

    override fun getItemCount(): Int = films.size
}
